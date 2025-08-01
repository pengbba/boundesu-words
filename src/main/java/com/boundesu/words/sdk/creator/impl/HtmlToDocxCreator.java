package com.boundesu.words.sdk.creator.impl;

import com.boundesu.words.sdk.creator.DocumentCreator;
import org.apache.poi.xwpf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于HTML转换的DOCX创建器
 * 使用Jsoup解析HTML，然后用POI创建DOCX
 */
public class HtmlToDocxCreator implements DocumentCreator {

    private String title = "";
    private String author = "";
    private final List<String> htmlContent = new ArrayList<>();
    private final StringBuilder htmlBuilder = new StringBuilder();

    public HtmlToDocxCreator() {
        initializeHtml();
    }

    private void initializeHtml() {
        htmlBuilder.append("<!DOCTYPE html>");
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head>");
        htmlBuilder.append("<meta charset='UTF-8'>");
        htmlBuilder.append("<style>");
        htmlBuilder.append("body { font-family: '宋体', SimSun, serif; font-size: 12pt; line-height: 1.5; }");
        htmlBuilder.append("h1 { font-size: 18pt; font-weight: bold; text-align: center; margin-bottom: 20pt; }");
        htmlBuilder.append("h2 { font-size: 16pt; font-weight: bold; margin-top: 15pt; margin-bottom: 10pt; }");
        htmlBuilder.append("h3 { font-size: 14pt; font-weight: bold; margin-top: 12pt; margin-bottom: 8pt; }");
        htmlBuilder.append("h4 { font-size: 13pt; font-weight: bold; margin-top: 10pt; margin-bottom: 6pt; }");
        htmlBuilder.append("h5 { font-size: 12pt; font-weight: bold; margin-top: 8pt; margin-bottom: 4pt; }");
        htmlBuilder.append("h6 { font-size: 11pt; font-weight: bold; margin-top: 6pt; margin-bottom: 3pt; }");
        htmlBuilder.append("p { margin-bottom: 10pt; text-align: justify; }");
        htmlBuilder.append("table { border-collapse: collapse; width: 100%; margin: 10pt 0; }");
        htmlBuilder.append("th, td { border: 1px solid #000; padding: 5pt; text-align: left; }");
        htmlBuilder.append("th { background-color: #f0f0f0; font-weight: bold; }");
        htmlBuilder.append(".bold { font-weight: bold; }");
        htmlBuilder.append("</style>");
        htmlBuilder.append("</head>");
        htmlBuilder.append("<body>");
    }

    @Override
    public DocumentCreator setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public DocumentCreator setAuthor(String author) {
        this.author = author;
        return this;
    }

    @Override
    public DocumentCreator addParagraph(String text) {
        htmlContent.add("<p>" + escapeHtml(text) + "</p>");
        return this;
    }

    @Override
    public DocumentCreator addHeading(String text, int level) {
        if (level < 1 || level > 6) {
            throw new IllegalArgumentException("标题级别必须在1-6之间");
        }
        htmlContent.add("<h" + level + ">" + escapeHtml(text) + "</h" + level + ">");
        return this;
    }

    /**
     * 添加粗体段落
     */
    public HtmlToDocxCreator addBoldParagraph(String text) {
        htmlContent.add("<p class='bold'>" + escapeHtml(text) + "</p>");
        return this;
    }

    /**
     * 添加HTML表格
     */
    public HtmlToDocxCreator addTable(String[][] data) {
        if (data.length == 0) return this;

        StringBuilder tableHtml = new StringBuilder();
        tableHtml.append("<table>");

        // 添加表头
        tableHtml.append("<thead><tr>");
        for (String header : data[0]) {
            tableHtml.append("<th>").append(escapeHtml(header)).append("</th>");
        }
        tableHtml.append("</tr></thead>");

        // 添加表体
        tableHtml.append("<tbody>");
        for (int i = 1; i < data.length; i++) {
            tableHtml.append("<tr>");
            for (String cell : data[i]) {
                tableHtml.append("<td>").append(escapeHtml(cell)).append("</td>");
            }
            tableHtml.append("</tr>");
        }
        tableHtml.append("</tbody>");
        tableHtml.append("</table>");

        htmlContent.add(tableHtml.toString());
        return this;
    }

    /**
     * 添加列表
     */
    public HtmlToDocxCreator addList(List<String> items, boolean ordered) {
        String tag = ordered ? "ol" : "ul";
        StringBuilder listHtml = new StringBuilder();
        listHtml.append("<").append(tag).append(">");
        for (String item : items) {
            listHtml.append("<li>").append(escapeHtml(item)).append("</li>");
        }
        listHtml.append("</").append(tag).append(">");
        htmlContent.add(listHtml.toString());
        return this;
    }

    /**
     * 添加自定义HTML内容
     */
    public HtmlToDocxCreator addCustomHtml(String html) {
        htmlContent.add(html);
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

        // 解析HTML并转换为Word内容
        String completeHtml = buildCompleteHtml();
        Document htmlDoc = Jsoup.parse(completeHtml);

        // 处理body中的所有元素
        Element body = htmlDoc.body();
        if (body != null) {
            processHtmlElements(document, body.children());
        }

        return document;
    }

    /**
     * 处理HTML元素并转换为Word内容
     */
    private void processHtmlElements(XWPFDocument document, Elements elements) {
        for (Element element : elements) {
            String tagName = element.tagName().toLowerCase();

            switch (tagName) {
                case "h1":
                case "h2":
                case "h3":
                case "h4":
                case "h5":
                case "h6":
                    addHeadingToDocument(document, element.text(), Integer.parseInt(tagName.substring(1)));
                    break;
                case "p":
                    boolean isBold = element.hasClass("bold") || element.select("strong, b").size() > 0;
                    addParagraphToDocument(document, element.text(), isBold);
                    break;
                case "table":
                    addTableToDocument(document, element);
                    break;
                case "ul":
                case "ol":
                    addListToDocument(document, element, tagName.equals("ol"));
                    break;
                default:
                    // 对于其他元素，提取文本内容作为段落
                    if (!element.text().trim().isEmpty()) {
                        addParagraphToDocument(document, element.text(), false);
                    }
                    break;
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
        Elements rows = tableElement.select("tr");
        if (rows.isEmpty()) return;

        // 计算列数
        int maxCols = 0;
        for (Element row : rows) {
            Elements cells = row.select("th, td");
            maxCols = Math.max(maxCols, cells.size());
        }

        XWPFTable table = document.createTable(rows.size(), maxCols);
        table.setWidth("100%");

        for (int i = 0; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements cells = row.select("th, td");
            XWPFTableRow tableRow = table.getRow(i);

            for (int j = 0; j < cells.size() && j < maxCols; j++) {
                XWPFTableCell cell = tableRow.getCell(j);
                cell.setText(cells.get(j).text());

                // 设置表头样式
                if (cells.get(j).tagName().equals("th")) {
                    XWPFParagraph para = cell.getParagraphs().get(0);
                    if (!para.getRuns().isEmpty()) {
                        para.getRuns().get(0).setBold(true);
                    }
                }
            }
        }
    }

    private void addListToDocument(XWPFDocument document, Element listElement, boolean ordered) {
        Elements items = listElement.select("li");
        for (int i = 0; i < items.size(); i++) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();

            String prefix = ordered ? (i + 1) + ". " : "• ";
            run.setText(prefix + items.get(i).text());
            run.setFontFamily("宋体");
            run.setFontSize(12);
        }
    }

    /**
     * 构建完整的HTML文档
     */
    private String buildCompleteHtml() {
        StringBuilder completeHtml = new StringBuilder(htmlBuilder);

        // 添加标题
        if (!title.isEmpty()) {
            completeHtml.append("<h1>").append(escapeHtml(title)).append("</h1>");
        }

        // 添加所有内容
        for (String content : htmlContent) {
            completeHtml.append(content);
        }

        completeHtml.append("</body></html>");

        return completeHtml.toString();
    }

    /**
     * HTML转义
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
        return buildCompleteHtml();
    }

    /**
     * 验证HTML格式
     */
    public boolean validateHtml() {
        try {
            Document doc = Jsoup.parse(buildCompleteHtml());
            return doc != null;
        } catch (Exception e) {
            return false;
        }
    }
}