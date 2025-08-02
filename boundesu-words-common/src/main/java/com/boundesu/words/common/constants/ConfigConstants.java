package com.boundesu.words.common.constants;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * 配置相关常量
 * 管理系统配置、默认设置、环境变量等
 * 
 * @author Boundesu Team
 * @version 1.0.0
 */
public final class ConfigConstants {
    
    /**
     * 私有构造函数，防止实例化
     */
    private ConfigConstants() {
        throw new UnsupportedOperationException("常量类不能被实例化");
    }
    
    // ========== 系统配置常量 ==========
    
    /** 默认语言 */
    public static final String DEFAULT_LANGUAGE = "zh-CN";
    
    /** 默认地区 */
    public static final String DEFAULT_LOCALE = "zh_CN";
    
    /** 默认时区 */
    public static final String DEFAULT_TIMEZONE = "Asia/Shanghai";
    
    /** 默认日期格式 */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    
    /** 默认时间格式 */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    
    /** 默认日期时间格式 */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    /** 默认数字格式 */
    public static final String DEFAULT_NUMBER_FORMAT = "#,##0.##";
    
    /** 默认货币格式 */
    public static final String DEFAULT_CURRENCY_FORMAT = "¥#,##0.00";
    
    // ========== 应用配置常量 ==========
    
    /** 应用名称 */
    public static final String APP_NAME = "Boundesu Words";
    
    /** 应用版本 */
    public static final String APP_VERSION = "1.0.0";
    
    /** 应用描述 */
    public static final String APP_DESCRIPTION = "Professional Document Processing Library";
    
    /** 应用作者 */
    public static final String APP_AUTHOR = "Boundesu Team";
    
    /** 应用官网 */
    public static final String APP_WEBSITE = "https://boundesu.com";
    
    /** 应用支持邮箱 */
    public static final String APP_SUPPORT_EMAIL = "support@boundesu.com";
    
    // ========== 文件配置常量 ==========
    
    /** 默认输出目录 */
    public static final String DEFAULT_OUTPUT_DIR = "output";
    
    /** 默认临时目录 */
    public static final String DEFAULT_TEMP_DIR = "temp";
    
    /** 默认缓存目录 */
    public static final String DEFAULT_CACHE_DIR = "cache";
    
    /** 默认日志目录 */
    public static final String DEFAULT_LOG_DIR = "logs";
    
    /** 默认配置文件名 */
    public static final String DEFAULT_CONFIG_FILE = "config.properties";
    
    /** 默认日志文件名 */
    public static final String DEFAULT_LOG_FILE = "application.log";
    
    /** 最大文件大小（字节） */
    public static final long MAX_FILE_SIZE = 100 * 1024 * 1024; // 100MB
    
    /** 最大缓存大小（字节） */
    public static final long MAX_CACHE_SIZE = 500 * 1024 * 1024; // 500MB
    
    /** 文件清理间隔（毫秒） */
    public static final long FILE_CLEANUP_INTERVAL = 24 * 60 * 60 * 1000; // 24小时
    
    // ========== 性能配置常量 ==========
    
    /** 默认线程池大小 */
    public static final int DEFAULT_THREAD_POOL_SIZE = 10;
    
    /** 最大线程池大小 */
    public static final int MAX_THREAD_POOL_SIZE = 50;
    
    /** 线程池空闲时间（秒） */
    public static final int THREAD_POOL_KEEP_ALIVE_TIME = 60;
    
    /** 默认连接超时时间（毫秒） */
    public static final int DEFAULT_CONNECTION_TIMEOUT = 30000; // 30秒
    
    /** 默认读取超时时间（毫秒） */
    public static final int DEFAULT_READ_TIMEOUT = 60000; // 60秒
    
    /** 默认写入超时时间（毫秒） */
    public static final int DEFAULT_WRITE_TIMEOUT = 60000; // 60秒
    
    /** 最大重试次数 */
    public static final int MAX_RETRY_COUNT = 3;
    
    /** 重试间隔（毫秒） */
    public static final int RETRY_INTERVAL = 1000; // 1秒
    
    // ========== 内存配置常量 ==========
    
    /** 默认缓冲区大小（字节） */
    public static final int DEFAULT_BUFFER_SIZE = 8192; // 8KB
    
    /** 大缓冲区大小（字节） */
    public static final int LARGE_BUFFER_SIZE = 65536; // 64KB
    
    /** 最大缓冲区大小（字节） */
    public static final int MAX_BUFFER_SIZE = 1048576; // 1MB
    
    /** 默认批处理大小 */
    public static final int DEFAULT_BATCH_SIZE = 100;
    
    /** 最大批处理大小 */
    public static final int MAX_BATCH_SIZE = 1000;
    
    /** 内存警告阈值（百分比） */
    public static final double MEMORY_WARNING_THRESHOLD = 0.8; // 80%
    
    /** 内存清理阈值（百分比） */
    public static final double MEMORY_CLEANUP_THRESHOLD = 0.9; // 90%
    
    // ========== 日志配置常量 ==========
    
    /** 默认日志级别 */
    public static final String DEFAULT_LOG_LEVEL = "INFO";
    
    /** 日志级别 */
    public static final String LOG_LEVEL_TRACE = "TRACE";
    public static final String LOG_LEVEL_DEBUG = "DEBUG";
    public static final String LOG_LEVEL_INFO = "INFO";
    public static final String LOG_LEVEL_WARN = "WARN";
    public static final String LOG_LEVEL_ERROR = "ERROR";
    public static final String LOG_LEVEL_FATAL = "FATAL";
    
    /** 支持的日志级别 */
    public static final String[] SUPPORTED_LOG_LEVELS = {
        LOG_LEVEL_TRACE, LOG_LEVEL_DEBUG, LOG_LEVEL_INFO, 
        LOG_LEVEL_WARN, LOG_LEVEL_ERROR, LOG_LEVEL_FATAL
    };
    
    /** 默认日志格式 */
    public static final String DEFAULT_LOG_PATTERN = "%d{yyyy-MM-dd HH:mm:ss} [%level] %logger{36} - %msg%n";
    
    /** 最大日志文件大小 */
    public static final String MAX_LOG_FILE_SIZE = "10MB";
    
    /** 最大日志文件数量 */
    public static final int MAX_LOG_FILE_COUNT = 10;
    
    // ========== 安全配置常量 ==========
    
    /** 默认加密算法 */
    public static final String DEFAULT_ENCRYPTION_ALGORITHM = "AES";
    
    /** 默认哈希算法 */
    public static final String DEFAULT_HASH_ALGORITHM = "SHA-256";
    
    /** 默认密钥长度 */
    public static final int DEFAULT_KEY_LENGTH = 256;
    
    /** 密码最小长度 */
    public static final int MIN_PASSWORD_LENGTH = 8;
    
    /** 密码最大长度 */
    public static final int MAX_PASSWORD_LENGTH = 128;
    
    /** 会话超时时间（毫秒） */
    public static final long SESSION_TIMEOUT = 30 * 60 * 1000; // 30分钟
    
    /** 令牌过期时间（毫秒） */
    public static final long TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 24小时
    
    // ========== 网络配置常量 ==========
    
    /** 默认用户代理 */
    public static final String DEFAULT_USER_AGENT = "Boundesu-Words/1.0.0";
    
    /** 默认字符集 */
    public static final String DEFAULT_CHARSET = "UTF-8";
    
    /** 默认内容类型 */
    public static final String DEFAULT_CONTENT_TYPE = "application/json";
    
    /** HTTP状态码 */
    public static final int HTTP_OK = 200;
    public static final int HTTP_CREATED = 201;
    public static final int HTTP_NO_CONTENT = 204;
    public static final int HTTP_BAD_REQUEST = 400;
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int HTTP_FORBIDDEN = 403;
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_INTERNAL_ERROR = 500;
    
    // ========== 环境变量常量 ==========
    
    /** 开发环境 */
    public static final String ENV_DEVELOPMENT = "development";
    
    /** 测试环境 */
    public static final String ENV_TEST = "test";
    
    /** 预生产环境 */
    public static final String ENV_STAGING = "staging";
    
    /** 生产环境 */
    public static final String ENV_PRODUCTION = "production";
    
    /** 默认环境 */
    public static final String DEFAULT_ENVIRONMENT = ENV_DEVELOPMENT;
    
    /** 支持的环境 */
    public static final String[] SUPPORTED_ENVIRONMENTS = {
        ENV_DEVELOPMENT, ENV_TEST, ENV_STAGING, ENV_PRODUCTION
    };
    
    // ========== 功能开关常量 ==========
    
    /** 启用调试模式 */
    public static final String FEATURE_DEBUG_MODE = "debug.mode.enabled";
    
    /** 启用性能监控 */
    public static final String FEATURE_PERFORMANCE_MONITORING = "performance.monitoring.enabled";
    
    /** 启用缓存 */
    public static final String FEATURE_CACHE_ENABLED = "cache.enabled";
    
    /** 启用压缩 */
    public static final String FEATURE_COMPRESSION_ENABLED = "compression.enabled";
    
    /** 启用加密 */
    public static final String FEATURE_ENCRYPTION_ENABLED = "encryption.enabled";
    
    /** 启用审计日志 */
    public static final String FEATURE_AUDIT_LOG_ENABLED = "audit.log.enabled";
    
    /** 启用自动备份 */
    public static final String FEATURE_AUTO_BACKUP_ENABLED = "auto.backup.enabled";
    
    /** 启用并发处理 */
    public static final String FEATURE_CONCURRENT_PROCESSING = "concurrent.processing.enabled";
    
    // ========== 配置键常量 ==========
    
    /** 配置文件路径键 */
    public static final String CONFIG_FILE_PATH_KEY = "config.file.path";
    
    /** 输出目录键 */
    public static final String OUTPUT_DIR_KEY = "output.directory";
    
    /** 临时目录键 */
    public static final String TEMP_DIR_KEY = "temp.directory";
    
    /** 日志级别键 */
    public static final String LOG_LEVEL_KEY = "log.level";
    
    /** 线程池大小键 */
    public static final String THREAD_POOL_SIZE_KEY = "thread.pool.size";
    
    /** 缓存大小键 */
    public static final String CACHE_SIZE_KEY = "cache.size";
    
    /** 数据库URL键 */
    public static final String DATABASE_URL_KEY = "database.url";
    
    /** 数据库用户名键 */
    public static final String DATABASE_USERNAME_KEY = "database.username";
    
    /** 数据库密码键 */
    public static final String DATABASE_PASSWORD_KEY = "database.password";
    
    // ========== 配置类 ==========
    
    /**
     * 系统配置类
     */
    public static class SystemConfig {
        private final String language;
        private final String locale;
        private final String timezone;
        private final String dateFormat;
        private final String timeFormat;
        private final String environment;
        
        public SystemConfig(String language, String locale, String timezone,
                           String dateFormat, String timeFormat, String environment) {
            this.language = language;
            this.locale = locale;
            this.timezone = timezone;
            this.dateFormat = dateFormat;
            this.timeFormat = timeFormat;
            this.environment = environment;
        }
        
        // Getters
        public String getLanguage() { return language; }
        public String getLocale() { return locale; }
        public String getTimezone() { return timezone; }
        public String getDateFormat() { return dateFormat; }
        public String getTimeFormat() { return timeFormat; }
        public String getEnvironment() { return environment; }
    }
    
    /**
     * 性能配置类
     */
    public static class PerformanceConfig {
        private final int threadPoolSize;
        private final int connectionTimeout;
        private final int readTimeout;
        private final int bufferSize;
        private final int batchSize;
        private final int maxRetryCount;
        
        public PerformanceConfig(int threadPoolSize, int connectionTimeout, int readTimeout,
                                int bufferSize, int batchSize, int maxRetryCount) {
            this.threadPoolSize = threadPoolSize;
            this.connectionTimeout = connectionTimeout;
            this.readTimeout = readTimeout;
            this.bufferSize = bufferSize;
            this.batchSize = batchSize;
            this.maxRetryCount = maxRetryCount;
        }
        
        // Getters
        public int getThreadPoolSize() { return threadPoolSize; }
        public int getConnectionTimeout() { return connectionTimeout; }
        public int getReadTimeout() { return readTimeout; }
        public int getBufferSize() { return bufferSize; }
        public int getBatchSize() { return batchSize; }
        public int getMaxRetryCount() { return maxRetryCount; }
    }
    
    /**
     * 安全配置类
     */
    public static class SecurityConfig {
        private final String encryptionAlgorithm;
        private final String hashAlgorithm;
        private final int keyLength;
        private final long sessionTimeout;
        private final long tokenExpirationTime;
        
        public SecurityConfig(String encryptionAlgorithm, String hashAlgorithm, int keyLength,
                             long sessionTimeout, long tokenExpirationTime) {
            this.encryptionAlgorithm = encryptionAlgorithm;
            this.hashAlgorithm = hashAlgorithm;
            this.keyLength = keyLength;
            this.sessionTimeout = sessionTimeout;
            this.tokenExpirationTime = tokenExpirationTime;
        }
        
        // Getters
        public String getEncryptionAlgorithm() { return encryptionAlgorithm; }
        public String getHashAlgorithm() { return hashAlgorithm; }
        public int getKeyLength() { return keyLength; }
        public long getSessionTimeout() { return sessionTimeout; }
        public long getTokenExpirationTime() { return tokenExpirationTime; }
    }
    
    // ========== 工具方法 ==========
    
    /**
     * 检查是否为支持的环境
     */
    public static boolean isSupportedEnvironment(String environment) {
        return Arrays.asList(SUPPORTED_ENVIRONMENTS).contains(environment);
    }
    
    /**
     * 检查是否为支持的日志级别
     */
    public static boolean isSupportedLogLevel(String logLevel) {
        return Arrays.asList(SUPPORTED_LOG_LEVELS).contains(logLevel);
    }
    
    /**
     * 检查是否为生产环境
     */
    public static boolean isProductionEnvironment(String environment) {
        return ENV_PRODUCTION.equals(environment);
    }
    
    /**
     * 检查是否为开发环境
     */
    public static boolean isDevelopmentEnvironment(String environment) {
        return ENV_DEVELOPMENT.equals(environment);
    }
    
    /**
     * 检查是否为测试环境
     */
    public static boolean isTestEnvironment(String environment) {
        return ENV_TEST.equals(environment) || ENV_STAGING.equals(environment);
    }
    
    // ========== 预设配置方法 ==========
    
    /**
     * 获取默认系统配置
     */
    public static SystemConfig getDefaultSystemConfig() {
        return new SystemConfig(DEFAULT_LANGUAGE, DEFAULT_LOCALE, DEFAULT_TIMEZONE,
                               DEFAULT_DATE_FORMAT, DEFAULT_TIME_FORMAT, DEFAULT_ENVIRONMENT);
    }
    
    /**
     * 获取生产环境系统配置
     */
    public static SystemConfig getProductionSystemConfig() {
        return new SystemConfig(DEFAULT_LANGUAGE, DEFAULT_LOCALE, DEFAULT_TIMEZONE,
                               DEFAULT_DATE_FORMAT, DEFAULT_TIME_FORMAT, ENV_PRODUCTION);
    }
    
    /**
     * 获取默认性能配置
     */
    public static PerformanceConfig getDefaultPerformanceConfig() {
        return new PerformanceConfig(DEFAULT_THREAD_POOL_SIZE, DEFAULT_CONNECTION_TIMEOUT,
                                    DEFAULT_READ_TIMEOUT, DEFAULT_BUFFER_SIZE,
                                    DEFAULT_BATCH_SIZE, MAX_RETRY_COUNT);
    }
    
    /**
     * 获取高性能配置
     */
    public static PerformanceConfig getHighPerformanceConfig() {
        return new PerformanceConfig(MAX_THREAD_POOL_SIZE, DEFAULT_CONNECTION_TIMEOUT,
                                    DEFAULT_READ_TIMEOUT, LARGE_BUFFER_SIZE,
                                    MAX_BATCH_SIZE, MAX_RETRY_COUNT);
    }
    
    /**
     * 获取默认安全配置
     */
    public static SecurityConfig getDefaultSecurityConfig() {
        return new SecurityConfig(DEFAULT_ENCRYPTION_ALGORITHM, DEFAULT_HASH_ALGORITHM,
                                 DEFAULT_KEY_LENGTH, SESSION_TIMEOUT, TOKEN_EXPIRATION_TIME);
    }
}