package com.boundesu.words.html.creator;

import com.boundesu.words.common.exception.BoundesuWordsException;
import com.boundesu.words.common.creator.DocumentCreator;
import com.boundesu.words.html.converter.HtmlToDocxConverter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * 基于HTML转换的DOCX文档创建器
 * 实现DocumentCreator接口，通过构建HTML内容然后转换为DOCX
 * 
 * @author Boundesu Team
 * @version 1.0.0
 */
public class HtmlToDocxCreator implements DocumentCreator {
    
    private static final Logger log = LoggerFactory.getLogger(HtmlToDocxCreator.class);
    
    private final StringBuilder htmlContent;
    private final HtmlToDocxConverter converter;
    private String title = "";
    private String author = "";
    private boolean hasHeader = false;
    private boolean hasFooter = false;
    private boolean hasPageNumbers = false;
    
    public HtmlToDocxCreator() {
        this.htmlContent = new StringBuilder();
        this.converter = new HtmlToDocxConverter();
        initializeHtml();
    }
    
    /**
     * 初始化HTML结构
     */
    private void initializeHtml() {
        htmlContent.append("<!DOCTYPE html>\n");
        htmlContent.append("<html>\n");
        htmlContent.append("<head>\n");
        htmlContent.append("<meta charset=\"UTF-8\">\n");
        htmlContent.append("<title></title>\n");
        htmlContent.append("<style>\n");
        htmlContent.append("body { font-family: '宋体', SimSun, serif; font-size: 12pt; line-height: 1.5; }\n");
        htmlContent.append("h1 { font-size: 18pt; font-weight: bold; margin: 12pt 0; }\n");
        htmlContent.append("h2 { font-size: 16pt; font-weight: bold; margin: 10pt 0; }\n");
        htmlContent.append("h3 { font-size: 14pt; font-weight: bold; margin: 8pt 0; }\n");
        htmlContent.append("h4, h5, h6 { font-size: 12pt; font-weight: bold; margin: 6pt 0; }\n");
        htmlContent.append("p { margin: 6pt 0; }\n");
        htmlContent.append("table { border-collapse: collapse; width: 100%; margin: 6pt 0; }\n");
        htmlContent.append("th, td { border: 1px solid #000; padding: 4pt; text-align: left; }\n");
        htmlContent.append("th { background-color: #f0f0f0; font-weight: bold; }\n");
        htmlContent.append("ul, ol { margin: 6pt 0; padding-left: 20pt; }\n");
        htmlContent.append("li { margin: 2pt 0; }\n");
        htmlContent.append("</style>\n");
        htmlContent.append("</head>\n");
        htmlContent.append("<body>\n");
    }
    
    @Override
    public DocumentCreator setTitle(String title) {
        this.title = title;
        // 更新HTML中的title标签
        String currentHtml = htmlContent.toString();
        String updatedHtml = currentHtml.replaceFirst(
            "<title></title>", 
            "<title>" + escapeHtml(title) + "</title>"
        );
        htmlContent.setLength(0);
        htmlContent.append(updatedHtml);
        
        log.debug("设置文档标题: {}", title);
        return this;
    }
    
    @Override
    public DocumentCreator setAuthor(String author) {
        this.author = author;
        log.debug("设置文档作者: {}", author);
        return this;
    }
    
    @Override
    public DocumentCreator addParagraph(String text) {
        htmlContent.append("<p>").append(escapeHtml(text)).append("</p>\n");
        log.debug("添加段落: {}", text.length() > 50 ? text.substring(0, 50) + "..." : text);
        return this;
    }
    
    @Override
    public DocumentCreator addHeading(String text, int level) {
        if (level < 1) level = 1;
        if (level > 6) level = 6;
        
        htmlContent.append("<h").append(level).append(">")
                  .append(escapeHtml(text))
                  .append("</h").append(level).append(">\n");
        
        log.debug("添加{}级标题: {}", level, text);
        return this;
    }
    
    public DocumentCreator addTable(String[][] data) {
        if (data == null || data.length == 0) {
            log.warn("表格数据为空，跳过添加");
            return this;
        }
        
        htmlContent.append("<table>\n");
        
        // 添加表头（第一行作为表头）
        if (data.length > 0) {
            htmlContent.append("<thead>\n<tr>\n");
            for (String cell : data[0]) {
                htmlContent.append("<th>").append(escapeHtml(cell != null ? cell : "")).append("</th>\n");
            }
            htmlContent.append("</tr>\n</thead>\n");
        }
        
        // 添加表体
        if (data.length > 1) {
            htmlContent.append("<tbody>\n");
            for (int i = 1; i < data.length; i++) {
                htmlContent.append("<tr>\n");
                for (String cell : data[i]) {
                    htmlContent.append("<td>").append(escapeHtml(cell != null ? cell : "")).append("</td>\n");
                }
                htmlContent.append("</tr>\n");
            }
            htmlContent.append("</tbody>\n");
        }
        
        htmlContent.append("</table>\n");
        
        log.debug("添加表格: {}行 x {}列", data.length, data[0].length);
        return this;
    }
    
    public DocumentCreator addList(List<String> items, boolean ordered) {
        if (items == null || items.isEmpty()) {
            log.warn("列表项为空，跳过添加");
            return this;
        }
        
        String listTag = ordered ? "ol" : "ul";
        htmlContent.append("<").append(listTag).append(">\n");
        
        for (String item : items) {
            htmlContent.append("<li>").append(escapeHtml(item != null ? item : "")).append("</li>\n");
        }
        
        htmlContent.append("</").append(listTag).append(">\n");
        
        log.debug("添加{}列表: {}项", ordered ? "有序" : "无序", items.size());
        return this;
    }
    
    public DocumentCreator addPageBreak() {
        // HTML中使用CSS的page-break-after属性
        htmlContent.append("<div style=\"page-break-after: always;\"></div>\n");
        log.debug("添加分页符");
        return this;
    }
    
    public DocumentCreator addLineBreak() {
        htmlContent.append("<br>\n");
        log.debug("添加换行符");
        return this;
    }
    
    @Override
    public DocumentCreator setHeader(String headerText) {
        this.hasHeader = true;
        // 在HTML中添加页眉样式
        String headerStyle = "@page { @top-center { content: '" + escapeHtml(headerText) + "'; } }";
        insertStyleRule(headerStyle);
        log.debug("设置页眉: {}", headerText);
        return this;
    }
    
    @Override
    public DocumentCreator setHeaderWithImage(String headerText, String imagePath) {
        return setHeaderWithImage(headerText, imagePath, 100, 50); // 默认尺寸
    }
    
    @Override
    public DocumentCreator setHeaderWithImage(String headerText, String imagePath, int width, int height) {
        this.hasHeader = true;
        // 在HTML中添加包含图片的页眉样式
        String imageHtml = "<img src='" + imagePath + "' style='width:" + width + "px;height:" + height + "px;vertical-align:middle;' alt='Header Image'/>";
        String headerContent = imageHtml;
        if (headerText != null && !headerText.trim().isEmpty()) {
            headerContent += " " + escapeHtml(headerText);
        }
        String headerStyle = "@page { @top-center { content: '" + headerContent + "'; } }";
        insertStyleRule(headerStyle);
        log.debug("设置带图片的页眉: {} (图片: {}, 尺寸: {}x{})", headerText, imagePath, width, height);
        return this;
    }
    
    @Override
    public DocumentCreator setFooter(String footerText) {
        this.hasFooter = true;
        // 在HTML中添加页脚样式
        String footerStyle = "@page { @bottom-center { content: '" + escapeHtml(footerText) + "'; } }";
        insertStyleRule(footerStyle);
        log.debug("设置页脚: {}", footerText);
        return this;
    }
    
    @Override
    public DocumentCreator setFooterWithImage(String footerText, String imagePath) {
        return setFooterWithImage(footerText, imagePath, 100, 50); // 默认尺寸
    }
    
    @Override
    public DocumentCreator setFooterWithImage(String footerText, String imagePath, int width, int height) {
        this.hasFooter = true;
        // 在HTML中添加包含图片的页脚样式
        String imageHtml = "<img src='" + imagePath + "' style='width:" + width + "px;height:" + height + "px;vertical-align:middle;' alt='Footer Image'/>";
        String footerContent = imageHtml;
        if (footerText != null && !footerText.trim().isEmpty()) {
            footerContent += " " + escapeHtml(footerText);
        }
        String footerStyle = "@page { @bottom-center { content: '" + footerContent + "'; } }";
        insertStyleRule(footerStyle);
        log.debug("设置带图片的页脚: {} (图片: {}, 尺寸: {}x{})", footerText, imagePath, width, height);
        return this;
    }
    
    @Override
    public DocumentCreator setPageNumberEnabled(boolean enabled) {
        this.hasPageNumbers = enabled;
        if (enabled) {
            // 在HTML中添加页码样式
            String pageNumberStyle = "@page { @bottom-right { content: 'Page ' counter(page); } }";
            insertStyleRule(pageNumberStyle);
            log.debug("启用页码");
        }
        return this;
    }
    
    /**
     * 在HTML样式中插入新的CSS规则
     */
    private void insertStyleRule(String rule) {
        String currentHtml = htmlContent.toString();
        String updatedHtml = currentHtml.replace("</style>", rule + "\n</style>");
        htmlContent.setLength(0);
        htmlContent.append(updatedHtml);
    }
    
    @Override
    public void createDocument(Path outputPath) throws IOException {
        try {
            byte[] documentBytes = createDocumentAsBytes();
            Files.write(outputPath, documentBytes);
            log.info("文档已保存到: {}", outputPath);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new IOException("创建文档失败", e);
        }
    }
    
    @Override
    public byte[] createDocumentAsBytes() throws IOException {
        try {
            return saveToBytes();
        } catch (Exception e) {
            throw new IOException("创建文档字节数组失败", e);
        }
    }
    
    public XWPFDocument createDocument() throws BoundesuWordsException {
        try {
            // 完成HTML结构
            finalizeHtml();
            
            // 转换为DOCX
            String completeHtml = htmlContent.toString();
            log.debug("开始将HTML转换为DOCX文档");
            
            XWPFDocument document = converter.convertHtmlToDocx(completeHtml);
            
            // 设置文档属性
            if (!title.isEmpty()) {
                document.getProperties().getCoreProperties().setTitle(title);
            }
            if (!author.isEmpty()) {
                document.getProperties().getCoreProperties().setCreator(author);
            }
            
            log.info("HTML到DOCX文档创建完成");
            return document;
            
        } catch (Exception e) {
            log.error("创建DOCX文档失败", e);
            throw new BoundesuWordsException("DOCUMENT_CREATE_ERROR", "创建DOCX文档失败", e);
        }
    }
    
    public void saveToFile(String filePath) throws BoundesuWordsException {
        try (XWPFDocument document = createDocument()) {
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                document.write(baos);
                
                // 这里应该将字节数组写入文件
                // 为了简化，我们使用FileUtils（如果存在）
                java.nio.file.Files.write(
                    java.nio.file.Paths.get(filePath), 
                    baos.toByteArray()
                );
                
                log.info("文档已保存到: {}", filePath);
            }
        } catch (IOException e) {
            log.error("保存文档到文件失败: {}", filePath, e);
            throw new BoundesuWordsException("FILE_SAVE_ERROR", "保存文档到文件失败: " + filePath, e);
        }
    }
    
    public byte[] saveToBytes() throws BoundesuWordsException {
        try (XWPFDocument document = createDocument()) {
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                document.write(baos);
                byte[] result = baos.toByteArray();
                log.debug("文档已转换为字节数组，大小: {} bytes", result.length);
                return result;
            }
        } catch (IOException e) {
            log.error("将文档转换为字节数组失败", e);
            throw new BoundesuWordsException("BYTES_CONVERT_ERROR", "将文档转换为字节数组失败", e);
        }
    }
    
    /**
     * 完成HTML结构
     */
    private void finalizeHtml() {
        htmlContent.append("</body>\n</html>");
    }
    
    /**
     * 转义HTML特殊字符
     */
    private String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;")
                  .replace("<", "&lt;")
                  .replace(">", "&gt;")
                  .replace("\"", "&quot;")
                  .replace("'", "&#39;");
    }
    
    /**
     * 获取当前HTML内容（用于调试）
     */
    public String getHtmlContent() {
        return htmlContent.toString();
    }
    
    /**
     * 清空所有内容
     */
    public HtmlToDocxCreator clear() {
        htmlContent.setLength(0);
        title = "";
        author = "";
        hasHeader = false;
        hasFooter = false;
        hasPageNumbers = false;
        initializeHtml();
        log.debug("HTML创建器已清空");
        return this;
    }
    
    /**
     * 添加自定义HTML内容
     */
    public HtmlToDocxCreator addCustomHtml(String html) {
        if (html != null && !html.trim().isEmpty()) {
            htmlContent.append(html).append("\n");
            log.debug("添加自定义HTML内容: {} 字符", html.length());
        }
        return this;
    }
    
    /**
     * 添加图片（通过HTML img标签）
     */
    public HtmlToDocxCreator addImage(String imagePath, String altText) {
        htmlContent.append("<img src=\"").append(escapeHtml(imagePath))
                  .append("\" alt=\"").append(escapeHtml(altText != null ? altText : ""))
                  .append("\" style=\"max-width: 100%; height: auto;\">\n");
        log.debug("添加图片: {}", imagePath);
        return this;
    }
    
    /**
     * 添加链接
     */
    public HtmlToDocxCreator addLink(String url, String text) {
        htmlContent.append("<a href=\"").append(escapeHtml(url))
                  .append("\">")
                  .append(escapeHtml(text != null ? text : url))
                  .append("</a>\n");
        log.debug("添加链接: {} -> {}", text, url);
        return this;
    }
}