package com.boundesu.words.core.options;

/**
 * HtmlLoadOptions provides options for loading HTML documents.
 * This class mimics the Aspose Words HtmlLoadOptions API.
 */
public class HtmlLoadOptions extends LoadOptions {
    private int webRequestTimeout;
    private BlockImportMode blockImportMode;
    private String preferredControlType;
    private String supportVml;

    public HtmlLoadOptions() {
        super(LoadFormat.HTML);
        this.webRequestTimeout = 100000;
        this.blockImportMode = BlockImportMode.MERGE;
        this.preferredControlType = "";
        this.supportVml = "true";
    }

    public HtmlLoadOptions(String password) {
        super(password);
        this.loadFormat = LoadFormat.HTML;
        this.webRequestTimeout = 100000;
        this.blockImportMode = BlockImportMode.MERGE;
        this.preferredControlType = "";
        this.supportVml = "true";
    }

    public HtmlLoadOptions(LoadFormat loadFormat, String password, String baseUri) {
        super(loadFormat, password, baseUri);
        this.webRequestTimeout = 100000;
        this.blockImportMode = BlockImportMode.MERGE;
        this.preferredControlType = "";
        this.supportVml = "true";
    }

    // Getters and setters
    public int getWebRequestTimeout() {
        return webRequestTimeout;
    }

    public void setWebRequestTimeout(int webRequestTimeout) {
        this.webRequestTimeout = webRequestTimeout;
    }

    public BlockImportMode getBlockImportMode() {
        return blockImportMode;
    }

    public void setBlockImportMode(BlockImportMode blockImportMode) {
        this.blockImportMode = blockImportMode;
    }

    public String getPreferredControlType() {
        return preferredControlType;
    }

    public void setPreferredControlType(String preferredControlType) {
        this.preferredControlType = preferredControlType;
    }

    public String getSupportVml() {
        return supportVml;
    }

    public void setSupportVml(String supportVml) {
        this.supportVml = supportVml;
    }

    // Enum
    public enum BlockImportMode {
        MERGE, PRESERVE
    }
}