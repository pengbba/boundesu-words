package com.boundesu.words.common.constants;

/**
 * DOCX导出相关常量
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public final class DocxConstants {

    // 防止实例化
    private DocxConstants() {
        throw new UnsupportedOperationException("常量类不能被实例化");
    }

    // 文档格式常量
    public static final String FORMAT_DOCX = "docx";
    public static final String FORMAT_HTML = "html";
    public static final String FORMAT_MARKDOWN = "markdown";
    public static final String FORMAT_TEXT = "text";

    // 文件扩展名
    public static final String DOCX_EXTENSION = ".docx";
    public static final String HTML_EXTENSION = ".html";
    public static final String MD_EXTENSION = ".md";
    public static final String TXT_EXTENSION = ".txt";

    // 默认样式设置
    public static final String DEFAULT_FONT_FAMILY = "宋体";
    public static final int DEFAULT_FONT_SIZE = 12;
    public static final String DEFAULT_HEADING_FONT = "黑体";

    // 标题级别
    public static final int HEADING_LEVEL_1 = 1;
    public static final int HEADING_LEVEL_2 = 2;
    public static final int HEADING_LEVEL_3 = 3;
    public static final int HEADING_LEVEL_4 = 4;
    public static final int HEADING_LEVEL_5 = 5;
    public static final int HEADING_LEVEL_6 = 6;
    public static final int MAX_HEADING_LEVEL = 6;
    public static final int MIN_FONT_SIZE = 8;

    // 表格相关常量
    public static final int MIN_TABLE_ROWS = 1;
    public static final int MIN_TABLE_COLS = 1;
    public static final int MAX_TABLE_ROWS = 1000;
    public static final int MAX_TABLE_COLS = 50;

    // 文档属性默认值
    public static final String DEFAULT_AUTHOR = "Boundesu Words SDK";
    public static final String DEFAULT_CREATOR = "Boundesu Words SDK v1.0.0";
    public static final String DEFAULT_SUBJECT = "使用Boundesu Words SDK创建的文档";

    // 错误消息常量
    public static final String ERROR_INVALID_FORMAT = "不支持的文档格式: %s";
    public static final String ERROR_FILE_NOT_FOUND = "文件不存在: %s";
    public static final String ERROR_INVALID_TABLE_INDEX = "表格索引超出范围: %d";
    public static final String ERROR_INVALID_HEADING_LEVEL = "标题级别必须在1-6之间: %d";
    public static final String ERROR_INVALID_TABLE_SIZE = "表格大小无效: %d行 x %d列";

    // 文档限制常量
    public static final int MAX_DOCUMENT_TITLE_LENGTH = 255;
    public static final int MAX_PARAGRAPH_LENGTH = 10000;
    public static final int MAX_KEYWORDS_COUNT = 20;

    // 输出目录常量
    public static final String OUTPUT_DIRECTORY = "output";
    public static final String DEFAULT_OUTPUT_DIR = "output";

    // 字体大小常量
    public static final int TITLE_FONT_SIZE = 24;
    public static final int HEADING_1_SIZE = 20;
    public static final int HEADING_2_SIZE = 18;
    public static final int HEADING_3_SIZE = 16;
    public static final int HEADING_DEFAULT_SIZE = 14;
    public static final int TABLE_TITLE_SIZE = 14;
    public static final int TABLE_CELL_SIZE = 11;
    public static final int SMALL_FONT_SIZE = 10;

    // 颜色常量
    public static final String INFO_TEXT_COLOR = "666666";

    // 表格宽度常量
    public static final int TABLE_DEFAULT_WIDTH = 9000;

    // 分隔符常量
    public static final String SEPARATOR_LINE = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";

    // 操作类型常量
    public static final String OPERATION_EXPORT_DOCX = "export_docx";

    // 日期格式
    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    // 表格样式
    public static final String TABLE_HEADER_COLOR = "D9E1F2";

    // 编码
    public static final String DEFAULT_ENCODING = "UTF-8";
}