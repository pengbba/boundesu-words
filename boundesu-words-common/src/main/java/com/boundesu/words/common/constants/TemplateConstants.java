package com.boundesu.words.common.constants;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * 模板相关常量
 * 管理文档模板、变量替换、模板引擎等配置
 * 
 * @author Boundesu Team
 * @version 1.0.0
 */
public final class TemplateConstants {
    
    /**
     * 私有构造函数，防止实例化
     */
    private TemplateConstants() {
        throw new UnsupportedOperationException("常量类不能被实例化");
    }
    
    // ========== 模板类型常量 ==========
    
    /** DOCX模板 */
    public static final String TEMPLATE_TYPE_DOCX = "docx";
    
    /** HTML模板 */
    public static final String TEMPLATE_TYPE_HTML = "html";
    
    /** XML模板 */
    public static final String TEMPLATE_TYPE_XML = "xml";
    
    /** JSON模板 */
    public static final String TEMPLATE_TYPE_JSON = "json";
    
    /** 文本模板 */
    public static final String TEMPLATE_TYPE_TEXT = "text";
    
    /** Freemarker模板 */
    public static final String TEMPLATE_TYPE_FREEMARKER = "freemarker";
    
    /** Velocity模板 */
    public static final String TEMPLATE_TYPE_VELOCITY = "velocity";
    
    /** Thymeleaf模板 */
    public static final String TEMPLATE_TYPE_THYMELEAF = "thymeleaf";
    
    /** 默认模板类型 */
    public static final String DEFAULT_TEMPLATE_TYPE = TEMPLATE_TYPE_DOCX;
    
    /** 支持的模板类型 */
    public static final String[] SUPPORTED_TEMPLATE_TYPES = {
        TEMPLATE_TYPE_DOCX, TEMPLATE_TYPE_HTML, TEMPLATE_TYPE_XML, TEMPLATE_TYPE_JSON,
        TEMPLATE_TYPE_TEXT, TEMPLATE_TYPE_FREEMARKER, TEMPLATE_TYPE_VELOCITY, TEMPLATE_TYPE_THYMELEAF
    };
    
    // ========== 变量标记常量 ==========
    
    /** 默认变量开始标记 */
    public static final String DEFAULT_VARIABLE_START = "${";
    
    /** 默认变量结束标记 */
    public static final String DEFAULT_VARIABLE_END = "}";
    
    /** 双花括号变量开始标记 */
    public static final String DOUBLE_BRACE_VARIABLE_START = "{{";
    
    /** 双花括号变量结束标记 */
    public static final String DOUBLE_BRACE_VARIABLE_END = "}}";
    
    /** 百分号变量开始标记 */
    public static final String PERCENT_VARIABLE_START = "%{";
    
    /** 百分号变量结束标记 */
    public static final String PERCENT_VARIABLE_END = "}%";
    
    /** 井号变量开始标记 */
    public static final String HASH_VARIABLE_START = "#{";
    
    /** 井号变量结束标记 */
    public static final String HASH_VARIABLE_END = "}";
    
    /** 方括号变量开始标记 */
    public static final String BRACKET_VARIABLE_START = "[";
    
    /** 方括号变量结束标记 */
    public static final String BRACKET_VARIABLE_END = "]";
    
    // ========== 循环标记常量 ==========
    
    /** 循环开始标记 */
    public static final String LOOP_START_TAG = "#foreach";
    
    /** 循环结束标记 */
    public static final String LOOP_END_TAG = "#end";
    
    /** 条件开始标记 */
    public static final String CONDITION_START_TAG = "#if";
    
    /** 条件结束标记 */
    public static final String CONDITION_END_TAG = "#end";
    
    /** 否则标记 */
    public static final String ELSE_TAG = "#else";
    
    /** 否则如果标记 */
    public static final String ELSEIF_TAG = "#elseif";
    
    // ========== 特殊变量常量 ==========
    
    /** 当前日期变量 */
    public static final String VAR_CURRENT_DATE = "currentDate";
    
    /** 当前时间变量 */
    public static final String VAR_CURRENT_TIME = "currentTime";
    
    /** 当前日期时间变量 */
    public static final String VAR_CURRENT_DATETIME = "currentDateTime";
    
    /** 当前年份变量 */
    public static final String VAR_CURRENT_YEAR = "currentYear";
    
    /** 当前月份变量 */
    public static final String VAR_CURRENT_MONTH = "currentMonth";
    
    /** 当前日期变量 */
    public static final String VAR_CURRENT_DAY = "currentDay";
    
    /** 用户名变量 */
    public static final String VAR_USERNAME = "username";
    
    /** 文档标题变量 */
    public static final String VAR_DOCUMENT_TITLE = "documentTitle";
    
    /** 文档作者变量 */
    public static final String VAR_DOCUMENT_AUTHOR = "documentAuthor";
    
    /** 文档版本变量 */
    public static final String VAR_DOCUMENT_VERSION = "documentVersion";
    
    /** 页码变量 */
    public static final String VAR_PAGE_NUMBER = "pageNumber";
    
    /** 总页数变量 */
    public static final String VAR_TOTAL_PAGES = "totalPages";
    
    // ========== 模板引擎常量 ==========
    
    /** 内置模板引擎 */
    public static final String ENGINE_BUILTIN = "builtin";
    
    /** Freemarker引擎 */
    public static final String ENGINE_FREEMARKER = "freemarker";
    
    /** Velocity引擎 */
    public static final String ENGINE_VELOCITY = "velocity";
    
    /** Thymeleaf引擎 */
    public static final String ENGINE_THYMELEAF = "thymeleaf";
    
    /** Mustache引擎 */
    public static final String ENGINE_MUSTACHE = "mustache";
    
    /** 默认模板引擎 */
    public static final String DEFAULT_TEMPLATE_ENGINE = ENGINE_BUILTIN;
    
    /** 支持的模板引擎 */
    public static final String[] SUPPORTED_TEMPLATE_ENGINES = {
        ENGINE_BUILTIN, ENGINE_FREEMARKER, ENGINE_VELOCITY, 
        ENGINE_THYMELEAF, ENGINE_MUSTACHE
    };
    
    // ========== 模板目录常量 ==========
    
    /** 默认模板目录 */
    public static final String DEFAULT_TEMPLATE_DIR = "templates";
    
    /** 系统模板目录 */
    public static final String SYSTEM_TEMPLATE_DIR = "system/templates";
    
    /** 用户模板目录 */
    public static final String USER_TEMPLATE_DIR = "user/templates";
    
    /** 临时模板目录 */
    public static final String TEMP_TEMPLATE_DIR = "temp/templates";
    
    /** 模板缓存目录 */
    public static final String TEMPLATE_CACHE_DIR = "cache/templates";
    
    // ========== 模板文件扩展名常量 ==========
    
    /** DOCX模板扩展名 */
    public static final String TEMPLATE_EXT_DOCX = ".docx";
    
    /** HTML模板扩展名 */
    public static final String TEMPLATE_EXT_HTML = ".html";
    
    /** XML模板扩展名 */
    public static final String TEMPLATE_EXT_XML = ".xml";
    
    /** JSON模板扩展名 */
    public static final String TEMPLATE_EXT_JSON = ".json";
    
    /** 文本模板扩展名 */
    public static final String TEMPLATE_EXT_TEXT = ".txt";
    
    /** Freemarker模板扩展名 */
    public static final String TEMPLATE_EXT_FREEMARKER = ".ftl";
    
    /** Velocity模板扩展名 */
    public static final String TEMPLATE_EXT_VELOCITY = ".vm";
    
    /** Thymeleaf模板扩展名 */
    public static final String TEMPLATE_EXT_THYMELEAF = ".html";
    
    /** Mustache模板扩展名 */
    public static final String TEMPLATE_EXT_MUSTACHE = ".mustache";
    
    // ========== 模板配置常量 ==========
    
    /** 默认编码 */
    public static final String DEFAULT_ENCODING = "UTF-8";
    
    /** 默认缓存大小 */
    public static final int DEFAULT_CACHE_SIZE = 100;
    
    /** 最大缓存大小 */
    public static final int MAX_CACHE_SIZE = 1000;
    
    /** 缓存过期时间（毫秒） */
    public static final long CACHE_EXPIRATION_TIME = 30 * 60 * 1000; // 30分钟
    
    /** 模板加载超时时间（毫秒） */
    public static final int TEMPLATE_LOAD_TIMEOUT = 10000; // 10秒
    
    /** 模板处理超时时间（毫秒） */
    public static final int TEMPLATE_PROCESS_TIMEOUT = 60000; // 60秒
    
    /** 最大变量数量 */
    public static final int MAX_VARIABLE_COUNT = 1000;
    
    /** 最大变量名长度 */
    public static final int MAX_VARIABLE_NAME_LENGTH = 100;
    
    /** 最大变量值长度 */
    public static final int MAX_VARIABLE_VALUE_LENGTH = 10000;
    
    /** 最大循环次数 */
    public static final int MAX_LOOP_COUNT = 10000;
    
    /** 最大嵌套深度 */
    public static final int MAX_NESTING_DEPTH = 10;
    
    // ========== 数据类型常量 ==========
    
    /** 字符串类型 */
    public static final String DATA_TYPE_STRING = "string";
    
    /** 数字类型 */
    public static final String DATA_TYPE_NUMBER = "number";
    
    /** 布尔类型 */
    public static final String DATA_TYPE_BOOLEAN = "boolean";
    
    /** 日期类型 */
    public static final String DATA_TYPE_DATE = "date";
    
    /** 列表类型 */
    public static final String DATA_TYPE_LIST = "list";
    
    /** 对象类型 */
    public static final String DATA_TYPE_OBJECT = "object";
    
    /** 图片类型 */
    public static final String DATA_TYPE_IMAGE = "image";
    
    /** 表格类型 */
    public static final String DATA_TYPE_TABLE = "table";
    
    /** 支持的数据类型 */
    public static final String[] SUPPORTED_DATA_TYPES = {
        DATA_TYPE_STRING, DATA_TYPE_NUMBER, DATA_TYPE_BOOLEAN, DATA_TYPE_DATE,
        DATA_TYPE_LIST, DATA_TYPE_OBJECT, DATA_TYPE_IMAGE, DATA_TYPE_TABLE
    };
    
    // ========== 格式化常量 ==========
    
    /** 日期格式化器 */
    public static final String FORMATTER_DATE = "date";
    
    /** 数字格式化器 */
    public static final String FORMATTER_NUMBER = "number";
    
    /** 货币格式化器 */
    public static final String FORMATTER_CURRENCY = "currency";
    
    /** 百分比格式化器 */
    public static final String FORMATTER_PERCENT = "percent";
    
    /** 大写格式化器 */
    public static final String FORMATTER_UPPERCASE = "uppercase";
    
    /** 小写格式化器 */
    public static final String FORMATTER_LOWERCASE = "lowercase";
    
    /** 首字母大写格式化器 */
    public static final String FORMATTER_CAPITALIZE = "capitalize";
    
    /** 支持的格式化器 */
    public static final String[] SUPPORTED_FORMATTERS = {
        FORMATTER_DATE, FORMATTER_NUMBER, FORMATTER_CURRENCY, FORMATTER_PERCENT,
        FORMATTER_UPPERCASE, FORMATTER_LOWERCASE, FORMATTER_CAPITALIZE
    };
    
    // ========== 模板映射 ==========
    
    /** 模板类型到扩展名映射 */
    private static final Map<String, String> TEMPLATE_TYPE_TO_EXTENSION_MAP = new HashMap<>();
    
    /** 扩展名到模板类型映射 */
    private static final Map<String, String> EXTENSION_TO_TEMPLATE_TYPE_MAP = new HashMap<>();
    
    /** 模板引擎到扩展名映射 */
    private static final Map<String, String> ENGINE_TO_EXTENSION_MAP = new HashMap<>();
    
    static {
        // 初始化模板类型到扩展名映射
        TEMPLATE_TYPE_TO_EXTENSION_MAP.put(TEMPLATE_TYPE_DOCX, TEMPLATE_EXT_DOCX);
        TEMPLATE_TYPE_TO_EXTENSION_MAP.put(TEMPLATE_TYPE_HTML, TEMPLATE_EXT_HTML);
        TEMPLATE_TYPE_TO_EXTENSION_MAP.put(TEMPLATE_TYPE_XML, TEMPLATE_EXT_XML);
        TEMPLATE_TYPE_TO_EXTENSION_MAP.put(TEMPLATE_TYPE_JSON, TEMPLATE_EXT_JSON);
        TEMPLATE_TYPE_TO_EXTENSION_MAP.put(TEMPLATE_TYPE_TEXT, TEMPLATE_EXT_TEXT);
        TEMPLATE_TYPE_TO_EXTENSION_MAP.put(TEMPLATE_TYPE_FREEMARKER, TEMPLATE_EXT_FREEMARKER);
        TEMPLATE_TYPE_TO_EXTENSION_MAP.put(TEMPLATE_TYPE_VELOCITY, TEMPLATE_EXT_VELOCITY);
        TEMPLATE_TYPE_TO_EXTENSION_MAP.put(TEMPLATE_TYPE_THYMELEAF, TEMPLATE_EXT_THYMELEAF);
        
        // 初始化扩展名到模板类型映射
        EXTENSION_TO_TEMPLATE_TYPE_MAP.put(TEMPLATE_EXT_DOCX, TEMPLATE_TYPE_DOCX);
        EXTENSION_TO_TEMPLATE_TYPE_MAP.put(TEMPLATE_EXT_HTML, TEMPLATE_TYPE_HTML);
        EXTENSION_TO_TEMPLATE_TYPE_MAP.put(TEMPLATE_EXT_XML, TEMPLATE_TYPE_XML);
        EXTENSION_TO_TEMPLATE_TYPE_MAP.put(TEMPLATE_EXT_JSON, TEMPLATE_TYPE_JSON);
        EXTENSION_TO_TEMPLATE_TYPE_MAP.put(TEMPLATE_EXT_TEXT, TEMPLATE_TYPE_TEXT);
        EXTENSION_TO_TEMPLATE_TYPE_MAP.put(TEMPLATE_EXT_FREEMARKER, TEMPLATE_TYPE_FREEMARKER);
        EXTENSION_TO_TEMPLATE_TYPE_MAP.put(TEMPLATE_EXT_VELOCITY, TEMPLATE_TYPE_VELOCITY);
        EXTENSION_TO_TEMPLATE_TYPE_MAP.put(TEMPLATE_EXT_MUSTACHE, ENGINE_MUSTACHE);
        
        // 初始化模板引擎到扩展名映射
        ENGINE_TO_EXTENSION_MAP.put(ENGINE_FREEMARKER, TEMPLATE_EXT_FREEMARKER);
        ENGINE_TO_EXTENSION_MAP.put(ENGINE_VELOCITY, TEMPLATE_EXT_VELOCITY);
        ENGINE_TO_EXTENSION_MAP.put(ENGINE_THYMELEAF, TEMPLATE_EXT_THYMELEAF);
        ENGINE_TO_EXTENSION_MAP.put(ENGINE_MUSTACHE, TEMPLATE_EXT_MUSTACHE);
    }
    
    // ========== 模板配置类 ==========
    
    /**
     * 模板配置类
     */
    public static class TemplateConfig {
        private final String templateType;
        private final String templateEngine;
        private final String encoding;
        private final String variableStart;
        private final String variableEnd;
        private final boolean enableCache;
        private final int cacheSize;
        private final long cacheExpirationTime;
        private final int maxVariableCount;
        private final int maxLoopCount;
        private final int maxNestingDepth;
        
        public TemplateConfig(String templateType, String templateEngine, String encoding,
                             String variableStart, String variableEnd, boolean enableCache,
                             int cacheSize, long cacheExpirationTime, int maxVariableCount,
                             int maxLoopCount, int maxNestingDepth) {
            this.templateType = templateType;
            this.templateEngine = templateEngine;
            this.encoding = encoding;
            this.variableStart = variableStart;
            this.variableEnd = variableEnd;
            this.enableCache = enableCache;
            this.cacheSize = cacheSize;
            this.cacheExpirationTime = cacheExpirationTime;
            this.maxVariableCount = maxVariableCount;
            this.maxLoopCount = maxLoopCount;
            this.maxNestingDepth = maxNestingDepth;
        }
        
        // Getters
        public String getTemplateType() { return templateType; }
        public String getTemplateEngine() { return templateEngine; }
        public String getEncoding() { return encoding; }
        public String getVariableStart() { return variableStart; }
        public String getVariableEnd() { return variableEnd; }
        public boolean isEnableCache() { return enableCache; }
        public int getCacheSize() { return cacheSize; }
        public long getCacheExpirationTime() { return cacheExpirationTime; }
        public int getMaxVariableCount() { return maxVariableCount; }
        public int getMaxLoopCount() { return maxLoopCount; }
        public int getMaxNestingDepth() { return maxNestingDepth; }
    }
    
    /**
     * 变量定义类
     */
    public static class VariableDefinition {
        private final String name;
        private final String type;
        private final String description;
        private final boolean required;
        private final Object defaultValue;
        private final String formatter;
        
        public VariableDefinition(String name, String type, String description,
                                 boolean required, Object defaultValue, String formatter) {
            this.name = name;
            this.type = type;
            this.description = description;
            this.required = required;
            this.defaultValue = defaultValue;
            this.formatter = formatter;
        }
        
        // Getters
        public String getName() { return name; }
        public String getType() { return type; }
        public String getDescription() { return description; }
        public boolean isRequired() { return required; }
        public Object getDefaultValue() { return defaultValue; }
        public String getFormatter() { return formatter; }
    }
    
    // ========== 工具方法 ==========
    
    /**
     * 根据模板类型获取扩展名
     */
    public static String getExtensionByTemplateType(String templateType) {
        return TEMPLATE_TYPE_TO_EXTENSION_MAP.get(templateType);
    }
    
    /**
     * 根据扩展名获取模板类型
     */
    public static String getTemplateTypeByExtension(String extension) {
        return EXTENSION_TO_TEMPLATE_TYPE_MAP.get(extension.toLowerCase());
    }
    
    /**
     * 根据模板引擎获取扩展名
     */
    public static String getExtensionByEngine(String engine) {
        return ENGINE_TO_EXTENSION_MAP.get(engine);
    }
    
    /**
     * 检查是否为支持的模板类型
     */
    public static boolean isSupportedTemplateType(String templateType) {
        return Arrays.asList(SUPPORTED_TEMPLATE_TYPES).contains(templateType);
    }
    
    /**
     * 检查是否为支持的模板引擎
     */
    public static boolean isSupportedTemplateEngine(String engine) {
        return Arrays.asList(SUPPORTED_TEMPLATE_ENGINES).contains(engine);
    }
    
    /**
     * 检查是否为支持的数据类型
     */
    public static boolean isSupportedDataType(String dataType) {
        return Arrays.asList(SUPPORTED_DATA_TYPES).contains(dataType);
    }
    
    /**
     * 检查是否为支持的格式化器
     */
    public static boolean isSupportedFormatter(String formatter) {
        return Arrays.asList(SUPPORTED_FORMATTERS).contains(formatter);
    }
    
    /**
     * 从文件名获取模板类型
     */
    public static String getTemplateTypeFromFilename(String filename) {
        if (filename == null || filename.isEmpty()) {
            return null;
        }
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex > 0) {
            String extension = filename.substring(lastDotIndex);
            return getTemplateTypeByExtension(extension);
        }
        return null;
    }
    
    /**
     * 构建变量表达式
     */
    public static String buildVariableExpression(String variableName, String start, String end) {
        return start + variableName + end;
    }
    
    /**
     * 构建默认变量表达式
     */
    public static String buildDefaultVariableExpression(String variableName) {
        return buildVariableExpression(variableName, DEFAULT_VARIABLE_START, DEFAULT_VARIABLE_END);
    }
    
    // ========== 预设配置方法 ==========
    
    /**
     * 获取默认模板配置
     */
    public static TemplateConfig getDefaultTemplateConfig() {
        return new TemplateConfig(DEFAULT_TEMPLATE_TYPE, DEFAULT_TEMPLATE_ENGINE, DEFAULT_ENCODING,
                                 DEFAULT_VARIABLE_START, DEFAULT_VARIABLE_END, true,
                                 DEFAULT_CACHE_SIZE, CACHE_EXPIRATION_TIME, MAX_VARIABLE_COUNT,
                                 MAX_LOOP_COUNT, MAX_NESTING_DEPTH);
    }
    
    /**
     * 获取Freemarker模板配置
     */
    public static TemplateConfig getFreemarkerTemplateConfig() {
        return new TemplateConfig(TEMPLATE_TYPE_FREEMARKER, ENGINE_FREEMARKER, DEFAULT_ENCODING,
                                 DEFAULT_VARIABLE_START, DEFAULT_VARIABLE_END, true,
                                 DEFAULT_CACHE_SIZE, CACHE_EXPIRATION_TIME, MAX_VARIABLE_COUNT,
                                 MAX_LOOP_COUNT, MAX_NESTING_DEPTH);
    }
    
    /**
     * 获取Velocity模板配置
     */
    public static TemplateConfig getVelocityTemplateConfig() {
        return new TemplateConfig(TEMPLATE_TYPE_VELOCITY, ENGINE_VELOCITY, DEFAULT_ENCODING,
                                 DEFAULT_VARIABLE_START, DEFAULT_VARIABLE_END, true,
                                 DEFAULT_CACHE_SIZE, CACHE_EXPIRATION_TIME, MAX_VARIABLE_COUNT,
                                 MAX_LOOP_COUNT, MAX_NESTING_DEPTH);
    }
    
    /**
     * 获取高性能模板配置
     */
    public static TemplateConfig getHighPerformanceTemplateConfig() {
        return new TemplateConfig(DEFAULT_TEMPLATE_TYPE, DEFAULT_TEMPLATE_ENGINE, DEFAULT_ENCODING,
                                 DEFAULT_VARIABLE_START, DEFAULT_VARIABLE_END, true,
                                 MAX_CACHE_SIZE, CACHE_EXPIRATION_TIME, MAX_VARIABLE_COUNT,
                                 MAX_LOOP_COUNT, MAX_NESTING_DEPTH);
    }
    
    /**
     * 获取简单模板配置（无缓存）
     */
    public static TemplateConfig getSimpleTemplateConfig() {
        return new TemplateConfig(DEFAULT_TEMPLATE_TYPE, DEFAULT_TEMPLATE_ENGINE, DEFAULT_ENCODING,
                                 DEFAULT_VARIABLE_START, DEFAULT_VARIABLE_END, false,
                                 0, 0, MAX_VARIABLE_COUNT, MAX_LOOP_COUNT, MAX_NESTING_DEPTH);
    }
}