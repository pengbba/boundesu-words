package com.boundesu.words.sdk.converter;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.util.Units;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 优化的XML转DOCX转换器
 * 基于Apache POI 5.4.1的新特性进行优化
 */
public class OptimizedXmlToDocxConverter {
    
    private static final Logger log = LoggerFactory.getLogger(OptimizedXmlToDocxConverter.class);
    
    private XWPFDocument document;
    private Map<String, ElementProcessor> elementProcessors;
    
    /**
     * 转换选项
     */
    public static class ConversionOptions {
        private String title;
        private String author;
        private boolean preserveFormatting = true;
        private boolean includeImages = true;
        private boolean createTOC = false;
        
        // Getters and setters
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        
        public String getAuthor() { return author; }
        public void setAuthor(String author) { this.author = author; }
        
        public boolean isPreserveFormatting() { return preserveFormatting; }
        public void setPreserveFormatting(boolean preserveFormatting) { this.preserveFormatting = preserveFormatting; }
        
        public boolean isIncludeImages() { return includeImages; }
        public void setIncludeImages(boolean includeImages) { this.includeImages = includeImages; }
        
        public boolean isCreateTOC() { return createTOC; }
        public void setCreateTOC(boolean createTOC) { this.createTOC = createTOC; }
    }
    
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
    public byte[] convertXmlToDocx(String xmlContent, ConversionOptions options) throws Exception {
        org.w3c.dom.Document xmlDoc = parseXmlString(xmlContent);
        return convertXmlToDocx(xmlDoc, options);
    }
    
    /**
     * 从XML文件转换为DOCX
     */
    public byte[] convertXmlFileToDocx(String xmlFilePath, ConversionOptions options) throws Exception {
        org.w3c.dom.Document xmlDoc = parseXmlFile(xmlFilePath);
        return convertXmlToDocx(xmlDoc, options);
    }
    
    /**
     * 从XML文档转换为DOCX
     */
    public byte[] convertXmlToDocx(org.w3c.dom.Document xmlDoc, ConversionOptions options) throws Exception {
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
        if (options.getTitle() != null) {
            document.getProperties().getCoreProperties().setTitle(options.getTitle());
        }
        if (options.getAuthor() != null) {
            document.getProperties().getCoreProperties().setCreator(options.getAuthor());
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
            if (child instanceof Element) {
                Element childElement = (Element) child;
                processElement(childElement, currentParagraph, options);
            } else if (child instanceof Text) {
                Text textNode = (Text) child;
                processTextNode(textNode, currentParagraph);
            }
        }
    }
    
    /**
     * 处理文本节点
     */
    private void processTextNode(Text textNode, XWPFParagraph paragraph) {
        String text = textNode.getTextContent();
        if (text != null && !text.trim().isEmpty()) {
            if (paragraph == null) {
                paragraph = document.createParagraph();
            }
            XWPFRun run = paragraph.createRun();
            run.setText(text);
        }
    }
    
    // 元素处理器方法
    
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
        
        log.debug("处理标题: {}", element.getTextContent());
    }
    
    /**
     * 处理标题元素
     */
    private void processHeadingElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        int level = getIntAttribute(element, "level", 1);
        processHeadingElement(element, currentParagraph, options, level);
    }
    
    /**
     * 处理指定级别的标题元素
     */
    private void processHeadingElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options, int level) {
        level = Math.max(1, Math.min(6, level)); // 限制在1-6之间
        
        XWPFParagraph paragraph = document.createParagraph();
        
        XWPFRun run = paragraph.createRun();
        run.setText(element.getTextContent());
        run.setBold(true);
        
        // 根据级别设置字体大小
        int fontSize = Math.max(12, 20 - level * 2);
        run.setFontSize(fontSize);
        
        log.debug("处理标题 H{}: {}", level, element.getTextContent());
    }
    
    /**
     * 处理段落元素
     */
    private void processParagraphElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        XWPFParagraph paragraph = document.createParagraph();
        
        // 处理段落属性
        String alignment = element.getAttribute("align");
        if (!alignment.isEmpty()) {
            switch (alignment.toLowerCase()) {
                case "center":
                    paragraph.setAlignment(ParagraphAlignment.CENTER);
                    break;
                case "right":
                    paragraph.setAlignment(ParagraphAlignment.RIGHT);
                    break;
                case "justify":
                    paragraph.setAlignment(ParagraphAlignment.BOTH);
                    break;
                default:
                    paragraph.setAlignment(ParagraphAlignment.LEFT);
                    break;
            }
        }
        
        processChildElements(element, paragraph, options);
        
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
        
        // 处理文本属性
        applyTextAttributes(run, element);
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
    }
    
    /**
     * 处理表格元素
     */
    private void processTableElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        XWPFTable table = document.createTable();
        
        // 设置表格宽度
        String width = element.getAttribute("width");
        if (!width.isEmpty()) {
            table.setWidth(width);
        } else {
            table.setWidth("100%");
        }
        
        // 处理表格行
        NodeList rows = element.getElementsByTagName("row");
        boolean isFirstRow = true;
        
        for (int i = 0; i < rows.getLength(); i++) {
            Element rowElement = (Element) rows.item(i);
            XWPFTableRow tableRow;
            
            if (i == 0) {
                tableRow = table.getRow(0);
            } else {
                tableRow = table.createRow();
            }
            
            NodeList cells = rowElement.getElementsByTagName("cell");
            for (int j = 0; j < cells.getLength(); j++) {
                Element cellElement = (Element) cells.item(j);
                XWPFTableCell tableCell;
                
                if (j < tableRow.getTableCells().size()) {
                    tableCell = tableRow.getCell(j);
                } else {
                    tableCell = tableRow.createCell();
                }
                
                // 清除默认段落
                if (!tableCell.getParagraphs().isEmpty()) {
                    tableCell.removeParagraph(0);
                }
                
                XWPFParagraph cellParagraph = tableCell.addParagraph();
                XWPFRun cellRun = cellParagraph.createRun();
                cellRun.setText(cellElement.getTextContent());
                
                // 如果是标题行，设置粗体
                if (isFirstRow) {
                    cellRun.setBold(true);
                }
            }
            
            isFirstRow = false;
        }
        
        log.debug("处理表格，{}行", rows.getLength());
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
        String type = element.getAttribute("type");
        boolean isOrdered = "ordered".equalsIgnoreCase(type) || "ol".equalsIgnoreCase(type);
        
        NodeList items = element.getElementsByTagName("item");
        for (int i = 0; i < items.getLength(); i++) {
            Element itemElement = (Element) items.item(i);
            XWPFParagraph listParagraph = document.createParagraph();
            
            XWPFRun run = listParagraph.createRun();
            if (isOrdered) {
                run.setText((i + 1) + ". " + itemElement.getTextContent());
            } else {
                run.setText("• " + itemElement.getTextContent());
            }
            
            // 设置缩进
            listParagraph.setIndentationLeft(720); // 0.5英寸
        }
        
        log.debug("处理列表，{}项", items.getLength());
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
        String src = element.getAttribute("src");
        String alt = element.getAttribute("alt");
        
        if (src != null && !src.trim().isEmpty() && options.isIncludeImages()) {
            try {
                // 尝试加载图片
                java.nio.file.Path imagePath = java.nio.file.Paths.get(src);
                if (java.nio.file.Files.exists(imagePath)) {
                    XWPFParagraph imageParagraph = currentParagraph != null ? currentParagraph : document.createParagraph();
                    XWPFRun imageRun = imageParagraph.createRun();
                    
                    try (FileInputStream imageStream = new FileInputStream(imagePath.toFile())) {
                        String fileName = imagePath.getFileName().toString();
                        int format = getImageFormat(fileName);
                        imageRun.addPicture(imageStream, format, fileName, Units.toEMU(200), Units.toEMU(150));
                    }
                } else {
                    // 图片不存在，添加替代文本
                    addTextToDocument(alt != null ? alt : "[图片: " + src + "]", currentParagraph);
                }
            } catch (Exception e) {
                log.warn("处理图片时出错: {}", e.getMessage());
                addTextToDocument(alt != null ? alt : "[图片加载失败]", currentParagraph);
            }
        }
    }
    
    /**
     * 处理链接元素
     */
    private void processLinkElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        String href = element.getAttribute("href");
        String text = element.getTextContent();
        
        XWPFParagraph paragraph = currentParagraph != null ? currentParagraph : document.createParagraph();
        
        if (href != null && !href.trim().isEmpty()) {
            try {
                // 简化的超链接处理
                XWPFRun run = paragraph.createRun();
                run.setText(text + " (" + href + ")");
                run.setColor("0000FF"); // 蓝色
                run.setUnderline(UnderlinePatterns.SINGLE);
            } catch (Exception e) {
                log.warn("创建超链接时出错: {}", e.getMessage());
                // 降级为普通文本
                XWPFRun run = paragraph.createRun();
                run.setText(text + " (" + href + ")");
            }
        } else {
            // 普通文本
            XWPFRun run = paragraph.createRun();
            run.setText(text);
        }
    }
    
    /**
     * 处理换行元素
     */
    private void processBreakElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        if (currentParagraph != null) {
            XWPFRun run = currentParagraph.createRun();
            run.addBreak();
        } else {
            document.createParagraph(); // 创建空段落作为换行
        }
    }
    
    /**
     * 处理章节元素
     */
    private void processSectionElement(Element element, XWPFParagraph currentParagraph, ConversionOptions options) {
        // 添加分页符
        XWPFParagraph sectionParagraph = document.createParagraph();
        XWPFRun run = sectionParagraph.createRun();
        run.addBreak(BreakType.PAGE);
        
        // 处理章节内容
        processChildElements(element, null, options);
    }
    
    // 辅助方法
    
    /**
     * 添加文本到文档
     */
    private void addTextToDocument(String text, XWPFParagraph currentParagraph) {
        if (currentParagraph != null) {
            XWPFRun run = currentParagraph.createRun();
            run.setText(text);
        } else {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(text);
        }
    }
    
    /**
     * 获取图片格式
     */
    private int getImageFormat(String fileName) {
        String extension = fileName.toLowerCase();
        if (extension.endsWith(".png")) {
            return XWPFDocument.PICTURE_TYPE_PNG;
        } else if (extension.endsWith(".jpg") || extension.endsWith(".jpeg")) {
            return XWPFDocument.PICTURE_TYPE_JPEG;
        } else if (extension.endsWith(".gif")) {
            return XWPFDocument.PICTURE_TYPE_GIF;
        } else {
            return XWPFDocument.PICTURE_TYPE_PNG; // 默认
        }
    }
    
    /**
     * 获取整数属性值
     */
    private int getIntAttribute(Element element, String attributeName, int defaultValue) {
        String value = element.getAttribute(attributeName);
        if (value != null && !value.trim().isEmpty()) {
            try {
                return Integer.parseInt(value.trim());
            } catch (NumberFormatException e) {
                log.debug("无法解析整数属性 {}: {}", attributeName, value);
            }
        }
        return defaultValue;
    }
    
    /**
     * 应用文本属性
     */
    private void applyTextAttributes(XWPFRun run, Element element) {
        String color = element.getAttribute("color");
        if (!color.isEmpty()) {
            run.setColor(color);
        }
        
        String fontSize = element.getAttribute("size");
        if (!fontSize.isEmpty()) {
            try {
                run.setFontSize(Integer.parseInt(fontSize));
            } catch (NumberFormatException e) {
                log.debug("无法解析字体大小: {}", fontSize);
            }
        }
        
        String fontFamily = element.getAttribute("font");
        if (!fontFamily.isEmpty()) {
            run.setFontFamily(fontFamily);
        }
    }
    
    /**
     * 元素处理器接口
     */
    @FunctionalInterface
    private interface ElementProcessor {
        void process(Element element, XWPFParagraph currentParagraph, ConversionOptions options);
    }
}