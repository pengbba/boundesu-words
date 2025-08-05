package com.boundesu.words.common.constants;

/**
 * Word文档核心常量类
 * 提供Microsoft Word相关的标准常量定义
 * 
 * @author Boundesu Team
 * @version 1.0.0
 */
public class WordConstants {

    // Microsoft Word版本枚举已提取到单独的MsWordVersion类中
    // 请使用: com.boundesu.words.common.constants.MsWordVersion

    // 文档格式常量
    public static final String DOCUMENT_FORMAT_DOCX = "docx";
    public static final String DOCUMENT_FORMAT_DOC = "doc";
    public static final String DOCUMENT_FORMAT_PDF = "pdf";
    public static final String DOCUMENT_FORMAT_HTML = "html";
    public static final String DOCUMENT_FORMAT_XML = "xml";

    // MIME类型常量
    public static final String MIME_TYPE_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    public static final String MIME_TYPE_DOC = "application/msword";
    public static final String MIME_TYPE_PDF = "application/pdf";
    public static final String MIME_TYPE_HTML = "text/html";
    public static final String MIME_TYPE_XML = "application/xml";

    // 编码格式常量
    public static final String ENCODING_UTF8 = "UTF-8";
    public static final String ENCODING_GBK = "GBK";
    public static final String ENCODING_GB2312 = "GB2312";
    public static final String ENCODING_ISO_8859_1 = "ISO-8859-1";

    // 字体系列常量
    public static final String FONT_FAMILY_SONG = "宋体";
    public static final String FONT_FAMILY_HEI = "黑体";
    public static final String FONT_FAMILY_KAI = "楷体";
    public static final String FONT_FAMILY_FANGSONG = "仿宋";
    public static final String FONT_FAMILY_ARIAL = "Arial";
    public static final String FONT_FAMILY_TIMES = "Times New Roman";
    public static final String FONT_FAMILY_CALIBRI = "Calibri";

    // 字体大小常量
    public static final int FONT_SIZE_SMALL = 9;
    public static final int FONT_SIZE_NORMAL = 12;
    public static final int FONT_SIZE_LARGE = 14;
    public static final int FONT_SIZE_XLARGE = 16;
    public static final int FONT_SIZE_XXLARGE = 18;
    public static final int FONT_SIZE_TITLE = 22;

    // 页面尺寸常量（磅）
    public static final double PAGE_SIZE_A4_WIDTH = 595.0;
    public static final double PAGE_SIZE_A4_HEIGHT = 842.0;
    public static final double PAGE_SIZE_A3_WIDTH = 842.0;
    public static final double PAGE_SIZE_A3_HEIGHT = 1191.0;
    public static final double PAGE_SIZE_LETTER_WIDTH = 612.0;
    public static final double PAGE_SIZE_LETTER_HEIGHT = 792.0;

    // 边距常量（磅）
    public static final double MARGIN_NORMAL = 72.0;    // 1英寸
    public static final double MARGIN_NARROW = 36.0;    // 0.5英寸
    public static final double MARGIN_WIDE = 108.0;     // 1.5英寸
    public static final double MARGIN_MODERATE = 54.0;  // 0.75英寸

    // 行间距常量
    public static final double LINE_SPACING_SINGLE = 1.0;
    public static final double LINE_SPACING_1_5 = 1.5;
    public static final double LINE_SPACING_DOUBLE = 2.0;
    public static final double LINE_SPACING_MULTIPLE = 1.15;

    // 颜色常量
    public static final String COLOR_BLACK = "000000";
    public static final String COLOR_WHITE = "FFFFFF";
    public static final String COLOR_RED = "FF0000";
    public static final String COLOR_GREEN = "00FF00";
    public static final String COLOR_BLUE = "0000FF";
    public static final String COLOR_GRAY = "808080";

    // 样式名称常量
    public static final String STYLE_NAME_NORMAL = "Normal";
    public static final String STYLE_NAME_HEADING1 = "Heading 1";
    public static final String STYLE_NAME_HEADING2 = "Heading 2";
    public static final String STYLE_NAME_HEADING3 = "Heading 3";
    public static final String STYLE_NAME_TITLE = "Title";
    public static final String STYLE_NAME_SUBTITLE = "Subtitle";

    // 表格样式常量
    public static final String TABLE_STYLE_GRID = "Table Grid";
    public static final String TABLE_STYLE_SIMPLE = "Table Simple";
    public static final String TABLE_STYLE_CLASSIC = "Table Classic";
    public static final String TABLE_STYLE_MODERN = "Table Modern";

    // 单位转换常量
    public static final double POINTS_PER_INCH = 72.0;
    public static final double POINTS_PER_CM = 28.35;
    public static final double POINTS_PER_MM = 2.835;

    // 私有构造函数，防止实例化
    private WordConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}