package com.boundesu.words.core.options;

/**
 * DocxSaveOptions provides options for saving DOCX documents.
 * This class mimics the Aspose Words DocxSaveOptions API.
 */
public class DocxSaveOptions extends SaveOptions {
    private CompressionLevel compressionLevel;
    private String password;

    public DocxSaveOptions() {
        super(SaveFormat.DOCX);
        this.compressionLevel = CompressionLevel.NORMAL;
    }

    public DocxSaveOptions(SaveFormat saveFormat) {
        super(saveFormat);
        this.compressionLevel = CompressionLevel.NORMAL;
    }

    // Getters and setters
    public CompressionLevel getCompressionLevel() {
        return compressionLevel;
    }

    public void setCompressionLevel(CompressionLevel compressionLevel) {
        this.compressionLevel = compressionLevel;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    // Enum
    public enum CompressionLevel {
        NORMAL, MAXIMUM, FAST, SUPER_FAST
    }
}