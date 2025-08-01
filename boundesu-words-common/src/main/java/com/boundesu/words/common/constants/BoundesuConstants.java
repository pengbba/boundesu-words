package com.boundesu.words.common.constants;

/**
 * Boundesu Words SDK 常量定义
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class BoundesuConstants {
    
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
     * 文档格式常量
     */
    public static final String FORMAT_HTML = "html";
    public static final String FORMAT_XML = "xml";
    public static final String FORMAT_DOCX = "docx";
    public static final String FORMAT_DOC = "doc";
    
    /**
     * 编码常量
     */
    public static final String ENCODING_UTF8 = "UTF-8";
    public static final String ENCODING_GBK = "GBK";
    
    /**
     * 文件扩展名常量
     */
    public static final String EXT_HTML = ".html";
    public static final String EXT_XML = ".xml";
    public static final String EXT_DOCX = ".docx";
    public static final String EXT_DOC = ".doc";
    
    /**
     * 私有构造函数，防止实例化
     */
    private BoundesuConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}