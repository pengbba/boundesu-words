package com.boundesu.words.common.constants;

/**
 * 文档验证相关常量
 * 管理验证规则、错误消息、限制条件等配置
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public final class ValidationConstants {

    /**
     * 最大文件路径长度
     */
    public static final int MAX_FILE_PATH_LENGTH = 260;

    // ========== 文件路径验证常量 ==========
    /**
     * 最大文件名长度
     */
    public static final int MAX_FILENAME_LENGTH = 255;
    /**
     * 最小文件名长度
     */
    public static final int MIN_FILENAME_LENGTH = 1;
    /**
     * 文件名非法字符
     */
    public static final String[] INVALID_FILENAME_CHARS = {
            "<", ">", ":", "\"", "/", "\\", "|", "?", "*"
    };
    /**
     * 保留文件名（Windows）
     */
    public static final String[] RESERVED_FILENAMES = {
            "CON", "PRN", "AUX", "NUL", "COM1", "COM2", "COM3", "COM4", "COM5",
            "COM6", "COM7", "COM8", "COM9", "LPT1", "LPT2", "LPT3", "LPT4",
            "LPT5", "LPT6", "LPT7", "LPT8", "LPT9"
    };
    /**
     * 最大文档标题长度
     */
    public static final int MAX_TITLE_LENGTH = 255;

    // ========== 文档内容验证常量 ==========
    /**
     * 最小文档标题长度
     */
    public static final int MIN_TITLE_LENGTH = 1;
    /**
     * 最大段落长度
     */
    public static final int MAX_PARAGRAPH_LENGTH = 32767;
    /**
     * 最大文档页数
     */
    public static final int MAX_DOCUMENT_PAGES = 1000;
    /**
     * 最大文档字数
     */
    public static final int MAX_DOCUMENT_WORDS = 1000000;
    /**
     * 最大文档字符数
     */
    public static final int MAX_DOCUMENT_CHARACTERS = 10000000;
    /**
     * 最小表格行数
     */
    public static final int MIN_TABLE_ROWS = 1;

    // ========== 表格验证常量 ==========
    /**
     * 最大表格行数
     */
    public static final int MAX_TABLE_ROWS = 1000;
    /**
     * 最小表格列数
     */
    public static final int MIN_TABLE_COLUMNS = 1;
    /**
     * 最大表格列数
     */
    public static final int MAX_TABLE_COLUMNS = 63;
    /**
     * 最大单元格内容长度
     */
    public static final int MAX_CELL_CONTENT_LENGTH = 32767;
    /**
     * 最小标题级别
     */
    public static final int MIN_HEADING_LEVEL = 1;

    // ========== 标题验证常量 ==========
    /**
     * 最大标题级别
     */
    public static final int MAX_HEADING_LEVEL = 9;
    /**
     * 最大标题长度
     */
    public static final int MAX_HEADING_LENGTH = 255;
    /**
     * 最小标题长度
     */
    public static final int MIN_HEADING_LENGTH = 1;
    /**
     * 最小字体大小
     */
    public static final int MIN_FONT_SIZE = 1;

    // ========== 字体验证常量 ==========
    /**
     * 最大字体大小
     */
    public static final int MAX_FONT_SIZE = 409;
    /**
     * 默认字体大小范围
     */
    public static final int DEFAULT_MIN_FONT_SIZE = 8;
    public static final int DEFAULT_MAX_FONT_SIZE = 72;
    /**
     * 最大字体名称长度
     */
    public static final int MAX_FONT_NAME_LENGTH = 100;
    /**
     * 最小图片宽度
     */
    public static final int MIN_IMAGE_WIDTH = 1;

    // ========== 图片验证常量 ==========
    /**
     * 最大图片宽度
     */
    public static final int MAX_IMAGE_WIDTH = 4096;
    /**
     * 最小图片高度
     */
    public static final int MIN_IMAGE_HEIGHT = 1;
    /**
     * 最大图片高度
     */
    public static final int MAX_IMAGE_HEIGHT = 4096;
    /**
     * 最大图片文件大小（字节）
     */
    public static final long MAX_IMAGE_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    /**
     * 推荐图片文件大小（字节）
     */
    public static final long RECOMMENDED_IMAGE_FILE_SIZE = 2 * 1024 * 1024; // 2MB
    /**
     * 最小页边距（磅）
     */
    public static final double MIN_PAGE_MARGIN = 0.0;

    // ========== 页面验证常量 ==========
    /**
     * 最大页边距（磅）
     */
    public static final double MAX_PAGE_MARGIN = 144.0; // 2英寸
    /**
     * 最小页面宽度（磅）
     */
    public static final double MIN_PAGE_WIDTH = 72.0; // 1英寸
    /**
     * 最大页面宽度（磅）
     */
    public static final double MAX_PAGE_WIDTH = 1728.0; // 24英寸
    /**
     * 最小页面高度（磅）
     */
    public static final double MIN_PAGE_HEIGHT = 72.0; // 1英寸
    /**
     * 最大页面高度（磅）
     */
    public static final double MAX_PAGE_HEIGHT = 1728.0; // 24英寸
    /**
     * 文件名验证正则表达式
     */
    public static final String FILENAME_REGEX = "^[^<>:\"/\\\\|?*]+$";

    // ========== 正则表达式常量 ==========
    /**
     * 邮箱验证正则表达式
     */
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    /**
     * URL验证正则表达式
     */
    public static final String URL_REGEX = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$";
    /**
     * 颜色代码验证正则表达式（十六进制）
     */
    public static final String COLOR_HEX_REGEX = "^[0-9A-Fa-f]{6}$";
    /**
     * 版本号验证正则表达式
     */
    public static final String VERSION_REGEX = "^\\d+\\.\\d+\\.\\d+(-[a-zA-Z0-9]+)?$";
    /**
     * 数字验证正则表达式
     */
    public static final String NUMBER_REGEX = "^-?\\d+(\\.\\d+)?$";
    /**
     * 正整数验证正则表达式
     */
    public static final String POSITIVE_INTEGER_REGEX = "^[1-9]\\d*$";
    /**
     * 非负整数验证正则表达式
     */
    public static final String NON_NEGATIVE_INTEGER_REGEX = "^(0|[1-9]\\d*)$";
    /**
     * 文件路径相关错误消息
     */
    public static final String ERROR_INVALID_FILE_PATH = "文件路径无效: %s";

    // ========== 错误消息常量 ==========
    public static final String ERROR_FILE_PATH_TOO_LONG = "文件路径过长，最大长度为 %d 字符";
    public static final String ERROR_FILENAME_TOO_LONG = "文件名过长，最大长度为 %d 字符";
    public static final String ERROR_FILENAME_TOO_SHORT = "文件名过短，最小长度为 %d 字符";
    public static final String ERROR_INVALID_FILENAME_CHARS = "文件名包含非法字符: %s";
    public static final String ERROR_RESERVED_FILENAME = "文件名为系统保留名称: %s";
    public static final String ERROR_FILE_NOT_EXISTS = "文件不存在: %s";
    public static final String ERROR_FILE_NOT_READABLE = "文件不可读: %s";
    /**
     * 文档内容相关错误消息
     */
    public static final String ERROR_TITLE_TOO_LONG = "标题过长，最大长度为 %d 字符";
    public static final String ERROR_TITLE_TOO_SHORT = "标题过短，最小长度为 %d 字符";
    public static final String ERROR_PARAGRAPH_TOO_LONG = "段落过长，最大长度为 %d 字符";
    public static final String ERROR_DOCUMENT_TOO_MANY_PAGES = "文档页数过多，最大页数为 %d";
    public static final String ERROR_DOCUMENT_TOO_MANY_WORDS = "文档字数过多，最大字数为 %d";
    public static final String ERROR_DOCUMENT_TOO_MANY_CHARACTERS = "文档字符数过多，最大字符数为 %d";
    /**
     * 表格相关错误消息
     */
    public static final String ERROR_INVALID_TABLE_ROWS = "表格行数无效，必须在 %d 到 %d 之间";
    public static final String ERROR_INVALID_TABLE_COLUMNS = "表格列数无效，必须在 %d 到 %d 之间";
    public static final String ERROR_CELL_CONTENT_TOO_LONG = "单元格内容过长，最大长度为 %d 字符";
    public static final String ERROR_INVALID_TABLE_INDEX = "表格索引超出范围: %d";
    public static final String ERROR_INVALID_CELL_INDEX = "单元格索引超出范围: 行=%d, 列=%d";
    /**
     * 标题相关错误消息
     */
    public static final String ERROR_INVALID_HEADING_LEVEL = "标题级别无效，必须在 %d 到 %d 之间";
    public static final String ERROR_HEADING_TOO_LONG = "标题过长，最大长度为 %d 字符";
    public static final String ERROR_HEADING_TOO_SHORT = "标题过短，最小长度为 %d 字符";
    /**
     * 字体相关错误消息
     */
    public static final String ERROR_INVALID_FONT_SIZE = "字体大小无效，必须在 %d 到 %d 之间";
    public static final String ERROR_FONT_NAME_TOO_LONG = "字体名称过长，最大长度为 %d 字符";
    public static final String ERROR_FONT_NOT_FOUND = "字体不存在: %s";
    /**
     * 图片相关错误消息
     */
    public static final String ERROR_INVALID_IMAGE_WIDTH = "图片宽度无效，必须在 %d 到 %d 之间";
    public static final String ERROR_INVALID_IMAGE_HEIGHT = "图片高度无效，必须在 %d 到 %d 之间";
    public static final String ERROR_IMAGE_FILE_TOO_LARGE = "图片文件过大，最大大小为 %d MB";
    public static final String ERROR_UNSUPPORTED_IMAGE_FORMAT = "不支持的图片格式: %s";
    public static final String ERROR_IMAGE_LOAD_FAILED = "图片加载失败: %s";
    /**
     * 页面相关错误消息
     */
    public static final String ERROR_INVALID_PAGE_MARGIN = "页边距无效，必须在 %.1f 到 %.1f 磅之间";
    public static final String ERROR_INVALID_PAGE_WIDTH = "页面宽度无效，必须在 %.1f 到 %.1f 磅之间";
    public static final String ERROR_INVALID_PAGE_HEIGHT = "页面高度无效，必须在 %.1f 到 %.1f 磅之间";
    /**
     * 通用错误消息
     */
    public static final String ERROR_NULL_PARAMETER = "参数不能为空: %s";
    public static final String ERROR_EMPTY_PARAMETER = "参数不能为空字符串: %s";
    public static final String ERROR_INVALID_PARAMETER = "参数值无效: %s";
    public static final String ERROR_PARAMETER_OUT_OF_RANGE = "参数值超出范围: %s，有效范围: %s";
    public static final String ERROR_UNSUPPORTED_OPERATION = "不支持的操作: %s";
    public static final String ERROR_OPERATION_FAILED = "操作失败: %s";
    /**
     * 验证成功
     */
    public static final String VALIDATION_SUCCESS = "VALIDATION_SUCCESS";

    // ========== 验证结果常量 ==========
    /**
     * 验证失败
     */
    public static final String VALIDATION_FAILED = "VALIDATION_FAILED";
    /**
     * 验证警告
     */
    public static final String VALIDATION_WARNING = "VALIDATION_WARNING";
    /**
     * 严格验证模式
     */
    public static final String VALIDATION_MODE_STRICT = "strict";

    // ========== 验证规则常量 ==========
    /**
     * 宽松验证模式
     */
    public static final String VALIDATION_MODE_LENIENT = "lenient";
    /**
     * 默认验证模式
     */
    public static final String VALIDATION_MODE_DEFAULT = VALIDATION_MODE_STRICT;

    /**
     * 私有构造函数，防止实例化
     */
    private ValidationConstants() {
        throw new UnsupportedOperationException("常量类不能被实例化");
    }

    // ========== 预设验证配置 ==========

    /**
     * 获取严格验证配置
     */
    public static ValidationConfig getStrictValidationConfig() {
        return new ValidationConfig(VALIDATION_MODE_STRICT, true, true, true, true, true);
    }

    /**
     * 获取宽松验证配置
     */
    public static ValidationConfig getLenientValidationConfig() {
        return new ValidationConfig(VALIDATION_MODE_LENIENT, true, false, false, true, false);
    }

    /**
     * 获取默认验证配置
     */
    public static ValidationConfig getDefaultValidationConfig() {
        return new ValidationConfig(VALIDATION_MODE_DEFAULT, true, true, true, true, false);
    }

    /**
     * 创建成功验证结果
     */
    public static ValidationResult createSuccessResult(String field, Object value) {
        return new ValidationResult(VALIDATION_SUCCESS, "验证通过", field, value);
    }

    /**
     * 创建失败验证结果
     */
    public static ValidationResult createFailedResult(String field, Object value, String message) {
        return new ValidationResult(VALIDATION_FAILED, message, field, value);
    }

    /**
     * 创建警告验证结果
     */
    public static ValidationResult createWarningResult(String field, Object value, String message) {
        return new ValidationResult(VALIDATION_WARNING, message, field, value);
    }

    /**
     * 验证配置类
     */
    public static class ValidationConfig {
        private final String mode;
        private final boolean enableFilePathValidation;
        private final boolean enableContentValidation;
        private final boolean enableSizeValidation;
        private final boolean enableFormatValidation;
        private final boolean throwOnError;

        public ValidationConfig(String mode, boolean enableFilePathValidation,
                                boolean enableContentValidation, boolean enableSizeValidation,
                                boolean enableFormatValidation, boolean throwOnError) {
            this.mode = mode;
            this.enableFilePathValidation = enableFilePathValidation;
            this.enableContentValidation = enableContentValidation;
            this.enableSizeValidation = enableSizeValidation;
            this.enableFormatValidation = enableFormatValidation;
            this.throwOnError = throwOnError;
        }

        // Getters
        public String getMode() {
            return mode;
        }

        public boolean isFilePathValidationEnabled() {
            return enableFilePathValidation;
        }

        public boolean isContentValidationEnabled() {
            return enableContentValidation;
        }

        public boolean isSizeValidationEnabled() {
            return enableSizeValidation;
        }

        public boolean isFormatValidationEnabled() {
            return enableFormatValidation;
        }

        public boolean isThrowOnError() {
            return throwOnError;
        }
    }

    /**
     * 验证结果类
     */
    public static class ValidationResult {
        private final String status;
        private final String message;
        private final String field;
        private final Object value;

        public ValidationResult(String status, String message, String field, Object value) {
            this.status = status;
            this.message = message;
            this.field = field;
            this.value = value;
        }

        // Getters
        public String getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public String getField() {
            return field;
        }

        public Object getValue() {
            return value;
        }

        public boolean isSuccess() {
            return VALIDATION_SUCCESS.equals(status);
        }

        public boolean isFailed() {
            return VALIDATION_FAILED.equals(status);
        }

        public boolean isWarning() {
            return VALIDATION_WARNING.equals(status);
        }
    }
}