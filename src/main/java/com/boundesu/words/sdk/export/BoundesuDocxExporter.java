package com.boundesu.words.sdk.export;

import com.boundesu.words.sdk.constants.DocxConstants;
import com.boundesu.words.sdk.model.*;
import com.boundesu.words.sdk.utils.DocumentValidator;
import com.boundesu.words.sdk.utils.PerformanceMonitor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;

/**
 * DOCX文档导出器
 * 使用Apache POI库将BoundesuDocument导出为Microsoft Word DOCX格式
 */
public class BoundesuDocxExporter {
    
    /**
     * 导出文档为DOCX格式
     */
    public static void exportToDocx(BoundesuDocument document, Path filePath) throws IOException, InvalidFormatException {
        // 验证输入参数
        DocumentValidator.validateDocument(document);
        DocumentValidator.validateFilePath(filePath);

        // 开始性能监控
        PerformanceMonitor.OperationContext context = PerformanceMonitor.startOperation(DocxConstants.OPERATION_EXPORT_DOCX);
        
        // 创建Word文档
        XWPFDocument docx = new XWPFDocument();
        
        try {
            // 设置文档属性
            setDocumentProperties(docx, document);
            
            // 添加标题
            addDocumentTitle(docx, document);
            
            // 添加文档信息
            addDocumentInfo(docx, document);
            
            // 添加页眉
            if (document.getHeader() != null && document.getHeader().getText() != null) {
                addHeader(docx, document.getHeader().getText());
            }
            
            // 添加页脚
            if (document.getFooter() != null && document.getFooter().getText() != null) {
                addFooter(docx, document.getFooter().getText());
            }
            
            // 添加段落内容
            for (BoundesuParagraph paragraph : document.getParagraphs()) {
                addParagraph(docx, paragraph);
            }
            
            // 添加表格
            for (BoundesuTable table : document.getTables()) {
                addTable(docx, table);
            }
            
            // 保存文档
            try (FileOutputStream out = new FileOutputStream(filePath.toFile())) {
                docx.write(out);
            }
            
        } finally {
            docx.close();
            // 结束性能监控
            PerformanceMonitor.endOperation(context);
        }
    }
    
    /**
     * 设置文档属性
     */
    private static void setDocumentProperties(XWPFDocument docx, BoundesuDocument document) throws InvalidFormatException {
        // 获取文档属性
        org.apache.poi.ooxml.POIXMLProperties properties = docx.getProperties();
        org.apache.poi.ooxml.POIXMLProperties.CoreProperties coreProperties = properties.getCoreProperties();
        
        // 设置基本属性
        if (document.getTitle() != null) {
            coreProperties.setTitle(document.getTitle());
        }
        if (document.getAuthor() != null) {
            coreProperties.setCreator(document.getAuthor());
        }
        if (document.getDescription() != null) {
            coreProperties.setDescription(document.getDescription());
        }
        if (document.getSubject() != null) {
            coreProperties.setSubjectProperty(document.getSubject());
        }
        if (document.getKeywords() != null && !document.getKeywords().isEmpty()) {
            coreProperties.setKeywords(String.join(", ", document.getKeywords()));
        }
        // 设置语言
        if (document.getLanguage() != null) {
            // POI 5.x中没有直接的setLanguage方法，可以通过自定义属性设置
            org.apache.poi.ooxml.POIXMLProperties.CustomProperties customProperties = properties.getCustomProperties();
            customProperties.addProperty("Language", document.getLanguage());
        }
        if (document.getCreatedAt() != null) {
            coreProperties.setCreated(document.getCreatedAt().toString());
        }
        if (document.getLastModified() != null) {
            coreProperties.setModified(document.getLastModified().toString());
        }
    }
    
    /**
     * 添加文档标题
     */
    private static void addDocumentTitle(XWPFDocument docx, BoundesuDocument document) {
        if (document.getTitle() != null) {
            XWPFParagraph titleParagraph = docx.createParagraph();
            titleParagraph.setAlignment(ParagraphAlignment.CENTER);
            
            XWPFRun titleRun = titleParagraph.createRun();
            titleRun.setText(document.getTitle());
            titleRun.setBold(true);
            titleRun.setFontSize(DocxConstants.TITLE_FONT_SIZE);
            titleRun.setFontFamily(DocxConstants.DEFAULT_FONT_FAMILY);
            
            // 添加空行
            docx.createParagraph();
        }
    }
    
    /**
     * 添加文档信息
     */
    private static void addDocumentInfo(XWPFDocument docx, BoundesuDocument document) {
        XWPFParagraph infoParagraph = docx.createParagraph();
        infoParagraph.setAlignment(ParagraphAlignment.CENTER);
        
        XWPFRun infoRun = infoParagraph.createRun();
        StringBuilder info = new StringBuilder();
        
        if (document.getAuthor() != null) {
            info.append("作者: ").append(document.getAuthor()).append("\n");
        }
        if (document.getCreatedAt() != null) {
            info.append("创建时间: ").append(document.getCreatedAt().toString()).append("\n");
        }
        if (document.getLastModified() != null) {
            info.append("最后修改: ").append(document.getLastModified().toString()).append("\n");
        }
        if (document.getVersion() != null) {
            info.append("版本: ").append(document.getVersion());
        }
        
        infoRun.setText(info.toString());
        infoRun.setFontSize(DocxConstants.DEFAULT_FONT_SIZE);
        infoRun.setFontFamily(DocxConstants.DEFAULT_FONT_FAMILY);
        infoRun.setColor(DocxConstants.INFO_TEXT_COLOR);
        
        // 添加分隔线
        XWPFParagraph separatorParagraph = docx.createParagraph();
        separatorParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun separatorRun = separatorParagraph.createRun();
        separatorRun.setText(DocxConstants.SEPARATOR_LINE);
        separatorRun.setFontSize(DocxConstants.SMALL_FONT_SIZE);
        
        // 添加空行
        docx.createParagraph();
    }
    
    /**
     * 添加页眉
     */
    private static void addHeader(XWPFDocument docx, String headerText) throws InvalidFormatException {
        XWPFHeader header = docx.createHeader(HeaderFooterType.DEFAULT);
        XWPFParagraph headerParagraph = header.createParagraph();
        headerParagraph.setAlignment(ParagraphAlignment.CENTER);
        
        XWPFRun headerRun = headerParagraph.createRun();
        headerRun.setText(headerText);
        headerRun.setFontSize(DocxConstants.SMALL_FONT_SIZE);
        headerRun.setFontFamily(DocxConstants.DEFAULT_FONT_FAMILY);
    }
    
    /**
     * 添加页脚
     */
    private static void addFooter(XWPFDocument docx, String footerText) throws InvalidFormatException {
        XWPFFooter footer = docx.createFooter(HeaderFooterType.DEFAULT);
        XWPFParagraph footerParagraph = footer.createParagraph();
        footerParagraph.setAlignment(ParagraphAlignment.CENTER);
        
        XWPFRun footerRun = footerParagraph.createRun();
        footerRun.setText(footerText);
        footerRun.setFontSize(DocxConstants.SMALL_FONT_SIZE);
        footerRun.setFontFamily(DocxConstants.DEFAULT_FONT_FAMILY);
    }
    
    /**
     * 添加段落
     */
    private static void addParagraph(XWPFDocument docx, BoundesuParagraph paragraph) {
        // 验证段落内容
        DocumentValidator.validateParagraphContent(paragraph.getText());

        XWPFParagraph docxParagraph = docx.createParagraph();
        
        // 设置段落对齐方式
        switch (paragraph.getAlignment()) {
            case LEFT:
                docxParagraph.setAlignment(ParagraphAlignment.LEFT);
                break;
            case CENTER:
                docxParagraph.setAlignment(ParagraphAlignment.CENTER);
                break;
            case RIGHT:
                docxParagraph.setAlignment(ParagraphAlignment.RIGHT);
                break;
            case JUSTIFY:
                docxParagraph.setAlignment(ParagraphAlignment.BOTH);
                break;
        }
        
        XWPFRun run = docxParagraph.createRun();
        run.setText(paragraph.getText());
        
        // 应用段落样式
        if (paragraph.getStyle() != null) {
            BoundesuParagraphStyle style = paragraph.getStyle();
            
            // 字体设置
            if (style.getFontFamily() != null) {
                run.setFontFamily(style.getFontFamily());
            } else {
                run.setFontFamily(DocxConstants.DEFAULT_FONT_FAMILY);
            }
            
            run.setFontSize(style.getFontSize());
            
            // 字体样式
            if (style.isBold()) {
                run.setBold(true);
            }
            if (style.isItalic()) {
                run.setItalic(true);
            }
            if (style.isUnderline()) {
                run.setUnderline(UnderlinePatterns.SINGLE);
            }
            
            // 字体颜色
            if (style.getColor() != null) {
                run.setColor(style.getColor().replace("#", ""));
            }
            
            // 标题样式
            if (style.isHeading()) {
                int headingLevel = style.getHeadingLevel();
                DocumentValidator.validateHeadingLevel(headingLevel);

                switch (headingLevel) {
                    case 1:
                        run.setFontSize(DocxConstants.HEADING_1_SIZE);
                        run.setBold(true);
                        break;
                    case 2:
                        run.setFontSize(DocxConstants.HEADING_2_SIZE);
                        run.setBold(true);
                        break;
                    case 3:
                        run.setFontSize(DocxConstants.HEADING_3_SIZE);
                        run.setBold(true);
                        break;
                    default:
                        run.setFontSize(DocxConstants.HEADING_DEFAULT_SIZE);
                        run.setBold(true);
                        break;
                }
            }
        } else {
            // 默认样式
            run.setFontFamily(DocxConstants.DEFAULT_FONT_FAMILY);
            run.setFontSize(DocxConstants.DEFAULT_FONT_SIZE);
        }
    }
    
    /**
     * 添加表格
     */
    private static void addTable(XWPFDocument docx, BoundesuTable table) {
        // 验证表格大小
        DocumentValidator.validateTableSize(table.getRows(), table.getColumns());

        // 添加表格标题
        if (table.getTitle() != null && !table.getTitle().isEmpty()) {
            XWPFParagraph titleParagraph = docx.createParagraph();
            titleParagraph.setAlignment(ParagraphAlignment.CENTER);
            
            XWPFRun titleRun = titleParagraph.createRun();
            titleRun.setText(table.getTitle());
            titleRun.setBold(true);
            titleRun.setFontSize(DocxConstants.TABLE_TITLE_SIZE);
            titleRun.setFontFamily(DocxConstants.DEFAULT_FONT_FAMILY);
        }
        
        // 创建表格
        XWPFTable docxTable = docx.createTable(table.getRows(), table.getColumns());
        
        // 设置表格样式
        CTTblPr tblPr = docxTable.getCTTbl().getTblPr();
        if (tblPr == null) {
            tblPr = docxTable.getCTTbl().addNewTblPr();
        }
        
        // 设置表格宽度
        CTTblWidth tblWidth = tblPr.getTblW();
        if (tblWidth == null) {
            tblWidth = tblPr.addNewTblW();
        }
        tblWidth.setType(STTblWidth.DXA);
        tblWidth.setW(BigInteger.valueOf(DocxConstants.TABLE_DEFAULT_WIDTH));
        
        // 填充表格数据
        for (int row = 0; row < table.getRows(); row++) {
            XWPFTableRow docxRow = docxTable.getRow(row);
            
            for (int col = 0; col < table.getColumns(); col++) {
                // 验证表格索引
                DocumentValidator.validateTableIndex(row, col, table.getRows(), table.getColumns());

                XWPFTableCell cell = docxRow.getCell(col);
                
                // 获取单元格数据
                BoundesuTableCell tableCell = table.getCell(row, col);
                if (tableCell != null && tableCell.getText() != null) {
                    // 清除默认段落
                    cell.removeParagraph(0);
                    
                    // 创建新段落
                    XWPFParagraph cellParagraph = cell.addParagraph();
                    XWPFRun cellRun = cellParagraph.createRun();
                    cellRun.setText(tableCell.getText());
                    cellRun.setFontFamily(DocxConstants.DEFAULT_FONT_FAMILY);
                    cellRun.setFontSize(DocxConstants.TABLE_CELL_SIZE);
                    
                    // 应用单元格样式
                    if (tableCell.getStyle() != null) {
                        BoundesuTableCellStyle cellStyle = tableCell.getStyle();
                        
                        if (cellStyle.isBold()) {
                            cellRun.setBold(true);
                        }
                        if (cellStyle.isItalic()) {
                            cellRun.setItalic(true);
                        }
                        if (cellStyle.getColor() != null) {
                            cellRun.setColor(cellStyle.getColor().replace("#", ""));
                        }
                    }
                    
                    // 第一行作为表头，设置为粗体
                    if (row == 0) {
                        cellRun.setBold(true);
                    }
                }
            }
        }
        
        // 添加空行
        docx.createParagraph();
    }
}