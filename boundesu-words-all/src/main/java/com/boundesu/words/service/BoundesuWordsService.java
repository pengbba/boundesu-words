package com.boundesu.words.service;

import com.boundesu.words.common.constants.BoundesuConstants;
import com.boundesu.words.common.exception.BoundesuWordsException;
import com.boundesu.words.common.util.StringUtils;
import com.boundesu.words.html.converter.HtmlToDocxConverter;
import com.boundesu.words.xml.converter.XmlToDocxConverter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Boundesu Words 核心服务类
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class BoundesuWordsService {
    
    private static final Logger log = LoggerFactory.getLogger(BoundesuWordsService.class);
    
    private final HtmlToDocxConverter htmlConverter;
    private final XmlToDocxConverter xmlConverter;
    
    /**
     * 构造函数
     */
    public BoundesuWordsService() {
        this.htmlConverter = new HtmlToDocxConverter();
        this.xmlConverter = new XmlToDocxConverter();
    }
    
    /**
     * 获取SDK版本信息
     * 
     * @return SDK信息
     */
    public static Map<String, String> getSDKInfo() {
        Map<String, String> info = new LinkedHashMap<>();
        info.put("name", BoundesuConstants.SDK_NAME);
        info.put("version", BoundesuConstants.SDK_VERSION);
        info.put("author", BoundesuConstants.SDK_AUTHOR);
        info.put("description", BoundesuConstants.SDK_DESCRIPTION);
        info.put("jdk", BoundesuConstants.SDK_JDK);
        info.put("license", BoundesuConstants.SDK_LICENSE);
        return info;
    }
    
    /**
     * 获取SDK版本号
     * 
     * @return 版本号
     */
    public static String getVersion() {
        return BoundesuConstants.SDK_VERSION;
    }
    
    /**
     * 将HTML内容转换为DOCX文档
     * 
     * @param htmlContent HTML内容
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument convertHtmlToDocx(String htmlContent) throws BoundesuWordsException {
        if (StringUtils.isBlank(htmlContent)) {
            throw new BoundesuWordsException("INVALID_INPUT", "HTML内容不能为空");
        }
        
        log.info("开始HTML到DOCX转换");
        return htmlConverter.convertHtmlToDocx(htmlContent);
    }
    
    /**
     * 将HTML文件转换为DOCX文档
     * 
     * @param htmlFile HTML文件
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument convertHtmlFileToDocx(File htmlFile) throws BoundesuWordsException {
        if (htmlFile == null || !htmlFile.exists()) {
            throw new BoundesuWordsException("FILE_NOT_FOUND", "HTML文件不存在");
        }
        
        try (FileInputStream fis = new FileInputStream(htmlFile)) {
            log.info("开始HTML文件到DOCX转换: {}", htmlFile.getAbsolutePath());
            return htmlConverter.convertHtmlToDocx(fis);
        } catch (IOException e) {
            throw new BoundesuWordsException("FILE_READ_ERROR", "读取HTML文件失败", e);
        }
    }
    
    /**
     * 将XML内容转换为DOCX文档
     * 
     * @param xmlContent XML内容
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument convertXmlToDocx(String xmlContent) throws BoundesuWordsException {
        if (StringUtils.isBlank(xmlContent)) {
            throw new BoundesuWordsException("INVALID_INPUT", "XML内容不能为空");
        }
        
        log.info("开始XML到DOCX转换");
        return xmlConverter.convertXmlToDocx(xmlContent);
    }
    
    /**
     * 将XML文件转换为DOCX文档
     * 
     * @param xmlFile XML文件
     * @return DOCX文档
     * @throws BoundesuWordsException 转换异常
     */
    public XWPFDocument convertXmlFileToDocx(File xmlFile) throws BoundesuWordsException {
        if (xmlFile == null || !xmlFile.exists()) {
            throw new BoundesuWordsException("FILE_NOT_FOUND", "XML文件不存在");
        }
        
        try (FileInputStream fis = new FileInputStream(xmlFile)) {
            log.info("开始XML文件到DOCX转换: {}", xmlFile.getAbsolutePath());
            return xmlConverter.convertXmlToDocx(fis);
        } catch (IOException e) {
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
        if (inputFile == null || !inputFile.exists()) {
            throw new BoundesuWordsException("FILE_NOT_FOUND", "输入文件不存在");
        }
        
        String fileName = inputFile.getName().toLowerCase();
        
        if (fileName.endsWith(BoundesuConstants.EXT_HTML)) {
            return convertHtmlFileToDocx(inputFile);
        } else if (fileName.endsWith(BoundesuConstants.EXT_XML)) {
            return convertXmlFileToDocx(inputFile);
        } else {
            throw new BoundesuWordsException("UNSUPPORTED_FORMAT", "不支持的文件格式: " + fileName);
        }
    }
    
    /**
     * 保存DOCX文档到文件
     * 
     * @param document DOCX文档
     * @param outputFile 输出文件
     * @throws BoundesuWordsException 保存异常
     */
    public void saveDocxToFile(XWPFDocument document, File outputFile) throws BoundesuWordsException {
        if (document == null) {
            throw new BoundesuWordsException("INVALID_INPUT", "DOCX文档不能为空");
        }
        
        if (outputFile == null) {
            throw new BoundesuWordsException("INVALID_INPUT", "输出文件路径不能为空");
        }
        
        try {
            // 确保父目录存在
            File parentDir = outputFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            
            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                document.write(fos);
                log.info("DOCX文档已保存到: {}", outputFile.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new BoundesuWordsException("FILE_WRITE_ERROR", "保存DOCX文档失败", e);
        }
    }
}