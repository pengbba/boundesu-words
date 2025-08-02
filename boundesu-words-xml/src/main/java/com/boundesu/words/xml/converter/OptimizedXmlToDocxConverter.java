package com.boundesu.words.xml.converter;

import com.boundesu.words.common.exception.BoundesuWordsException;
import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 优化的XML转DOCX转换器
 * 基于Apache POI的新特性进行优化
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public class OptimizedXmlToDocxConverter {

    private static final Logger log = LoggerFactory.getLogger(OptimizedXmlToDocxConverter.class);

    private XWPFDocument document;
    private Map<String, ElementProcessor> elementProcessors;

    /**
     * 构造函数
     */
    public OptimizedXmlToDocxConverter() {
        this.document = new XWPFDocument();
        initializeElementProcessors();
    }

    /**
     * 初始化元素处理器
     */
    private void initializeElementProcessors() {
        elementProcessors = new HashMap<>();

        // 文档结构元素
        elementProcessors.put("document", this::processDocumentElement);
        elementProcessors.put("root", this::processDocumentElement);

        // 标题和标题元素
        elementProcessors.put("title", this::processTitleElement);
        elementProcessors.put("heading", this::processHeadingElement);
        elementProcessors.put("h1", (e, p, o) -> processHeadingElement(e, p, o, 1));
        elementProcessors.put("h2", (e, p, o) -> processHeadingElement(e, p, o, 2));
        elementProcessors.put("h3", (e, p, o) -> processHeadingElement(e, p, o, 3));
        elementProcessors.put("h4", (e, p, o) -> processHeadingElement(e, p, o, 4));
        elementProcessors.put("h5", (e, p, o) -> processHeadingElement(e, p, o, 5));
        elementProcessors.put("h6", (e, p, o) -> processHeadingElement(e, p, o, 6));

        // 段落和文本元素
        elementProcessors.put("paragraph", this::processParagraphElement);
        elementProcessors.put("p", this::processParagraphElement);
        elementProcessors.put("text", this::processTextElement);

        // 格式化元素
        elementProcessors.put("bold", this::processBoldElement);
        elementProcessors.put("b", this::processBoldElement);
        elementProcessors.put("italic", this::processItalicElement);
        elementProcessors.put("i", this::processItalicElement);
        elementProcessors.put("underline", this::processUnderlineElement);
        elementProcessors.put("u", this::processUnderlineElement);

        // 表格元素
        elementProcessors.put("table", this::processTableElement);
        elementProcessors.put("row", this::processRowElement);
        elementProcessors.put("cell", this::processCellElement);

        // 列表元素
        elementProcessors.put("list", this::processListElement);
        elementProcessors.put("item", this::processItemElement);

        // 媒体元素
        elementProcessors.put("image", this::processImageElement);
        elementProcessors.put("img", this::processImageElement);
        elementProcessors.put("link", this::processLinkElement);
        elementProcessors.put("a", this::processLinkElement);

        // 其他元素
        elementProcessors.put("break", this::processBreakElement);
        elementProcessors.put("br", this::processBreakElement);
        elementProcessors.put("section", this::processSectionElement);
    }

    /**
     * 从XML字符串转换为DOCX
     */
    public byte[] convertXmlToDocx(String xmlContent, ConversionOptions options) throws BoundesuWordsException {
        try {
            org.w3c.dom.Document xmlDoc = parseXmlString(xmlContent);
            return convertXmlToDocx(xmlDoc, options);
        } catch (Exception e) {
            throw new BoundesuWordsException("XML_CONVERSION_ERROR", "XML转换失败: " + e.getMessage(), e);
        }
    }

    /**
     * 从XML文件转换为DOCX
     */
    public byte[] convertXmlFileToDocx(String xmlFilePath, ConversionOptions options) throws BoundesuWordsException {
        try {
            org.w3c.dom.Document xmlDoc = parseXmlFile(xmlFilePath);
            return convertXmlToDocx(xmlDoc, options);
        } catch (Exception e) {
            throw new BoundesuWordsException("XML_FILE_CONVERSION_ERROR", "XML文件转换失败: " + e.getMessage(), e);
        }
    }

    /**
     * 从XML文档转换为DOCX
     */
    public byte[] convertXmlToDocx(org.w3c.dom.Document xmlDoc, ConversionOptions options) throws BoundesuWordsException {
        try {
            if (options == null) {
                options = new ConversionOptions();
            }

            // 设置文档属性
            setDocumentProperties(options);

            // 处理XML文档
            processXmlDocument(xmlDoc, options);

            // 转换为字节数组
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                document.write(outputStream);
                return outputStream.toByteArray();
            }
        } catch (Exception e) {
            throw new BoundesuWordsException("DOCX_GENERATION_ERROR", "DOCX生成失败: " + e.getMessage(), e);
        }
    }

    /**
     * 解析XML字符串
     */
    private org.w3c.dom.Document parseXmlString(String xmlContent) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new ByteArrayInputStream(xmlContent.getBytes("UTF-8")));
    }

    /**
     * 解析XML文件
     */
    private org.w3c.dom.Document parseXmlFile(String xmlFilePath) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new File(xmlFilePath));
    }

    /**
     * 设置文档属性
     */
    private void setDocumentProperties(ConversionOptions options) {
        // 设置文档核心属性
        if (options.getTitle() != null && !options.getTitle().isEmpty()) {
            document.getProperties().getCoreProperties().setTitle(options.getTitle());
        }
        if (options.getAuthor() != null && !options.getAuthor().isEmpty()) {
            document.getProperties().getCoreProperties().setCreator(options.getAuthor());
        }
        if (options.getSubject() != null && !options.getSubject().isEmpty()) {
            document.getProperties().getCoreProperties().setSubjectProperty(options.getSubject());
        }
    }

    /**
     * 处理XML文档
     */
    private void processXmlDocument(org.w3c.dom.Document xmlDoc, ConversionOptions options) {
        Element rootElement = xmlDoc.getDocumentElement();
        processElement(rootElement, null, options);
    }

    /**
     * 处理XML元素
     */
    private void processElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        String elementName = element.getNodeName().toLowerCase();

        ElementProcessor processor = elementProcessors.get(elementName);
        if (processor != null) {
            processor.process(element, currentParagraph, options);
        } else {
            // 默认处理：递归处理子元素
            processChildElements(element, currentParagraph, options);
        }
    }

    /**
     * 处理子元素
     */
    private void processChildElements(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                processElement((Element) child, currentParagraph, options);
            } else if (child.getNodeType() == Node.TEXT_NODE) {
                String text = child.getNodeValue().trim();
                if (!text.isEmpty()) {
                    if (currentParagraph == null) {
                        currentParagraph = document.createParagraph();
                    }
                    XWPFRun run = currentParagraph.createRun();
                    run.setText(text);
                    run.setFontSize(options.getDefaultFontSize());
                    run.setFontFamily(options.getDefaultFontFamily());
                }
            }
        }
    }

    /**
     * 处理文档元素
     */
    private void processDocumentElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        log.debug("处理文档根元素");
        processChildElements(element, null, options);
    }

    /**
     * 处理标题元素
     */
    private void processTitleElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun run = paragraph.createRun();
        run.setText(element.getTextContent());
        run.setBold(true);
        run.setFontSize(18);
        run.setFontFamily(options.getDefaultFontFamily());

        log.debug("处理标题: {}", element.getTextContent());
    }

    // 元素处理器方法

    /**
     * 处理标题元素
     */
    private void processHeadingElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        String levelAttr = element.getAttribute("level");
        int level = 1;
        try {
            if (levelAttr != null && !levelAttr.isEmpty()) {
                level = Integer.parseInt(levelAttr);
            }
        } catch (NumberFormatException e) {
            level = 1;
        }
        processHeadingElement(element, currentParagraph, options, level);
    }

    /**
     * 处理指定级别的标题元素
     */
    private void processHeadingElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options, int level) {
        XWPFParagraph paragraph = document.createParagraph();

        XWPFRun run = paragraph.createRun();
        run.setText(element.getTextContent());
        run.setBold(true);

        // 根据级别设置字体大小
        int fontSize = Math.max(12, 20 - level * 2);
        run.setFontSize(fontSize);
        run.setFontFamily(options.getDefaultFontFamily());

        log.debug("处理{}级标题: {}", level, element.getTextContent());
    }

    /**
     * 处理段落元素
     */
    private void processParagraphElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        XWPFParagraph paragraph = document.createParagraph();

        // 处理段落内容和子元素
        processChildElements(element, paragraph, options);

        // 如果段落为空，添加文本内容
        if (paragraph.getRuns().isEmpty()) {
            String text = element.getTextContent().trim();
            if (!text.isEmpty()) {
                XWPFRun run = paragraph.createRun();
                run.setText(text);
                run.setFontSize(options.getDefaultFontSize());
                run.setFontFamily(options.getDefaultFontFamily());
            }
        }

        log.debug("处理段落");
    }

    /**
     * 处理文本元素
     */
    private void processTextElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        if (currentParagraph == null) {
            currentParagraph = document.createParagraph();
        }

        XWPFRun run = currentParagraph.createRun();
        run.setText(element.getTextContent());
        run.setFontSize(options.getDefaultFontSize());
        run.setFontFamily(options.getDefaultFontFamily());
    }

    /**
     * 处理粗体元素
     */
    private void processBoldElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        if (currentParagraph == null) {
            currentParagraph = document.createParagraph();
        }

        XWPFRun run = currentParagraph.createRun();
        run.setText(element.getTextContent());
        run.setBold(true);
        run.setFontSize(options.getDefaultFontSize());
        run.setFontFamily(options.getDefaultFontFamily());
    }

    /**
     * 处理斜体元素
     */
    private void processItalicElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        if (currentParagraph == null) {
            currentParagraph = document.createParagraph();
        }

        XWPFRun run = currentParagraph.createRun();
        run.setText(element.getTextContent());
        run.setItalic(true);
        run.setFontSize(options.getDefaultFontSize());
        run.setFontFamily(options.getDefaultFontFamily());
    }

    /**
     * 处理下划线元素
     */
    private void processUnderlineElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        if (currentParagraph == null) {
            currentParagraph = document.createParagraph();
        }

        XWPFRun run = currentParagraph.createRun();
        run.setText(element.getTextContent());
        run.setUnderline(UnderlinePatterns.SINGLE);
        run.setFontSize(options.getDefaultFontSize());
        run.setFontFamily(options.getDefaultFontFamily());
    }

    /**
     * 处理表格元素
     */
    private void processTableElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        XWPFTable table = document.createTable();

        // 处理表格行
        NodeList rows = element.getElementsByTagName("row");
        if (rows.getLength() == 0) {
            rows = element.getElementsByTagName("tr");
        }

        for (int i = 0; i < rows.getLength(); i++) {
            Element rowElement = (Element) rows.item(i);
            XWPFTableRow row = (i == 0) ? table.getRow(0) : table.createRow();

            // 处理表格单元格
            NodeList cells = rowElement.getElementsByTagName("cell");
            if (cells.getLength() == 0) {
                cells = rowElement.getElementsByTagName("td");
            }

            for (int j = 0; j < cells.getLength(); j++) {
                Element cellElement = (Element) cells.item(j);
                XWPFTableCell cell = (j == 0 && row.getTableCells().size() > 0) ?
                        row.getCell(0) : row.createCell();

                // 设置单元格内容
                XWPFParagraph cellParagraph = cell.getParagraphs().get(0);
                XWPFRun cellRun = cellParagraph.createRun();
                cellRun.setText(cellElement.getTextContent());
                cellRun.setFontSize(options.getDefaultFontSize());
                cellRun.setFontFamily(options.getDefaultFontFamily());
            }
        }

        log.debug("处理表格，行数: {}", rows.getLength());
    }

    /**
     * 处理表格行元素（由表格处理器调用）
     */
    private void processRowElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        // 行处理在表格处理器中完成
    }

    /**
     * 处理表格单元格元素（由表格处理器调用）
     */
    private void processCellElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        // 单元格处理在表格处理器中完成
    }

    /**
     * 处理列表元素
     */
    private void processListElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        NodeList items = element.getElementsByTagName("item");
        if (items.getLength() == 0) {
            items = element.getElementsByTagName("li");
        }

        for (int i = 0; i < items.getLength(); i++) {
            Element itemElement = (Element) items.item(i);
            XWPFParagraph paragraph = document.createParagraph();

            XWPFRun run = paragraph.createRun();
            run.setText("• " + itemElement.getTextContent());
            run.setFontSize(options.getDefaultFontSize());
            run.setFontFamily(options.getDefaultFontFamily());
        }

        log.debug("处理列表，项目数: {}", items.getLength());
    }

    /**
     * 处理列表项元素（由列表处理器调用）
     */
    private void processItemElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        // 列表项处理在列表处理器中完成
    }

    /**
     * 处理图片元素
     */
    private void processImageElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        // 图片处理暂时跳过，可以根据需要实现
        log.debug("跳过图片元素处理");
    }

    /**
     * 处理链接元素
     */
    private void processLinkElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        if (currentParagraph == null) {
            currentParagraph = document.createParagraph();
        }

        XWPFRun run = currentParagraph.createRun();
        run.setText(element.getTextContent());
        run.setColor("0000FF"); // 蓝色
        run.setUnderline(UnderlinePatterns.SINGLE);
        run.setFontSize(options.getDefaultFontSize());
        run.setFontFamily(options.getDefaultFontFamily());
    }

    /**
     * 处理换行元素
     */
    private void processBreakElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        if (currentParagraph == null) {
            currentParagraph = document.createParagraph();
        }

        XWPFRun run = currentParagraph.createRun();
        run.addBreak();
    }

    /**
     * 处理章节元素
     */
    private void processSectionElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        // 添加分页符
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.addBreak(BreakType.PAGE);

        // 处理章节内容
        processChildElements(element, null, options);

        log.debug("处理章节");
    }

    /**
     * 获取当前文档
     */
    public XWPFDocument getDocument() {
        return document;
    }

    /**
     * 关闭文档
     */
    public void close() throws IOException {
        if (document != null) {
            document.close();
        }
    }

    /**
     * 元素处理器接口
     */
    @FunctionalInterface
    private interface ElementProcessor {
        void process(Element element, XWPFParagraph currentParagraph, ConversionOptions options);
    }

    /**
     * 转换选项配置
     */
    public static class ConversionOptions {
        private String title = "";
        private String author = "";
        private String subject = "";
        private boolean includeStyles = true;
        private boolean preserveFormatting = true;
        private int defaultFontSize = 12;
        private String defaultFontFamily = "宋体";

        // Getters and Setters
        public String getTitle() {
            return title;
        }

        public ConversionOptions setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getAuthor() {
            return author;
        }

        public ConversionOptions setAuthor(String author) {
            this.author = author;
            return this;
        }

        public String getSubject() {
            return subject;
        }

        public ConversionOptions setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public boolean isIncludeStyles() {
            return includeStyles;
        }

        public ConversionOptions setIncludeStyles(boolean includeStyles) {
            this.includeStyles = includeStyles;
            return this;
        }

        public boolean isPreserveFormatting() {
            return preserveFormatting;
        }

        public ConversionOptions setPreserveFormatting(boolean preserveFormatting) {
            this.preserveFormatting = preserveFormatting;
            return this;
        }

        public int getDefaultFontSize() {
            return defaultFontSize;
        }

        public ConversionOptions setDefaultFontSize(int defaultFontSize) {
            this.defaultFontSize = defaultFontSize;
            return this;
        }

        public String getDefaultFontFamily() {
            return defaultFontFamily;
        }

        public ConversionOptions setDefaultFontFamily(String defaultFontFamily) {
            this.defaultFontFamily = defaultFontFamily;
            return this;
        }
    }
}