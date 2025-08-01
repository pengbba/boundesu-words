package com.boundesu.words.sdk.config;

/**
 * Boundesu Words SDK 配置管理器
 * 独立的配置类，不依赖Spring框架
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class BoundesuWordsConfig {
    
    private static BoundesuWordsConfig instance;
    
    /**
     * 获取配置实例（单例模式）
     */
    public static BoundesuWordsConfig getInstance() {
        if (instance == null) {
            synchronized (BoundesuWordsConfig.class) {
                if (instance == null) {
                    instance = new BoundesuWordsConfig();
                }
            }
        }
        return instance;
    }
    
    private BoundesuWordsConfig() {
        // 私有构造函数
    }

    // 基本配置
    private String encoding = "UTF-8";
    private DateConfig date = new DateConfig();

    // 文档限制配置
    private MaxConfig max = new MaxConfig();

    // 功能开关配置
    private EnableConfig enable = new EnableConfig();
    private DebugConfig debug = new DebugConfig();
    private PerformanceConfig performance = new PerformanceConfig();

    // 导出配置
    private ExportConfig export = new ExportConfig();

    // 缓存配置
    private CacheConfig cache = new CacheConfig();

    // 日志配置
    private LogConfig log = new LogConfig();

    // 安全配置
    private SecurityConfig security = new SecurityConfig();

    // 公司信息
    private CompanyConfig company = new CompanyConfig();

    // 默认配置
    private DefaultConfig defaultConfig = new DefaultConfig();

    // 路径配置
    private TemplateConfig template = new TemplateConfig();
    private TempConfig temp = new TempConfig();

    // Getters and Setters
    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public DateConfig getDate() {
        return date;
    }

    public void setDate(DateConfig date) {
        this.date = date;
    }

    public MaxConfig getMax() {
        return max;
    }

    public void setMax(MaxConfig max) {
        this.max = max;
    }

    public EnableConfig getEnable() {
        return enable;
    }

    public void setEnable(EnableConfig enable) {
        this.enable = enable;
    }

    public DebugConfig getDebug() {
        return debug;
    }

    public void setDebug(DebugConfig debug) {
        this.debug = debug;
    }

    public PerformanceConfig getPerformance() {
        return performance;
    }

    public void setPerformance(PerformanceConfig performance) {
        this.performance = performance;
    }

    public ExportConfig getExport() {
        return export;
    }

    public void setExport(ExportConfig export) {
        this.export = export;
    }

    public CacheConfig getCache() {
        return cache;
    }

    public void setCache(CacheConfig cache) {
        this.cache = cache;
    }

    public LogConfig getLog() {
        return log;
    }

    public void setLog(LogConfig log) {
        this.log = log;
    }

    public SecurityConfig getSecurity() {
        return security;
    }

    public void setSecurity(SecurityConfig security) {
        this.security = security;
    }

    public CompanyConfig getCompany() {
        return company;
    }

    public void setCompany(CompanyConfig company) {
        this.company = company;
    }

    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    public void setDefaultConfig(DefaultConfig defaultConfig) {
        this.defaultConfig = defaultConfig;
    }

    public TemplateConfig getTemplate() {
        return template;
    }

    public void setTemplate(TemplateConfig template) {
        this.template = template;
    }

    public TempConfig getTemp() {
        return temp;
    }

    public void setTemp(TempConfig temp) {
        this.temp = temp;
    }

    // 便捷方法 - 保持向后兼容
    public int getMaxDocumentSize() {
        return max.getDocument().getSize();
    }
    
    public int getMaxParagraphLength() {
        return max.getParagraph().getLength();
    }
    
    public int getMaxTableRows() {
        return max.getTable().getRows();
    }
    
    public int getMaxTableColumns() {
        return max.getTable().getColumns();
    }
    
    public boolean isValidationEnabled() {
        return enable.isValidation();
    }
    
    public boolean isAutoSaveEnabled() {
        return enable.getAuto().isSave();
    }
    
    public String getDateFormat() {
        return date.getFormat();
    }
    
    public String getExportEncoding() {
        return export.getEncoding();
    }
    
    /**
     * 打印当前配置
     */
    public void printConfiguration() {
        System.out.println("=== Boundesu Words SDK 配置信息 ===");
        System.out.println("字符编码: " + getEncoding());
        System.out.println("文档最大大小: " + getMaxDocumentSize() + " 字节");
        System.out.println("段落最大长度: " + getMaxParagraphLength());
        System.out.println("表格最大行数: " + getMaxTableRows());
        System.out.println("表格最大列数: " + getMaxTableColumns());
        System.out.println("启用验证: " + isValidationEnabled());
        System.out.println("启用自动保存: " + isAutoSaveEnabled());
        System.out.println("日期格式: " + getDateFormat());
        System.out.println("导出编码: " + getExportEncoding());
        System.out.println("调试模式: " + debug.isMode());
        System.out.println("性能监控: " + performance.isMonitoring());
        System.out.println("缓存启用: " + cache.isEnabled());
        System.out.println("日志级别: " + log.getLevel());
        System.out.println("公司名称: " + company.getName());
        System.out.println("================================");
    }

    // 内部配置类
    public static class DateConfig {
        private String format = "yyyy-MM-dd HH:mm:ss";

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }
    }

    public static class MaxConfig {
        private DocumentConfig document = new DocumentConfig();
        private ParagraphConfig paragraph = new ParagraphConfig();
        private TableConfig table = new TableConfig();

        public DocumentConfig getDocument() {
            return document;
        }

        public void setDocument(DocumentConfig document) {
            this.document = document;
        }

        public ParagraphConfig getParagraph() {
            return paragraph;
        }

        public void setParagraph(ParagraphConfig paragraph) {
            this.paragraph = paragraph;
        }

        public TableConfig getTable() {
            return table;
        }

        public void setTable(TableConfig table) {
            this.table = table;
        }

        public static class DocumentConfig {
            private int size = 10485760; // 10MB

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }
        }

        public static class ParagraphConfig {
            private int length = 10000;

            public int getLength() {
                return length;
            }

            public void setLength(int length) {
                this.length = length;
            }
        }

        public static class TableConfig {
            private int rows = 1000;
            private int columns = 100;

            public int getRows() {
                return rows;
            }

            public void setRows(int rows) {
                this.rows = rows;
            }

            public int getColumns() {
                return columns;
            }

            public void setColumns(int columns) {
                this.columns = columns;
            }
        }
    }

    public static class EnableConfig {
        private boolean validation = true;
        private AutoConfig auto = new AutoConfig();

        public boolean isValidation() {
            return validation;
        }

        public void setValidation(boolean validation) {
            this.validation = validation;
        }

        public AutoConfig getAuto() {
            return auto;
        }

        public void setAuto(AutoConfig auto) {
            this.auto = auto;
        }

        public static class AutoConfig {
            private boolean save = false;

            public boolean isSave() {
                return save;
            }

            public void setSave(boolean save) {
                this.save = save;
            }
        }
    }

    public static class DebugConfig {
        private boolean mode = false;

        public boolean isMode() {
            return mode;
        }

        public void setMode(boolean mode) {
            this.mode = mode;
        }
    }

    public static class PerformanceConfig {
        private boolean monitoring = false;

        public boolean isMonitoring() {
            return monitoring;
        }

        public void setMonitoring(boolean monitoring) {
            this.monitoring = monitoring;
        }
    }

    public static class ExportConfig {
        private String encoding = "UTF-8";
        private HtmlConfig html = new HtmlConfig();
        private MarkdownConfig markdown = new MarkdownConfig();
        private AddConfig add = new AddConfig();

        public String getEncoding() {
            return encoding;
        }

        public void setEncoding(String encoding) {
            this.encoding = encoding;
        }

        public HtmlConfig getHtml() {
            return html;
        }

        public void setHtml(HtmlConfig html) {
            this.html = html;
        }

        public MarkdownConfig getMarkdown() {
            return markdown;
        }

        public void setMarkdown(MarkdownConfig markdown) {
            this.markdown = markdown;
        }

        public AddConfig getAdd() {
            return add;
        }

        public void setAdd(AddConfig add) {
            this.add = add;
        }

        public static class HtmlConfig {
            private IncludeConfig include = new IncludeConfig();

            public IncludeConfig getInclude() {
                return include;
            }

            public void setInclude(IncludeConfig include) {
                this.include = include;
            }

            public static class IncludeConfig {
                private boolean css = true;

                public boolean isCss() {
                    return css;
                }

                public void setCss(boolean css) {
                    this.css = css;
                }
            }
        }

        public static class MarkdownConfig {
            private GithubConfig github = new GithubConfig();

            public GithubConfig getGithub() {
                return github;
            }

            public void setGithub(GithubConfig github) {
                this.github = github;
            }

            public static class GithubConfig {
                private boolean style = true;

                public boolean isStyle() {
                    return style;
                }

                public void setStyle(boolean style) {
                    this.style = style;
                }
            }
        }

        public static class AddConfig {
            private boolean timestamp = true;

            public boolean isTimestamp() {
                return timestamp;
            }

            public void setTimestamp(boolean timestamp) {
                this.timestamp = timestamp;
            }
        }
    }

    public static class CacheConfig {
        private boolean enabled = true;
        private MaxConfig max = new MaxConfig();
        private ExpiryConfig expiry = new ExpiryConfig();

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public MaxConfig getMax() {
            return max;
        }

        public void setMax(MaxConfig max) {
            this.max = max;
        }

        public ExpiryConfig getExpiry() {
            return expiry;
        }

        public void setExpiry(ExpiryConfig expiry) {
            this.expiry = expiry;
        }

        public static class MaxConfig {
            private int entries = 1000;

            public int getEntries() {
                return entries;
            }

            public void setEntries(int entries) {
                this.entries = entries;
            }
        }

        public static class ExpiryConfig {
            private int minutes = 30;

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }
        }
    }

    public static class LogConfig {
        private String level = "INFO";
        private FileConfig file = new FileConfig();

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public FileConfig getFile() {
            return file;
        }

        public void setFile(FileConfig file) {
            this.file = file;
        }

        public static class FileConfig {
            private boolean enabled = false;
            private String path = "logs/boundesu-words.log";

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }
        }
    }

    public static class SecurityConfig {
        private ContentConfig content = new ContentConfig();
        private AllowedConfig allowed = new AllowedConfig();

        public ContentConfig getContent() {
            return content;
        }

        public void setContent(ContentConfig content) {
            this.content = content;
        }

        public AllowedConfig getAllowed() {
            return allowed;
        }

        public void setAllowed(AllowedConfig allowed) {
            this.allowed = allowed;
        }

        public static class ContentConfig {
            private boolean filter = false;

            public boolean isFilter() {
                return filter;
            }

            public void setFilter(boolean filter) {
                this.filter = filter;
            }
        }

        public static class AllowedConfig {
            private HtmlConfig html = new HtmlConfig();

            public HtmlConfig getHtml() {
                return html;
            }

            public void setHtml(HtmlConfig html) {
                this.html = html;
            }

            public static class HtmlConfig {
                private String tags = "p,div,span,h1,h2,h3,h4,h5,h6,strong,em,ul,ol,li,table,tr,td,th";

                public String getTags() {
                    return tags;
                }

                public void setTags(String tags) {
                    this.tags = tags;
                }
            }
        }
    }

    public static class CompanyConfig {
        private String name = "Boundesu";
        private String website = "https://boundesu.com";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }
    }

    public static class DefaultConfig {
        private String author = "系统用户";
        private String language = "zh-CN";

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }
    }

    public static class TemplateConfig {
        private String path = "templates/";

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }

    public static class TempConfig {
        private String path = "temp/";

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}