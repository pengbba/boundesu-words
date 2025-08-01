package com.boundesu.words.sdk.service;

import com.boundesu.words.sdk.converter.HtmlToDocxConverter;
import com.boundesu.words.sdk.export.BoundesuDocumentExporter;
import com.boundesu.words.sdk.export.BoundesuDocxExporter;
import com.boundesu.words.sdk.model.*;
import com.boundesu.words.sdk.util.BoundesuDocumentUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Boundesu Words JDK17 SDK - 核心文档处理服务
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class BoundesuDocumentService {

    private static final Logger log = LoggerFactory.getLogger(BoundesuDocumentService.class);

    private static final String DEFAULT_OUTPUT_DIR = "output";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * 创建新文档
     */
    public BoundesuDocument createDocument() {
        return BoundesuDocument.builder()
                .documentId(UUID.randomUUID().toString())
                .title("新建文档")
                .author("Boundesu")
                .createdAt(LocalDateTime.now())
                .lastModified(LocalDateTime.now())
                .version("1.0")
                .language("zh-CN")
                .status(BoundesuDocument.DocumentStatus.DRAFT)
                .style(new BoundesuDocumentStyle())
                .paragraphs(new ArrayList<>())
                .tables(new ArrayList<>())
                .customProperties(new HashMap<>())
                .build();
    }
    
    /**
     * 创建带标题的文档
     */
    public BoundesuDocument createDocument(String title, String author) {
        BoundesuDocument document = createDocument();
        document.setTitle(title);
        document.setAuthor(author);
        return document;
    }
    
    /**
     * 添加段落
     */
    public BoundesuDocument addParagraph(BoundesuDocument document, String text) {
        BoundesuParagraph paragraph = BoundesuParagraph.builder()
                .paragraphId(UUID.randomUUID().toString())
                .text(text)
                .style(new BoundesuParagraphStyle())
                .alignment(BoundesuParagraph.TextAlignment.LEFT)
                .build();
        
        document.getParagraphs().add(paragraph);
        document.updateLastModified();
        return document;
    }
    
    /**
     * 添加带样式的段落
     */
    public BoundesuDocument addParagraph(BoundesuDocument document, String text, BoundesuParagraphStyle style) {
        BoundesuParagraph paragraph = BoundesuParagraph.builder()
                .paragraphId(UUID.randomUUID().toString())
                .text(text)
                .style(style)
                .alignment(BoundesuParagraph.TextAlignment.LEFT)
                .build();
        
        document.getParagraphs().add(paragraph);
        document.updateLastModified();
        return document;
    }
    
    /**
     * 添加标题
     */
    public BoundesuDocument addHeading(BoundesuDocument document, String text, int level) {
        BoundesuParagraphStyle headingStyle = BoundesuParagraphStyle.builder()
                .fontFamily("Arial")
                .fontSize(18 - level * 2) // 根据级别调整字体大小
                .bold(true)
                .isHeading(true)
                .headingLevel(level)
                .build();
        
        return addParagraph(document, text, headingStyle);
    }
    
    /**
     * 添加表格
     */
    public BoundesuDocument addTable(BoundesuDocument document, int rows, int columns) {
        BoundesuTable table = BoundesuTable.builder()
                .tableId(UUID.randomUUID().toString())
                .title("表格")
                .rows(rows)
                .columns(columns)
                .style(new BoundesuTableStyle())
                .alignment(BoundesuTable.TableAlignment.LEFT)
                .build();
        
        // 初始化表格
        table.initializeTable(rows, columns);
        
        document.getTables().add(table);
        document.updateLastModified();
        return document;
    }
    
    /**
     * 设置表格数据
     */
    public BoundesuDocument setTableData(BoundesuDocument document, int tableIndex, String[][] data) {
        if (tableIndex < 0 || tableIndex >= document.getTables().size()) {
            throw new IllegalArgumentException("表格索引超出范围");
        }
        
        BoundesuTable table = document.getTables().get(tableIndex);
        
        for (int row = 0; row < data.length && row < table.getRows(); row++) {
            for (int col = 0; col < data[row].length && col < table.getColumns(); col++) {
                table.setCellText(row, col, data[row][col]);
            }
        }
        
        document.updateLastModified();
        return document;
    }
    
    /**
     * 设置文档样式
     */
    public BoundesuDocument setDocumentStyle(BoundesuDocument document, BoundesuDocumentStyle style) {
        document.setStyle(style);
        document.updateLastModified();
        return document;
    }
    
    /**
     * 设置1
     */
    public BoundesuDocument setHeader(BoundesuDocument document, String text) {
        if (document.getHeader() == null) {
            document.setHeader(new BoundesuHeader());
        }
        document.getHeader().setText(text);
        document.updateLastModified();
        return document;
    }
    
    /**
     * 设置文档页脚
     */
    public BoundesuDocument setFooter(BoundesuDocument document, String text) {
        if (document.getFooter() == null) {
            document.setFooter(new BoundesuFooter());
        }
        document.getFooter().setText(text);
        document.updateLastModified();
        return document;
    }
    
    /**
     * 保存文档为HTML格式
     */
    public String saveAsHtml(BoundesuDocument document, String filePath) throws IOException {
        StringBuilder html = new StringBuilder();
        
        // HTML头部
        html.append("<!DOCTYPE html>\n");
        html.append("<html lang=\"").append(document.getLanguage() != null ? document.getLanguage() : "zh-CN").append("\">\n");
        html.append("<head>\n");
        html.append("    <meta charset=\"UTF-8\">\n");
        html.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        html.append("    <title>").append(escapeHtml(document.getTitle())).append("</title>\n");
        
        // 添加meta信息
        if (document.getAuthor() != null) {
            html.append("    <meta name=\"author\" content=\"").append(escapeHtml(document.getAuthor())).append("\">\n");
        }
        if (document.getDescription() != null) {
            html.append("    <meta name=\"description\" content=\"").append(escapeHtml(document.getDescription())).append("\">\n");
        }
        if (document.getKeywords() != null) {
            html.append("    <meta name=\"keywords\" content=\"").append(escapeHtml(String.join(",", document.getKeywords()))).append("\">\n");
        }
        
        html.append("    <style>\n");
        html.append(generateCss(document));
        html.append("    </style>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        
        // 页眉
        if (document.getHeader() != null && document.getHeader().isVisible()) {
            html.append("    <header class=\"document-header\">\n");
            html.append("        <p>").append(escapeHtml(document.getHeader().getText())).append("</p>\n");
            html.append("    </header>\n");
        }
        
        // 文档内容
        html.append("    <div class=\"document-content\">\n");
        
        // 段落
        for (BoundesuParagraph paragraph : document.getParagraphs()) {
            html.append("        ").append(generateParagraphHtml(paragraph)).append("\n");
        }
        
        // 表格
        for (BoundesuTable table : document.getTables()) {
            html.append("        ").append(generateTableHtml(table)).append("\n");
        }
        
        html.append("    </div>\n");
        
        // 页脚
        if (document.getFooter() != null && document.getFooter().isVisible()) {
            html.append("    <footer class=\"document-footer\">\n");
            html.append("        <p>").append(escapeHtml(document.getFooter().getText())).append("</p>\n");
            if (document.getFooter().isShowPageNumber()) {
                html.append("        <p class=\"page-number\">").append(document.getFooter().getPageNumberFormat()).append("</p>\n");
            }
            html.append("    </footer>\n");
        }
        
        html.append("</body>\n");
        html.append("</html>");
        
        // 确保输出目录存在
        Path outputPath = Paths.get(filePath);
        Files.createDirectories(outputPath.getParent());
        
        // 写入文件
        Files.write(outputPath, html.toString().getBytes(StandardCharsets.UTF_8));
        
        log.info("文档已保存为HTML格式: {}", filePath);
        return filePath;
    }
    
    /**
     * 保存文档为纯文本格式
     */
    public String saveAsText(BoundesuDocument document, String filePath) throws IOException {
        StringBuilder text = new StringBuilder();
        
        // 文档标题
        text.append(document.getTitle()).append("\n");
        text.append("作者: ").append(document.getAuthor()).append("\n");
        text.append("创建时间: ").append(document.getCreatedAt().format(DATE_FORMATTER)).append("\n");
        text.append("更新时间: ").append(document.getLastModified().format(DATE_FORMATTER)).append("\n");
        for (int i = 0; i < 50; i++) {
            text.append("=");
        }
        text.append("\n\n");
        
        // 段落内容
        for (BoundesuParagraph paragraph : document.getParagraphs()) {
            text.append(paragraph.getText()).append("\n\n");
        }
        
        // 表格内容
        for (BoundesuTable table : document.getTables()) {
            text.append("表格: ").append(table.getTitle()).append("\n");
            for (int i = 0; i < 30; i++) {
                text.append("-");
            }
            text.append("\n");
            
            for (int row = 0; row < table.getRows(); row++) {
                for (int col = 0; col < table.getColumns(); col++) {
                    BoundesuTableCell cell = table.getCell(row, col);
                    if (cell != null && cell.getText() != null) {
                        text.append(cell.getText()).append("\t");
                    } else {
                        text.append("\t");
                    }
                }
                text.append("\n");
            }
            text.append("\n");
        }
        
        // 确保输出目录存在
        Path outputPath = Paths.get(filePath);
        Files.createDirectories(outputPath.getParent());
        
        // 写入文件
        Files.write(outputPath, text.toString().getBytes(StandardCharsets.UTF_8));
        
        log.info("文档已保存为文本格式: {}", filePath);
        return filePath;
    }
    
    /**
     * 生成CSS样式
     */
    private String generateCss(BoundesuDocument document) {
        StringBuilder css = new StringBuilder();
        BoundesuDocumentStyle style = document.getStyle();
        
        css.append("        body {\n");
        css.append("            font-family: ").append(style.getDefaultFontFamily()).append(";\n");
        css.append("            font-size: ").append(style.getDefaultFontSize()).append("px;\n");
        css.append("            line-height: ").append(style.getDefaultLineSpacing()).append(";\n");
        css.append("            background-color: ").append(style.getBackgroundColor()).append(";\n");
        css.append("            margin: 0;\n");
        css.append("            padding: 0;\n");
        css.append("        }\n");
        
        css.append("        .document-content {\n");
        css.append("            width: ").append(style.getPageWidth()).append("px;\n");
        css.append("            margin: 0 auto;\n");
        css.append("            padding: ").append(style.getMargins().getTop()).append("px ");
        css.append(style.getMargins().getRight()).append("px ");
        css.append(style.getMargins().getBottom()).append("px ");
        css.append(style.getMargins().getLeft()).append("px;\n");
        css.append("        }\n");
        
        css.append("        .document-header, .document-footer {\n");
        css.append("            text-align: center;\n");
        css.append("            padding: 10px;\n");
        css.append("            border-bottom: 1px solid #ccc;\n");
        css.append("        }\n");
        
        css.append("        .document-footer {\n");
        css.append("            border-top: 1px solid #ccc;\n");
        css.append("            border-bottom: none;\n");
        css.append("        }\n");
        
        css.append("        table {\n");
        css.append("            border-collapse: collapse;\n");
        css.append("            width: 100%;\n");
        css.append("            margin: 10px 0;\n");
        css.append("        }\n");
        
        css.append("        th, td {\n");
        css.append("            border: 1px solid #ddd;\n");
        css.append("            padding: 8px;\n");
        css.append("            text-align: left;\n");
        css.append("        }\n");
        
        css.append("        th {\n");
        css.append("            background-color: #f2f2f2;\n");
        css.append("            font-weight: bold;\n");
        css.append("        }\n");
        
        return css.toString();
    }
    
    /**
     * 生成段落HTML
     */
    private String generateParagraphHtml(BoundesuParagraph paragraph) {
        StringBuilder html = new StringBuilder();
        BoundesuParagraphStyle style = paragraph.getStyle();
        
        String tag = style.isHeading() ? "h" + style.getHeadingLevel() : "p";
        
        html.append("<").append(tag);
        html.append(" style=\"");
        
        if (style.getFontFamily() != null) {
            html.append("font-family: ").append(style.getFontFamily()).append("; ");
        }
        if (style.getFontSize() > 0) {
            html.append("font-size: ").append(style.getFontSize()).append("px; ");
        }
        if (style.isBold()) {
            html.append("font-weight: bold; ");
        }
        if (style.isItalic()) {
            html.append("font-style: italic; ");
        }
        if (style.isUnderline()) {
            html.append("text-decoration: underline; ");
        }
        if (style.getColor() != null) {
            html.append("color: ").append(style.getColor()).append("; ");
        }
        if (paragraph.getAlignment() != null) {
            html.append("text-align: ").append(paragraph.getAlignment().name().toLowerCase()).append("; ");
        }
        
        html.append("\">");
        html.append(escapeHtml(paragraph.getText()));
        html.append("</").append(tag).append(">");
        
        return html.toString();
    }
    
    /**
     * 生成表格HTML
     */
    private String generateTableHtml(BoundesuTable table) {
        StringBuilder html = new StringBuilder();
        
        html.append("<table>\n");
        
        for (int row = 0; row < table.getRows(); row++) {
            html.append("            <tr>\n");
            
            for (int col = 0; col < table.getColumns(); col++) {
                BoundesuTableCell cell = table.getCell(row, col);
                String cellTag = (cell != null && cell.isHeader()) ? "th" : "td";
                
                html.append("                <").append(cellTag).append(">");
                if (cell != null && cell.getText() != null) {
                    html.append(escapeHtml(cell.getText()));
                }
                html.append("</").append(cellTag).append(">\n");
            }
            
            html.append("            </tr>\n");
        }
        
        html.append("        </table>");
        
        return html.toString();
    }
    
    /**
     * HTML转义
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
     * 复制文档
     */
    public BoundesuDocument cloneDocument(BoundesuDocument source) {
        if (source == null) {
            throw new IllegalArgumentException("源文档不能为空");
        }
        
        BoundesuDocument cloned = BoundesuDocument.builder()
                .documentId(UUID.randomUUID().toString())
                .title(source.getTitle() + " - 副本")
                .author(source.getAuthor())
                .subject(source.getSubject())
                .description(source.getDescription())
                .keywords(source.getKeywords() != null ? new ArrayList<>(source.getKeywords()) : null)
                .createdAt(LocalDateTime.now())
                .lastModified(LocalDateTime.now())
                .version("1.0")
                .language(source.getLanguage())
                .status(BoundesuDocument.DocumentStatus.DRAFT)
                .paragraphs(new ArrayList<>())
                .tables(new ArrayList<>())
                .images(source.getImages() != null ? new ArrayList<>(source.getImages()) : new ArrayList<>())
                .style(source.getStyle())
                .customProperties(source.getCustomProperties() != null ? new HashMap<>(source.getCustomProperties()) : new HashMap<>())
                .build();
        
        // 复制段落
        if (source.getParagraphs() != null) {
            for (BoundesuParagraph paragraph : source.getParagraphs()) {
                BoundesuParagraph clonedParagraph = BoundesuParagraph.builder()
                        .paragraphId(UUID.randomUUID().toString())
                        .text(paragraph.getText())
                        .style(paragraph.getStyle())
                        .alignment(paragraph.getAlignment())
                        .build();
                cloned.getParagraphs().add(clonedParagraph);
            }
        }
        
        // 复制表格
        if (source.getTables() != null) {
            for (BoundesuTable table : source.getTables()) {
                BoundesuTable clonedTable = BoundesuTable.builder()
                        .tableId(UUID.randomUUID().toString())
                        .title(table.getTitle())
                        .rows(table.getRows())
                        .columns(table.getColumns())
                        .style(table.getStyle())
                        .alignment(table.getAlignment())
                        .build();
                clonedTable.initializeTable(table.getRows(), table.getColumns());
                cloned.getTables().add(clonedTable);
            }
        }
        
        return cloned;
    }
    
    /**
     * 合并文档
     */
    public BoundesuDocument mergeDocuments(BoundesuDocument target, BoundesuDocument source) {
        if (target == null || source == null) {
            throw new IllegalArgumentException("目标文档和源文档都不能为空");
        }
        
        // 合并段落
        if (source.getParagraphs() != null) {
            for (BoundesuParagraph paragraph : source.getParagraphs()) {
                BoundesuParagraph mergedParagraph = BoundesuParagraph.builder()
                        .paragraphId(UUID.randomUUID().toString())
                        .text(paragraph.getText())
                        .style(paragraph.getStyle())
                        .alignment(paragraph.getAlignment())
                        .build();
                target.getParagraphs().add(mergedParagraph);
            }
        }
        
        // 合并表格
        if (source.getTables() != null) {
            for (BoundesuTable table : source.getTables()) {
                BoundesuTable mergedTable = BoundesuTable.builder()
                        .tableId(UUID.randomUUID().toString())
                        .title(table.getTitle())
                        .rows(table.getRows())
                        .columns(table.getColumns())
                        .style(table.getStyle())
                        .alignment(table.getAlignment())
                        .build();
                mergedTable.initializeTable(table.getRows(), table.getColumns());
                target.getTables().add(mergedTable);
            }
        }
        
        // 合并图片
        if (source.getImages() != null) {
            target.getImages().addAll(source.getImages());
        }
        
        // 合并自定义属性
        if (source.getCustomProperties() != null) {
            target.getCustomProperties().putAll(source.getCustomProperties());
        }
        
        target.updateLastModified();
        return target;
    }
    
    /**
     * 搜索文档内容
     */
    public List<BoundesuParagraph> searchInDocument(BoundesuDocument document, String keyword) {
        return BoundesuDocumentUtils.findParagraphsContaining(document, keyword);
    }
    
    /**
     * 替换文档内容
     */
    public BoundesuDocument replaceInDocument(BoundesuDocument document, String searchText, String replaceText) {
        if (document == null || searchText == null || replaceText == null) {
            throw new IllegalArgumentException("文档、搜索文本和替换文本都不能为空");
        }
        
        boolean hasChanges = false;
        
        // 替换段落中的文本
        if (document.getParagraphs() != null) {
            for (BoundesuParagraph paragraph : document.getParagraphs()) {
                if (paragraph.getText() != null && paragraph.getText().contains(searchText)) {
                    paragraph.setText(paragraph.getText().replace(searchText, replaceText));
                    hasChanges = true;
                }
            }
        }
        
        // 替换标题
        if (document.getTitle() != null && document.getTitle().contains(searchText)) {
            document.setTitle(document.getTitle().replace(searchText, replaceText));
            hasChanges = true;
        }
        
        // 替换描述
        if (document.getDescription() != null && document.getDescription().contains(searchText)) {
            document.setDescription(document.getDescription().replace(searchText, replaceText));
            hasChanges = true;
        }
        
        if (hasChanges) {
            document.updateLastModified();
        }
        
        return document;
    }
    
    
    /**
     * 验证文档
     */
    public boolean validateDocument(BoundesuDocument document) {
        return BoundesuDocumentUtils.validateDocument(document);
    }
    
    /**
     * 生成文档摘要
     */
    public String generateDocumentSummary(BoundesuDocument document, int maxLength) {
        return BoundesuDocumentUtils.generateSummary(document, maxLength);
    }
    
    /**
     * 导出文档为HTML
     */
    public String exportToHtml(BoundesuDocument document) {
        return BoundesuDocumentExporter.exportToHtml(document);
    }
    
    /**
     * 导出文档为Markdown
     */
    public String exportToMarkdown(BoundesuDocument document) {
        return BoundesuDocumentExporter.exportToMarkdown(document);
    }
    
    /**
     * 导出文档为纯文本
     */
    public String exportToText(BoundesuDocument document) {
        return BoundesuDocumentExporter.exportToText(document);
    }
    
    /**
     * 导出文档为DOCX格式
     */
    public void exportToDocx(BoundesuDocument document, Path filePath) throws IOException, InvalidFormatException {
        BoundesuDocxExporter.exportToDocx(document, filePath);
    }
    
    /**
     * 将HTML字符串转换为DOCX文件
     */
    public void convertHtmlToDocx(String htmlContent, Path outputPath) throws IOException {
        HtmlToDocxConverter.convertHtmlToDocx(htmlContent, outputPath);
    }
    
    /**
     * 将HTML文件转换为DOCX文件
     */
    public void convertHtmlFileToDocx(Path htmlFilePath, Path outputPath) throws IOException {
        HtmlToDocxConverter.convertHtmlFileToDocx(htmlFilePath, outputPath);
    }
    
    /**
     * 保存文档到文件
     */
    public void saveDocumentToFile(BoundesuDocument document, String format, Path filePath) throws IOException, InvalidFormatException {
        switch (format.toLowerCase()) {
            case "html":
                String htmlContent = exportToHtml(document);
                BoundesuDocumentExporter.saveToFile(htmlContent, filePath);
                break;
            case "markdown":
            case "md":
                String markdownContent = exportToMarkdown(document);
                BoundesuDocumentExporter.saveToFile(markdownContent, filePath);
                break;
            case "text":
            case "txt":
                String textContent = exportToText(document);
                BoundesuDocumentExporter.saveToFile(textContent, filePath);
                break;
            case "docx":
            case "word":
                exportToDocx(document, filePath);
                break;
            default:
                throw new IllegalArgumentException("不支持的格式: " + format + "。支持的格式: html, markdown, text, docx");
        }
    }
    
    /**
     * 批量处理文档
     */
    public List<BoundesuDocument> batchProcess(List<BoundesuDocument> documents, 
                                              java.util.function.Function<BoundesuDocument, BoundesuDocument> processor) {
        if (documents == null || processor == null) {
            throw new IllegalArgumentException("文档列表和处理器都不能为空");
        }
        
        return documents.stream()
                .map(processor)
                .collect(Collectors.toList());
    }
    
    /**
     * 设置文档状态
     */
    public BoundesuDocument setDocumentStatus(BoundesuDocument document, BoundesuDocument.DocumentStatus status) {
        document.setStatus(status);
        document.updateLastModified();
        return document;
    }
    
    /**
     * 添加文档关键词
     */
    public BoundesuDocument addKeywords(BoundesuDocument document, String... keywords) {
        if (document.getKeywords() == null) {
            document.setKeywords(new ArrayList<>());
        }
        
        for (String keyword : keywords) {
            if (keyword != null && !keyword.trim().isEmpty() && !document.getKeywords().contains(keyword)) {
                document.getKeywords().add(keyword.trim());
            }
        }
        
        document.updateLastModified();
        return document;
    }
    
    /**
     * 设置自定义属性
     */
    public BoundesuDocument setCustomProperty(BoundesuDocument document, String key, Object value) {
        if (document.getCustomProperties() == null) {
            document.setCustomProperties(new HashMap<>());
        }
        
        document.getCustomProperties().put(key, value);
        document.updateLastModified();
        return document;
    }

    /**
     * 获取文档统计信息
     */
    public Map<String, Object> getDocumentStatistics(BoundesuDocument document) {
        Map<String, Object> stats = new HashMap<>();
        
        stats.put("title", document.getTitle());
        stats.put("author", document.getAuthor());
        stats.put("paragraphCount", document.getParagraphs().size());
        stats.put("tableCount", document.getTables().size());
        stats.put("wordCount", document.getWordCount());
        stats.put("characterCount", document.getCharacterCount());
        stats.put("createdAt", document.getCreatedAt().format(DATE_FORMATTER));
        stats.put("updatedAt", document.getLastModified().format(DATE_FORMATTER));
        stats.put("status", document.getStatus().getDescription());
        stats.put("version", document.getVersion());
        stats.put("language", document.getLanguage());
        
        return stats;
    }
}