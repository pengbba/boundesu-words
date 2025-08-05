package com.boundesu.words.core.config;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * XML加载配置类
 * 参考Aspose.Words的XmlLoadOptions功能，提供XML文档加载时的各种配置选项
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public class XmlLoadConfig {

    // 编码设置
    private Charset encoding = StandardCharsets.UTF_8;
    // XML解析模式
    private XmlParseMode parseMode = XmlParseMode.LENIENT;
    // 命名空间处理
    private NamespaceMode namespaceMode = NamespaceMode.PRESERVE;
    // 验证选项
    private boolean validateXml = false;
    private String schemaLocation;
    private String dtdLocation;
    // 转换选项
    private boolean preserveWhitespace = true;
    private boolean preserveComments = false;
    private boolean preserveProcessingInstructions = false;
    // 元素处理选项
    private boolean convertEmptyElements = true;
    private boolean mergeAdjacentText = true;
    private boolean normalizeText = false;
    // 属性处理选项
    private boolean preserveAttributes = true;
    private boolean convertAttributesToElements = false;
    // CDATA处理选项
    private boolean preserveCData = true;
    private boolean convertCDataToText = false;
    // 实体处理选项
    private boolean resolveExternalEntities = false;
    private boolean expandEntityReferences = true;
    // 错误处理选项
    private boolean ignoreParseErrors = false;
    private boolean logParseErrors = true;
    private int maxErrorCount = 10;
    // 性能选项
    private boolean useStreamingParser = false;
    private int bufferSize = 8192;
    private boolean enableCaching = true;
    // 自定义映射
    private Map<String, String> elementMappings = new HashMap<>();
    private Map<String, String> attributeMappings = new HashMap<>();
    // 过滤选项
    private String[] includeElements;
    private String[] excludeElements;
    private String[] includeAttributes;
    private String[] excludeAttributes;

    /**
     * 创建默认配置
     */
    public static XmlLoadConfig createDefault() {
        return new XmlLoadConfig()
                .setEncoding(StandardCharsets.UTF_8)
                .setParseMode(XmlParseMode.LENIENT)
                .setNamespaceMode(NamespaceMode.PRESERVE)
                .setValidationOptions(false, null, null)
                .setContentPreservation(true, false, false)
                .setElementOptions(true, true, false)
                .setAttributeOptions(true, false)
                .setCDataOptions(true, false)
                .setEntityOptions(false, true)
                .setErrorHandling(false, true, 10)
                .setPerformanceOptions(false, 8192, true);
    }

    /**
     * 创建严格配置（用于结构化XML）
     */
    public static XmlLoadConfig createStrict() {
        return new XmlLoadConfig()
                .setEncoding(StandardCharsets.UTF_8)
                .setParseMode(XmlParseMode.STRICT)
                .setNamespaceMode(NamespaceMode.PRESERVE)
                .setValidationOptions(true, null, null)
                .setContentPreservation(true, true, true)
                .setElementOptions(false, false, true)
                .setAttributeOptions(true, false)
                .setCDataOptions(true, false)
                .setEntityOptions(true, true)
                .setErrorHandling(false, true, 0)
                .setPerformanceOptions(false, 8192, true);
    }

    /**
     * 创建快速配置（优化性能）
     */
    public static XmlLoadConfig createFast() {
        return new XmlLoadConfig()
                .setEncoding(StandardCharsets.UTF_8)
                .setParseMode(XmlParseMode.LENIENT)
                .setNamespaceMode(NamespaceMode.IGNORE)
                .setValidationOptions(false, null, null)
                .setContentPreservation(false, false, false)
                .setElementOptions(true, true, true)
                .setAttributeOptions(false, false)
                .setCDataOptions(false, true)
                .setEntityOptions(false, false)
                .setErrorHandling(true, false, 100)
                .setPerformanceOptions(true, 16384, false);
    }

    /**
     * 创建Word兼容配置
     */
    public static XmlLoadConfig createWordCompatible() {
        XmlLoadConfig config = new XmlLoadConfig()
                .setEncoding(StandardCharsets.UTF_8)
                .setParseMode(XmlParseMode.LENIENT)
                .setNamespaceMode(NamespaceMode.STRIP)
                .setValidationOptions(false, null, null)
                .setContentPreservation(true, false, false)
                .setElementOptions(true, true, true)
                .setAttributeOptions(true, true)
                .setCDataOptions(false, true)
                .setEntityOptions(false, true)
                .setErrorHandling(true, true, 50)
                .setPerformanceOptions(false, 8192, true);

        // 添加常用的Word元素映射
        config.addElementMapping("title", "heading")
                .addElementMapping("subtitle", "heading")
                .addElementMapping("para", "paragraph")
                .addElementMapping("text", "run")
                .addElementMapping("bold", "strong")
                .addElementMapping("italic", "emphasis")
                .addElementMapping("underline", "underline")
                .addElementMapping("list", "list")
                .addElementMapping("item", "listItem")
                .addElementMapping("table", "table")
                .addElementMapping("row", "tableRow")
                .addElementMapping("cell", "tableCell");

        return config;
    }

    /**
     * 设置XML验证选项
     */
    public XmlLoadConfig setValidationOptions(boolean validateXml, String schemaLocation, String dtdLocation) {
        this.validateXml = validateXml;
        this.schemaLocation = schemaLocation;
        this.dtdLocation = dtdLocation;
        return this;
    }

    /**
     * 设置内容保留选项
     */
    public XmlLoadConfig setContentPreservation(boolean preserveWhitespace, boolean preserveComments, boolean preservePI) {
        this.preserveWhitespace = preserveWhitespace;
        this.preserveComments = preserveComments;
        this.preserveProcessingInstructions = preservePI;
        return this;
    }

    /**
     * 设置元素处理选项
     */
    public XmlLoadConfig setElementOptions(boolean convertEmpty, boolean mergeText, boolean normalizeText) {
        this.convertEmptyElements = convertEmpty;
        this.mergeAdjacentText = mergeText;
        this.normalizeText = normalizeText;
        return this;
    }

    /**
     * 设置属性处理选项
     */
    public XmlLoadConfig setAttributeOptions(boolean preserveAttributes, boolean convertToElements) {
        this.preserveAttributes = preserveAttributes;
        this.convertAttributesToElements = convertToElements;
        return this;
    }

    /**
     * 设置CDATA处理选项
     */
    public XmlLoadConfig setCDataOptions(boolean preserveCData, boolean convertToText) {
        this.preserveCData = preserveCData;
        this.convertCDataToText = convertToText;
        return this;
    }

    /**
     * 设置实体处理选项
     */
    public XmlLoadConfig setEntityOptions(boolean resolveExternal, boolean expandReferences) {
        this.resolveExternalEntities = resolveExternal;
        this.expandEntityReferences = expandReferences;
        return this;
    }

    /**
     * 设置错误处理选项
     */
    public XmlLoadConfig setErrorHandling(boolean ignoreErrors, boolean logErrors, int maxErrors) {
        this.ignoreParseErrors = ignoreErrors;
        this.logParseErrors = logErrors;
        this.maxErrorCount = maxErrors;
        return this;
    }

    /**
     * 设置性能选项
     */
    public XmlLoadConfig setPerformanceOptions(boolean useStreaming, int bufferSize, boolean enableCaching) {
        this.useStreamingParser = useStreaming;
        this.bufferSize = bufferSize;
        this.enableCaching = enableCaching;
        return this;
    }

    /**
     * 添加元素映射
     */
    public XmlLoadConfig addElementMapping(String xmlElement, String wordElement) {
        this.elementMappings.put(xmlElement, wordElement);
        return this;
    }

    /**
     * 添加属性映射
     */
    public XmlLoadConfig addAttributeMapping(String xmlAttribute, String wordAttribute) {
        this.attributeMappings.put(xmlAttribute, wordAttribute);
        return this;
    }

    /**
     * 设置元素过滤
     */
    public XmlLoadConfig setElementFilter(String[] includeElements, String[] excludeElements) {
        this.includeElements = includeElements;
        this.excludeElements = excludeElements;
        return this;
    }

    /**
     * 设置属性过滤
     */
    public XmlLoadConfig setAttributeFilter(String[] includeAttributes, String[] excludeAttributes) {
        this.includeAttributes = includeAttributes;
        this.excludeAttributes = excludeAttributes;
        return this;
    }

    // Getter方法
    public Charset getEncoding() {
        return encoding;
    }

    /**
     * 设置字符编码
     */
    public XmlLoadConfig setEncoding(Charset encoding) {
        this.encoding = encoding;
        return this;
    }

    public XmlParseMode getParseMode() {
        return parseMode;
    }

    /**
     * 设置XML解析模式
     */
    public XmlLoadConfig setParseMode(XmlParseMode parseMode) {
        this.parseMode = parseMode;
        return this;
    }

    public NamespaceMode getNamespaceMode() {
        return namespaceMode;
    }

    /**
     * 设置命名空间处理模式
     */
    public XmlLoadConfig setNamespaceMode(NamespaceMode namespaceMode) {
        this.namespaceMode = namespaceMode;
        return this;
    }

    public boolean isValidateXml() {
        return validateXml;
    }

    public String getSchemaLocation() {
        return schemaLocation;
    }

    public String getDtdLocation() {
        return dtdLocation;
    }

    public boolean isPreserveWhitespace() {
        return preserveWhitespace;
    }

    public boolean isPreserveComments() {
        return preserveComments;
    }

    public boolean isPreserveProcessingInstructions() {
        return preserveProcessingInstructions;
    }

    public boolean isConvertEmptyElements() {
        return convertEmptyElements;
    }

    public boolean isMergeAdjacentText() {
        return mergeAdjacentText;
    }

    public boolean isNormalizeText() {
        return normalizeText;
    }

    public boolean isPreserveAttributes() {
        return preserveAttributes;
    }

    public boolean isConvertAttributesToElements() {
        return convertAttributesToElements;
    }

    public boolean isPreserveCData() {
        return preserveCData;
    }

    public boolean isConvertCDataToText() {
        return convertCDataToText;
    }

    public boolean isResolveExternalEntities() {
        return resolveExternalEntities;
    }

    public boolean isExpandEntityReferences() {
        return expandEntityReferences;
    }

    public boolean isIgnoreParseErrors() {
        return ignoreParseErrors;
    }

    public boolean isLogParseErrors() {
        return logParseErrors;
    }

    public int getMaxErrorCount() {
        return maxErrorCount;
    }

    public boolean isUseStreamingParser() {
        return useStreamingParser;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public boolean isEnableCaching() {
        return enableCaching;
    }

    public Map<String, String> getElementMappings() {
        return elementMappings;
    }

    public Map<String, String> getAttributeMappings() {
        return attributeMappings;
    }

    public String[] getIncludeElements() {
        return includeElements;
    }

    public String[] getExcludeElements() {
        return excludeElements;
    }

    public String[] getIncludeAttributes() {
        return includeAttributes;
    }

    public String[] getExcludeAttributes() {
        return excludeAttributes;
    }

    // XML解析模式枚举
    public enum XmlParseMode {
        STRICT,     // 严格模式
        LENIENT,    // 宽松模式
        AUTO        // 自动检测
    }

    // XML命名空间处理模式
    public enum NamespaceMode {
        IGNORE,     // 忽略命名空间
        PRESERVE,   // 保留命名空间
        STRIP       // 移除命名空间
    }
}