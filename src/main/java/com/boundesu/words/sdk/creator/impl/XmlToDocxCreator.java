package com.boundesu.words.sdk.creator.impl;

import com.boundesu.words.sdk.creator.DocumentCreator;
import org.apache.poi.xwpf.usermodel.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于XML转换的DOCX创建器
 * 使用自定义XML格式，然后转换为DOCX
 */
public class XmlToDocxCreator implements DocumentCreator {

    private String title = "";
    private String author = "";
    private final List<XmlElement> xmlElements = new ArrayList<>();
    private Document xmlDocument;
    private Element rootElement;

    public XmlToDocxCreator() {
        initializeXmlDocument();
    }

    /**
     * 初始化XML文档
     */
    private void initializeXmlDocument() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            xmlDocument = builder.newDocument();

            // 创建根元素
            rootElement = xmlDocument.createElement("document");
            xmlDocument.appendChild(rootElement);

            // 添加文档信息元素
            Element metaElement = xmlDocument.createElement("metadata");
            rootElement.appendChild(metaElement);

            // 添加内容元素
            Element contentElement = xmlDocument.createElement("content");
            rootElement.appendChild(contentElement);

        } catch (ParserConfigurationException e) {
            throw new RuntimeException("初始化XML文档失败", e);
        }
    }

    @Override
    public DocumentCreator setTitle(String title) {
        this.title = title;
        updateMetadata();
        return this;
    }

    @Override
    public DocumentCreator setAuthor(String author) {
        this.author = author;
        updateMetadata();
        return this;
    }

    /**
     * 更新元数据
     */
    private void updateMetadata() {
        Element metaElement = (Element) rootElement.getElementsByTagName("metadata").item(0);

        // 清除现有元数据
        while (metaElement.hasChildNodes()) {
            metaElement.removeChild(metaElement.getFirstChild());
        }

        // 添加标题
        if (!title.isEmpty()) {
            Element titleElement = xmlDocument.createElement("title");
            titleElement.setTextContent(title);
            metaElement.appendChild(titleElement);
        }

        // 添加作者
        if (!author.isEmpty()) {
            Element authorElement = xmlDocument.createElement("author");
            authorElement.setTextContent(author);
            metaElement.appendChild(authorElement);
        }
    }

    @Override
    public DocumentCreator addParagraph(String text) {
        Element contentElement = (Element) rootElement.getElementsByTagName("content").item(0);
        Element paragraphElement = xmlDocument.createElement("paragraph");
        paragraphElement.setTextContent(text);
        contentElement.appendChild(paragraphElement);

        xmlElements.add(new XmlElement("paragraph", text, null));
        return this;
    }

    @Override
    public DocumentCreator addHeading(String text, int level) {
        if (level < 1 || level > 6) {
            throw new IllegalArgumentException("标题级别必须在1-6之间");
        }

        Element contentElement = (Element) rootElement.getElementsByTagName("content").item(0);
        Element headingElement = xmlDocument.createElement("heading");
        headingElement.setAttribute("level", String.valueOf(level));
        headingElement.setTextContent(text);
        contentElement.appendChild(headingElement);

        xmlElements.add(new XmlElement("heading", text, "level=" + level));
        return this;
    }

    /**
     * 添加粗体段落
     */
    public XmlToDocxCreator addBoldParagraph(String text) {
        Element contentElement = (Element) rootElement.getElementsByTagName("content").item(0);
        Element paragraphElement = xmlDocument.createElement("paragraph");
        paragraphElement.setAttribute("style", "bold");
        paragraphElement.setTextContent(text);
        contentElement.appendChild(paragraphElement);

        xmlElements.add(new XmlElement("paragraph", text, "style=bold"));
        return this;
    }

    /**
     * 添加表格
     */
    public XmlToDocxCreator addTable(String[][] data) {
        if (data.length == 0) return this;

        Element contentElement = (Element) rootElement.getElementsByTagName("content").item(0);
        Element tableElement = xmlDocument.createElement("table");

        for (int i = 0; i < data.length; i++) {
            Element rowElement = xmlDocument.createElement("row");
            if (i == 0) {
                rowElement.setAttribute("type", "header");
            }

            for (String cellData : data[i]) {
                Element cellElement = xmlDocument.createElement("cell");
                cellElement.setTextContent(cellData);
                rowElement.appendChild(cellElement);
            }

            tableElement.appendChild(rowElement);
        }

        contentElement.appendChild(tableElement);
        xmlElements.add(new XmlElement("table", "", "rows=" + data.length + ",cols=" + data[0].length));
        return this;
    }

    /**
     * 添加列表
     */
    public XmlToDocxCreator addList(List<String> items, boolean ordered) {
        Element contentElement = (Element) rootElement.getElementsByTagName("content").item(0);
        Element listElement = xmlDocument.createElement("list");
        listElement.setAttribute("type", ordered ? "ordered" : "unordered");

        for (String item : items) {
            Element itemElement = xmlDocument.createElement("item");
            itemElement.setTextContent(item);
            listElement.appendChild(itemElement);
        }

        contentElement.appendChild(listElement);
        xmlElements.add(new XmlElement("list", "", "type=" + (ordered ? "ordered" : "unordered") + ",items=" + items.size()));
        return this;
    }

    /**
     * 添加自定义XML元素
     */
    public XmlToDocxCreator addCustomXmlElement(String tagName, String content, String attributes) {
        Element contentElement = (Element) rootElement.getElementsByTagName("content").item(0);
        Element customElement = xmlDocument.createElement(tagName);
        customElement.setTextContent(content);

        // 解析并添加属性
        if (attributes != null && !attributes.isEmpty()) {
            String[] attrs = attributes.split(",");
            for (String attr : attrs) {
                String[] keyValue = attr.split("=");
                if (keyValue.length == 2) {
                    customElement.setAttribute(keyValue[0].trim(), keyValue[1].trim());
                }
            }
        }

        contentElement.appendChild(customElement);
        xmlElements.add(new XmlElement(tagName, content, attributes));
        return this;
    }

    @Override
    public void createDocument(Path outputPath) throws IOException {
        XWPFDocument document = createWordDocument();
        try (FileOutputStream out = new FileOutputStream(outputPath.toFile())) {
            document.write(out);
        }
        document.close();
    }

    @Override
    public byte[] createDocumentAsBytes() throws IOException {
        XWPFDocument document = createWordDocument();
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            document.write(out);
            return out.toByteArray();
        } finally {
            document.close();
        }
    }

    /**
     * 创建Word文档
     */
    private XWPFDocument createWordDocument() throws IOException {
        XWPFDocument document = new XWPFDocument();

        // 设置文档属性
        if (!title.isEmpty()) {
            document.getProperties().getCoreProperties().setTitle(title);
        }
        if (!author.isEmpty()) {
            document.getProperties().getCoreProperties().setCreator(author);
        }

        // 处理XML内容并转换为Word内容
        Element contentElement = (Element) rootElement.getElementsByTagName("content").item(0);
        processXmlElements(document, contentElement.getChildNodes());

        return document;
    }

    /**
     * 处理XML元素并转换为Word内容
     */
    private void processXmlElements(XWPFDocument document, NodeList nodes) {
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String tagName = element.getTagName();

                switch (tagName) {
                    case "heading":
                        int level = Integer.parseInt(element.getAttribute("level"));
                        addHeadingToDocument(document, element.getTextContent(), level);
                        break;
                    case "paragraph":
                        boolean isBold = "bold".equals(element.getAttribute("style"));
                        addParagraphToDocument(document, element.getTextContent(), isBold);
                        break;
                    case "table":
                        addTableToDocument(document, element);
                        break;
                    case "list":
                        boolean ordered = "ordered".equals(element.getAttribute("type"));
                        addListToDocument(document, element, ordered);
                        break;
                    default:
                        // 对于其他元素，提取文本内容作为段落
                        if (!element.getTextContent().trim().isEmpty()) {
                            addParagraphToDocument(document, element.getTextContent(), false);
                        }
                        break;
                }
            }
        }
    }

    private void addHeadingToDocument(XWPFDocument document, String text, int level) {
        XWPFParagraph heading = document.createParagraph();
        XWPFRun run = heading.createRun();
        run.setText(text);
        run.setBold(true);
        run.setFontFamily("宋体");
        run.setFontSize(20 - level * 2); // 根据级别调整字体大小

        if (level == 1) {
            heading.setAlignment(ParagraphAlignment.CENTER);
        }
    }

    private void addParagraphToDocument(XWPFDocument document, String text, boolean bold) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setFontFamily("宋体");
        run.setFontSize(12);
        if (bold) {
            run.setBold(true);
        }
    }

    private void addTableToDocument(XWPFDocument document, Element tableElement) {
        NodeList rows = tableElement.getElementsByTagName("row");
        if (rows.getLength() == 0) return;

        // 计算列数
        int maxCols = 0;
        for (int i = 0; i < rows.getLength(); i++) {
            Element row = (Element) rows.item(i);
            NodeList cells = row.getElementsByTagName("cell");
            maxCols = Math.max(maxCols, cells.getLength());
        }

        XWPFTable table = document.createTable(rows.getLength(), maxCols);
        table.setWidth("100%");

        for (int i = 0; i < rows.getLength(); i++) {
            Element row = (Element) rows.item(i);
            NodeList cells = row.getElementsByTagName("cell");
            XWPFTableRow tableRow = table.getRow(i);
            boolean isHeader = "header".equals(row.getAttribute("type"));

            for (int j = 0; j < cells.getLength() && j < maxCols; j++) {
                XWPFTableCell cell = tableRow.getCell(j);
                cell.setText(cells.item(j).getTextContent());

                // 设置表头样式
                if (isHeader) {
                    XWPFParagraph para = cell.getParagraphs().get(0);
                    if (!para.getRuns().isEmpty()) {
                        para.getRuns().get(0).setBold(true);
                    }
                }
            }
        }
    }

    private void addListToDocument(XWPFDocument document, Element listElement, boolean ordered) {
        NodeList items = listElement.getElementsByTagName("item");
        for (int i = 0; i < items.getLength(); i++) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();

            String prefix = ordered ? (i + 1) + ". " : "• ";
            run.setText(prefix + items.item(i).getTextContent());
            run.setFontFamily("宋体");
            run.setFontSize(12);
        }
    }

    /**
     * 获取当前XML内容（用于调试）
     */
    public String getXmlContent() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(xmlDocument), new StreamResult(writer));
            return writer.toString();
        } catch (Exception e) {
            return "获取XML内容失败: " + e.getMessage();
        }
    }

    /**
     * 从XML字符串加载内容
     */
    public XmlToDocxCreator loadFromXml(String xmlContent) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        xmlDocument = builder.parse(new ByteArrayInputStream(xmlContent.getBytes(StandardCharsets.UTF_8)));
        rootElement = xmlDocument.getDocumentElement();

        // 更新内部状态
        Element metaElement = (Element) rootElement.getElementsByTagName("metadata").item(0);
        if (metaElement != null) {
            NodeList titleNodes = metaElement.getElementsByTagName("title");
            if (titleNodes.getLength() > 0) {
                this.title = titleNodes.item(0).getTextContent();
            }

            NodeList authorNodes = metaElement.getElementsByTagName("author");
            if (authorNodes.getLength() > 0) {
                this.author = authorNodes.item(0).getTextContent();
            }
        }

        return this;
    }

    /**
     * 验证XML格式
     */
    public boolean validateXml() {
        try {
            // 基本验证：检查根元素和必要的子元素
            if (rootElement == null) return false;
            if (!rootElement.getTagName().equals("document")) return false;
            if (rootElement.getElementsByTagName("metadata").getLength() == 0) return false;
            return rootElement.getElementsByTagName("content").getLength() != 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * XML元素内部类
     */
    private static class XmlElement {
        private final String tagName;
        private final String content;
        private final String attributes;

        public XmlElement(String tagName, String content, String attributes) {
            this.tagName = tagName;
            this.content = content;
            this.attributes = attributes;
        }

        public String getTagName() {
            return tagName;
        }

        public String getContent() {
            return content;
        }

        public String getAttributes() {
            return attributes;
        }
    }
}