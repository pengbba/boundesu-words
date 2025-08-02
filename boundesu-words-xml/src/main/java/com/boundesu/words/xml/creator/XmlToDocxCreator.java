package com.boundesu.words.xml.creator;

import com.boundesu.words.common.exception.BoundesuWordsException;
import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于XML转换的DOCX创建器
 * 使用自定义XML格式，然后转换为DOCX
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public class XmlToDocxCreator {

    private static final Logger log = LoggerFactory.getLogger(XmlToDocxCreator.class);
    private final List<XmlElement> xmlElements = new ArrayList<>();
    private String title = "";
    private String author = "";
    private org.w3c.dom.Document xmlDocument;
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

    /**
     * 更新元数据
     */
    private void updateMetadata(String key, String value) {
        NodeList metaNodes = rootElement.getElementsByTagName("metadata");
        if (metaNodes.getLength() > 0) {
            Element metaElement = (Element) metaNodes.item(0);

            // 查找或创建指定的元数据元素
            NodeList keyNodes = metaElement.getElementsByTagName(key);
            Element keyElement;
            if (keyNodes.getLength() > 0) {
                keyElement = (Element) keyNodes.item(0);
            } else {
                keyElement = xmlDocument.createElement(key);
                metaElement.appendChild(keyElement);
            }
            keyElement.setTextContent(value);
        }
    }

    /**
     * 添加标题
     */
    public XmlToDocxCreator addHeading(String text, int level) {
        Element headingElement = xmlDocument.createElement("heading");
        headingElement.setAttribute("level", String.valueOf(level));
        headingElement.setTextContent(text);

        addToContent(headingElement);
        xmlElements.add(new XmlElement("heading", text, "level=" + level));

        log.debug("添加{}级标题: {}", level, text);
        return this;
    }

    /**
     * 添加段落
     */
    public XmlToDocxCreator addParagraph(String text) {
        return addParagraph(text, false);
    }

    /**
     * 添加段落（可指定是否加粗）
     */
    public XmlToDocxCreator addParagraph(String text, boolean bold) {
        Element paragraphElement = xmlDocument.createElement("paragraph");
        if (bold) {
            paragraphElement.setAttribute("style", "bold");
        }
        paragraphElement.setTextContent(text);

        addToContent(paragraphElement);
        xmlElements.add(new XmlElement("paragraph", text, bold ? "style=bold" : ""));

        log.debug("添加段落: {}", text.length() > 50 ? text.substring(0, 50) + "..." : text);
        return this;
    }

    /**
     * 添加表格
     */
    public XmlToDocxCreator addTable(String[][] data) {
        if (data == null || data.length == 0) {
            return this;
        }

        Element tableElement = xmlDocument.createElement("table");

        for (String[] rowData : data) {
            Element rowElement = xmlDocument.createElement("row");

            for (String cellData : rowData) {
                Element cellElement = xmlDocument.createElement("cell");
                cellElement.setTextContent(cellData != null ? cellData : "");
                rowElement.appendChild(cellElement);
            }

            tableElement.appendChild(rowElement);
        }

        addToContent(tableElement);
        xmlElements.add(new XmlElement("table", "", "rows=" + data.length + ",cols=" + data[0].length));

        log.debug("添加表格: {}行{}列", data.length, data[0].length);
        return this;
    }

    /**
     * 添加列表
     */
    public XmlToDocxCreator addList(List<String> items, boolean ordered) {
        if (items == null || items.isEmpty()) {
            return this;
        }

        Element listElement = xmlDocument.createElement("list");
        listElement.setAttribute("type", ordered ? "ordered" : "unordered");

        for (String item : items) {
            Element itemElement = xmlDocument.createElement("item");
            itemElement.setTextContent(item);
            listElement.appendChild(itemElement);
        }

        addToContent(listElement);
        xmlElements.add(new XmlElement("list", "", "type=" + (ordered ? "ordered" : "unordered") + ",items=" + items.size()));

        log.debug("添加{}列表: {}项", ordered ? "有序" : "无序", items.size());
        return this;
    }

    /**
     * 添加分页符
     */
    public XmlToDocxCreator addPageBreak() {
        Element breakElement = xmlDocument.createElement("pagebreak");
        addToContent(breakElement);
        xmlElements.add(new XmlElement("pagebreak", "", ""));

        log.debug("添加分页符");
        return this;
    }

    /**
     * 添加元素到内容区域
     */
    private void addToContent(Element element) {
        NodeList contentNodes = rootElement.getElementsByTagName("content");
        if (contentNodes.getLength() > 0) {
            Element contentElement = (Element) contentNodes.item(0);
            contentElement.appendChild(element);
        }
    }

    /**
     * 创建DOCX文档
     */
    public XWPFDocument createDocument() throws BoundesuWordsException {
        try {
            XWPFDocument document = new XWPFDocument();

            // 设置文档属性
            if (!title.isEmpty()) {
                document.getProperties().getCoreProperties().setTitle(title);
            }
            if (!author.isEmpty()) {
                document.getProperties().getCoreProperties().setCreator(author);
            }

            // 处理XML内容
            NodeList contentNodes = rootElement.getElementsByTagName("content");
            if (contentNodes.getLength() > 0) {
                Element contentElement = (Element) contentNodes.item(0);
                processXmlElements(document, contentElement.getChildNodes());
            }

            log.info("DOCX文档创建完成");
            return document;

        } catch (Exception e) {
            throw new BoundesuWordsException("DOCUMENT_CREATION_ERROR", "创建DOCX文档失败: " + e.getMessage(), e);
        }
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
                    case "pagebreak":
                        addPageBreakToDocument(document);
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

    /**
     * 添加标题到文档
     */
    private void addHeadingToDocument(XWPFDocument document, String text, int level) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setBold(true);

        // 根据级别设置字体大小
        int fontSize = Math.max(12, 20 - level * 2);
        run.setFontSize(fontSize);
        run.setFontFamily("宋体");
    }

    /**
     * 添加段落到文档
     */
    private void addParagraphToDocument(XWPFDocument document, String text, boolean bold) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setBold(bold);
        run.setFontSize(12);
        run.setFontFamily("宋体");
    }

    /**
     * 添加表格到文档
     */
    private void addTableToDocument(XWPFDocument document, Element tableElement) {
        NodeList rows = tableElement.getElementsByTagName("row");
        if (rows.getLength() == 0) {
            return;
        }

        XWPFTable table = document.createTable();

        for (int i = 0; i < rows.getLength(); i++) {
            Element rowElement = (Element) rows.item(i);
            XWPFTableRow row = (i == 0) ? table.getRow(0) : table.createRow();

            NodeList cells = rowElement.getElementsByTagName("cell");
            for (int j = 0; j < cells.getLength(); j++) {
                Element cellElement = (Element) cells.item(j);
                XWPFTableCell cell = (j == 0 && row.getTableCells().size() > 0) ?
                        row.getCell(0) : row.createCell();

                XWPFParagraph cellParagraph = cell.getParagraphs().get(0);
                XWPFRun cellRun = cellParagraph.createRun();
                cellRun.setText(cellElement.getTextContent());
                cellRun.setFontSize(12);
                cellRun.setFontFamily("宋体");
            }
        }
    }

    /**
     * 添加列表到文档
     */
    private void addListToDocument(XWPFDocument document, Element listElement, boolean ordered) {
        NodeList items = listElement.getElementsByTagName("item");

        for (int i = 0; i < items.getLength(); i++) {
            Element itemElement = (Element) items.item(i);
            XWPFParagraph paragraph = document.createParagraph();

            XWPFRun run = paragraph.createRun();
            String prefix = ordered ? (i + 1) + ". " : "• ";
            run.setText(prefix + itemElement.getTextContent());
            run.setFontSize(12);
            run.setFontFamily("宋体");
        }
    }

    /**
     * 添加分页符到文档
     */
    private void addPageBreakToDocument(XWPFDocument document) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.addBreak(BreakType.PAGE);
    }

    /**
     * 导出为字节数组
     */
    public byte[] exportToBytes() throws BoundesuWordsException {
        try (XWPFDocument document = createDocument();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            document.write(outputStream);
            return outputStream.toByteArray();

        } catch (IOException e) {
            throw new BoundesuWordsException("EXPORT_ERROR", "导出文档失败: " + e.getMessage(), e);
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
     * 清空所有内容
     */
    public XmlToDocxCreator clear() {
        xmlElements.clear();
        initializeXmlDocument();
        title = "";
        author = "";

        log.debug("清空所有内容");
        return this;
    }

    /**
     * 获取元素数量
     */
    public int getElementCount() {
        return xmlElements.size();
    }

    /**
     * 获取文档标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置文档标题
     */
    public XmlToDocxCreator setTitle(String title) {
        this.title = title;
        updateMetadata("title", title);
        return this;
    }

    /**
     * 获取文档作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置文档作者
     */
    public XmlToDocxCreator setAuthor(String author) {
        this.author = author;
        updateMetadata("author", author);
        return this;
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

        @Override
        public String toString() {
            return String.format("XmlElement{tagName='%s', content='%s', attributes='%s'}",
                    tagName, content.length() > 30 ? content.substring(0, 30) + "..." : content, attributes);
        }
    }
}