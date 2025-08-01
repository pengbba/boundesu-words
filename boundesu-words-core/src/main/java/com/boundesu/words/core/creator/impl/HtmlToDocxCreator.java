package com.boundesu.words.core.creator.impl;

import com.boundesu.words.core.creator.DocumentCreator;
import com.boundesu.words.html.converter.HtmlToDocxConverter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过HTML转换创建DOCX文档的实现类
 * 支持丰富的HTML格式转换
 * 
 * @author Boundesu Team
 * @version 1.0.0
 */
public class HtmlToDocxCreator implements DocumentCreator {

    private final List<String> htmlContent;
    private String documentTitle;
    private String documentAuthor;
    private String headerText;
    private String footerText;
    private boolean pageNumberEnabled;

    /**
     * 构造函数
     */
    public HtmlToDocxCreator() {
        this.htmlContent = new ArrayList<>();
        this.documentTitle = "";
        this.documentAuthor = "";
        this.headerText = "";
        this.footerText = "";
        this.pageNumberEnabled = false;
    }

    @Override
    public void createDocument(Path outputPath) throws IOException {
        try {
            String fullHtml = buildFullHtmlDocument();
            HtmlToDocxConverter converter = new HtmlToDocxConverter();
            org.apache.poi.xwpf.usermodel.XWPFDocument document = converter.convertHtmlToDocx(fullHtml);
            
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
            String fullHtml = buildFullHtmlDocument();
            HtmlToDocxConverter converter = new HtmlToDocxConverter();
            org.apache.poi.xwpf.usermodel.XWPFDocument document = converter.convertHtmlToDocx(fullHtml);
            
            try (java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream()) {
                document.write(out);
                document.close();
                return out.toByteArray();
            }
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
                htmlContent.add("<br/>");
            } else {
                htmlContent.add("<p>" + escapeHtml(line) + "</p>");
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
        
        htmlContent.add("<h" + level + ">" + escapeHtml(text.trim()) + "</h" + level + ">");
        return this;
    }

    /**
     * 添加HTML内容
     *
     * @param html HTML内容
     * @return 当前创建器实例
     */
    public HtmlToDocxCreator addHtml(String html) {
        if (html != null && !html.trim().isEmpty()) {
            htmlContent.add(html);
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
    public HtmlToDocxCreator addTable(String[] headers, String[][] rows) {
        if (headers == null || headers.length == 0) {
            throw new IllegalArgumentException("表头不能为空");
        }
        
        StringBuilder tableHtml = new StringBuilder();
        tableHtml.append("<table border='1' style='border-collapse: collapse; width: 100%;'>");
        
        // 添加表头
        tableHtml.append("<thead><tr>");
        for (String header : headers) {
            tableHtml.append("<th style='background-color: #f2f2f2; padding: 8px; text-align: left;'>")
                     .append(escapeHtml(header != null ? header : ""))
                     .append("</th>");
        }
        tableHtml.append("</tr></thead>");
        
        // 添加数据行
        if (rows != null && rows.length > 0) {
            tableHtml.append("<tbody>");
            for (String[] row : rows) {
                tableHtml.append("<tr>");
                for (int i = 0; i < headers.length; i++) {
                    String cellValue = (row != null && i < row.length && row[i] != null) ? row[i] : "";
                    tableHtml.append("<td style='padding: 8px; border: 1px solid #ddd;'>")
                             .append(escapeHtml(cellValue))
                             .append("</td>");
                }
                tableHtml.append("</tr>");
            }
            tableHtml.append("</tbody>");
        }
        
        tableHtml.append("</table>");
        htmlContent.add(tableHtml.toString());
        
        return this;
    }

    /**
     * 添加列表
     *
     * @param items    列表项
     * @param numbered 是否为有序列表
     * @return 当前创建器实例
     */
    public HtmlToDocxCreator addList(String[] items, boolean numbered) {
        if (items == null || items.length == 0) {
            return this;
        }
        
        String listTag = numbered ? "ol" : "ul";
        StringBuilder listHtml = new StringBuilder();
        listHtml.append("<").append(listTag).append(">");
        
        for (String item : items) {
            listHtml.append("<li>")
                    .append(escapeHtml(item != null ? item : ""))
                    .append("</li>");
        }
        
        listHtml.append("</").append(listTag).append(">");
        htmlContent.add(listHtml.toString());
        
        return this;
    }

    /**
     * 添加分页符
     *
     * @return 当前创建器实例
     */
    public HtmlToDocxCreator addPageBreak() {
        htmlContent.add("<div style='page-break-after: always;'></div>");
        return this;
    }

    /**
     * 添加换行符
     *
     * @return 当前创建器实例
     */
    public HtmlToDocxCreator addLineBreak() {
        htmlContent.add("<br/>");
        return this;
    }

    /**
     * 添加粗体文本
     *
     * @param text 文本内容
     * @return 当前创建器实例
     */
    public HtmlToDocxCreator addBoldText(String text) {
        if (text != null && !text.trim().isEmpty()) {
            htmlContent.add("<p><strong>" + escapeHtml(text) + "</strong></p>");
        }
        return this;
    }

    /**
     * 添加斜体文本
     *
     * @param text 文本内容
     * @return 当前创建器实例
     */
    public HtmlToDocxCreator addItalicText(String text) {
        if (text != null && !text.trim().isEmpty()) {
            htmlContent.add("<p><em>" + escapeHtml(text) + "</em></p>");
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
    public HtmlToDocxCreator addLink(String text, String url) {
        if (text != null && !text.trim().isEmpty() && url != null && !url.trim().isEmpty()) {
            htmlContent.add("<p><a href='" + escapeHtml(url) + "'>" + escapeHtml(text) + "</a></p>");
        }
        return this;
    }

    /**
     * 构建完整的HTML文档
     *
     * @return 完整的HTML文档字符串
     */
    private String buildFullHtmlDocument() {
        StringBuilder html = new StringBuilder();
        
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        
        if (!documentTitle.isEmpty()) {
            html.append("<title>").append(escapeHtml(documentTitle)).append("</title>");
        }
        
        // 添加基本样式
        html.append("<style>");
        html.append("body { font-family: 'Times New Roman', serif; font-size: 12pt; line-height: 1.5; margin: 1in; }");
        html.append("h1, h2, h3, h4, h5, h6 { color: #333; margin-top: 1em; margin-bottom: 0.5em; }");
        html.append("p { margin-bottom: 1em; }");
        html.append("table { border-collapse: collapse; width: 100%; margin-bottom: 1em; }");
        html.append("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
        html.append("th { background-color: #f2f2f2; font-weight: bold; }");
        html.append("ul, ol { margin-bottom: 1em; }");
        html.append("</style>");
        
        html.append("</head>");
        html.append("<body>");
        
        // 添加文档标题（如果有）
        if (!documentTitle.isEmpty()) {
            html.append("<h1>").append(escapeHtml(documentTitle)).append("</h1>");
        }
        
        // 添加作者信息（如果有）
        if (!documentAuthor.isEmpty()) {
            html.append("<p><em>作者: ").append(escapeHtml(documentAuthor)).append("</em></p>");
        }
        
        // 添加所有内容
        for (String content : htmlContent) {
            html.append(content);
        }
        
        html.append("</body>");
        html.append("</html>");
        
        return html.toString();
    }

    /**
     * HTML转义
     *
     * @param text 原始文本
     * @return 转义后的文本
     */
    private String escapeHtml(String text) {
        if (text == null) {
            return "";
        }
        
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#39;");
    }

    /**
     * 清空所有内容
     *
     * @return 当前创建器实例
     */
    public HtmlToDocxCreator clear() {
        htmlContent.clear();
        return this;
    }

    /**
     * 获取当前HTML内容
     *
     * @return HTML内容列表
     */
    public List<String> getHtmlContent() {
        return new ArrayList<>(htmlContent);
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