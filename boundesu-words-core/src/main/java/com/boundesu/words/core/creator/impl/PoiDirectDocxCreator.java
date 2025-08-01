package com.boundesu.words.core.creator.impl;

import com.boundesu.words.common.constants.DocxConstants;
import com.boundesu.words.common.util.DocumentValidator;
import com.boundesu.words.core.creator.DocumentCreator;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 * 直接使用Apache POI创建DOCX文档的实现类
 * 提供最佳性能和完整功能支持
 * 
 * @author Boundesu Team
 * @version 1.0.0
 */
public class PoiDirectDocxCreator implements DocumentCreator {

    private final XWPFDocument document;
    private String documentTitle;
    private String documentAuthor;
    private String headerText;
    private String footerText;
    private boolean pageNumberEnabled;

    /**
     * 构造函数，初始化POI文档
     */
    public PoiDirectDocxCreator() {
        this.document = new XWPFDocument();
        this.documentTitle = "";
        this.documentAuthor = "";
        this.headerText = "";
        this.footerText = "";
        this.pageNumberEnabled = false;
        
        // 初始化文档属性
        try {
            initializeDocumentProperties();
        } catch (InvalidFormatException e) {
            throw new RuntimeException("初始化文档属性失败: " + e.getMessage(), e);
        }
    }

    /**
     * 初始化文档属性
     */
    private void initializeDocumentProperties() throws InvalidFormatException {
        // 设置文档的核心属性
        org.apache.poi.ooxml.POIXMLProperties.CoreProperties coreProperties = document.getProperties().getCoreProperties();
        coreProperties.setCreated(LocalDateTime.now().toString());
        coreProperties.setCreator(DocxConstants.DEFAULT_AUTHOR);
        coreProperties.setLastModifiedByUser(DocxConstants.DEFAULT_AUTHOR);
        coreProperties.setModified(LocalDateTime.now().toString());
    }

    @Override
    public void createDocument(Path outputPath) throws IOException {
        try {
            DocumentValidator.validateFilePath(outputPath);
        } catch (IllegalArgumentException e) {
            throw new IOException("文件路径验证失败: " + e.getMessage(), e);
        }
        
        try {
            finalizeDocumentProperties();
        } catch (InvalidFormatException e) {
            throw new IOException("文档属性设置失败: " + e.getMessage(), e);
        }
        
        // 应用页头页脚设置
        applyHeaderFooter();
        
        try (FileOutputStream out = new FileOutputStream(outputPath.toFile())) {
            document.write(out);
        } catch (IllegalArgumentException e) {
            throw new IOException("文档标题验证失败: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] createDocumentAsBytes() throws IOException {
        try {
            finalizeDocumentProperties();
        } catch (InvalidFormatException e) {
            throw new IOException("文档属性设置失败: " + e.getMessage(), e);
        }
        
        // 应用页头页脚设置
        applyHeaderFooter();
        
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            document.write(out);
            return out.toByteArray();
        }
    }

    @Override
    public DocumentCreator setTitle(String title) {
        try {
            DocumentValidator.validateDocumentTitle(title);
            this.documentTitle = title;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("无效的文档标题: " + title, e);
        }
        return this;
    }

    @Override
    public DocumentCreator setAuthor(String author) {
        if (author != null && !author.trim().isEmpty()) {
            this.documentAuthor = author.trim();
        } else {
            this.documentAuthor = DocxConstants.DEFAULT_AUTHOR;
        }
        return this;
    }

    @Override
    public DocumentCreator addParagraph(String text) {
        if (text == null) {
            text = "";
        }
        
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setFontFamily(DocxConstants.DEFAULT_FONT_FAMILY);
        run.setFontSize(DocxConstants.DEFAULT_FONT_SIZE);
        
        return this;
    }

    @Override
    public DocumentCreator addHeading(String text, int level) {
        try {
            DocumentValidator.validateHeadingLevel(level);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("无效的标题级别: " + level + 
                    "。有效范围: 1-" + DocxConstants.MAX_HEADING_LEVEL, e);
        }
        
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("标题文本不能为空");
        }
        
        XWPFParagraph heading = document.createParagraph();
        heading.setStyle("Heading" + level);
        
        XWPFRun run = heading.createRun();
        run.setText(text.trim());
        run.setBold(true);
        run.setFontFamily(DocxConstants.DEFAULT_FONT_FAMILY);
        
        // 根据级别设置字体大小
        int fontSize = DocxConstants.DEFAULT_FONT_SIZE + (DocxConstants.MAX_HEADING_LEVEL - level) * 2;
        run.setFontSize(Math.max(fontSize, DocxConstants.MIN_FONT_SIZE));
        
        return this;
    }

    /**
     * 添加表格
     *
     * @param headers 表头
     * @param rows    表格数据行
     * @return 当前创建器实例
     */
    public PoiDirectDocxCreator addTable(String[] headers, String[][] rows) {
        if (headers == null || headers.length == 0) {
            throw new IllegalArgumentException("表头不能为空");
        }
        
        if (!DocumentValidator.isValidTableSize(rows != null ? rows.length : 0, headers.length)) {
            throw new IllegalArgumentException("表格大小超出限制");
        }
        
        XWPFTable table = document.createTable();
        
        // 创建表头
        XWPFTableRow headerRow = table.getRow(0);
        for (int i = 0; i < headers.length; i++) {
            if (i == 0) {
                headerRow.getCell(0).setText(headers[i]);
            } else {
                headerRow.addNewTableCell().setText(headers[i]);
            }
            
            // 设置表头样式
            XWPFTableCell cell = headerRow.getCell(i);
            cell.setColor(DocxConstants.TABLE_HEADER_COLOR);
            
            XWPFParagraph paragraph = cell.getParagraphs().get(0);
            XWPFRun run = paragraph.getRuns().get(0);
            if (run != null) {
                run.setBold(true);
                run.setFontFamily(DocxConstants.DEFAULT_FONT_FAMILY);
                run.setFontSize(DocxConstants.DEFAULT_FONT_SIZE);
            }
        }
        
        // 添加数据行
        if (rows != null) {
            for (String[] row : rows) {
                XWPFTableRow tableRow = table.createRow();
                for (int i = 0; i < Math.min(row.length, headers.length); i++) {
                    tableRow.getCell(i).setText(row[i] != null ? row[i] : "");
                    
                    // 设置数据行样式
                    XWPFTableCell cell = tableRow.getCell(i);
                    XWPFParagraph paragraph = cell.getParagraphs().get(0);
                    if (!paragraph.getRuns().isEmpty()) {
                        XWPFRun run = paragraph.getRuns().get(0);
                        run.setFontFamily(DocxConstants.DEFAULT_FONT_FAMILY);
                        run.setFontSize(DocxConstants.DEFAULT_FONT_SIZE);
                    }
                }
            }
        }
        
        return this;
    }

    /**
     * 添加列表
     *
     * @param items    列表项
     * @param numbered 是否为有序列表
     * @return 当前创建器实例
     */
    public PoiDirectDocxCreator addList(String[] items, boolean numbered) {
        if (items == null || items.length == 0) {
            return this;
        }
        
        for (int i = 0; i < items.length; i++) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            
            String prefix = numbered ? (i + 1) + ". " : "• ";
            run.setText(prefix + (items[i] != null ? items[i] : ""));
            run.setFontFamily(DocxConstants.DEFAULT_FONT_FAMILY);
            run.setFontSize(DocxConstants.DEFAULT_FONT_SIZE);
        }
        
        return this;
    }

    /**
     * 添加分页符
     *
     * @return 当前创建器实例
     */
    public PoiDirectDocxCreator addPageBreak() {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.addBreak(BreakType.PAGE);
        return this;
    }

    /**
     * 添加换行符
     *
     * @return 当前创建器实例
     */
    public PoiDirectDocxCreator addLineBreak() {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.addBreak();
        return this;
    }

    /**
     * 设置最终的文档属性
     */
    private void finalizeDocumentProperties() throws InvalidFormatException {
        org.apache.poi.ooxml.POIXMLProperties.CoreProperties coreProperties = document.getProperties().getCoreProperties();
        
        if (!documentTitle.isEmpty()) {
            coreProperties.setTitle(documentTitle);
        }
        
        if (!documentAuthor.isEmpty()) {
            coreProperties.setCreator(documentAuthor);
            coreProperties.setLastModifiedByUser(documentAuthor);
        }
        
        // 更新修改时间
        coreProperties.setModified(LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    /**
     * 获取底层的XWPFDocument对象
     * 用于高级操作
     *
     * @return XWPFDocument对象
     */
    public XWPFDocument getDocument() {
        return document;
    }

    /**
     * 关闭文档资源
     *
     * @throws IOException IO异常
     */
    public void close() throws IOException {
        if (document != null) {
            document.close();
        }
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
    private void applyHeaderFooter() {
        try {
            // 创建页头
            if (!headerText.isEmpty()) {
                XWPFHeaderFooterPolicy headerFooterPolicy = document.getHeaderFooterPolicy();
                if (headerFooterPolicy == null) {
                    headerFooterPolicy = document.createHeaderFooterPolicy();
                }
                
                XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
                XWPFParagraph headerParagraph = header.createParagraph();
                headerParagraph.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun headerRun = headerParagraph.createRun();
                headerRun.setText(headerText);
                headerRun.setFontFamily(DocxConstants.DEFAULT_FONT_FAMILY);
                headerRun.setFontSize(DocxConstants.DEFAULT_FONT_SIZE - 2);
            }
            
            // 创建页脚
            if (!footerText.isEmpty() || pageNumberEnabled) {
                XWPFHeaderFooterPolicy headerFooterPolicy = document.getHeaderFooterPolicy();
                if (headerFooterPolicy == null) {
                    headerFooterPolicy = document.createHeaderFooterPolicy();
                }
                
                XWPFFooter footer = headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);
                XWPFParagraph footerParagraph = footer.createParagraph();
                footerParagraph.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun footerRun = footerParagraph.createRun();
                
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
                    footerRun.setFontFamily(DocxConstants.DEFAULT_FONT_FAMILY);
                    footerRun.setFontSize(DocxConstants.DEFAULT_FONT_SIZE - 2);
                    
                    // 添加页码字段
                    footerParagraph.getCTP().addNewFldSimple().setInstr("PAGE");
                    
                    XWPFRun pageRun = footerParagraph.createRun();
                    pageRun.setText(" 页");
                    pageRun.setFontFamily(DocxConstants.DEFAULT_FONT_FAMILY);
                    pageRun.setFontSize(DocxConstants.DEFAULT_FONT_SIZE - 2);
                } else {
                    footerRun.setText(footerContent);
                    footerRun.setFontFamily(DocxConstants.DEFAULT_FONT_FAMILY);
                    footerRun.setFontSize(DocxConstants.DEFAULT_FONT_SIZE - 2);
                }
            }
        } catch (Exception e) {
            // 页头页脚设置失败时不影响文档创建
            System.err.println("设置页头页脚失败: " + e.getMessage());
        }
    }
}