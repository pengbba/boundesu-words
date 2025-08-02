package com.boundesu.words.core.creator.impl;

import com.boundesu.words.common.creator.DocumentCreator;
import com.boundesu.words.xml.creator.XmlToDocxCreator;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过XML转换创建DOCX文档的实现类
 * 提供结构化程度最高的文档创建方式
 * 
 * @author Boundesu Team
 * @version 1.0.0
 */
public class XmlBasedDocxCreator implements DocumentCreator {

    private final List<String> xmlContent;
    private String documentTitle;
    private String documentAuthor;
    private String headerText;
    private String footerText;
    private boolean pageNumberEnabled;
    private String headerImagePath;
    private String footerImagePath;
    private int headerImageWidth = -1;
    private int headerImageHeight = -1;
    private int footerImageWidth = -1;
    private int footerImageHeight = -1;

    /**
     * 构造函数
     */
    public XmlBasedDocxCreator() {
        this.xmlContent = new ArrayList<>();
        this.documentTitle = "";
        this.documentAuthor = "";
        this.headerText = "";
        this.footerText = "";
        this.pageNumberEnabled = false;
        this.headerImagePath = null;
        this.footerImagePath = null;
    }

    @Override
    public void createDocument(Path outputPath) throws IOException {
        try {
            com.boundesu.words.xml.creator.XmlToDocxCreator converter = new com.boundesu.words.xml.creator.XmlToDocxCreator();
            
            if (!documentTitle.isEmpty()) {
                converter.setTitle(documentTitle);
            }
            
            if (!documentAuthor.isEmpty()) {
                converter.setAuthor(documentAuthor);
            }
            
            // 添加XML内容
            for (String content : xmlContent) {
                converter.addParagraph(content);
            }
            
            org.apache.poi.xwpf.usermodel.XWPFDocument document = converter.createDocument();
            
            // 应用页头页脚设置
            applyHeaderFooter(document);
            
            try (java.io.FileOutputStream out = new java.io.FileOutputStream(outputPath.toFile())) {
                document.write(out);
            }
            document.close();
        } catch (Exception e) {
            throw new IOException("Failed to create document: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] createDocumentAsBytes() throws IOException {
        try {
            com.boundesu.words.xml.creator.XmlToDocxCreator converter = new com.boundesu.words.xml.creator.XmlToDocxCreator();
            
            if (!documentTitle.isEmpty()) {
                converter.setTitle(documentTitle);
            }
            
            if (!documentAuthor.isEmpty()) {
                converter.setAuthor(documentAuthor);
            }
            
            // 添加XML内容
            for (String content : xmlContent) {
                converter.addParagraph(content);
            }
            
            return converter.exportToBytes();
        } catch (Exception e) {
            throw new IOException("Failed to create document bytes: " + e.getMessage(), e);
        }
    }

    @Override
    public DocumentCreator setTitle(String title) {
        this.documentTitle = title != null ? title.trim() : "";
        return this;
    }

    @Override
    public DocumentCreator setAuthor(String author) {
        this.documentAuthor = author != null ? author.trim() : "";
        return this;
    }

    @Override
    public DocumentCreator addParagraph(String text) {
        if (text == null) {
            text = "";
        }
        
        // 处理换行符
        String[] lines = text.split("\n");
        for (String line : lines) {
            if (line.trim().isEmpty()) {
                xmlContent.add("<break/>");
            } else {
                xmlContent.add("<paragraph>" + escapeXml(line) + "</paragraph>");
            }
        }
        
        return this;
    }

    @Override
    public DocumentCreator addHeading(String text, int level) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("标题文本不能为空");
        }
        
        if (level < 1 || level > 6) {
            throw new IllegalArgumentException("标题级别必须在1-6之间");
        }
        
        xmlContent.add("<heading level='" + level + "'>" + escapeXml(text.trim()) + "</heading>");
        return this;
    }

    /**
     * 添加XML内容
     *
     * @param xml XML内容
     * @return 当前创建器实例
     */
    public XmlBasedDocxCreator addXml(String xml) {
        if (xml != null && !xml.trim().isEmpty()) {
            xmlContent.add(xml);
        }
        return this;
    }

    /**
     * 添加表格
     *
     * @param headers 表头
     * @param rows    表格数据行
     * @return 当前创建器实例
     */
    public XmlBasedDocxCreator addTable(String[] headers, String[][] rows) {
        if (headers == null || headers.length == 0) {
            throw new IllegalArgumentException("表头不能为空");
        }
        
        StringBuilder tableXml = new StringBuilder();
        tableXml.append("<table>");
        
        // 添加表头
        tableXml.append("<header>");
        for (String header : headers) {
            tableXml.append("<cell>")
                    .append(escapeXml(header != null ? header : ""))
                    .append("</cell>");
        }
        tableXml.append("</header>");
        
        // 添加数据行
        if (rows != null && rows.length > 0) {
            for (String[] row : rows) {
                tableXml.append("<row>");
                for (int i = 0; i < headers.length; i++) {
                    String cellValue = (row != null && i < row.length && row[i] != null) ? row[i] : "";
                    tableXml.append("<cell>")
                            .append(escapeXml(cellValue))
                            .append("</cell>");
                }
                tableXml.append("</row>");
            }
        }
        
        tableXml.append("</table>");
        xmlContent.add(tableXml.toString());
        
        return this;
    }

    /**
     * 添加列表
     *
     * @param items    列表项
     * @param numbered 是否为有序列表
     * @return 当前创建器实例
     */
    public XmlBasedDocxCreator addList(String[] items, boolean numbered) {
        if (items == null || items.length == 0) {
            return this;
        }
        
        String listType = numbered ? "ordered" : "unordered";
        StringBuilder listXml = new StringBuilder();
        listXml.append("<list type='").append(listType).append("'>");
        
        for (String item : items) {
            listXml.append("<item>")
                   .append(escapeXml(item != null ? item : ""))
                   .append("</item>");
        }
        
        listXml.append("</list>");
        xmlContent.add(listXml.toString());
        
        return this;
    }

    /**
     * 添加分页符
     *
     * @return 当前创建器实例
     */
    public XmlBasedDocxCreator addPageBreak() {
        xmlContent.add("<pagebreak/>");
        return this;
    }

    /**
     * 添加换行符
     *
     * @return 当前创建器实例
     */
    public XmlBasedDocxCreator addLineBreak() {
        xmlContent.add("<break/>");
        return this;
    }

    /**
     * 添加粗体文本
     *
     * @param text 文本内容
     * @return 当前创建器实例
     */
    public XmlBasedDocxCreator addBoldText(String text) {
        if (text != null && !text.trim().isEmpty()) {
            xmlContent.add("<paragraph><bold>" + escapeXml(text) + "</bold></paragraph>");
        }
        return this;
    }

    /**
     * 添加斜体文本
     *
     * @param text 文本内容
     * @return 当前创建器实例
     */
    public XmlBasedDocxCreator addItalicText(String text) {
        if (text != null && !text.trim().isEmpty()) {
            xmlContent.add("<paragraph><italic>" + escapeXml(text) + "</italic></paragraph>");
        }
        return this;
    }

    /**
     * 添加下划线文本
     *
     * @param text 文本内容
     * @return 当前创建器实例
     */
    public XmlBasedDocxCreator addUnderlineText(String text) {
        if (text != null && !text.trim().isEmpty()) {
            xmlContent.add("<paragraph><underline>" + escapeXml(text) + "</underline></paragraph>");
        }
        return this;
    }

    /**
     * 添加链接
     *
     * @param text 链接文本
     * @param url  链接地址
     * @return 当前创建器实例
     */
    public XmlBasedDocxCreator addLink(String text, String url) {
        if (text != null && !text.trim().isEmpty() && url != null && !url.trim().isEmpty()) {
            xmlContent.add("<paragraph><link url='" + escapeXml(url) + "'>" + escapeXml(text) + "</link></paragraph>");
        }
        return this;
    }

    /**
     * 添加图片引用
     *
     * @param imagePath 图片路径
     * @param altText   替代文本
     * @return 当前创建器实例
     */
    public XmlBasedDocxCreator addImage(String imagePath, String altText) {
        if (imagePath != null && !imagePath.trim().isEmpty()) {
            String alt = altText != null ? altText : "";
            xmlContent.add("<image src='" + escapeXml(imagePath) + "' alt='" + escapeXml(alt) + "'/>");
        }
        return this;
    }

    /**
     * 添加章节
     *
     * @param title   章节标题
     * @param content 章节内容
     * @return 当前创建器实例
     */
    public XmlBasedDocxCreator addSection(String title, String content) {
        xmlContent.add("<section>");
        
        if (title != null && !title.trim().isEmpty()) {
            xmlContent.add("<title>" + escapeXml(title) + "</title>");
        }
        
        if (content != null && !content.trim().isEmpty()) {
            xmlContent.add("<content>" + escapeXml(content) + "</content>");
        }
        
        xmlContent.add("</section>");
        return this;
    }

    /**
     * 构建完整的XML文档
     *
     * @return 完整的XML文档字符串
     */
    private String buildFullXmlDocument() {
        StringBuilder xml = new StringBuilder();
        
        xml.append("<?xml version='1.0' encoding='UTF-8'?>");
        xml.append("<document>");
        
        // 添加文档元数据
        if (!documentTitle.isEmpty() || !documentAuthor.isEmpty()) {
            xml.append("<metadata>");
            
            if (!documentTitle.isEmpty()) {
                xml.append("<title>").append(escapeXml(documentTitle)).append("</title>");
            }
            
            if (!documentAuthor.isEmpty()) {
                xml.append("<author>").append(escapeXml(documentAuthor)).append("</author>");
            }
            
            xml.append("</metadata>");
        }
        
        // 添加文档内容
        xml.append("<content>");
        for (String content : xmlContent) {
            xml.append(content);
        }
        xml.append("</content>");
        
        xml.append("</document>");
        
        return xml.toString();
    }

    /**
     * XML转义
     *
     * @param text 原始文本
     * @return 转义后的文本
     */
    private String escapeXml(String text) {
        if (text == null) {
            return "";
        }
        
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&apos;");
    }

    /**
     * 清空所有内容
     *
     * @return 当前创建器实例
     */
    public XmlBasedDocxCreator clear() {
        xmlContent.clear();
        return this;
    }

    /**
     * 获取当前XML内容
     *
     * @return XML内容列表
     */
    public List<String> getXmlContent() {
        return new ArrayList<>(xmlContent);
    }

    @Override
    public DocumentCreator setHeader(String headerText) {
        this.headerText = headerText != null ? headerText.trim() : "";
        return this;
    }

    @Override
    public DocumentCreator setFooter(String footerText) {
        this.footerText = footerText != null ? footerText.trim() : "";
        return this;
    }

    @Override
    public DocumentCreator setPageNumberEnabled(boolean enabled) {
        this.pageNumberEnabled = enabled;
        return this;
    }

    @Override
    public DocumentCreator setHeaderWithImage(String headerText, String imagePath) {
        this.headerText = headerText != null ? headerText.trim() : "";
        this.headerImagePath = imagePath;
        this.headerImageWidth = -1;
        this.headerImageHeight = -1;
        return this;
    }

    @Override
    public DocumentCreator setHeaderWithImage(String headerText, String imagePath, int width, int height) {
        this.headerText = headerText != null ? headerText.trim() : "";
        this.headerImagePath = imagePath;
        this.headerImageWidth = width;
        this.headerImageHeight = height;
        return this;
    }

    @Override
    public DocumentCreator setFooterWithImage(String footerText, String imagePath) {
        this.footerText = footerText != null ? footerText.trim() : "";
        this.footerImagePath = imagePath;
        this.footerImageWidth = -1;
        this.footerImageHeight = -1;
        return this;
    }

    @Override
    public DocumentCreator setFooterWithImage(String footerText, String imagePath, int width, int height) {
        this.footerText = footerText != null ? footerText.trim() : "";
        this.footerImagePath = imagePath;
        this.footerImageWidth = width;
        this.footerImageHeight = height;
        return this;
    }

    /**
     * 应用页头页脚设置
     */
    private void applyHeaderFooter(org.apache.poi.xwpf.usermodel.XWPFDocument document) {
        try {
            // 创建页头
            if (!headerText.isEmpty()) {
                org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy headerFooterPolicy = document.getHeaderFooterPolicy();
                if (headerFooterPolicy == null) {
                    headerFooterPolicy = document.createHeaderFooterPolicy();
                }
                
                org.apache.poi.xwpf.usermodel.XWPFHeader header = headerFooterPolicy.createHeader(org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy.DEFAULT);
                org.apache.poi.xwpf.usermodel.XWPFParagraph headerParagraph = header.createParagraph();
                headerParagraph.setAlignment(org.apache.poi.xwpf.usermodel.ParagraphAlignment.CENTER);
                org.apache.poi.xwpf.usermodel.XWPFRun headerRun = headerParagraph.createRun();
                headerRun.setText(headerText);
                headerRun.setFontFamily("Times New Roman");
                headerRun.setFontSize(10);
            }
            
            // 创建页脚
            if (!footerText.isEmpty() || pageNumberEnabled) {
                org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy headerFooterPolicy = document.getHeaderFooterPolicy();
                if (headerFooterPolicy == null) {
                    headerFooterPolicy = document.createHeaderFooterPolicy();
                }
                
                org.apache.poi.xwpf.usermodel.XWPFFooter footer = headerFooterPolicy.createFooter(org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy.DEFAULT);
                org.apache.poi.xwpf.usermodel.XWPFParagraph footerParagraph = footer.createParagraph();
                footerParagraph.setAlignment(org.apache.poi.xwpf.usermodel.ParagraphAlignment.CENTER);
                org.apache.poi.xwpf.usermodel.XWPFRun footerRun = footerParagraph.createRun();
                
                String footerContent = "";
                if (!footerText.isEmpty()) {
                    footerContent = footerText;
                }
                
                if (pageNumberEnabled) {
                    if (!footerContent.isEmpty()) {
                        footerContent += " - ";
                    }
                    footerContent += "第 ";
                    footerRun.setText(footerContent);
                    footerRun.setFontFamily("Times New Roman");
                    footerRun.setFontSize(10);
                    
                    // 添加页码字段
                    footerParagraph.getCTP().addNewFldSimple().setInstr("PAGE");
                    
                    org.apache.poi.xwpf.usermodel.XWPFRun pageRun = footerParagraph.createRun();
                    pageRun.setText(" 页");
                    pageRun.setFontFamily("Times New Roman");
                    pageRun.setFontSize(10);
                } else {
                    footerRun.setText(footerContent);
                    footerRun.setFontFamily("Times New Roman");
                    footerRun.setFontSize(10);
                }
            }
        } catch (Exception e) {
            // 页头页脚设置失败时不影响文档创建
            System.err.println("设置页头页脚失败: " + e.getMessage());
        }
    }
}