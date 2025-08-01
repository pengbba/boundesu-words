package com.boundesu.words.sdk.creator.impl;

import com.boundesu.words.sdk.creator.DocumentCreator;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于Apache POI的直接DOCX创建器
 * 参考Aspose.Words的实现方式，提供流畅的API
 */
public class PoiDirectDocxCreator implements DocumentCreator {

    private final XWPFDocument document;
    private String title = "";
    private String author = "";
    private final List<ContentItem> contentItems = new ArrayList<>();

    public PoiDirectDocxCreator() {
        this.document = new XWPFDocument();
        setupDefaultStyles();
    }

    /**
     * 设置默认样式，类似Aspose.Words的默认配置
     */
    private void setupDefaultStyles() {
        // 设置页面边距
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        sectPr.addNewPgMar().setTop(1440); // 1英寸 = 1440 twips
        sectPr.addNewPgMar().setBottom(1440);
        sectPr.addNewPgMar().setLeft(1440);
        sectPr.addNewPgMar().setRight(1440);
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
        contentItems.add(new ContentItem(ContentType.PARAGRAPH, text, 0));
        return this;
    }

    @Override
    public DocumentCreator addHeading(String text, int level) {
        if (level < 1 || level > 6) {
            throw new IllegalArgumentException("标题级别必须在1-6之间");
        }
        contentItems.add(new ContentItem(ContentType.HEADING, text, level));
        return this;
    }

    /**
     * 添加粗体段落
     */
    public PoiDirectDocxCreator addBoldParagraph(String text) {
        contentItems.add(new ContentItem(ContentType.BOLD_PARAGRAPH, text, 0));
        return this;
    }

    /**
     * 添加表格
     */
    public PoiDirectDocxCreator addTable(String[][] data) {
        contentItems.add(new ContentItem(ContentType.TABLE, data, 0));
        return this;
    }

    @Override
    public void createDocument(Path outputPath) throws IOException {
        // 创建新的文档实例来避免重复关闭问题
        XWPFDocument newDoc = createNewDocument();
        try (FileOutputStream out = new FileOutputStream(outputPath.toFile())) {
            newDoc.write(out);
        } finally {
            newDoc.close();
        }
    }

    @Override
    public byte[] createDocumentAsBytes() throws IOException {
        // 创建新的文档实例来避免重复关闭问题
        XWPFDocument newDoc = createNewDocument();
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            newDoc.write(out);
            return out.toByteArray();
        } finally {
            newDoc.close();
        }
    }

    /**
     * 创建新的文档实例
     */
    private XWPFDocument createNewDocument() {
        XWPFDocument newDoc = new XWPFDocument();

        // 设置文档属性
        if (!title.isEmpty()) {
            newDoc.getProperties().getCoreProperties().setTitle(title);
        }
        if (!author.isEmpty()) {
            newDoc.getProperties().getCoreProperties().setCreator(author);
        }

        // 设置页面边距
        CTSectPr sectPr = newDoc.getDocument().getBody().addNewSectPr();
        sectPr.addNewPgMar().setTop(1440);
        sectPr.addNewPgMar().setBottom(1440);
        sectPr.addNewPgMar().setLeft(1440);
        sectPr.addNewPgMar().setRight(1440);

        // 添加标题（如果有）
        if (!title.isEmpty()) {
            XWPFParagraph titlePara = newDoc.createParagraph();
            titlePara.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = titlePara.createRun();
            titleRun.setText(title);
            titleRun.setBold(true);
            titleRun.setFontSize(18);
            titleRun.setFontFamily("宋体");
        }

        // 添加内容项
        for (ContentItem item : contentItems) {
            switch (item.type) {
                case PARAGRAPH:
                    addParagraphToNewDocument(newDoc, (String) item.content);
                    break;
                case BOLD_PARAGRAPH:
                    addBoldParagraphToNewDocument(newDoc, (String) item.content);
                    break;
                case HEADING:
                    addHeadingToNewDocument(newDoc, (String) item.content, item.level);
                    break;
                case TABLE:
                    addTableToNewDocument(newDoc, (String[][]) item.content);
                    break;
            }
        }

        return newDoc;
    }

    private void addParagraphToNewDocument(XWPFDocument doc, String text) {
        XWPFParagraph paragraph = doc.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setFontFamily("宋体");
        run.setFontSize(12);
    }

    private void addBoldParagraphToNewDocument(XWPFDocument doc, String text) {
        XWPFParagraph paragraph = doc.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setBold(true);
        run.setFontFamily("宋体");
        run.setFontSize(12);
    }

    private void addHeadingToNewDocument(XWPFDocument doc, String text, int level) {
        XWPFParagraph heading = doc.createParagraph();
        heading.setStyle("Heading" + level);
        XWPFRun run = heading.createRun();
        run.setText(text);
        run.setBold(true);
        run.setFontFamily("宋体");
        run.setFontSize(16 - level); // 级别越高字体越小
    }

    private void addTableToNewDocument(XWPFDocument doc, String[][] data) {
        if (data.length == 0) return;

        XWPFTable table = doc.createTable(data.length, data[0].length);
        table.setWidth("100%");

        for (int i = 0; i < data.length; i++) {
            XWPFTableRow row = table.getRow(i);
            for (int j = 0; j < data[i].length; j++) {
                XWPFTableCell cell = row.getCell(j);
                cell.setText(data[i][j]);

                // 设置表头样式
                if (i == 0) {
                    XWPFParagraph para = cell.getParagraphs().get(0);
                    XWPFRun run = para.getRuns().get(0);
                    if (run != null) {
                        run.setBold(true);
                    }
                }
            }
        }
    }

    /**
     * 内容项类型
     */
    private enum ContentType {
        PARAGRAPH, BOLD_PARAGRAPH, HEADING, TABLE
    }

    /**
     * 内容项
     */
    private static class ContentItem {
        final ContentType type;
        final Object content;
        final int level;

        ContentItem(ContentType type, Object content, int level) {
            this.type = type;
            this.content = content;
            this.level = level;
        }
    }
}