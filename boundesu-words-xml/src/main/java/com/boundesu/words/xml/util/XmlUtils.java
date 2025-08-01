package com.boundesu.words.xml.util;

import com.boundesu.words.common.exception.BoundesuWordsException;
import com.boundesu.words.common.util.StringUtils;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * XML工具类
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class XmlUtils {
    
    /**
     * 私有构造函数，防止实例化
     */
    private XmlUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    
    /**
     * 解析XML字符串
     * 
     * @param xmlContent XML内容
     * @return XML文档
     * @throws BoundesuWordsException 解析异常
     */
    public static Document parseXml(String xmlContent) throws BoundesuWordsException {
        if (StringUtils.isBlank(xmlContent)) {
            throw new BoundesuWordsException("XML内容不能为空");
        }
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(false);
            
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new ByteArrayInputStream(xmlContent.getBytes("UTF-8")));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new BoundesuWordsException("解析XML失败", e);
        }
    }
    
    /**
     * 解析XML文件
     * 
     * @param xmlFilePath XML文件路径
     * @return XML文档
     * @throws BoundesuWordsException 解析异常
     */
    public static Document parseXmlFile(String xmlFilePath) throws BoundesuWordsException {
        if (StringUtils.isBlank(xmlFilePath)) {
            throw new BoundesuWordsException("XML文件路径不能为空");
        }
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(false);
            
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new File(xmlFilePath));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new BoundesuWordsException("解析XML文件失败: " + xmlFilePath, e);
        }
    }
    
    /**
     * 将XML文档转换为字符串
     * 
     * @param document XML文档
     * @return XML字符串
     * @throws BoundesuWordsException 转换异常
     */
    public static String documentToString(Document document) throws BoundesuWordsException {
        return documentToString(document, true);
    }
    
    /**
     * 将XML文档转换为字符串
     * 
     * @param document XML文档
     * @param prettyPrint 是否格式化输出
     * @return XML字符串
     * @throws BoundesuWordsException 转换异常
     */
    public static String documentToString(Document document, boolean prettyPrint) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("XML文档不能为空");
        }
        
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            
            if (prettyPrint) {
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            }
            
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));
            
            return writer.toString();
        } catch (TransformerException e) {
            throw new BoundesuWordsException("XML文档转换为字符串失败", e);
        }
    }
    
    /**
     * 将XML文档保存到文件
     * 
     * @param document XML文档
     * @param filePath 文件路径
     * @throws BoundesuWordsException 保存异常
     */
    public static void saveDocumentToFile(Document document, String filePath) throws BoundesuWordsException {
        saveDocumentToFile(document, filePath, true);
    }
    
    /**
     * 将XML文档保存到文件
     * 
     * @param document XML文档
     * @param filePath 文件路径
     * @param prettyPrint 是否格式化输出
     * @throws BoundesuWordsException 保存异常
     */
    public static void saveDocumentToFile(Document document, String filePath, boolean prettyPrint) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("XML文档不能为空");
        }
        
        if (StringUtils.isBlank(filePath)) {
            throw new BoundesuWordsException("文件路径不能为空");
        }
        
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            
            if (prettyPrint) {
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            }
            
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            
            transformer.transform(new DOMSource(document), new StreamResult(new File(filePath)));
        } catch (TransformerException e) {
            throw new BoundesuWordsException("保存XML文档到文件失败: " + filePath, e);
        }
    }
    
    /**
     * 创建新的XML文档
     * 
     * @return 新的XML文档
     * @throws BoundesuWordsException 创建异常
     */
    public static Document createDocument() throws BoundesuWordsException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.newDocument();
        } catch (ParserConfigurationException e) {
            throw new BoundesuWordsException("创建XML文档失败", e);
        }
    }
    
    /**
     * 创建新的XML文档，带根元素
     * 
     * @param rootElementName 根元素名称
     * @return 新的XML文档
     * @throws BoundesuWordsException 创建异常
     */
    public static Document createDocument(String rootElementName) throws BoundesuWordsException {
        if (StringUtils.isBlank(rootElementName)) {
            throw new BoundesuWordsException("根元素名称不能为空");
        }
        
        Document document = createDocument();
        Element rootElement = document.createElement(rootElementName);
        document.appendChild(rootElement);
        return document;
    }
    
    /**
     * 获取元素的文本内容
     * 
     * @param element 元素
     * @return 文本内容
     */
    public static String getElementText(Element element) {
        if (element == null) {
            return "";
        }
        return element.getTextContent();
    }
    
    /**
     * 设置元素的文本内容
     * 
     * @param element 元素
     * @param text 文本内容
     */
    public static void setElementText(Element element, String text) {
        if (element != null) {
            element.setTextContent(text != null ? text : "");
        }
    }
    
    /**
     * 获取元素的属性值
     * 
     * @param element 元素
     * @param attributeName 属性名
     * @return 属性值
     */
    public static String getAttributeValue(Element element, String attributeName) {
        if (element == null || StringUtils.isBlank(attributeName)) {
            return "";
        }
        return element.getAttribute(attributeName);
    }
    
    /**
     * 设置元素的属性值
     * 
     * @param element 元素
     * @param attributeName 属性名
     * @param attributeValue 属性值
     */
    public static void setAttributeValue(Element element, String attributeName, String attributeValue) {
        if (element != null && StringUtils.isNotBlank(attributeName)) {
            element.setAttribute(attributeName, attributeValue != null ? attributeValue : "");
        }
    }
    
    /**
     * 获取子元素列表
     * 
     * @param parent 父元素
     * @param tagName 标签名
     * @return 子元素列表
     */
    public static List<Element> getChildElements(Element parent, String tagName) {
        List<Element> children = new ArrayList<>();
        
        if (parent == null || StringUtils.isBlank(tagName)) {
            return children;
        }
        
        NodeList nodeList = parent.getElementsByTagName(tagName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element && node.getParentNode() == parent) {
                children.add((Element) node);
            }
        }
        
        return children;
    }
    
    /**
     * 获取第一个子元素
     * 
     * @param parent 父元素
     * @param tagName 标签名
     * @return 第一个子元素，如果不存在则返回null
     */
    public static Element getFirstChildElement(Element parent, String tagName) {
        List<Element> children = getChildElements(parent, tagName);
        return children.isEmpty() ? null : children.get(0);
    }
    
    /**
     * 创建子元素
     * 
     * @param parent 父元素
     * @param tagName 标签名
     * @return 新创建的子元素
     */
    public static Element createChildElement(Element parent, String tagName) {
        if (parent == null || StringUtils.isBlank(tagName)) {
            return null;
        }
        
        Document document = parent.getOwnerDocument();
        Element child = document.createElement(tagName);
        parent.appendChild(child);
        return child;
    }
    
    /**
     * 创建子元素并设置文本内容
     * 
     * @param parent 父元素
     * @param tagName 标签名
     * @param textContent 文本内容
     * @return 新创建的子元素
     */
    public static Element createChildElement(Element parent, String tagName, String textContent) {
        Element child = createChildElement(parent, tagName);
        if (child != null) {
            setElementText(child, textContent);
        }
        return child;
    }
    
    /**
     * 移除子元素
     * 
     * @param parent 父元素
     * @param child 要移除的子元素
     */
    public static void removeChildElement(Element parent, Element child) {
        if (parent != null && child != null && child.getParentNode() == parent) {
            parent.removeChild(child);
        }
    }
    
    /**
     * 检查XML是否格式良好
     * 
     * @param xmlContent XML内容
     * @return 是否格式良好
     */
    public static boolean isWellFormed(String xmlContent) {
        if (StringUtils.isBlank(xmlContent)) {
            return false;
        }
        
        try {
            parseXml(xmlContent);
            return true;
        } catch (BoundesuWordsException e) {
            return false;
        }
    }
    
    /**
     * 验证XML文件是否格式良好
     * 
     * @param xmlFilePath XML文件路径
     * @return 是否格式良好
     */
    public static boolean isWellFormedFile(String xmlFilePath) {
        if (StringUtils.isBlank(xmlFilePath)) {
            return false;
        }
        
        try {
            parseXmlFile(xmlFilePath);
            return true;
        } catch (BoundesuWordsException e) {
            return false;
        }
    }
    
    /**
     * 格式化XML字符串
     * 
     * @param xmlContent XML内容
     * @return 格式化后的XML字符串
     * @throws BoundesuWordsException 格式化异常
     */
    public static String formatXml(String xmlContent) throws BoundesuWordsException {
        Document document = parseXml(xmlContent);
        return documentToString(document, true);
    }
    
    /**
     * 压缩XML字符串（移除格式化）
     * 
     * @param xmlContent XML内容
     * @return 压缩后的XML字符串
     * @throws BoundesuWordsException 压缩异常
     */
    public static String compressXml(String xmlContent) throws BoundesuWordsException {
        Document document = parseXml(xmlContent);
        return documentToString(document, false);
    }
}