package com.boundesu.words.core.options;

/**
 * HtmlLoadOptions provides options for loading HTML documents.
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public class HtmlLoadOptions extends LoadOptions {
    private int webRequestTimeout;
    private BlockImportMode blockImportMode;
    private HtmlControlType preferredControlType;
    private boolean supportVml;
    private boolean convertSvgToEmf;
    private boolean ignoreNoscriptElements;
    private boolean supportFontFaceRules;

    /**
     * 初始化HtmlLoadOptions的新实例，使用默认值
     */
    public HtmlLoadOptions() {
        super(LoadFormat.HTML);
        this.webRequestTimeout = 100000; // 100秒
        this.blockImportMode = BlockImportMode.MERGE;
        this.preferredControlType = HtmlControlType.FORM_FIELD;
        this.supportVml = false;
        this.convertSvgToEmf = false;
        this.ignoreNoscriptElements = false;
        this.supportFontFaceRules = false;
    }

    /**
     * 使用指定密码初始化HtmlLoadOptions的新实例，用于加载加密文档
     *
     * @param password 打开加密文档的密码，可以为null或空字符串
     */
    public HtmlLoadOptions(String password) {
        super(password);
        this.loadFormat = LoadFormat.HTML;
        this.webRequestTimeout = 100000;
        this.blockImportMode = BlockImportMode.MERGE;
        this.preferredControlType = HtmlControlType.FORM_FIELD;
        this.supportVml = false;
        this.convertSvgToEmf = false;
        this.ignoreNoscriptElements = false;
        this.supportFontFaceRules = false;
    }

    /**
     * 使用指定属性值初始化HtmlLoadOptions的新实例
     *
     * @param loadFormat 要加载的文档格式
     * @param password   打开加密文档的密码，可以为null或空字符串
     * @param baseUri    用于将相对URI解析为绝对URI的字符串，可以为null或空字符串
     */
    public HtmlLoadOptions(LoadFormat loadFormat, String password, String baseUri) {
        super(loadFormat, password, baseUri);
        this.webRequestTimeout = 100000;
        this.blockImportMode = BlockImportMode.MERGE;
        this.preferredControlType = HtmlControlType.FORM_FIELD;
        this.supportVml = false;
        this.convertSvgToEmf = false;
        this.ignoreNoscriptElements = false;
        this.supportFontFaceRules = false;
    }

    // Getters and setters

    /**
     * 获取或设置Web请求超时时间（毫秒）
     * 默认值为100000毫秒（100秒）
     */
    public int getWebRequestTimeout() {
        return webRequestTimeout;
    }

    public void setWebRequestTimeout(int webRequestTimeout) {
        this.webRequestTimeout = webRequestTimeout;
    }

    /**
     * 获取或设置指定如何导入块级元素属性的值
     * 默认值为BlockImportMode.MERGE
     */
    public BlockImportMode getBlockImportMode() {
        return blockImportMode;
    }

    public void setBlockImportMode(BlockImportMode blockImportMode) {
        this.blockImportMode = blockImportMode;
    }

    /**
     * 获取或设置将表示导入的<input>和<select>元素的首选文档节点类型
     * 默认值为HtmlControlType.FORM_FIELD
     */
    public HtmlControlType getPreferredControlType() {
        return preferredControlType;
    }

    public void setPreferredControlType(HtmlControlType preferredControlType) {
        this.preferredControlType = preferredControlType;
    }

    /**
     * 获取或设置是否支持VML图像的值
     * 默认值为false
     */
    public boolean getSupportVml() {
        return supportVml;
    }

    public void setSupportVml(boolean supportVml) {
        this.supportVml = supportVml;
    }

    /**
     * 获取或设置是否将加载的SVG图像转换为EMF格式的值
     * 默认值为false，如果可能，加载的SVG图像将按原样存储而不进行转换
     */
    public boolean getConvertSvgToEmf() {
        return convertSvgToEmf;
    }

    public void setConvertSvgToEmf(boolean convertSvgToEmf) {
        this.convertSvgToEmf = convertSvgToEmf;
    }

    /**
     * 获取或设置是否忽略<noscript> HTML元素的值
     * 默认值为false
     */
    public boolean getIgnoreNoscriptElements() {
        return ignoreNoscriptElements;
    }

    public void setIgnoreNoscriptElements(boolean ignoreNoscriptElements) {
        this.ignoreNoscriptElements = ignoreNoscriptElements;
    }

    /**
     * 获取或设置是否支持@font-face规则以及是否加载声明字体的值
     * 默认值为false
     */
    public boolean getSupportFontFaceRules() {
        return supportFontFaceRules;
    }

    public void setSupportFontFaceRules(boolean supportFontFaceRules) {
        this.supportFontFaceRules = supportFontFaceRules;
    }

    // Enums

    /**
     * 指定如何导入块级元素属性
     */
    public enum BlockImportMode {
        /**
         * 合并父块的属性并将其存储在子元素上
         */
        MERGE,
        /**
         * 保留块级元素的原始属性
         */
        PRESERVE
    }

    /**
     * HTML控件类型枚举
     */
    public enum HtmlControlType {
        /**
         * 表单字段
         */
        FORM_FIELD,
        /**
         * 结构化文档标签
         */
        STRUCTURED_DOCUMENT_TAG
    }
}