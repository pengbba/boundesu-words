package com.boundesu.words.core.options;

/**
 * SaveOptions provides options for saving documents.
 * This class mimics the Aspose Words SaveOptions API.
 */
public abstract class SaveOptions {
    protected SaveFormat saveFormat;
    protected String password;
    protected boolean prettyFormat;
    protected String tempFolder;
    protected boolean updateCreatedTimeProperty;
    protected boolean updateFields;
    protected boolean updateLastPrintedProperty;
    protected boolean updateLastSavedTimeProperty;
    protected boolean updateSdtContent;
    protected boolean useAntiAliasing;
    protected boolean useHighQualityRendering;
    protected Object warningCallback;

    protected SaveOptions(SaveFormat saveFormat) {
        this.saveFormat = saveFormat;
        this.prettyFormat = false;
        this.updateCreatedTimeProperty = false;
        this.updateFields = false;
        this.updateLastPrintedProperty = true;
        this.updateLastSavedTimeProperty = true;
        this.updateSdtContent = true;
        this.useAntiAliasing = false;
        this.useHighQualityRendering = false;
    }

    // Getters and setters
    public SaveFormat getSaveFormat() {
        return saveFormat;
    }

    public void setSaveFormat(SaveFormat saveFormat) {
        this.saveFormat = saveFormat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPrettyFormat() {
        return prettyFormat;
    }

    public void setPrettyFormat(boolean prettyFormat) {
        this.prettyFormat = prettyFormat;
    }

    public String getTempFolder() {
        return tempFolder;
    }

    public void setTempFolder(String tempFolder) {
        this.tempFolder = tempFolder;
    }

    public boolean isUpdateCreatedTimeProperty() {
        return updateCreatedTimeProperty;
    }

    public void setUpdateCreatedTimeProperty(boolean updateCreatedTimeProperty) {
        this.updateCreatedTimeProperty = updateCreatedTimeProperty;
    }

    public boolean isUpdateFields() {
        return updateFields;
    }

    public void setUpdateFields(boolean updateFields) {
        this.updateFields = updateFields;
    }

    public boolean isUpdateLastPrintedProperty() {
        return updateLastPrintedProperty;
    }

    public void setUpdateLastPrintedProperty(boolean updateLastPrintedProperty) {
        this.updateLastPrintedProperty = updateLastPrintedProperty;
    }

    public boolean isUpdateLastSavedTimeProperty() {
        return updateLastSavedTimeProperty;
    }

    public void setUpdateLastSavedTimeProperty(boolean updateLastSavedTimeProperty) {
        this.updateLastSavedTimeProperty = updateLastSavedTimeProperty;
    }

    public boolean isUpdateSdtContent() {
        return updateSdtContent;
    }

    public void setUpdateSdtContent(boolean updateSdtContent) {
        this.updateSdtContent = updateSdtContent;
    }

    public boolean isUseAntiAliasing() {
        return useAntiAliasing;
    }

    public void setUseAntiAliasing(boolean useAntiAliasing) {
        this.useAntiAliasing = useAntiAliasing;
    }

    public boolean isUseHighQualityRendering() {
        return useHighQualityRendering;
    }

    public void setUseHighQualityRendering(boolean useHighQualityRendering) {
        this.useHighQualityRendering = useHighQualityRendering;
    }

    public Object getWarningCallback() {
        return warningCallback;
    }

    public void setWarningCallback(Object warningCallback) {
        this.warningCallback = warningCallback;
    }

    // Enum
    public enum SaveFormat {
        DOC, DOCX, DOCM, DOT, DOTX, DOTM, RTF, PDF, XPS, EPUB, MHTML, HTML, ODT, OTT, TXT, XAML_FLOW, XAML_FLOW_PACK, TIFF, PNG, BMP, EMF, JPEG, GIF, SVG
    }
}