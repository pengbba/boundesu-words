package com.boundesu.words.core.options;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * LoadOptions provides options for loading documents.
 * This class mimics the Aspose Words LoadOptions API.
 */
public class LoadOptions {
    protected LoadFormat loadFormat;
    protected String baseUri;
    protected boolean convertMetafilesToPng;
    protected boolean convertShapeToOfficeMath;
    protected Charset encoding;
    protected Object fontSettings;
    protected Object languagePreferences;
    protected MsWordVersion msWordVersion;
    protected String password;
    protected boolean preserveIncludePictureField;
    protected Object resourceLoadingCallback;
    protected String tempFolder;
    protected boolean updateDirtyFields;
    protected Object warningCallback;
    protected boolean ignoreOleData;

    public LoadOptions() {
        this.loadFormat = LoadFormat.AUTO;
        this.encoding = StandardCharsets.UTF_8;
        this.msWordVersion = MsWordVersion.WORD_2019;
        this.convertMetafilesToPng = true;
        this.convertShapeToOfficeMath = false;
        this.preserveIncludePictureField = false;
        this.updateDirtyFields = false;
        this.ignoreOleData = false;
    }

    public LoadOptions(LoadFormat loadFormat) {
        this();
        this.loadFormat = loadFormat;
    }

    public LoadOptions(String password) {
        this();
        this.password = password;
    }

    public LoadOptions(LoadFormat loadFormat, String password, String baseUri) {
        this();
        this.loadFormat = loadFormat;
        this.password = password;
        this.baseUri = baseUri;
    }

    // Getters and setters
    public LoadFormat getLoadFormat() {
        return loadFormat;
    }

    public void setLoadFormat(LoadFormat loadFormat) {
        this.loadFormat = loadFormat;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public boolean isConvertMetafilesToPng() {
        return convertMetafilesToPng;
    }

    public void setConvertMetafilesToPng(boolean convertMetafilesToPng) {
        this.convertMetafilesToPng = convertMetafilesToPng;
    }

    public boolean isConvertShapeToOfficeMath() {
        return convertShapeToOfficeMath;
    }

    public void setConvertShapeToOfficeMath(boolean convertShapeToOfficeMath) {
        this.convertShapeToOfficeMath = convertShapeToOfficeMath;
    }

    public Charset getEncoding() {
        return encoding;
    }

    public void setEncoding(Charset encoding) {
        this.encoding = encoding;
    }

    public Object getFontSettings() {
        return fontSettings;
    }

    public void setFontSettings(Object fontSettings) {
        this.fontSettings = fontSettings;
    }

    public Object getLanguagePreferences() {
        return languagePreferences;
    }

    public void setLanguagePreferences(Object languagePreferences) {
        this.languagePreferences = languagePreferences;
    }

    public MsWordVersion getMsWordVersion() {
        return msWordVersion;
    }

    public void setMsWordVersion(MsWordVersion msWordVersion) {
        this.msWordVersion = msWordVersion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPreserveIncludePictureField() {
        return preserveIncludePictureField;
    }

    public void setPreserveIncludePictureField(boolean preserveIncludePictureField) {
        this.preserveIncludePictureField = preserveIncludePictureField;
    }

    public Object getResourceLoadingCallback() {
        return resourceLoadingCallback;
    }

    public void setResourceLoadingCallback(Object resourceLoadingCallback) {
        this.resourceLoadingCallback = resourceLoadingCallback;
    }

    public String getTempFolder() {
        return tempFolder;
    }

    public void setTempFolder(String tempFolder) {
        this.tempFolder = tempFolder;
    }

    public boolean isUpdateDirtyFields() {
        return updateDirtyFields;
    }

    public void setUpdateDirtyFields(boolean updateDirtyFields) {
        this.updateDirtyFields = updateDirtyFields;
    }

    public Object getWarningCallback() {
        return warningCallback;
    }

    public void setWarningCallback(Object warningCallback) {
        this.warningCallback = warningCallback;
    }

    public boolean isIgnoreOleData() {
        return ignoreOleData;
    }

    public void setIgnoreOleData(boolean ignoreOleData) {
        this.ignoreOleData = ignoreOleData;
    }

    // Enums
    public enum LoadFormat {
        AUTO, DOC, DOCX, DOCM, DOT, DOTX, DOTM, RTF, WORD_ML, HTML, MHTML, MOBI, CHM, ODT, OTT, TXT, MD
    }

    public enum MsWordVersion {
        WORD_2000, WORD_2002, WORD_2003, WORD_2007, WORD_2010, WORD_2013, WORD_2016, WORD_2019
    }
}