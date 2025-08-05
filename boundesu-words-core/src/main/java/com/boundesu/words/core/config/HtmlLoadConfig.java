package com.boundesu.words.core.config;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * HTML加载配置类
 * 参考Aspose.Words的HtmlLoadOptions功能，提供HTML文档加载时的各种配置选项
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public class HtmlLoadConfig {

    // 编码设置
    private Charset encoding = StandardCharsets.UTF_8;
    // Word版本兼容性
    private MsWordVersion msWordVersion = MsWordVersion.WORD_2019;
    // 是否保留原始格式
    private boolean preserveFormatting = true;
    // 是否转换图片
    private boolean convertImages = true;
    // 图片处理选项
    private String imageFolder;
    private boolean embedImages = true;
    // CSS处理选项
    private boolean processCss = true;
    private boolean inlineCss = true;
    // 表格处理选项
    private boolean preserveTableLayout = true;
    private boolean autoFitTables = true;
    // 字体处理选项
    private String defaultFontFamily = "Microsoft YaHei";
    private int defaultFontSize = 12;
    private boolean substituteSystemFonts = true;
    // 段落和间距选项
    private boolean preserveLineSpacing = true;
    private boolean preserveParagraphSpacing = true;
    // 列表处理选项
    private boolean preserveLists = true;
    private boolean convertBulletLists = true;
    private boolean convertNumberedLists = true;
    // 链接处理选项
    private boolean preserveHyperlinks = true;
    private boolean convertRelativeUrls = false;
    private String baseUrl;
    // 元数据选项
    private boolean includeMetadata = true;
    private Map<String, String> customProperties = new HashMap<>();
    // 清理选项
    private boolean removeEmptyParagraphs = true;
    private boolean removeExtraSpaces = true;
    private boolean normalizeWhitespace = true;

    /**
     * 创建默认配置
     */
    public static HtmlLoadConfig createDefault() {
        return new HtmlLoadConfig()
                .setEncoding(StandardCharsets.UTF_8)
                .setMsWordVersion(MsWordVersion.WORD_2019)
                .setPreserveFormatting(true)
                .setImageOptions(true, true, null)
                .setCssOptions(true, true)
                .setTableOptions(true, true)
                .setDefaultFont("Microsoft YaHei", 12)
                .setFontSubstitution(true)
                .setSpacingOptions(true, true)
                .setListOptions(true, true, true)
                .setLinkOptions(true, false, null)
                .setMetadataOptions(true)
                .setCleanupOptions(true, true, true);
    }

    /**
     * 创建高质量配置（保留更多原始格式）
     */
    public static HtmlLoadConfig createHighQuality() {
        return new HtmlLoadConfig()
                .setEncoding(StandardCharsets.UTF_8)
                .setMsWordVersion(MsWordVersion.WORD_2019)
                .setPreserveFormatting(true)
                .setImageOptions(true, true, null)
                .setCssOptions(true, false)  // 不内联CSS，保持原始样式
                .setTableOptions(true, false)  // 不自动调整表格
                .setDefaultFont("Microsoft YaHei", 12)
                .setFontSubstitution(false)  // 不替换字体
                .setSpacingOptions(true, true)
                .setListOptions(true, true, true)
                .setLinkOptions(true, true, null)
                .setMetadataOptions(true)
                .setCleanupOptions(false, false, false);  // 不进行清理
    }

    /**
     * 创建快速配置（优化性能）
     */
    public static HtmlLoadConfig createFast() {
        return new HtmlLoadConfig()
                .setEncoding(StandardCharsets.UTF_8)
                .setMsWordVersion(MsWordVersion.WORD_2019)
                .setPreserveFormatting(false)
                .setImageOptions(false, false, null)  // 不处理图片
                .setCssOptions(false, false)  // 不处理CSS
                .setTableOptions(false, true)
                .setDefaultFont("Arial", 12)
                .setFontSubstitution(true)
                .setSpacingOptions(false, false)
                .setListOptions(false, false, false)
                .setLinkOptions(false, false, null)
                .setMetadataOptions(false)
                .setCleanupOptions(true, true, true);
    }

    /**
     * 设置图片处理选项
     */
    public HtmlLoadConfig setImageOptions(boolean convertImages, boolean embedImages, String imageFolder) {
        this.convertImages = convertImages;
        this.embedImages = embedImages;
        this.imageFolder = imageFolder;
        return this;
    }

    /**
     * 设置CSS处理选项
     */
    public HtmlLoadConfig setCssOptions(boolean processCss, boolean inlineCss) {
        this.processCss = processCss;
        this.inlineCss = inlineCss;
        return this;
    }

    /**
     * 设置表格处理选项
     */
    public HtmlLoadConfig setTableOptions(boolean preserveLayout, boolean autoFit) {
        this.preserveTableLayout = preserveLayout;
        this.autoFitTables = autoFit;
        return this;
    }

    /**
     * 设置默认字体
     */
    public HtmlLoadConfig setDefaultFont(String fontFamily, int fontSize) {
        this.defaultFontFamily = fontFamily;
        this.defaultFontSize = fontSize;
        return this;
    }

    /**
     * 设置字体替换选项
     */
    public HtmlLoadConfig setFontSubstitution(boolean substituteSystemFonts) {
        this.substituteSystemFonts = substituteSystemFonts;
        return this;
    }

    /**
     * 设置间距保留选项
     */
    public HtmlLoadConfig setSpacingOptions(boolean preserveLineSpacing, boolean preserveParagraphSpacing) {
        this.preserveLineSpacing = preserveLineSpacing;
        this.preserveParagraphSpacing = preserveParagraphSpacing;
        return this;
    }

    /**
     * 设置列表处理选项
     */
    public HtmlLoadConfig setListOptions(boolean preserveLists, boolean convertBullets, boolean convertNumbered) {
        this.preserveLists = preserveLists;
        this.convertBulletLists = convertBullets;
        this.convertNumberedLists = convertNumbered;
        return this;
    }

    /**
     * 设置链接处理选项
     */
    public HtmlLoadConfig setLinkOptions(boolean preserveHyperlinks, boolean convertRelativeUrls, String baseUrl) {
        this.preserveHyperlinks = preserveHyperlinks;
        this.convertRelativeUrls = convertRelativeUrls;
        this.baseUrl = baseUrl;
        return this;
    }

    /**
     * 设置元数据选项
     */
    public HtmlLoadConfig setMetadataOptions(boolean includeMetadata) {
        this.includeMetadata = includeMetadata;
        return this;
    }

    /**
     * 添加自定义属性
     */
    public HtmlLoadConfig addCustomProperty(String key, String value) {
        this.customProperties.put(key, value);
        return this;
    }

    /**
     * 设置清理选项
     */
    public HtmlLoadConfig setCleanupOptions(boolean removeEmptyParagraphs, boolean removeExtraSpaces, boolean normalizeWhitespace) {
        this.removeEmptyParagraphs = removeEmptyParagraphs;
        this.removeExtraSpaces = removeExtraSpaces;
        this.normalizeWhitespace = normalizeWhitespace;
        return this;
    }

    // Getter方法
    public Charset getEncoding() {
        return encoding;
    }

    /**
     * 设置字符编码
     */
    public HtmlLoadConfig setEncoding(Charset encoding) {
        this.encoding = encoding;
        return this;
    }

    public MsWordVersion getMsWordVersion() {
        return msWordVersion;
    }

    /**
     * 设置Word版本兼容性
     */
    public HtmlLoadConfig setMsWordVersion(MsWordVersion version) {
        this.msWordVersion = version;
        return this;
    }

    public boolean isPreserveFormatting() {
        return preserveFormatting;
    }

    /**
     * 设置是否保留原始格式
     */
    public HtmlLoadConfig setPreserveFormatting(boolean preserveFormatting) {
        this.preserveFormatting = preserveFormatting;
        return this;
    }

    public boolean isConvertImages() {
        return convertImages;
    }

    public String getImageFolder() {
        return imageFolder;
    }

    public boolean isEmbedImages() {
        return embedImages;
    }

    public boolean isProcessCss() {
        return processCss;
    }

    public boolean isInlineCss() {
        return inlineCss;
    }

    public boolean isPreserveTableLayout() {
        return preserveTableLayout;
    }

    public boolean isAutoFitTables() {
        return autoFitTables;
    }

    public String getDefaultFontFamily() {
        return defaultFontFamily;
    }

    public int getDefaultFontSize() {
        return defaultFontSize;
    }

    public boolean isSubstituteSystemFonts() {
        return substituteSystemFonts;
    }

    public boolean isPreserveLineSpacing() {
        return preserveLineSpacing;
    }

    public boolean isPreserveParagraphSpacing() {
        return preserveParagraphSpacing;
    }

    public boolean isPreserveLists() {
        return preserveLists;
    }

    public boolean isConvertBulletLists() {
        return convertBulletLists;
    }

    public boolean isConvertNumberedLists() {
        return convertNumberedLists;
    }

    public boolean isPreserveHyperlinks() {
        return preserveHyperlinks;
    }

    public boolean isConvertRelativeUrls() {
        return convertRelativeUrls;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public boolean isIncludeMetadata() {
        return includeMetadata;
    }

    public Map<String, String> getCustomProperties() {
        return customProperties;
    }

    public boolean isRemoveEmptyParagraphs() {
        return removeEmptyParagraphs;
    }

    public boolean isRemoveExtraSpaces() {
        return removeExtraSpaces;
    }

    public boolean isNormalizeWhitespace() {
        return normalizeWhitespace;
    }

    // Word版本枚举
    public enum MsWordVersion {
        WORD_2007("2007"),
        WORD_2010("2010"),
        WORD_2013("2013"),
        WORD_2016("2016"),
        WORD_2019("2019"),
        WORD_365("365");

        private final String version;

        MsWordVersion(String version) {
            this.version = version;
        }

        public String getVersion() {
            return version;
        }
    }
}