package com.boundesu.words.html.converter;

import com.boundesu.words.common.constants.ErrorConstants;
import com.boundesu.words.common.constants.FormatConstants;
import com.boundesu.words.common.constants.PageConstants;
import com.boundesu.words.common.constants.StyleConstants;
import com.boundesu.words.common.exception.BoundesuWordsException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

/**
 * HTML转DOCX转换器
 *
 * @author Boundesu
 * @version 1.0.0
 */
public class HtmlToDocxConverter {

    private static final Logger log = LoggerFactory.getLogger(HtmlToDocxConverter.class);

    /**
     * 将HTML内容转换为DOCX文档
     *
     * @param htmlContent HTML内容
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument convertHtmlToDocx(String htmlContent) throws BoundesuWordsException {
        return convertHtmlToDocx(htmlContent, null);
    }

    /**
     * 将HTML内容转换为DOCX文档，并设置页边距
     *
     * @param htmlContent HTML内容
     * @param margins     页边距设置，为null时使用默认边距
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument convertHtmlToDocx(String htmlContent, PageMargins margins) throws BoundesuWordsException {
        try {
            log.info("开始转换HTML内容到DOCX文档");

            // 解析HTML内容
            Document htmlDoc = Jsoup.parse(htmlContent);

            // 创建DOCX文档
            XWPFDocument docxDoc = new XWPFDocument();

            // 设置页边距
            if (margins != null) {
                setPageMargins(docxDoc, margins);
            }

            // 处理HTML元素
            processHtmlElements(htmlDoc, docxDoc);

            log.info("HTML到DOCX转换完成");
            return docxDoc;

        } catch (Exception e) {
            log.error("HTML到DOCX转换失败", e);
            throw new BoundesuWordsException(ErrorConstants.DOCUMENT_CONVERSION_ERROR, "HTML到DOCX转换失败", e);
        }
    }

    /**
     * 将HTML输入流转换为DOCX文档
     *
     * @param htmlInputStream HTML输入流
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument convertHtmlToDocx(InputStream htmlInputStream) throws BoundesuWordsException {
        return convertHtmlToDocx(htmlInputStream, null);
    }

    /**
     * 将HTML输入流转换为DOCX文档，并设置页边距
     *
     * @param htmlInputStream HTML输入流
     * @param margins         页边距设置，为null时使用默认边距
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument convertHtmlToDocx(InputStream htmlInputStream, PageMargins margins) throws BoundesuWordsException {
        try {
            log.info("开始转换HTML输入流到DOCX文档");

            // 解析HTML输入流
            Document htmlDoc = Jsoup.parse(htmlInputStream, FormatConstants.ENCODING_UTF8, "");

            // 创建DOCX文档
            XWPFDocument docxDoc = new XWPFDocument();

            // 设置页边距
            if (margins != null) {
                setPageMargins(docxDoc, margins);
            }

            // 处理HTML元素
            processHtmlElements(htmlDoc, docxDoc);

            log.info("HTML输入流到DOCX转换完成");
            return docxDoc;

        } catch (IOException e) {
            log.error("HTML输入流到DOCX转换失败", e);
            throw new BoundesuWordsException(ErrorConstants.DOCUMENT_CONVERSION_ERROR, "HTML输入流到DOCX转换失败", e);
        }
    }

    /**
     * 处理HTML元素
     *
     * @param htmlDoc HTML文档
     * @param docxDoc DOCX文档
     */
    private void processHtmlElements(Document htmlDoc, XWPFDocument docxDoc) {
        // 获取body元素
        Element body = htmlDoc.body();
        if (body != null) {
            processElement(body, docxDoc);
        }
    }

    /**
     * 递归处理HTML元素
     *
     * @param element HTML元素
     * @param docxDoc DOCX文档
     */
    private void processElement(Element element, XWPFDocument docxDoc) {
        String tagName = element.tagName().toLowerCase();

        switch (tagName) {
            case "p":
                processParagraph(element, docxDoc);
                break;
            case "h1":
            case "h2":
            case "h3":
            case "h4":
            case "h5":
            case "h6":
                processHeading(element, docxDoc, tagName);
                break;
            case "br":
                processLineBreak(docxDoc);
                break;
            default:
                // 处理其他元素的子元素
                for (Element child : element.children()) {
                    processElement(child, docxDoc);
                }
                break;
        }
    }

    /**
     * 处理段落
     *
     * @param element 段落元素
     * @param docxDoc DOCX文档
     */
    private void processParagraph(Element element, XWPFDocument docxDoc) {
        XWPFParagraph paragraph = docxDoc.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(element.text());
    }

    /**
     * 处理标题
     *
     * @param element 标题元素
     * @param docxDoc DOCX文档
     * @param tagName 标签名
     */
    private void processHeading(Element element, XWPFDocument docxDoc, String tagName) {
        XWPFParagraph paragraph = docxDoc.createParagraph();

        // 设置标题样式
        String headingStyle = getHeadingStyle(tagName);
        paragraph.setStyle(headingStyle);

        XWPFRun run = paragraph.createRun();
        run.setText(element.text());
        run.setBold(true);

        // 根据标题级别设置字体大小
        int fontSize = getHeadingFontSize(tagName);
        run.setFontSize(fontSize);
    }

    /**
     * 处理换行
     *
     * @param docxDoc DOCX文档
     */
    private void processLineBreak(XWPFDocument docxDoc) {
        XWPFParagraph paragraph = docxDoc.createParagraph();
        paragraph.createRun().addBreak();
    }

    /**
     * 获取标题样式名称
     *
     * @param tagName 标签名
     * @return Word标题样式名称
     */
    private String getHeadingStyle(String tagName) {
        switch (tagName) {
            case "h1":
                return "Heading 1";
            case "h2":
                return "Heading 2";
            case "h3":
                return "Heading 3";
            case "h4":
                return "Heading 4";
            case "h5":
                return "Heading 5";
            case "h6":
                return "Heading 6";
            default:
                return "Normal";
        }
    }

    /**
     * 获取标题字体大小
     *
     * @param tagName 标签名
     * @return 字体大小
     */
    private int getHeadingFontSize(String tagName) {
        switch (tagName) {
            case "h1":
                return StyleConstants.FONT_SIZE_H1;
            case "h2":
                return StyleConstants.FONT_SIZE_H2;
            case "h3":
                return StyleConstants.FONT_SIZE_H3;
            case "h4":
                return StyleConstants.FONT_SIZE_H4;
            case "h5":
                return StyleConstants.FONT_SIZE_H5;
            case "h6":
                return StyleConstants.FONT_SIZE_H6;
            default:
                return StyleConstants.FONT_SIZE_NORMAL;
        }
    }

    /**
     * 设置文档页边距
     *
     * @param document DOCX文档
     * @param margins  页边距配置
     */
    private void setPageMargins(XWPFDocument document, PageMargins margins) {
        try {
            CTDocument1 ctDocument = document.getDocument();
            CTSectPr sectPr;

            // 获取或创建节属性
            if (ctDocument.getBody().getSectPr() != null) {
                sectPr = ctDocument.getBody().getSectPr();
            } else {
                sectPr = ctDocument.getBody().addNewSectPr();
            }

            // 获取或创建页边距设置
            CTPageMar pageMar;
            if (sectPr.getPgMar() != null) {
                pageMar = sectPr.getPgMar();
            } else {
                pageMar = sectPr.addNewPgMar();
            }

            // 设置页边距值
            pageMar.setTop(BigInteger.valueOf(margins.getTop()));
            pageMar.setBottom(BigInteger.valueOf(margins.getBottom()));
            pageMar.setLeft(BigInteger.valueOf(margins.getLeft()));
            pageMar.setRight(BigInteger.valueOf(margins.getRight()));

            log.debug("页边距设置完成: 上={}, 下={}, 左={}, 右={}",
                    margins.getTop(), margins.getBottom(), margins.getLeft(), margins.getRight());

        } catch (Exception e) {
            log.warn("设置页边距失败: {}", e.getMessage());
        }
    }

    /**
     * 页边距配置类
     * 提供灵活的页边距设置和验证功能
     */
    public static class PageMargins {
        private final int top;    // 上边距，单位：磅的1/20
        private final int bottom; // 下边距，单位：磅的1/20
        private final int left;   // 左边距，单位：磅的1/20
        private final int right;  // 右边距，单位：磅的1/20

        /**
         * 构造函数
         *
         * @param top    上边距（磅）
         * @param bottom 下边距（磅）
         * @param left   左边距（磅）
         * @param right  右边距（磅）
         */
        public PageMargins(double top, double bottom, double left, double right) {
            // 验证页边距值
            validateMargin(top, "上边距");
            validateMargin(bottom, "下边距");
            validateMargin(left, "左边距");
            validateMargin(right, "右边距");

            this.top = (int) (top * 20);
            this.bottom = (int) (bottom * 20);
            this.left = (int) (left * 20);
            this.right = (int) (right * 20);
        }

        /**
         * 统一页边距构造函数
         *
         * @param margin 四边统一的页边距（磅）
         */
        public PageMargins(double margin) {
            this(margin, margin, margin, margin);
        }

        /**
         * 从PageConstants.MarginConfig创建
         *
         * @param marginConfig 边距配置
         */
        public PageMargins(PageConstants.MarginConfig marginConfig) {
            this(marginConfig.getTop(), marginConfig.getBottom(),
                    marginConfig.getLeft(), marginConfig.getRight());
        }

        /**
         * 默认页边距（上下左右各1英寸）
         */
        public static PageMargins defaultMargins() {
            return new PageMargins(PageConstants.getDefaultMarginConfig());
        }

        /**
         * 窄页边距（上下左右各0.5英寸）
         */
        public static PageMargins narrowMargins() {
            return new PageMargins(PageConstants.getNarrowMarginConfig());
        }

        /**
         * 宽页边距（上下左右各1.5英寸）
         */
        public static PageMargins wideMargins() {
            return new PageMargins(PageConstants.getWideMarginConfig());
        }

        /**
         * 装订页边距（左边距增加，适合装订）
         *
         * @param bindingMargin 装订边距增量（磅）
         */
        public static PageMargins bindingMargins(double bindingMargin) {
            return new PageMargins(
                    PageConstants.DEFAULT_MARGIN,
                    PageConstants.DEFAULT_MARGIN,
                    PageConstants.DEFAULT_MARGIN + bindingMargin,
                    PageConstants.DEFAULT_MARGIN
            );
        }

        /**
         * 从英寸创建页边距
         *
         * @param topInches    上边距（英寸）
         * @param bottomInches 下边距（英寸）
         * @param leftInches   左边距（英寸）
         * @param rightInches  右边距（英寸）
         */
        public static PageMargins fromInches(double topInches, double bottomInches,
                                             double leftInches, double rightInches) {
            return new PageMargins(
                    PageConstants.UnitConverter.inchesToPoints(topInches),
                    PageConstants.UnitConverter.inchesToPoints(bottomInches),
                    PageConstants.UnitConverter.inchesToPoints(leftInches),
                    PageConstants.UnitConverter.inchesToPoints(rightInches)
            );
        }

        /**
         * 从厘米创建页边距
         *
         * @param topCm    上边距（厘米）
         * @param bottomCm 下边距（厘米）
         * @param leftCm   左边距（厘米）
         * @param rightCm  右边距（厘米）
         */
        public static PageMargins fromCentimeters(double topCm, double bottomCm,
                                                  double leftCm, double rightCm) {
            return new PageMargins(
                    PageConstants.UnitConverter.cmToPoints(topCm),
                    PageConstants.UnitConverter.cmToPoints(bottomCm),
                    PageConstants.UnitConverter.cmToPoints(leftCm),
                    PageConstants.UnitConverter.cmToPoints(rightCm)
            );
        }

        /**
         * 验证页边距值
         *
         * @param margin 页边距值（磅）
         * @param name   边距名称
         */
        private void validateMargin(double margin, String name) {
            if (margin < PageConstants.MIN_MARGIN) {
                throw new IllegalArgumentException(
                        String.format("%s不能小于最小值%.1f磅", name, PageConstants.MIN_MARGIN));
            }
            if (margin > PageConstants.MAX_MARGIN) {
                throw new IllegalArgumentException(
                        String.format("%s不能大于最大值%.1f磅", name, PageConstants.MAX_MARGIN));
            }
        }

        /**
         * 获取页边距信息字符串
         */
        public String getMarginInfo() {
            return String.format("页边距设置 - 上:%.1f磅, 下:%.1f磅, 左:%.1f磅, 右:%.1f磅",
                    getTopInPoints(), getBottomInPoints(), getLeftInPoints(), getRightInPoints());
        }

        /**
         * 获取上边距（磅）
         */
        public double getTopInPoints() {
            return top / 20.0;
        }

        /**
         * 获取下边距（磅）
         */
        public double getBottomInPoints() {
            return bottom / 20.0;
        }

        /**
         * 获取左边距（磅）
         */
        public double getLeftInPoints() {
            return left / 20.0;
        }

        /**
         * 获取右边距（磅）
         */
        public double getRightInPoints() {
            return right / 20.0;
        }

        // 原有的getter方法（返回缇单位）
        public int getTop() {
            return top;
        }

        public int getBottom() {
            return bottom;
        }

        public int getLeft() {
            return left;
        }

        public int getRight() {
            return right;
        }
    }
}