package com.boundesu.words.html.converter;

import com.boundesu.words.common.exception.BoundesuWordsException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
     * 页边距配置类
     */
    public static class PageMargins {
        private final int top;    // 上边距，单位：磅的1/20
        private final int bottom; // 下边距，单位：磅的1/20
        private final int left;   // 左边距，单位：磅的1/20
        private final int right;  // 右边距，单位：磅的1/20
        
        /**
         * 构造函数
         * @param top 上边距（磅）
         * @param bottom 下边距（磅）
         * @param left 左边距（磅）
         * @param right 右边距（磅）
         */
        public PageMargins(double top, double bottom, double left, double right) {
            this.top = (int) (top * 20);
            this.bottom = (int) (bottom * 20);
            this.left = (int) (left * 20);
            this.right = (int) (right * 20);
        }
        
        /**
         * 默认页边距（上下左右各1英寸）
         */
        public static PageMargins defaultMargins() {
            return new PageMargins(72, 72, 72, 72);
        }
        
        public int getTop() { return top; }
        public int getBottom() { return bottom; }
        public int getLeft() { return left; }
        public int getRight() { return right; }
    }
    
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
     * @param margins 页边距设置，为null时使用默认边距
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
            throw new BoundesuWordsException("HTML_CONVERT_ERROR", "HTML到DOCX转换失败", e);
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
     * @param margins 页边距设置，为null时使用默认边距
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument convertHtmlToDocx(InputStream htmlInputStream, PageMargins margins) throws BoundesuWordsException {
        try {
            log.info("开始转换HTML输入流到DOCX文档");
            
            // 解析HTML输入流
            Document htmlDoc = Jsoup.parse(htmlInputStream, "UTF-8", "");
            
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
            throw new BoundesuWordsException("HTML_STREAM_CONVERT_ERROR", "HTML输入流到DOCX转换失败", e);
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
     * 获取标题字体大小
     * 
     * @param tagName 标签名
     * @return 字体大小
     */
    private int getHeadingFontSize(String tagName) {
        switch (tagName) {
            case "h1": return 18;
            case "h2": return 16;
            case "h3": return 14;
            case "h4": return 12;
            case "h5": return 11;
            case "h6": return 10;
            default: return 12;
        }
    }
    
    /**
     * 设置文档页边距
     * 
     * @param document DOCX文档
     * @param margins 页边距配置
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
}