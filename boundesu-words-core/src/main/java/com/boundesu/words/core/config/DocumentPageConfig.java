package com.boundesu.words.core.config;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

/**
 * 文档页面配置类
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public class DocumentPageConfig {

    private PaperSize paperSize = PaperSize.A4;
    private Orientation orientation = Orientation.PORTRAIT;
    private double topMargin = 72;    // 1英寸 = 72磅
    private double bottomMargin = 72;
    private double leftMargin = 72;
    private double rightMargin = 72;
    private double headerDistance = 36;  // 页眉距离
    private double footerDistance = 36;  // 页脚距离
    // 页眉页脚内容
    private String headerText;
    private String footerText;
    private InputStream headerImageStream;
    private double headerImageWidth = 100;
    private double headerImageHeight = 30;
    // 背景设置
    private String backgroundColor;
    private InputStream backgroundImageStream;
    // 目录设置
    private boolean generateToc = false;
    private String tocTitle = "目录";
    private String tocLevels = "1-3";  // 目录级别

    /**
     * 设置页边距（磅）
     */
    public DocumentPageConfig setMargins(double top, double bottom, double left, double right) {
        this.topMargin = top;
        this.bottomMargin = bottom;
        this.leftMargin = left;
        this.rightMargin = right;
        return this;
    }

    /**
     * 设置页眉页脚距离
     */
    public DocumentPageConfig setHeaderFooterDistance(double headerDistance, double footerDistance) {
        this.headerDistance = headerDistance;
        this.footerDistance = footerDistance;
        return this;
    }

    /**
     * 设置页眉图片
     */
    public DocumentPageConfig setHeaderImage(InputStream imageStream, double width, double height) {
        this.headerImageStream = imageStream;
        this.headerImageWidth = width;
        this.headerImageHeight = height;
        return this;
    }

    /**
     * 设置背景图片
     */
    public DocumentPageConfig setBackgroundImage(InputStream imageStream) {
        this.backgroundImageStream = imageStream;
        return this;
    }

    /**
     * 应用配置到Word文档
     */
    public void applyToDocument(XWPFDocument document) throws IOException, InvalidFormatException {
        // 应用页面设置
        applyPageSetup(document);

        // 应用页眉页脚
        applyHeaderFooter(document);

        // 应用背景
        applyBackground(document);

        // 生成目录
        if (generateToc) {
            generateTableOfContents(document);
        }
    }

    /**
     * 应用页面设置
     */
    private void applyPageSetup(XWPFDocument document) {
        CTDocument1 ctDocument = document.getDocument();
        CTBody body = ctDocument.getBody();
        CTSectPr sectPr = body.isSetSectPr() ? body.getSectPr() : body.addNewSectPr();

        // 设置纸张大小
        CTPageSz pageSize = sectPr.isSetPgSz() ? sectPr.getPgSz() : sectPr.addNewPgSz();
        if (orientation == Orientation.PORTRAIT) {
            pageSize.setW(BigInteger.valueOf(paperSize.getWidth() * 20)); // 转换为twips
            pageSize.setH(BigInteger.valueOf(paperSize.getHeight() * 20));
            pageSize.setOrient(STPageOrientation.PORTRAIT);
        } else {
            pageSize.setW(BigInteger.valueOf(paperSize.getHeight() * 20));
            pageSize.setH(BigInteger.valueOf(paperSize.getWidth() * 20));
            pageSize.setOrient(STPageOrientation.LANDSCAPE);
        }

        // 设置页边距
        CTPageMar pageMargins = sectPr.isSetPgMar() ? sectPr.getPgMar() : sectPr.addNewPgMar();
        pageMargins.setTop(BigInteger.valueOf((long) (topMargin * 20)));
        pageMargins.setBottom(BigInteger.valueOf((long) (bottomMargin * 20)));
        pageMargins.setLeft(BigInteger.valueOf((long) (leftMargin * 20)));
        pageMargins.setRight(BigInteger.valueOf((long) (rightMargin * 20)));
        pageMargins.setHeader(BigInteger.valueOf((long) (headerDistance * 20)));
        pageMargins.setFooter(BigInteger.valueOf((long) (footerDistance * 20)));
    }

    /**
     * 应用页眉页脚
     */
    private void applyHeaderFooter(XWPFDocument document) throws IOException, InvalidFormatException {
        if (headerText != null || headerImageStream != null) {
            // 创建页眉策略
            XWPFHeaderFooterPolicy policy = document.getHeaderFooterPolicy();
            if (policy == null) {
                policy = document.createHeaderFooterPolicy();
            }

            XWPFHeader header = policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
            XWPFParagraph headerPara = header.createParagraph();
            headerPara.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun headerRun = headerPara.createRun();

            // 添加页眉图片
            if (headerImageStream != null) {
                headerRun.addPicture(headerImageStream, XWPFDocument.PICTURE_TYPE_PNG, "header.png",
                        (int) (headerImageWidth * 9525), (int) (headerImageHeight * 9525)); // 转换为EMU
            }

            // 添加页眉文本
            if (headerText != null) {
                if (headerImageStream != null) {
                    headerRun.addTab();
                }
                headerRun.setText(headerText);
            }
        }

        if (footerText != null) {
            // 创建页脚策略
            XWPFHeaderFooterPolicy policy = document.getHeaderFooterPolicy();
            if (policy == null) {
                policy = document.createHeaderFooterPolicy();
            }

            XWPFFooter footer = policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);
            XWPFParagraph footerPara = footer.createParagraph();
            footerPara.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun footerRun = footerPara.createRun();
            footerRun.setText(footerText);

            // 添加页码
            footerRun.addTab();
            footerRun.setText("第");
            footerPara.getCTP().addNewFldSimple().setInstr("PAGE");
            footerRun = footerPara.createRun();
            footerRun.setText("页 共");
            footerPara.getCTP().addNewFldSimple().setInstr("NUMPAGES");
            footerRun = footerPara.createRun();
            footerRun.setText("页");
        }
    }

    /**
     * 应用背景
     */
    private void applyBackground(XWPFDocument document) throws IOException, InvalidFormatException {
        CTDocument1 ctDocument = document.getDocument();
        CTBody body = ctDocument.getBody();
        CTSectPr sectPr = body.isSetSectPr() ? body.getSectPr() : body.addNewSectPr();

        if (backgroundColor != null) {
            // 设置背景颜色
            CTPageBorders pageBorders = sectPr.isSetPgBorders() ? sectPr.getPgBorders() : sectPr.addNewPgBorders();

            // 创建背景颜色设置
            CTShd shd = CTShd.Factory.newInstance();
            shd.setVal(STShd.CLEAR);
            shd.setColor("auto");

            // 解析颜色值（支持#RRGGBB格式）
            String colorValue = backgroundColor;
            if (colorValue.startsWith("#")) {
                colorValue = colorValue.substring(1);
            }
            shd.setFill(colorValue.toUpperCase());

        }

        if (backgroundImageStream != null) {
            // 设置背景图片
            try {
                // 添加图片到文档
                String relationId = document.addPictureData(backgroundImageStream, XWPFDocument.PICTURE_TYPE_PNG);

                // 创建背景图片设置
                CTBackground background = CTBackground.Factory.newInstance();
                background.setColor("FFFFFF"); // 默认白色背景


            } catch (Exception e) {
                // 如果背景图片设置失败，记录错误但不中断处理
                System.err.println("设置背景图片失败: " + e.getMessage());
            }
        }
    }

    /**
     * 生成目录
     */
    private void generateTableOfContents(XWPFDocument document) {
        // 在文档开头插入目录
        XWPFParagraph tocPara = document.createParagraph();
        tocPara.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun tocRun = tocPara.createRun();
        tocRun.setText(tocTitle);
        tocRun.setBold(true);
        tocRun.setFontSize(20);
        tocRun.setFontFamily("Microsoft YaHei");

        // 插入分页符
        XWPFParagraph breakPara = document.createParagraph();
        XWPFRun breakRun = breakPara.createRun();
        breakRun.addBreak(BreakType.PAGE);

        // 这里需要更复杂的逻辑来生成实际的目录
        // 包括扫描文档中的标题、生成目录项等
    }

    // Getter方法
    public PaperSize getPaperSize() {
        return paperSize;
    }

    /**
     * 设置纸张大小
     */
    public DocumentPageConfig setPaperSize(PaperSize paperSize) {
        this.paperSize = paperSize;
        return this;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * 设置页面方向
     */
    public DocumentPageConfig setOrientation(Orientation orientation) {
        this.orientation = orientation;
        return this;
    }

    public double getTopMargin() {
        return topMargin;
    }

    public double getBottomMargin() {
        return bottomMargin;
    }

    public double getLeftMargin() {
        return leftMargin;
    }

    public double getRightMargin() {
        return rightMargin;
    }

    public double getHeaderDistance() {
        return headerDistance;
    }

    public double getFooterDistance() {
        return footerDistance;
    }

    public String getHeaderText() {
        return headerText;
    }

    /**
     * 设置页眉文本
     */
    public DocumentPageConfig setHeaderText(String headerText) {
        this.headerText = headerText;
        return this;
    }

    public String getFooterText() {
        return footerText;
    }

    /**
     * 设置页脚文本
     */
    public DocumentPageConfig setFooterText(String footerText) {
        this.footerText = footerText;
        return this;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * 设置背景颜色
     */
    public DocumentPageConfig setBackgroundColor(String color) {
        this.backgroundColor = color;
        return this;
    }

    public boolean isGenerateToc() {
        return generateToc;
    }

    /**
     * 设置目录生成
     */
    public DocumentPageConfig setGenerateToc(boolean generateToc) {
        this.generateToc = generateToc;
        return this;
    }

    public String getTocTitle() {
        return tocTitle;
    }

    /**
     * 设置目录标题
     */
    public DocumentPageConfig setTocTitle(String tocTitle) {
        this.tocTitle = tocTitle;
        return this;
    }

    public String getTocLevels() {
        return tocLevels;
    }

    /**
     * 设置目录级别
     */
    public DocumentPageConfig setTocLevels(String tocLevels) {
        this.tocLevels = tocLevels;
        return this;
    }

    // 纸张大小枚举
    public enum PaperSize {
        A4(595, 842),
        A3(842, 1191),
        A5(420, 595),
        LETTER(612, 792),
        LEGAL(612, 1008);

        private final int width;
        private final int height;

        PaperSize(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

    // 页面方向枚举
    public enum Orientation {
        PORTRAIT,
        LANDSCAPE
    }

    // 页眉页脚类型枚举 - 使用POI的标准类型
    public enum HeaderFooterType {
        DEFAULT,
        FIRST,
        EVEN
    }
}