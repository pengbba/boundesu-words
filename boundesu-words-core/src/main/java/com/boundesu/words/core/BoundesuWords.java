package com.boundesu.words.core;

import com.boundesu.words.common.exception.BoundesuWordsException;
import com.boundesu.words.core.service.BoundesuWordsService;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;

/**
 * Boundesu Words SDK 主入口类
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class BoundesuWords {
    
    private static final Logger log = LoggerFactory.getLogger(BoundesuWords.class);
    
    private final BoundesuWordsService service;
    
    /**
     * 构造函数
     */
    public BoundesuWords() {
        this.service = new BoundesuWordsService();
        log.info("Boundesu Words SDK 初始化完成");
    }
    
    /**
     * 获取SDK版本信息
     * 
     * @return SDK信息
     */
    public static Map<String, String> getSDKInfo() {
        return BoundesuWordsService.getSDKInfo();
    }
    
    /**
     * 获取SDK版本号
     * 
     * @return 版本号
     */
    public static String getVersion() {
        return BoundesuWordsService.getVersion();
    }
    
    /**
     * 将HTML内容转换为DOCX文档
     * 
     * @param htmlContent HTML内容
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument htmlToDocx(String htmlContent) throws BoundesuWordsException {
        return service.convertHtmlToDocx(htmlContent);
    }
    
    /**
     * 将HTML文件转换为DOCX文档
     * 
     * @param htmlFile HTML文件
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument htmlToDocx(File htmlFile) throws BoundesuWordsException {
        return service.convertHtmlFileToDocx(htmlFile);
    }
    
    /**
     * 将XML内容转换为DOCX文档
     * 
     * @param xmlContent XML内容
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument xmlToDocx(String xmlContent) throws BoundesuWordsException {
        return service.convertXmlToDocx(xmlContent);
    }
    
    /**
     * 将XML文件转换为DOCX文档
     * 
     * @param xmlFile XML文件
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument xmlToDocx(File xmlFile) throws BoundesuWordsException {
        return service.convertXmlFileToDocx(xmlFile);
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
     * 自动识别文件类型并转换为DOCX文档
     * 
     * @param inputFilePath 输入文件路径
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument convertToDocx(String inputFilePath) throws BoundesuWordsException {
        return service.convertToDocx(new File(inputFilePath));
    }
    
    /**
     * 保存DOCX文档到文件
     * 
     * @param document DOCX文档
     * @param outputFile 输出文件
     * @throws BoundesuWordsException 保存异常
     */
    public void saveToFile(XWPFDocument document, File outputFile) throws BoundesuWordsException {
        service.saveDocxToFile(document, outputFile);
    }
    
    /**
     * 保存DOCX文档到文件
     * 
     * @param document DOCX文档
     * @param outputFilePath 输出文件路径
     * @throws BoundesuWordsException 保存异常
     */
    public void saveToFile(XWPFDocument document, String outputFilePath) throws BoundesuWordsException {
        service.saveDocxToFile(document, new File(outputFilePath));
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
}