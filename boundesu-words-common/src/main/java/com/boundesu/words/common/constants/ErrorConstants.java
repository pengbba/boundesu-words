package com.boundesu.words.common.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 错误码和错误消息常量
 * 管理系统中的错误代码、错误消息、异常类型等
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public final class ErrorConstants {

    /**
     * 成功
     */
    public static final String SUCCESS = "0000";

    // ========== 通用错误码 ==========
    /**
     * 系统错误
     */
    public static final String SYSTEM_ERROR = "1000";
    /**
     * 参数错误
     */
    public static final String PARAMETER_ERROR = "1001";
    /**
     * 参数为空
     */
    public static final String PARAMETER_NULL = "1002";
    /**
     * 参数格式错误
     */
    public static final String PARAMETER_FORMAT_ERROR = "1003";
    /**
     * 参数超出范围
     */
    public static final String PARAMETER_OUT_OF_RANGE = "1004";
    /**
     * 操作失败
     */
    public static final String OPERATION_FAILED = "1005";
    /**
     * 不支持的操作
     */
    public static final String UNSUPPORTED_OPERATION = "1006";
    /**
     * 权限不足
     */
    public static final String PERMISSION_DENIED = "1007";
    /**
     * 资源不存在
     */
    public static final String RESOURCE_NOT_FOUND = "1008";
    /**
     * 资源已存在
     */
    public static final String RESOURCE_ALREADY_EXISTS = "1009";
    /**
     * 配置错误
     */
    public static final String CONFIGURATION_ERROR = "1010";
    /**
     * 文件不存在
     */
    public static final String FILE_NOT_FOUND = "2001";

    // ========== 文件相关错误码 ==========
    /**
     * 文件已存在
     */
    public static final String FILE_ALREADY_EXISTS = "2002";
    /**
     * 文件读取失败
     */
    public static final String FILE_READ_ERROR = "2003";
    /**
     * 文件写入失败
     */
    public static final String FILE_WRITE_ERROR = "2004";
    /**
     * 文件删除失败
     */
    public static final String FILE_DELETE_ERROR = "2005";
    /**
     * 文件创建失败
     */
    public static final String FILE_CREATE_ERROR = "2006";
    /**
     * 文件格式不支持
     */
    public static final String FILE_FORMAT_UNSUPPORTED = "2007";
    /**
     * 文件大小超限
     */
    public static final String FILE_SIZE_EXCEEDED = "2008";
    /**
     * 文件路径无效
     */
    public static final String FILE_PATH_INVALID = "2009";
    /**
     * 文件名无效
     */
    public static final String FILE_NAME_INVALID = "2010";
    /**
     * 文件权限不足
     */
    public static final String FILE_PERMISSION_DENIED = "2011";
    /**
     * 文件损坏
     */
    public static final String FILE_CORRUPTED = "2012";
    /**
     * 文件锁定
     */
    public static final String FILE_LOCKED = "2013";
    /**
     * 磁盘空间不足
     */
    public static final String DISK_SPACE_INSUFFICIENT = "2014";
    /**
     * 文档解析失败
     */
    public static final String DOCUMENT_PARSE_ERROR = "3001";

    // ========== 文档处理错误码 ==========
    /**
     * 文档生成失败
     */
    public static final String DOCUMENT_GENERATION_ERROR = "3002";
    /**
     * 文档转换失败
     */
    public static final String DOCUMENT_CONVERSION_ERROR = "3003";
    /**
     * 文档格式错误
     */
    public static final String DOCUMENT_FORMAT_ERROR = "3004";
    /**
     * 文档内容为空
     */
    public static final String DOCUMENT_CONTENT_EMPTY = "3005";
    /**
     * 文档内容过长
     */
    public static final String DOCUMENT_CONTENT_TOO_LONG = "3006";
    /**
     * 文档结构错误
     */
    public static final String DOCUMENT_STRUCTURE_ERROR = "3007";
    /**
     * 文档编码错误
     */
    public static final String DOCUMENT_ENCODING_ERROR = "3008";
    /**
     * 文档密码错误
     */
    public static final String DOCUMENT_PASSWORD_ERROR = "3009";
    /**
     * 文档版本不兼容
     */
    public static final String DOCUMENT_VERSION_INCOMPATIBLE = "3010";
    /**
     * 模板不存在
     */
    public static final String TEMPLATE_NOT_FOUND = "3011";
    /**
     * 模板格式错误
     */
    public static final String TEMPLATE_FORMAT_ERROR = "3012";
    /**
     * 模板变量错误
     */
    public static final String TEMPLATE_VARIABLE_ERROR = "3013";
    /**
     * 图片格式不支持
     */
    public static final String IMAGE_FORMAT_UNSUPPORTED = "4001";

    // ========== 图片处理错误码 ==========
    /**
     * 图片加载失败
     */
    public static final String IMAGE_LOAD_ERROR = "4002";
    /**
     * 图片保存失败
     */
    public static final String IMAGE_SAVE_ERROR = "4003";
    /**
     * 图片尺寸无效
     */
    public static final String IMAGE_SIZE_INVALID = "4004";
    /**
     * 图片质量无效
     */
    public static final String IMAGE_QUALITY_INVALID = "4005";
    /**
     * 图片压缩失败
     */
    public static final String IMAGE_COMPRESSION_ERROR = "4006";
    /**
     * 图片缩放失败
     */
    public static final String IMAGE_RESIZE_ERROR = "4007";
    /**
     * 图片旋转失败
     */
    public static final String IMAGE_ROTATION_ERROR = "4008";
    /**
     * 图片裁剪失败
     */
    public static final String IMAGE_CROP_ERROR = "4009";
    /**
     * 图片水印失败
     */
    public static final String IMAGE_WATERMARK_ERROR = "4010";
    /**
     * 表格创建失败
     */
    public static final String TABLE_CREATE_ERROR = "5001";

    // ========== 表格处理错误码 ==========
    /**
     * 表格行数无效
     */
    public static final String TABLE_ROWS_INVALID = "5002";
    /**
     * 表格列数无效
     */
    public static final String TABLE_COLUMNS_INVALID = "5003";
    /**
     * 单元格索引无效
     */
    public static final String CELL_INDEX_INVALID = "5004";
    /**
     * 单元格内容过长
     */
    public static final String CELL_CONTENT_TOO_LONG = "5005";
    /**
     * 表格样式错误
     */
    public static final String TABLE_STYLE_ERROR = "5006";
    /**
     * 表格合并失败
     */
    public static final String TABLE_MERGE_ERROR = "5007";
    /**
     * 表格拆分失败
     */
    public static final String TABLE_SPLIT_ERROR = "5008";
    /**
     * 字体不存在
     */
    public static final String FONT_NOT_FOUND = "6001";

    // ========== 字体处理错误码 ==========
    /**
     * 字体加载失败
     */
    public static final String FONT_LOAD_ERROR = "6002";
    /**
     * 字体大小无效
     */
    public static final String FONT_SIZE_INVALID = "6003";
    /**
     * 字体样式无效
     */
    public static final String FONT_STYLE_INVALID = "6004";
    /**
     * 字体颜色无效
     */
    public static final String FONT_COLOR_INVALID = "6005";
    /**
     * 字体编码错误
     */
    public static final String FONT_ENCODING_ERROR = "6006";
    /**
     * 网络连接失败
     */
    public static final String NETWORK_CONNECTION_ERROR = "7001";

    // ========== 网络相关错误码 ==========
    /**
     * 网络超时
     */
    public static final String NETWORK_TIMEOUT = "7002";
    /**
     * 网络中断
     */
    public static final String NETWORK_INTERRUPTED = "7003";
    /**
     * URL无效
     */
    public static final String URL_INVALID = "7004";
    /**
     * HTTP错误
     */
    public static final String HTTP_ERROR = "7005";
    /**
     * 下载失败
     */
    public static final String DOWNLOAD_ERROR = "7006";
    /**
     * 上传失败
     */
    public static final String UPLOAD_ERROR = "7007";
    /**
     * 数据库连接失败
     */
    public static final String DATABASE_CONNECTION_ERROR = "8001";

    // ========== 数据库相关错误码 ==========
    /**
     * SQL语法错误
     */
    public static final String SQL_SYNTAX_ERROR = "8002";
    /**
     * 数据不存在
     */
    public static final String DATA_NOT_FOUND = "8003";
    /**
     * 数据已存在
     */
    public static final String DATA_ALREADY_EXISTS = "8004";
    /**
     * 数据插入失败
     */
    public static final String DATA_INSERT_ERROR = "8005";
    /**
     * 数据更新失败
     */
    public static final String DATA_UPDATE_ERROR = "8006";
    /**
     * 数据删除失败
     */
    public static final String DATA_DELETE_ERROR = "8007";
    /**
     * 事务失败
     */
    public static final String TRANSACTION_ERROR = "8008";
    /**
     * 内存不足
     */
    public static final String OUT_OF_MEMORY = "9001";

    // ========== 内存相关错误码 ==========
    /**
     * 内存泄漏
     */
    public static final String MEMORY_LEAK = "9002";
    /**
     * 缓冲区溢出
     */
    public static final String BUFFER_OVERFLOW = "9003";
    /**
     * 栈溢出
     */
    public static final String STACK_OVERFLOW = "9004";
    /**
     * 信息级别
     */
    public static final String LEVEL_INFO = "INFO";

    // ========== 错误消息映射 ==========
    /**
     * 警告级别
     */
    public static final String LEVEL_WARN = "WARN";
    /**
     * 错误级别
     */
    public static final String LEVEL_ERROR = "ERROR";

    // ========== 错误级别常量 ==========
    /**
     * 致命错误级别
     */
    public static final String LEVEL_FATAL = "FATAL";
    /**
     * 业务错误
     */
    public static final String TYPE_BUSINESS = "BUSINESS";
    /**
     * 系统错误
     */
    public static final String TYPE_SYSTEM = "SYSTEM";
    /**
     * 网络错误
     */
    public static final String TYPE_NETWORK = "NETWORK";

    // ========== 错误类型常量 ==========
    /**
     * 数据错误
     */
    public static final String TYPE_DATA = "DATA";
    /**
     * 验证错误
     */
    public static final String TYPE_VALIDATION = "VALIDATION";
    /**
     * 错误码到错误消息的映射
     */
    private static final Map<String, String> ERROR_MESSAGES = new HashMap<>();

    static {
        // 通用错误消息
        ERROR_MESSAGES.put(SUCCESS, "操作成功");
        ERROR_MESSAGES.put(SYSTEM_ERROR, "系统内部错误");
        ERROR_MESSAGES.put(PARAMETER_ERROR, "参数错误");
        ERROR_MESSAGES.put(PARAMETER_NULL, "参数不能为空");
        ERROR_MESSAGES.put(PARAMETER_FORMAT_ERROR, "参数格式错误");
        ERROR_MESSAGES.put(PARAMETER_OUT_OF_RANGE, "参数超出有效范围");
        ERROR_MESSAGES.put(OPERATION_FAILED, "操作执行失败");
        ERROR_MESSAGES.put(UNSUPPORTED_OPERATION, "不支持的操作");
        ERROR_MESSAGES.put(PERMISSION_DENIED, "权限不足，无法执行操作");
        ERROR_MESSAGES.put(RESOURCE_NOT_FOUND, "请求的资源不存在");
        ERROR_MESSAGES.put(RESOURCE_ALREADY_EXISTS, "资源已存在");
        ERROR_MESSAGES.put(CONFIGURATION_ERROR, "配置错误");

        // 文件相关错误消息
        ERROR_MESSAGES.put(FILE_NOT_FOUND, "文件不存在");
        ERROR_MESSAGES.put(FILE_ALREADY_EXISTS, "文件已存在");
        ERROR_MESSAGES.put(FILE_READ_ERROR, "文件读取失败");
        ERROR_MESSAGES.put(FILE_WRITE_ERROR, "文件写入失败");
        ERROR_MESSAGES.put(FILE_DELETE_ERROR, "文件删除失败");
        ERROR_MESSAGES.put(FILE_CREATE_ERROR, "文件创建失败");
        ERROR_MESSAGES.put(FILE_FORMAT_UNSUPPORTED, "不支持的文件格式");
        ERROR_MESSAGES.put(FILE_SIZE_EXCEEDED, "文件大小超出限制");
        ERROR_MESSAGES.put(FILE_PATH_INVALID, "文件路径无效");
        ERROR_MESSAGES.put(FILE_NAME_INVALID, "文件名无效");
        ERROR_MESSAGES.put(FILE_PERMISSION_DENIED, "文件访问权限不足");
        ERROR_MESSAGES.put(FILE_CORRUPTED, "文件已损坏");
        ERROR_MESSAGES.put(FILE_LOCKED, "文件被锁定，无法访问");
        ERROR_MESSAGES.put(DISK_SPACE_INSUFFICIENT, "磁盘空间不足");

        // 文档处理错误消息
        ERROR_MESSAGES.put(DOCUMENT_PARSE_ERROR, "文档解析失败");
        ERROR_MESSAGES.put(DOCUMENT_GENERATION_ERROR, "文档生成失败");
        ERROR_MESSAGES.put(DOCUMENT_CONVERSION_ERROR, "文档转换失败");
        ERROR_MESSAGES.put(DOCUMENT_FORMAT_ERROR, "文档格式错误");
        ERROR_MESSAGES.put(DOCUMENT_CONTENT_EMPTY, "文档内容为空");
        ERROR_MESSAGES.put(DOCUMENT_CONTENT_TOO_LONG, "文档内容过长");
        ERROR_MESSAGES.put(DOCUMENT_STRUCTURE_ERROR, "文档结构错误");
        ERROR_MESSAGES.put(DOCUMENT_ENCODING_ERROR, "文档编码错误");
        ERROR_MESSAGES.put(DOCUMENT_PASSWORD_ERROR, "文档密码错误");
        ERROR_MESSAGES.put(DOCUMENT_VERSION_INCOMPATIBLE, "文档版本不兼容");
        ERROR_MESSAGES.put(TEMPLATE_NOT_FOUND, "模板文件不存在");
        ERROR_MESSAGES.put(TEMPLATE_FORMAT_ERROR, "模板格式错误");
        ERROR_MESSAGES.put(TEMPLATE_VARIABLE_ERROR, "模板变量错误");

        // 图片处理错误消息
        ERROR_MESSAGES.put(IMAGE_FORMAT_UNSUPPORTED, "不支持的图片格式");
        ERROR_MESSAGES.put(IMAGE_LOAD_ERROR, "图片加载失败");
        ERROR_MESSAGES.put(IMAGE_SAVE_ERROR, "图片保存失败");
        ERROR_MESSAGES.put(IMAGE_SIZE_INVALID, "图片尺寸无效");
        ERROR_MESSAGES.put(IMAGE_QUALITY_INVALID, "图片质量参数无效");
        ERROR_MESSAGES.put(IMAGE_COMPRESSION_ERROR, "图片压缩失败");
        ERROR_MESSAGES.put(IMAGE_RESIZE_ERROR, "图片缩放失败");
        ERROR_MESSAGES.put(IMAGE_ROTATION_ERROR, "图片旋转失败");
        ERROR_MESSAGES.put(IMAGE_CROP_ERROR, "图片裁剪失败");
        ERROR_MESSAGES.put(IMAGE_WATERMARK_ERROR, "图片水印添加失败");

        // 表格处理错误消息
        ERROR_MESSAGES.put(TABLE_CREATE_ERROR, "表格创建失败");
        ERROR_MESSAGES.put(TABLE_ROWS_INVALID, "表格行数无效");
        ERROR_MESSAGES.put(TABLE_COLUMNS_INVALID, "表格列数无效");
        ERROR_MESSAGES.put(CELL_INDEX_INVALID, "单元格索引无效");
        ERROR_MESSAGES.put(CELL_CONTENT_TOO_LONG, "单元格内容过长");
        ERROR_MESSAGES.put(TABLE_STYLE_ERROR, "表格样式设置错误");
        ERROR_MESSAGES.put(TABLE_MERGE_ERROR, "表格单元格合并失败");
        ERROR_MESSAGES.put(TABLE_SPLIT_ERROR, "表格单元格拆分失败");

        // 字体处理错误消息
        ERROR_MESSAGES.put(FONT_NOT_FOUND, "字体文件不存在");
        ERROR_MESSAGES.put(FONT_LOAD_ERROR, "字体加载失败");
        ERROR_MESSAGES.put(FONT_SIZE_INVALID, "字体大小无效");
        ERROR_MESSAGES.put(FONT_STYLE_INVALID, "字体样式无效");
        ERROR_MESSAGES.put(FONT_COLOR_INVALID, "字体颜色无效");
        ERROR_MESSAGES.put(FONT_ENCODING_ERROR, "字体编码错误");

        // 网络相关错误消息
        ERROR_MESSAGES.put(NETWORK_CONNECTION_ERROR, "网络连接失败");
        ERROR_MESSAGES.put(NETWORK_TIMEOUT, "网络请求超时");
        ERROR_MESSAGES.put(NETWORK_INTERRUPTED, "网络连接中断");
        ERROR_MESSAGES.put(URL_INVALID, "URL地址无效");
        ERROR_MESSAGES.put(HTTP_ERROR, "HTTP请求错误");
        ERROR_MESSAGES.put(DOWNLOAD_ERROR, "文件下载失败");
        ERROR_MESSAGES.put(UPLOAD_ERROR, "文件上传失败");

        // 数据库相关错误消息
        ERROR_MESSAGES.put(DATABASE_CONNECTION_ERROR, "数据库连接失败");
        ERROR_MESSAGES.put(SQL_SYNTAX_ERROR, "SQL语法错误");
        ERROR_MESSAGES.put(DATA_NOT_FOUND, "数据不存在");
        ERROR_MESSAGES.put(DATA_ALREADY_EXISTS, "数据已存在");
        ERROR_MESSAGES.put(DATA_INSERT_ERROR, "数据插入失败");
        ERROR_MESSAGES.put(DATA_UPDATE_ERROR, "数据更新失败");
        ERROR_MESSAGES.put(DATA_DELETE_ERROR, "数据删除失败");
        ERROR_MESSAGES.put(TRANSACTION_ERROR, "事务处理失败");

        // 内存相关错误消息
        ERROR_MESSAGES.put(OUT_OF_MEMORY, "内存不足");
        ERROR_MESSAGES.put(MEMORY_LEAK, "检测到内存泄漏");
        ERROR_MESSAGES.put(BUFFER_OVERFLOW, "缓冲区溢出");
        ERROR_MESSAGES.put(STACK_OVERFLOW, "栈溢出");
    }

    /**
     * 私有构造函数，防止实例化
     */
    private ErrorConstants() {
        throw new UnsupportedOperationException("常量类不能被实例化");
    }

    // ========== 错误信息类 ==========

    /**
     * 根据错误码获取错误消息
     */
    public static String getErrorMessage(String errorCode) {
        return ERROR_MESSAGES.getOrDefault(errorCode, "未知错误");
    }

    // ========== 工具方法 ==========

    /**
     * 检查是否为成功状态
     */
    public static boolean isSuccess(String errorCode) {
        return SUCCESS.equals(errorCode);
    }

    /**
     * 检查是否为系统错误
     */
    public static boolean isSystemError(String errorCode) {
        return errorCode != null && errorCode.startsWith("1");
    }

    /**
     * 检查是否为文件错误
     */
    public static boolean isFileError(String errorCode) {
        return errorCode != null && errorCode.startsWith("2");
    }

    /**
     * 检查是否为文档错误
     */
    public static boolean isDocumentError(String errorCode) {
        return errorCode != null && errorCode.startsWith("3");
    }

    /**
     * 检查是否为图片错误
     */
    public static boolean isImageError(String errorCode) {
        return errorCode != null && errorCode.startsWith("4");
    }

    /**
     * 检查是否为表格错误
     */
    public static boolean isTableError(String errorCode) {
        return errorCode != null && errorCode.startsWith("5");
    }

    /**
     * 检查是否为字体错误
     */
    public static boolean isFontError(String errorCode) {
        return errorCode != null && errorCode.startsWith("6");
    }

    /**
     * 检查是否为网络错误
     */
    public static boolean isNetworkError(String errorCode) {
        return errorCode != null && errorCode.startsWith("7");
    }

    /**
     * 检查是否为数据库错误
     */
    public static boolean isDatabaseError(String errorCode) {
        return errorCode != null && errorCode.startsWith("8");
    }

    /**
     * 检查是否为内存错误
     */
    public static boolean isMemoryError(String errorCode) {
        return errorCode != null && errorCode.startsWith("9");
    }

    /**
     * 创建错误信息
     */
    public static ErrorInfo createErrorInfo(String code, String level, String type, String detail) {
        String message = getErrorMessage(code);
        return new ErrorInfo(code, message, level, type, detail);
    }

    /**
     * 创建业务错误信息
     */
    public static ErrorInfo createBusinessError(String code, String detail) {
        return createErrorInfo(code, LEVEL_ERROR, TYPE_BUSINESS, detail);
    }

    /**
     * 创建系统错误信息
     */
    public static ErrorInfo createSystemError(String code, String detail) {
        return createErrorInfo(code, LEVEL_FATAL, TYPE_SYSTEM, detail);
    }

    /**
     * 创建验证错误信息
     */
    public static ErrorInfo createValidationError(String code, String detail) {
        return createErrorInfo(code, LEVEL_WARN, TYPE_VALIDATION, detail);
    }

    /**
     * 格式化错误消息
     */
    public static String formatErrorMessage(String errorCode, Object... args) {
        String message = getErrorMessage(errorCode);
        if (args != null && args.length > 0) {
            return String.format(message, args);
        }
        return message;
    }

    /**
     * 错误信息类
     */
    public static class ErrorInfo {
        private final String code;
        private final String message;
        private final String level;
        private final String type;
        private final String detail;
        private final long timestamp;

        public ErrorInfo(String code, String message, String level, String type, String detail) {
            this.code = code;
            this.message = message;
            this.level = level;
            this.type = type;
            this.detail = detail;
            this.timestamp = System.currentTimeMillis();
        }

        // Getters
        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public String getLevel() {
            return level;
        }

        public String getType() {
            return type;
        }

        public String getDetail() {
            return detail;
        }

        public long getTimestamp() {
            return timestamp;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s: %s - %s", level, code, message, detail);
        }
    }
}