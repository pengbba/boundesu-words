package com.boundesu.words.common.constants;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * 文档格式相关常量
 * 管理文档格式、编码、转换规则等配置
 * 
 * @author Boundesu Team
 * @version 1.0.0
 */
public final class FormatConstants {
    
    /**
     * 私有构造函数，防止实例化
     */
    private FormatConstants() {
        throw new UnsupportedOperationException("常量类不能被实例化");
    }
    
    // ========== 文档格式常量 ==========
    
    /** DOCX格式 */
    public static final String FORMAT_DOCX = "docx";
    
    /** DOC格式 */
    public static final String FORMAT_DOC = "doc";
    
    /** PDF格式 */
    public static final String FORMAT_PDF = "pdf";
    
    /** HTML格式 */
    public static final String FORMAT_HTML = "html";
    
    /** HTM格式 */
    public static final String FORMAT_HTM = "htm";
    
    /** TXT格式 */
    public static final String FORMAT_TXT = "txt";
    
    /** RTF格式 */
    public static final String FORMAT_RTF = "rtf";
    
    /** ODT格式 */
    public static final String FORMAT_ODT = "odt";
    
    /** XML格式 */
    public static final String FORMAT_XML = "xml";
    
    /** JSON格式 */
    public static final String FORMAT_JSON = "json";
    
    /** MARKDOWN格式 */
    public static final String FORMAT_MARKDOWN = "md";
    
    /** EPUB格式 */
    public static final String FORMAT_EPUB = "epub";
    
    // ========== 文件扩展名常量 ==========
    
    /** DOCX文件扩展名 */
    public static final String EXTENSION_DOCX = ".docx";
    
    /** DOC文件扩展名 */
    public static final String EXTENSION_DOC = ".doc";
    
    /** PDF文件扩展名 */
    public static final String EXTENSION_PDF = ".pdf";
    
    /** HTML文件扩展名 */
    public static final String EXTENSION_HTML = ".html";
    
    /** HTM文件扩展名 */
    public static final String EXTENSION_HTM = ".htm";
    
    /** TXT文件扩展名 */
    public static final String EXTENSION_TXT = ".txt";
    
    /** RTF文件扩展名 */
    public static final String EXTENSION_RTF = ".rtf";
    
    /** ODT文件扩展名 */
    public static final String EXTENSION_ODT = ".odt";
    
    /** XML文件扩展名 */
    public static final String EXTENSION_XML = ".xml";
    
    /** JSON文件扩展名 */
    public static final String EXTENSION_JSON = ".json";
    
    /** MARKDOWN文件扩展名 */
    public static final String EXTENSION_MARKDOWN = ".md";
    
    /** EPUB文件扩展名 */
    public static final String EXTENSION_EPUB = ".epub";
    
    // ========== 支持的格式数组 ==========
    
    /** 支持的输入格式 */
    public static final String[] SUPPORTED_INPUT_FORMATS = {
        FORMAT_DOCX, FORMAT_DOC, FORMAT_HTML, FORMAT_HTM, FORMAT_TXT, 
        FORMAT_RTF, FORMAT_ODT, FORMAT_XML, FORMAT_JSON, FORMAT_MARKDOWN
    };
    
    /** 支持的输出格式 */
    public static final String[] SUPPORTED_OUTPUT_FORMATS = {
        FORMAT_DOCX, FORMAT_PDF, FORMAT_HTML, FORMAT_TXT, FORMAT_RTF, 
        FORMAT_ODT, FORMAT_XML, FORMAT_JSON, FORMAT_MARKDOWN, FORMAT_EPUB
    };
    
    /** 支持的文档格式 */
    public static final String[] SUPPORTED_DOCUMENT_FORMATS = {
        FORMAT_DOCX, FORMAT_DOC, FORMAT_PDF, FORMAT_RTF, FORMAT_ODT
    };
    
    /** 支持的文本格式 */
    public static final String[] SUPPORTED_TEXT_FORMATS = {
        FORMAT_TXT, FORMAT_HTML, FORMAT_HTM, FORMAT_XML, FORMAT_JSON, FORMAT_MARKDOWN
    };
    
    /** 支持的扩展名 */
    public static final String[] SUPPORTED_EXTENSIONS = {
        EXTENSION_DOCX, EXTENSION_DOC, EXTENSION_PDF, EXTENSION_HTML, EXTENSION_HTM,
        EXTENSION_TXT, EXTENSION_RTF, EXTENSION_ODT, EXTENSION_XML, EXTENSION_JSON,
        EXTENSION_MARKDOWN, EXTENSION_EPUB
    };
    
    // ========== MIME类型常量 ==========
    
    /** DOCX MIME类型 */
    public static final String MIME_TYPE_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    
    /** DOC MIME类型 */
    public static final String MIME_TYPE_DOC = "application/msword";
    
    /** PDF MIME类型 */
    public static final String MIME_TYPE_PDF = "application/pdf";
    
    /** HTML MIME类型 */
    public static final String MIME_TYPE_HTML = "text/html";
    
    /** TXT MIME类型 */
    public static final String MIME_TYPE_TXT = "text/plain";
    
    /** RTF MIME类型 */
    public static final String MIME_TYPE_RTF = "application/rtf";
    
    /** ODT MIME类型 */
    public static final String MIME_TYPE_ODT = "application/vnd.oasis.opendocument.text";
    
    /** XML MIME类型 */
    public static final String MIME_TYPE_XML = "application/xml";
    
    /** JSON MIME类型 */
    public static final String MIME_TYPE_JSON = "application/json";
    
    /** MARKDOWN MIME类型 */
    public static final String MIME_TYPE_MARKDOWN = "text/markdown";
    
    /** EPUB MIME类型 */
    public static final String MIME_TYPE_EPUB = "application/epub+zip";
    
    // ========== 编码常量 ==========
    
    /** UTF-8编码 */
    public static final String ENCODING_UTF8 = "UTF-8";
    
    /** UTF-16编码 */
    public static final String ENCODING_UTF16 = "UTF-16";
    
    /** GBK编码 */
    public static final String ENCODING_GBK = "GBK";
    
    /** GB2312编码 */
    public static final String ENCODING_GB2312 = "GB2312";
    
    /** ISO-8859-1编码 */
    public static final String ENCODING_ISO_8859_1 = "ISO-8859-1";
    
    /** ASCII编码 */
    public static final String ENCODING_ASCII = "ASCII";
    
    /** 默认编码 */
    public static final String DEFAULT_ENCODING = ENCODING_UTF8;
    
    /** 支持的编码 */
    public static final String[] SUPPORTED_ENCODINGS = {
        ENCODING_UTF8, ENCODING_UTF16, ENCODING_GBK, ENCODING_GB2312, 
        ENCODING_ISO_8859_1, ENCODING_ASCII
    };
    
    // ========== 转换质量常量 ==========
    
    /** 高质量转换 */
    public static final String QUALITY_HIGH = "high";
    
    /** 中等质量转换 */
    public static final String QUALITY_MEDIUM = "medium";
    
    /** 低质量转换 */
    public static final String QUALITY_LOW = "low";
    
    /** 默认转换质量 */
    public static final String DEFAULT_QUALITY = QUALITY_MEDIUM;
    
    // ========== 转换模式常量 ==========
    
    /** 精确转换模式 */
    public static final String MODE_EXACT = "exact";
    
    /** 兼容转换模式 */
    public static final String MODE_COMPATIBLE = "compatible";
    
    /** 快速转换模式 */
    public static final String MODE_FAST = "fast";
    
    /** 默认转换模式 */
    public static final String DEFAULT_MODE = MODE_COMPATIBLE;
    
    // ========== 压缩级别常量 ==========
    
    /** 无压缩 */
    public static final int COMPRESSION_NONE = 0;
    
    /** 最快压缩 */
    public static final int COMPRESSION_FASTEST = 1;
    
    /** 快速压缩 */
    public static final int COMPRESSION_FAST = 3;
    
    /** 标准压缩 */
    public static final int COMPRESSION_NORMAL = 6;
    
    /** 最大压缩 */
    public static final int COMPRESSION_MAXIMUM = 9;
    
    /** 默认压缩级别 */
    public static final int DEFAULT_COMPRESSION = COMPRESSION_NORMAL;
    
    // ========== 格式特性常量 ==========
    
    /** 支持富文本格式 */
    public static final String FEATURE_RICH_TEXT = "rich_text";
    
    /** 支持图片 */
    public static final String FEATURE_IMAGES = "images";
    
    /** 支持表格 */
    public static final String FEATURE_TABLES = "tables";
    
    /** 支持页眉页脚 */
    public static final String FEATURE_HEADER_FOOTER = "header_footer";
    
    /** 支持目录 */
    public static final String FEATURE_TOC = "table_of_contents";
    
    /** 支持书签 */
    public static final String FEATURE_BOOKMARKS = "bookmarks";
    
    /** 支持超链接 */
    public static final String FEATURE_HYPERLINKS = "hyperlinks";
    
    /** 支持注释 */
    public static final String FEATURE_COMMENTS = "comments";
    
    /** 支持修订 */
    public static final String FEATURE_REVISIONS = "revisions";
    
    /** 支持密码保护 */
    public static final String FEATURE_PASSWORD_PROTECTION = "password_protection";
    
    // ========== 格式映射 ==========
    
    /** 格式到扩展名映射 */
    private static final Map<String, String> FORMAT_TO_EXTENSION_MAP = new HashMap<>();
    
    /** 扩展名到格式映射 */
    private static final Map<String, String> EXTENSION_TO_FORMAT_MAP = new HashMap<>();
    
    /** 格式到MIME类型映射 */
    private static final Map<String, String> FORMAT_TO_MIME_TYPE_MAP = new HashMap<>();
    
    /** 格式特性映射 */
    private static final Map<String, List<String>> FORMAT_FEATURES_MAP = new HashMap<>();
    
    static {
        // 初始化格式到扩展名映射
        FORMAT_TO_EXTENSION_MAP.put(FORMAT_DOCX, EXTENSION_DOCX);
        FORMAT_TO_EXTENSION_MAP.put(FORMAT_DOC, EXTENSION_DOC);
        FORMAT_TO_EXTENSION_MAP.put(FORMAT_PDF, EXTENSION_PDF);
        FORMAT_TO_EXTENSION_MAP.put(FORMAT_HTML, EXTENSION_HTML);
        FORMAT_TO_EXTENSION_MAP.put(FORMAT_HTM, EXTENSION_HTM);
        FORMAT_TO_EXTENSION_MAP.put(FORMAT_TXT, EXTENSION_TXT);
        FORMAT_TO_EXTENSION_MAP.put(FORMAT_RTF, EXTENSION_RTF);
        FORMAT_TO_EXTENSION_MAP.put(FORMAT_ODT, EXTENSION_ODT);
        FORMAT_TO_EXTENSION_MAP.put(FORMAT_XML, EXTENSION_XML);
        FORMAT_TO_EXTENSION_MAP.put(FORMAT_JSON, EXTENSION_JSON);
        FORMAT_TO_EXTENSION_MAP.put(FORMAT_MARKDOWN, EXTENSION_MARKDOWN);
        FORMAT_TO_EXTENSION_MAP.put(FORMAT_EPUB, EXTENSION_EPUB);
        
        // 初始化扩展名到格式映射
        EXTENSION_TO_FORMAT_MAP.put(EXTENSION_DOCX, FORMAT_DOCX);
        EXTENSION_TO_FORMAT_MAP.put(EXTENSION_DOC, FORMAT_DOC);
        EXTENSION_TO_FORMAT_MAP.put(EXTENSION_PDF, FORMAT_PDF);
        EXTENSION_TO_FORMAT_MAP.put(EXTENSION_HTML, FORMAT_HTML);
        EXTENSION_TO_FORMAT_MAP.put(EXTENSION_HTM, FORMAT_HTML);
        EXTENSION_TO_FORMAT_MAP.put(EXTENSION_TXT, FORMAT_TXT);
        EXTENSION_TO_FORMAT_MAP.put(EXTENSION_RTF, FORMAT_RTF);
        EXTENSION_TO_FORMAT_MAP.put(EXTENSION_ODT, FORMAT_ODT);
        EXTENSION_TO_FORMAT_MAP.put(EXTENSION_XML, FORMAT_XML);
        EXTENSION_TO_FORMAT_MAP.put(EXTENSION_JSON, FORMAT_JSON);
        EXTENSION_TO_FORMAT_MAP.put(EXTENSION_MARKDOWN, FORMAT_MARKDOWN);
        EXTENSION_TO_FORMAT_MAP.put(EXTENSION_EPUB, FORMAT_EPUB);
        
        // 初始化格式到MIME类型映射
        FORMAT_TO_MIME_TYPE_MAP.put(FORMAT_DOCX, MIME_TYPE_DOCX);
        FORMAT_TO_MIME_TYPE_MAP.put(FORMAT_DOC, MIME_TYPE_DOC);
        FORMAT_TO_MIME_TYPE_MAP.put(FORMAT_PDF, MIME_TYPE_PDF);
        FORMAT_TO_MIME_TYPE_MAP.put(FORMAT_HTML, MIME_TYPE_HTML);
        FORMAT_TO_MIME_TYPE_MAP.put(FORMAT_HTM, MIME_TYPE_HTML);
        FORMAT_TO_MIME_TYPE_MAP.put(FORMAT_TXT, MIME_TYPE_TXT);
        FORMAT_TO_MIME_TYPE_MAP.put(FORMAT_RTF, MIME_TYPE_RTF);
        FORMAT_TO_MIME_TYPE_MAP.put(FORMAT_ODT, MIME_TYPE_ODT);
        FORMAT_TO_MIME_TYPE_MAP.put(FORMAT_XML, MIME_TYPE_XML);
        FORMAT_TO_MIME_TYPE_MAP.put(FORMAT_JSON, MIME_TYPE_JSON);
        FORMAT_TO_MIME_TYPE_MAP.put(FORMAT_MARKDOWN, MIME_TYPE_MARKDOWN);
        FORMAT_TO_MIME_TYPE_MAP.put(FORMAT_EPUB, MIME_TYPE_EPUB);
        
        // 初始化格式特性映射
        FORMAT_FEATURES_MAP.put(FORMAT_DOCX, Arrays.asList(
            FEATURE_RICH_TEXT, FEATURE_IMAGES, FEATURE_TABLES, FEATURE_HEADER_FOOTER,
            FEATURE_TOC, FEATURE_BOOKMARKS, FEATURE_HYPERLINKS, FEATURE_COMMENTS,
            FEATURE_REVISIONS, FEATURE_PASSWORD_PROTECTION
        ));
        FORMAT_FEATURES_MAP.put(FORMAT_DOC, Arrays.asList(
            FEATURE_RICH_TEXT, FEATURE_IMAGES, FEATURE_TABLES, FEATURE_HEADER_FOOTER,
            FEATURE_TOC, FEATURE_BOOKMARKS, FEATURE_HYPERLINKS, FEATURE_COMMENTS
        ));
        FORMAT_FEATURES_MAP.put(FORMAT_PDF, Arrays.asList(
            FEATURE_RICH_TEXT, FEATURE_IMAGES, FEATURE_TABLES, FEATURE_HEADER_FOOTER,
            FEATURE_TOC, FEATURE_BOOKMARKS, FEATURE_HYPERLINKS, FEATURE_PASSWORD_PROTECTION
        ));
        FORMAT_FEATURES_MAP.put(FORMAT_HTML, Arrays.asList(
            FEATURE_RICH_TEXT, FEATURE_IMAGES, FEATURE_TABLES, FEATURE_HYPERLINKS
        ));
        FORMAT_FEATURES_MAP.put(FORMAT_RTF, Arrays.asList(
            FEATURE_RICH_TEXT, FEATURE_IMAGES, FEATURE_TABLES, FEATURE_HEADER_FOOTER
        ));
        FORMAT_FEATURES_MAP.put(FORMAT_ODT, Arrays.asList(
            FEATURE_RICH_TEXT, FEATURE_IMAGES, FEATURE_TABLES, FEATURE_HEADER_FOOTER,
            FEATURE_TOC, FEATURE_BOOKMARKS, FEATURE_HYPERLINKS
        ));
        FORMAT_FEATURES_MAP.put(FORMAT_TXT, Arrays.asList());
        FORMAT_FEATURES_MAP.put(FORMAT_XML, Arrays.asList());
        FORMAT_FEATURES_MAP.put(FORMAT_JSON, Arrays.asList());
        FORMAT_FEATURES_MAP.put(FORMAT_MARKDOWN, Arrays.asList(
            FEATURE_RICH_TEXT, FEATURE_IMAGES, FEATURE_TABLES, FEATURE_HYPERLINKS
        ));
        FORMAT_FEATURES_MAP.put(FORMAT_EPUB, Arrays.asList(
            FEATURE_RICH_TEXT, FEATURE_IMAGES, FEATURE_TABLES, FEATURE_TOC,
            FEATURE_BOOKMARKS, FEATURE_HYPERLINKS
        ));
    }
    
    // ========== 格式配置类 ==========
    
    /**
     * 格式配置类
     */
    public static class FormatConfig {
        private final String format;
        private final String encoding;
        private final String quality;
        private final String mode;
        private final int compression;
        private final boolean preserveFormatting;
        private final boolean includeImages;
        private final boolean includeTables;
        private final boolean includeHeaderFooter;
        
        public FormatConfig(String format, String encoding, String quality, String mode,
                           int compression, boolean preserveFormatting, boolean includeImages,
                           boolean includeTables, boolean includeHeaderFooter) {
            this.format = format;
            this.encoding = encoding;
            this.quality = quality;
            this.mode = mode;
            this.compression = compression;
            this.preserveFormatting = preserveFormatting;
            this.includeImages = includeImages;
            this.includeTables = includeTables;
            this.includeHeaderFooter = includeHeaderFooter;
        }
        
        // Getters
        public String getFormat() { return format; }
        public String getEncoding() { return encoding; }
        public String getQuality() { return quality; }
        public String getMode() { return mode; }
        public int getCompression() { return compression; }
        public boolean isPreserveFormatting() { return preserveFormatting; }
        public boolean isIncludeImages() { return includeImages; }
        public boolean isIncludeTables() { return includeTables; }
        public boolean isIncludeHeaderFooter() { return includeHeaderFooter; }
    }
    
    // ========== 工具方法 ==========
    
    /**
     * 根据格式获取扩展名
     */
    public static String getExtensionByFormat(String format) {
        return FORMAT_TO_EXTENSION_MAP.get(format);
    }
    
    /**
     * 根据扩展名获取格式
     */
    public static String getFormatByExtension(String extension) {
        return EXTENSION_TO_FORMAT_MAP.get(extension.toLowerCase());
    }
    
    /**
     * 根据格式获取MIME类型
     */
    public static String getMimeTypeByFormat(String format) {
        return FORMAT_TO_MIME_TYPE_MAP.get(format);
    }
    
    /**
     * 检查格式是否支持指定特性
     */
    public static boolean supportsFeature(String format, String feature) {
        List<String> features = FORMAT_FEATURES_MAP.get(format);
        return features != null && features.contains(feature);
    }
    
    /**
     * 获取格式支持的所有特性
     */
    public static List<String> getSupportedFeatures(String format) {
        return FORMAT_FEATURES_MAP.get(format);
    }
    
    /**
     * 检查是否为支持的输入格式
     */
    public static boolean isSupportedInputFormat(String format) {
        return Arrays.asList(SUPPORTED_INPUT_FORMATS).contains(format);
    }
    
    /**
     * 检查是否为支持的输出格式
     */
    public static boolean isSupportedOutputFormat(String format) {
        return Arrays.asList(SUPPORTED_OUTPUT_FORMATS).contains(format);
    }
    
    /**
     * 检查是否为支持的文档格式
     */
    public static boolean isSupportedDocumentFormat(String format) {
        return Arrays.asList(SUPPORTED_DOCUMENT_FORMATS).contains(format);
    }
    
    /**
     * 检查是否为支持的文本格式
     */
    public static boolean isSupportedTextFormat(String format) {
        return Arrays.asList(SUPPORTED_TEXT_FORMATS).contains(format);
    }
    
    /**
     * 检查是否为支持的扩展名
     */
    public static boolean isSupportedExtension(String extension) {
        return Arrays.asList(SUPPORTED_EXTENSIONS).contains(extension.toLowerCase());
    }
    
    /**
     * 从文件名获取扩展名
     */
    public static String getFileExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return "";
        }
        int lastDotIndex = filename.lastIndexOf('.');
        return lastDotIndex > 0 ? filename.substring(lastDotIndex) : "";
    }
    
    /**
     * 从文件名获取格式
     */
    public static String getFormatFromFilename(String filename) {
        String extension = getFileExtension(filename);
        return getFormatByExtension(extension);
    }
    
    // ========== 预设配置方法 ==========
    
    /**
     * 获取高质量DOCX配置
     */
    public static FormatConfig getHighQualityDocxConfig() {
        return new FormatConfig(FORMAT_DOCX, DEFAULT_ENCODING, QUALITY_HIGH, MODE_EXACT,
                               COMPRESSION_NORMAL, true, true, true, true);
    }
    
    /**
     * 获取快速转换配置
     */
    public static FormatConfig getFastConversionConfig(String format) {
        return new FormatConfig(format, DEFAULT_ENCODING, QUALITY_LOW, MODE_FAST,
                               COMPRESSION_FASTEST, false, false, false, false);
    }
    
    /**
     * 获取兼容性配置
     */
    public static FormatConfig getCompatibilityConfig(String format) {
        return new FormatConfig(format, DEFAULT_ENCODING, QUALITY_MEDIUM, MODE_COMPATIBLE,
                               COMPRESSION_NORMAL, true, true, true, false);
    }
    
    /**
     * 获取文本格式配置
     */
    public static FormatConfig getTextFormatConfig(String format, String encoding) {
        return new FormatConfig(format, encoding, QUALITY_HIGH, MODE_EXACT,
                               COMPRESSION_NONE, false, false, false, false);
    }
    
    /**
     * 获取默认配置
     */
    public static FormatConfig getDefaultConfig(String format) {
        return new FormatConfig(format, DEFAULT_ENCODING, DEFAULT_QUALITY, DEFAULT_MODE,
                               DEFAULT_COMPRESSION, true, true, true, true);
    }
}