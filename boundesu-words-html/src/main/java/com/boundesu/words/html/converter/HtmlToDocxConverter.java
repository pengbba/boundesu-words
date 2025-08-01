package com.boundesu.words.html.converter;

import com.boundesu.words.common.exception.BoundesuWordsException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

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
        try {
            log.info("开始转换HTML内容到DOCX文档");
            
            // 解析HTML内容
            Document htmlDoc = Jsoup.parse(htmlContent);
            
            // 创建DOCX文档
            XWPFDocument docxDoc = new XWPFDocument();
            
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
     * 将HTML文件转换为DOCX文档
     * 
     * @param htmlInputStream HTML文件输入流
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument convertHtmlFileToDocx(InputStream htmlInputStream) throws BoundesuWordsException {
        try {
            log.info("开始转换HTML文件到DOCX文档");
            
            // 解析HTML文件
            Document htmlDoc = Jsoup.parse(htmlInputStream, "UTF-8", "");
            
            // 创建DOCX文档
            XWPFDocument docxDoc = new XWPFDocument();
            
            // 处理HTML元素
            processHtmlElements(htmlDoc, docxDoc);
            
            log.info("HTML文件到DOCX转换完成");
            return docxDoc;
            
        } catch (IOException e) {
            log.error("HTML文件到DOCX转换失败", e);
            throw new BoundesuWordsException("HTML_FILE_CONVERT_ERROR", "HTML文件到DOCX转换失败", e);
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
}