package com.boundesu.words;

import com.boundesu.words.core.BoundesuWords;
import com.boundesu.words.service.BoundesuWordsService;
import com.boundesu.words.common.exception.BoundesuWordsException;
import com.boundesu.words.html.converter.HtmlToDocxConverter;
import com.boundesu.words.xml.converter.XmlToDocxConverter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Boundesu Words SDK 全功能聚合类
 * 提供所有模块的功能，用户只需导入这一个包即可使用所有功能
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class BoundesuWordsAll {
    
    private static final Logger log = LoggerFactory.getLogger(BoundesuWordsAll.class);
    
    private final BoundesuWords coreService;
    private final BoundesuWordsService service;
    private final HtmlToDocxConverter htmlConverter;
    private final XmlToDocxConverter xmlConverter;
    
    /**
     * 构造函数
     */
    public BoundesuWordsAll() {
        this.coreService = new BoundesuWords();
        this.service = new BoundesuWordsService();
        this.htmlConverter = new HtmlToDocxConverter();
        this.xmlConverter = new XmlToDocxConverter();
        log.info("Boundesu Words All SDK 初始化完成");
    }
    
    /**
     * 获取SDK版本信息
     * 
     * @return SDK信息
     */
    public static Map<String, String> getSDKInfo() {
        return BoundesuWords.getSDKInfo();
    }
    
    /**
     * 获取SDK版本号
     * 
     * @return 版本号
     */
    public static String getVersion() {
        return BoundesuWords.getVersion();
    }
    
    // ==================== 核心功能 ====================
    
    /**
     * 将HTML内容转换为DOCX文档
     * 
     * @param htmlContent HTML内容
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument htmlToDocx(String htmlContent) throws BoundesuWordsException {
        return htmlConverter.convertHtmlToDocx(htmlContent);
    }
    
    /**
     * 将HTML文件转换为DOCX文档
     * 
     * @param htmlFile HTML文件
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument htmlToDocx(File htmlFile) throws BoundesuWordsException {
        try (FileInputStream fis = new FileInputStream(htmlFile)) {
            return htmlConverter.convertHtmlToDocx(fis);
        } catch (IOException e) {
            throw new BoundesuWordsException("读取HTML文件失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 将XML内容转换为DOCX文档
     * 
     * @param xmlContent XML内容
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument xmlToDocx(String xmlContent) throws BoundesuWordsException {
        return xmlConverter.convertXmlToDocx(xmlContent);
    }
    
    /**
     * 将XML文件转换为DOCX文档
     * 
     * @param xmlFile XML文件
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument xmlToDocx(File xmlFile) throws BoundesuWordsException {
        try (java.io.FileInputStream fis = new java.io.FileInputStream(xmlFile)) {
            return xmlConverter.convertXmlToDocx(fis);
        } catch (java.io.IOException e) {
            throw new BoundesuWordsException("FILE_READ_ERROR", "读取XML文件失败", e);
        }
    }
    
    /**
     * 自动识别文件类型并转换为DOCX文档
     * 
     * @param inputFile 输入文件
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument convertToDocx(File inputFile) throws BoundesuWordsException {
        return service.convertToDocx(inputFile);
    }
    
    /**
     * 保存DOCX文档到文件
     * 
     * @param document DOCX文档
     * @param outputFile 输出文件
     * @throws BoundesuWordsException 保存异常
     */
    public void saveToFile(XWPFDocument document, File outputFile) throws BoundesuWordsException {
        coreService.saveToFile(document, outputFile);
    }
    
    /**
     * 保存DOCX文档到文件
     * 
     * @param document DOCX文档
     * @param outputFilePath 输出文件路径
     * @throws BoundesuWordsException 保存异常
     */
    public void saveToFile(XWPFDocument document, String outputFilePath) throws BoundesuWordsException {
        coreService.saveToFile(document, outputFilePath);
    }
    
    /**
     * 一键转换：从输入文件转换并保存到输出文件
     * 
     * @param inputFile 输入文件
     * @param outputFile 输出文件
     * @throws BoundesuWordsException 转换异常
     */
    public void convert(File inputFile, File outputFile) throws BoundesuWordsException {
        XWPFDocument document = convertToDocx(inputFile);
        saveToFile(document, outputFile);
    }
    
    /**
     * 一键转换：从输入文件转换并保存到输出文件
     * 
     * @param inputFilePath 输入文件路径
     * @param outputFilePath 输出文件路径
     * @throws BoundesuWordsException 转换异常
     */
    public void convert(String inputFilePath, String outputFilePath) throws BoundesuWordsException {
        convert(new File(inputFilePath), new File(outputFilePath));
    }
    
    // ==================== 直接访问转换器 ====================
    
    /**
     * 获取HTML转换器实例
     * 
     * @return HTML转换器
     */
    public HtmlToDocxConverter getHtmlConverter() {
        return htmlConverter;
    }
    
    /**
     * 获取XML转换器实例
     * 
     * @return XML转换器
     */
    public XmlToDocxConverter getXmlConverter() {
        return xmlConverter;
    }
    
    /**
     * 获取核心服务实例
     * 
     * @return 核心服务
     */
    public BoundesuWords getCoreService() {
        return coreService;
    }
}