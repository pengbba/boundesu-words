package com.boundesu.words.common.constant;

/**
 * DOCX文档相关常量
 *
 * @author Boundesu
 * @version 1.0.0
 */
public final class DocxConstants {

    /**
     * 私有构造函数，防止实例化
     */
    private DocxConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // ========== 文档限制常量 ==========
    
    /** 最大文档标题长度 */
    public static final int MAX_TITLE_LENGTH = 255;
    
    /** 最大段落内容长度 */
    public static final int MAX_PARAGRAPH_LENGTH = 32767;
    
    /** 最大标题级别 */
    public static final int MAX_HEADING_LEVEL = 9;
    
    /** 最小标题级别 */
    public static final int MIN_HEADING_LEVEL = 1;
    
    /** 最大表格行数 */
    public static final int MAX_TABLE_ROWS = 1000;
    
    /** 最大表格列数 */
    public static final int MAX_TABLE_COLUMNS = 63;
    
    /** 最大文件名长度 */
    public static final int MAX_FILENAME_LENGTH = 255;
    
    /** 最大作者名长度 */
    public static final int MAX_AUTHOR_LENGTH = 255;
    
    /** 最大关键词长度 */
    public static final int MAX_KEYWORD_LENGTH = 100;
    
    // ========== 默认值常量 ==========
    
    /** 默认字体名称 */
    public static final String DEFAULT_FONT_FAMILY = "宋体";
    
    /** 默认英文字体名称 */
    public static final String DEFAULT_ENGLISH_FONT_FAMILY = "Times New Roman";
    
    /** 默认字体大小 */
    public static final int DEFAULT_FONT_SIZE = 12;
    
    /** 默认标题字体大小 */
    public static final int DEFAULT_HEADING_FONT_SIZE = 16;
    
    /** 默认行间距 */
    public static final double DEFAULT_LINE_SPACING = 1.5;
    
    /** 默认页边距（磅） */
    public static final double DEFAULT_PAGE_MARGIN = 72.0; // 1英寸
    
    /** 默认文档作者 */
    public static final String DEFAULT_AUTHOR = "Boundesu Words SDK";
    
    /** 默认文档标题 */
    public static final String DEFAULT_TITLE = "Untitled Document";
    
    // ========== 文件格式常量 ==========
    
    /** DOCX文件扩展名 */
    public static final String DOCX_EXTENSION = ".docx";
    
    /** HTML文件扩展名 */
    public static final String HTML_EXTENSION = ".html";
    
    /** XML文件扩展名 */
    public static final String XML_EXTENSION = ".xml";
    
    /** 支持的文档格式 */
    public static final String[] SUPPORTED_FORMATS = {"docx", "html", "xml"};
    
    // ========== 样式常量 ==========
    
    /** 标题样式前缀 */
    public static final String HEADING_STYLE_PREFIX = "Heading";
    
    /** 正文样式名称 */
    public static final String NORMAL_STYLE = "Normal";
    
    /** 表格样式名称 */
    public static final String TABLE_STYLE = "TableGrid";
    
    /** 列表样式名称 */
    public static final String LIST_STYLE = "ListParagraph";
    
    // ========== 颜色常量 ==========
    
    /** 默认文本颜色（黑色） */
    public static final String DEFAULT_TEXT_COLOR = "000000";
    
    /** 标题文本颜色（深蓝色） */
    public static final String HEADING_TEXT_COLOR = "1F4E79";
    
    /** 链接文本颜色（蓝色） */
    public static final String LINK_TEXT_COLOR = "0563C1";
    
    /** 表格边框颜色（灰色） */
    public static final String TABLE_BORDER_COLOR = "A6A6A6";
    
    // ========== 错误消息常量 ==========
    
    /** 标题长度超限错误 */
    public static final String ERROR_TITLE_TOO_LONG = "文档标题长度不能超过 " + MAX_TITLE_LENGTH + " 个字符";
    
    /** 段落内容超限错误 */
    public static final String ERROR_PARAGRAPH_TOO_LONG = "段落内容长度不能超过 " + MAX_PARAGRAPH_LENGTH + " 个字符";
    
    /** 标题级别无效错误 */
    public static final String ERROR_INVALID_HEADING_LEVEL = "标题级别必须在 " + MIN_HEADING_LEVEL + " 到 " + MAX_HEADING_LEVEL + " 之间";
    
    /** 表格尺寸超限错误 */
    public static final String ERROR_TABLE_TOO_LARGE = "表格行数不能超过 " + MAX_TABLE_ROWS + "，列数不能超过 " + MAX_TABLE_COLUMNS;
    
    /** 文件路径无效错误 */
    public static final String ERROR_INVALID_FILE_PATH = "文件路径无效或为空";
    
    /** 不支持的文件格式错误 */
    public static final String ERROR_UNSUPPORTED_FORMAT = "不支持的文件格式";
    
    /** 文档内容为空错误 */
    public static final String ERROR_EMPTY_CONTENT = "文档内容不能为空";
    
    /** 关键词长度超限错误 */
    public static final String ERROR_KEYWORD_TOO_LONG = "关键词长度不能超过 " + MAX_KEYWORD_LENGTH + " 个字符";
    
    // ========== 单位转换常量 ==========
    
    /** 磅到像素的转换比例 */
    public static final double POINTS_TO_PIXELS = 1.33;
    
    /** 英寸到磅的转换比例 */
    public static final double INCHES_TO_POINTS = 72.0;
    
    /** 厘米到磅的转换比例 */
    public static final double CM_TO_POINTS = 28.35;
    
    /** 毫米到磅的转换比例 */
    public static final double MM_TO_POINTS = 2.835;
    
    // ========== 性能相关常量 ==========
    
    /** 默认缓冲区大小 */
    public static final int DEFAULT_BUFFER_SIZE = 8192;
    
    /** 最大内存使用量（MB） */
    public static final int MAX_MEMORY_USAGE_MB = 512;
    
    /** 批处理默认大小 */
    public static final int DEFAULT_BATCH_SIZE = 100;
    
    /** 超时时间（毫秒） */
    public static final long DEFAULT_TIMEOUT_MS = 30000;
}