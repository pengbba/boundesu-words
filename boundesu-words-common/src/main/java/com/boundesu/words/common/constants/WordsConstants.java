package com.boundesu.words.common.constants;

/**
 * Boundesu Words SDK 通用常量
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class WordsConstants {
    
    /**
     * SDK版本信息
     */
    public static final String SDK_VERSION = "1.0.0";
    public static final String SDK_NAME = "Boundesu Words SDK";
    public static final String SDK_AUTHOR = "Boundesu Team";
    public static final String SDK_DESCRIPTION = "自主研发的文档处理SDK，不依赖第三方库";
    public static final String SDK_JDK = "8+";
    public static final String SDK_LICENSE = "MIT";
    
    /**
     * 文档类型常量
     */
    public static final String DOC_TYPE_HTML = "html";
    public static final String DOC_TYPE_XML = "xml";
    public static final String DOC_TYPE_DOCX = "docx";
    public static final String DOC_TYPE_PDF = "pdf";
    
    /**
     * 编码常量
     */
    public static final String CHARSET_UTF8 = "UTF-8";
    public static final String CHARSET_GBK = "GBK";
    
    /**
     * 文件扩展名常量
     */
    public static final String EXT_HTML = ".html";
    public static final String EXT_XML = ".xml";
    public static final String EXT_DOCX = ".docx";
    public static final String EXT_PDF = ".pdf";
    
    /**
     * 私有构造函数，防止实例化
     */
    private WordsConstants() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}