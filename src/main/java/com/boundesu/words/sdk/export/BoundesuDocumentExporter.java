package com.boundesu.words.sdk.export;

import com.boundesu.words.sdk.model.*;
import com.boundesu.words.sdk.util.BoundesuDocumentUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Boundesu Words JDK17 SDK - 文档导出器
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class BoundesuDocumentExporter {
    
    /**
     * 导出为HTML格式
     */
    public static String exportToHtml(BoundesuDocument document) {
        if (document == null) {
            throw new IllegalArgumentException("文档不能为空");
        }
        
        StringBuilder html = new StringBuilder();
        
        // HTML头部
        html.append("<!DOCTYPE html>\n")
            .append("<html lang=\"").append(document.getLanguage() != null ? document.getLanguage() : "zh-CN").append("\">\n")
            .append("<head>\n")
            .append("    <meta charset=\"UTF-8\">\n")
            .append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n")
            .append("    <title>").append(escapeHtml(document.getTitle())).append("</title>\n")
            .append("    <meta name=\"author\" content=\"").append(escapeHtml(document.getAuthor())).append("\">\n");
        
        if (document.getDescription() != null) {
            html.append("    <meta name=\"description\" content=\"").append(escapeHtml(document.getDescription())).append("\">\n");
        }
        
        if (document.getKeywords() != null && !document.getKeywords().isEmpty()) {
            html.append("    <meta name=\"keywords\" content=\"").append(String.join(", ", document.getKeywords())).append("\">\n");
        }
        
        // CSS样式
        html.append("    <style>\n")
            .append("        body { font-family: 'Microsoft YaHei', Arial, sans-serif; line-height: 1.6; margin: 40px; background-color: #f9f9f9; }\n")
            .append("        .document { max-width: 800px; margin: 0 auto; background: white; padding: 40px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }\n")
            .append("        .header { text-align: center; border-bottom: 2px solid #333; padding-bottom: 20px; margin-bottom: 30px; }\n")
            .append("        .title { font-size: 28px; font-weight: bold; color: #333; margin-bottom: 10px; }\n")
            .append("        .meta { color: #666; font-size: 14px; }\n")
            .append("        .paragraph { margin-bottom: 15px; text-align: justify; }\n")
            .append("        .heading { font-weight: bold; margin-top: 25px; margin-bottom: 15px; }\n")
            .append("        .heading-1 { font-size: 24px; color: #333; }\n")
            .append("        .heading-2 { font-size: 20px; color: #444; }\n")
            .append("        .heading-3 { font-size: 18px; color: #555; }\n")
            .append("        .table { width: 100%; border-collapse: collapse; margin: 20px 0; }\n")
            .append("        .table th, .table td { border: 1px solid #ddd; padding: 8px; text-align: left; }\n")
            .append("        .table th { background-color: #f2f2f2; font-weight: bold; }\n")
            .append("        .footer { text-align: center; border-top: 1px solid #ddd; padding-top: 20px; margin-top: 30px; color: #666; font-size: 12px; }\n")
            .append("        .statistics { background-color: #f8f9fa; padding: 15px; border-radius: 5px; margin: 20px 0; }\n")
            .append("        .statistics h3 { margin-top: 0; color: #495057; }\n")
            .append("    </style>\n")
            .append("</head>\n")
            .append("<body>\n")
            .append("    <div class=\"document\">\n");
        
        // 文档头部
        html.append("        <div class=\"header\">\n")
            .append("            <div class=\"title\">").append(escapeHtml(document.getTitle())).append("</div>\n")
            .append("            <div class=\"meta\">\n");
        
        if (document.getAuthor() != null) {
            html.append("                作者: ").append(escapeHtml(document.getAuthor())).append("<br>\n");
        }
        
        if (document.getCreatedAt() != null) {
            html.append("                创建时间: ").append(BoundesuDocumentUtils.formatDateTime(document.getCreatedAt())).append("<br>\n");
        }
        
        if (document.getLastModified() != null) {
            html.append("                最后修改: ").append(BoundesuDocumentUtils.formatDateTime(document.getLastModified())).append("<br>\n");
        }
        
        if (document.getVersion() != null) {
            html.append("                版本: ").append(escapeHtml(document.getVersion())).append("\n");
        }
        
        html.append("            </div>\n")
            .append("        </div>\n");
        
        // 页眉
        if (document.getHeader() != null && document.getHeader().getText() != null) {
            html.append("        <div class=\"page-header\">\n")
                .append("            ").append(document.getHeader().getText()).append("\n")
                .append("        </div>\n");
        }
        
        // 文档统计信息
        BoundesuDocumentUtils.DocumentStatistics stats = BoundesuDocumentUtils.getDocumentStatistics(document);
        html.append("        <div class=\"statistics\">\n")
            .append("            <h3>文档统计</h3>\n")
            .append("            <p>字数: ").append(stats.getWordCount()).append(" | ")
            .append("字符数: ").append(stats.getCharacterCount()).append(" | ")
            .append("段落数: ").append(stats.getParagraphCount()).append(" | ")
            .append("表格数: ").append(stats.getTableCount()).append(" | ")
            .append("图片数: ").append(stats.getImageCount()).append("</p>\n")
            .append("        </div>\n");
        
        // 文档内容
        html.append("        <div class=\"content\">\n");
        
        // 段落内容
        if (document.getParagraphs() != null) {
            for (BoundesuParagraph paragraph : document.getParagraphs()) {
                html.append(formatParagraphToHtml(paragraph));
            }
        }
        
        // 表格内容
        if (document.getTables() != null) {
            for (BoundesuTable table : document.getTables()) {
                html.append(formatTableToHtml(table));
            }
        }
        
        html.append("        </div>\n");
        
        // 页脚
        if (document.getFooter() != null && document.getFooter().getText() != null) {
            html.append("        <div class=\"footer\">\n")
                .append("            ").append(document.getFooter().getText()).append("\n")
                .append("        </div>\n");
        }
        
        html.append("    </div>\n")
            .append("</body>\n")
            .append("</html>");
        
        return html.toString();
    }
    
    /**
     * 导出为Markdown格式
     */
    public static String exportToMarkdown(BoundesuDocument document) {
        if (document == null) {
            throw new IllegalArgumentException("文档不能为空");
        }
        
        StringBuilder md = new StringBuilder();
        
        // 文档标题
        md.append("# ").append(document.getTitle()).append("\n\n");
        
        // 文档元信息
        if (document.getAuthor() != null || document.getCreatedAt() != null || document.getVersion() != null) {
            md.append("---\n");
            if (document.getAuthor() != null) {
                md.append("**作者**: ").append(document.getAuthor()).append("  \n");
            }
            if (document.getCreatedAt() != null) {
                md.append("**创建时间**: ").append(BoundesuDocumentUtils.formatDateTime(document.getCreatedAt())).append("  \n");
            }
            if (document.getLastModified() != null) {
                md.append("**最后修改**: ").append(BoundesuDocumentUtils.formatDateTime(document.getLastModified())).append("  \n");
            }
            if (document.getVersion() != null) {
                md.append("**版本**: ").append(document.getVersion()).append("  \n");
            }
            md.append("---\n\n");
        }
        
        // 文档描述
        if (document.getDescription() != null) {
            md.append("> ").append(document.getDescription()).append("\n\n");
        }
        
        // 段落内容
        if (document.getParagraphs() != null) {
            for (BoundesuParagraph paragraph : document.getParagraphs()) {
                md.append(formatParagraphToMarkdown(paragraph));
            }
        }
        
        // 表格内容
        if (document.getTables() != null) {
            for (BoundesuTable table : document.getTables()) {
                md.append(formatTableToMarkdown(table));
            }
        }
        
        return md.toString();
    }
    
    /**
     * 导出为纯文本格式
     */
    public static String exportToText(BoundesuDocument document) {
        if (document == null) {
            throw new IllegalArgumentException("文档不能为空");
        }
        
        StringBuilder text = new StringBuilder();
        
        // 文档标题
        text.append(document.getTitle()).append("\n");
        for (int i = 0; i < document.getTitle().length(); i++) {
            text.append("=");
        }
        text.append("\n\n");
        
        // 文档元信息
        if (document.getAuthor() != null) {
            text.append("作者: ").append(document.getAuthor()).append("\n");
        }
        if (document.getCreatedAt() != null) {
            text.append("创建时间: ").append(BoundesuDocumentUtils.formatDateTime(document.getCreatedAt())).append("\n");
        }
        if (document.getLastModified() != null) {
            text.append("最后修改: ").append(BoundesuDocumentUtils.formatDateTime(document.getLastModified())).append("\n");
        }
        if (document.getVersion() != null) {
            text.append("版本: ").append(document.getVersion()).append("\n");
        }
        text.append("\n");
        
        // 文档描述
        if (document.getDescription() != null) {
            text.append(document.getDescription()).append("\n\n");
        }
        
        // 段落内容
        if (document.getParagraphs() != null) {
            for (BoundesuParagraph paragraph : document.getParagraphs()) {
                if (paragraph.getText() != null) {
                    String cleanText = BoundesuDocumentUtils.stripHtmlTags(paragraph.getText());
                    text.append(cleanText).append("\n\n");
                }
            }
        }
        
        // 表格内容
        if (document.getTables() != null) {
            for (BoundesuTable table : document.getTables()) {
                text.append(formatTableToText(table));
            }
        }
        
        return text.toString();
    }
    
    /**
     * 保存文档到文件
     */
    public static void saveToFile(String content, Path filePath) throws IOException {
        Files.write(filePath, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
    
    // 私有辅助方法
    
    private static String formatParagraphToHtml(BoundesuParagraph paragraph) {
        if (paragraph.getText() == null) return "";
        
        StringBuilder html = new StringBuilder();
        String cssClass = "paragraph";
        
        if (paragraph.getStyle() != null && paragraph.getStyle().isHeading()) {
            int level = paragraph.getStyle().getHeadingLevel();
            cssClass = "heading heading-" + Math.min(level, 3);
        }
        
        html.append("            <div class=\"").append(cssClass).append("\">")
            .append(paragraph.getText())
            .append("</div>\n");
        
        return html.toString();
    }
    
    private static String formatParagraphToMarkdown(BoundesuParagraph paragraph) {
        if (paragraph.getText() == null) return "";
        
        StringBuilder md = new StringBuilder();
        String cleanText = BoundesuDocumentUtils.stripHtmlTags(paragraph.getText());
        
        if (paragraph.getStyle() != null && paragraph.getStyle().isHeading()) {
            int level = Math.min(paragraph.getStyle().getHeadingLevel(), 6);
            for (int i = 0; i < level; i++) {
                md.append("#");
            }
            md.append(" ");
        }
        
        md.append(cleanText).append("\n\n");
        return md.toString();
    }
    
    private static String formatTableToHtml(BoundesuTable table) {
        StringBuilder html = new StringBuilder();
        html.append("            <table class=\"table\">\n");
        
        // 这里简化处理，实际应该根据表格数据结构来生成
        html.append("                <tr><th>列1</th><th>列2</th><th>列3</th></tr>\n");
        html.append("                <tr><td>数据1</td><td>数据2</td><td>数据3</td></tr>\n");
        
        html.append("            </table>\n");
        return html.toString();
    }
    
    private static String formatTableToMarkdown(BoundesuTable table) {
        StringBuilder md = new StringBuilder();
        
        // 简化处理
        md.append("| 列1 | 列2 | 列3 |\n");
        md.append("|-----|-----|-----|\n");
        md.append("| 数据1 | 数据2 | 数据3 |\n\n");
        
        return md.toString();
    }
    
    private static String formatTableToText(BoundesuTable table) {
        StringBuilder text = new StringBuilder();
        
        // 简化处理
        text.append("表格:\n");
        text.append("列1\t列2\t列3\n");
        text.append("数据1\t数据2\t数据3\n\n");
        
        return text.toString();
    }
    
    private static String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;")
                  .replace("<", "&lt;")
                  .replace(">", "&gt;")
                  .replace("\"", "&quot;")
                  .replace("'", "&#39;");
    }
}