package com.boundesu.words.xml.converter;

import com.boundesu.words.common.constants.FormatConstants;
import com.boundesu.words.common.constants.ErrorConstants;
import com.boundesu.words.common.exception.BoundesuWordsException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * XML转DOCX转换器
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class XmlToDocxConverter {
    
    private static final Logger log = LoggerFactory.getLogger(XmlToDocxConverter.class);
    
    /**
     * 将XML内容转换为DOCX文档
     * 
     * @param xmlContent XML内容
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument convertXmlToDocx(String xmlContent) throws BoundesuWordsException {
        try {
            log.info("开始转换XML内容到DOCX文档");
            
            // 解析XML内容
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xmlDoc = builder.parse(new ByteArrayInputStream(xmlContent.getBytes(FormatConstants.ENCODING_UTF8)));
            
            // 创建DOCX文档
            XWPFDocument docxDoc = new XWPFDocument();
            
            // 处理XML元素
            processXmlElements(xmlDoc, docxDoc);
            
            log.info("XML到DOCX转换完成");
            return docxDoc;
            
        } catch (Exception e) {
            log.error("XML到DOCX转换失败", e);
            throw new BoundesuWordsException(ErrorConstants.DOCUMENT_CONVERSION_ERROR, "XML到DOCX转换失败", e);
        }
    }
    
    /**
     * 将XML输入流转换为DOCX文档
     * 
     * @param xmlInputStream XML输入流
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument convertXmlToDocx(InputStream xmlInputStream) throws BoundesuWordsException {
        try {
            log.info("开始转换XML输入流到DOCX文档");
            
            // 解析XML输入流
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xmlDoc = builder.parse(xmlInputStream);
            
            // 创建DOCX文档
            XWPFDocument docxDoc = new XWPFDocument();
            
            // 处理XML元素
            processXmlElements(xmlDoc, docxDoc);
            
            log.info("XML输入流到DOCX转换完成");
            return docxDoc;
            
        } catch (Exception e) {
            log.error("XML输入流到DOCX转换失败", e);
            throw new BoundesuWordsException(ErrorConstants.DOCUMENT_CONVERSION_ERROR, "XML输入流到DOCX转换失败", e);
        }
    }
    
    /**
     * 处理XML元素
     * 
     * @param xmlDoc XML文档
     * @param docxDoc DOCX文档
     */
    private void processXmlElements(Document xmlDoc, XWPFDocument docxDoc) {
        Element root = xmlDoc.getDocumentElement();
        if (root != null) {
            processNode(root, docxDoc);
        }
    }
    
    /**
     * 递归处理XML节点
     * 
     * @param node XML节点
     * @param docxDoc DOCX文档
     */
    private void processNode(Node node, XWPFDocument docxDoc) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            String tagName = element.getTagName().toLowerCase();
            
            switch (tagName) {
                case "paragraph":
                case "p":
                    processParagraph(element, docxDoc);
                    break;
                case "title":
                case "heading":
                case "h1":
                case "h2":
                case "h3":
                    processHeading(element, docxDoc);
                    break;
                case "text":
                    processText(element, docxDoc);
                    break;
                default:
                    // 处理子节点
                    NodeList children = node.getChildNodes();
                    for (int i = 0; i < children.getLength(); i++) {
                        processNode(children.item(i), docxDoc);
                    }
                    break;
            }
        } else if (node.getNodeType() == Node.TEXT_NODE) {
            String textContent = node.getTextContent().trim();
            if (!textContent.isEmpty()) {
                processTextContent(textContent, docxDoc);
            }
        }
    }
    
    /**
     * 处理段落元素
     * 
     * @param element 段落元素
     * @param docxDoc DOCX文档
     */
    private void processParagraph(Element element, XWPFDocument docxDoc) {
        XWPFParagraph paragraph = docxDoc.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(element.getTextContent());
    }
    
    /**
     * 处理标题元素
     * 
     * @param element 标题元素
     * @param docxDoc DOCX文档
     */
    private void processHeading(Element element, XWPFDocument docxDoc) {
        XWPFParagraph paragraph = docxDoc.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(element.getTextContent());
        run.setBold(true);
        run.setFontSize(16);
    }
    
    /**
     * 处理文本元素
     * 
     * @param element 文本元素
     * @param docxDoc DOCX文档
     */
    private void processText(Element element, XWPFDocument docxDoc) {
        XWPFParagraph paragraph = docxDoc.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(element.getTextContent());
    }
    
    /**
     * 处理文本内容
     * 
     * @param textContent 文本内容
     * @param docxDoc DOCX文档
     */
    private void processTextContent(String textContent, XWPFDocument docxDoc) {
        XWPFParagraph paragraph = docxDoc.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(textContent);
    }
}