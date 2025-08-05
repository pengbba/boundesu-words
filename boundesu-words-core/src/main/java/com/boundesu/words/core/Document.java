package com.boundesu.words.core;

import com.boundesu.words.core.options.LoadOptions;
import com.boundesu.words.core.options.SaveOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

/**
 * Document represents a Word document.
 * This class mimics the Aspose Words Document API.
 */
public class Document {
    private XWPFDocument document;
    private String originalFileName;

    public Document() {
        this.document = new XWPFDocument();
    }

    public Document(String fileName) throws IOException {
        this.originalFileName = fileName;
        try (FileInputStream fis = new FileInputStream(fileName)) {
            this.document = new XWPFDocument(fis);
        }
    }

    public Document(String fileName, LoadOptions loadOptions) throws IOException {
        this.originalFileName = fileName;
        try (FileInputStream fis = new FileInputStream(fileName)) {
            this.document = new XWPFDocument(fis);
        }
        // Apply load options if needed
        applyLoadOptions(loadOptions);
    }

    public Document(InputStream stream) throws IOException {
        this.document = new XWPFDocument(stream);
    }

    public Document(InputStream stream, LoadOptions loadOptions) throws IOException {
        this.document = new XWPFDocument(stream);
        applyLoadOptions(loadOptions);
    }

    public Document(XWPFDocument document) {
        this.document = document;
    }

    private void applyLoadOptions(LoadOptions loadOptions) {
        if (loadOptions != null) {
            // Apply load options to the document
            // This is a placeholder for actual implementation
        }
    }

    public XWPFDocument getInternalDocument() {
        return document;
    }

    public void save(String fileName) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            document.write(fos);
        }
    }

    public void save(String fileName, SaveOptions saveOptions) throws IOException {
        // Apply save options before saving
        applySaveOptions(saveOptions);
        save(fileName);
    }

    public void save(OutputStream stream) throws IOException {
        document.write(stream);
    }

    public void save(OutputStream stream, SaveOptions saveOptions) throws IOException {
        applySaveOptions(saveOptions);
        save(stream);
    }

    private void applySaveOptions(SaveOptions saveOptions) {
        if (saveOptions != null) {
            // Apply save options to the document
            // This is a placeholder for actual implementation
            if (saveOptions.isUpdateFields()) {
                updateFields();
            }
            if (saveOptions.isUpdateLastSavedTimeProperty()) {
                updateLastSavedTime();
            }
        }
    }

    public void updateFields() {
        // Update all fields in the document
        // This is a placeholder for actual implementation
    }

    private void updateLastSavedTime() {
        // Update the last saved time property
        // This is a placeholder for actual implementation
    }

    public void acceptAllRevisions() {
        // Accept all revisions in the document
        // This is a placeholder for actual implementation
    }

    public void protect(ProtectionType protectionType) {
        protect(protectionType, null);
    }

    public void protect(ProtectionType protectionType, String password) {
        // Protect the document with the specified protection type and password
        // This is a placeholder for actual implementation
    }

    public void unprotect() {
        unprotect(null);
    }

    public void unprotect(String password) {
        // Remove protection from the document
        // This is a placeholder for actual implementation
    }

    public boolean hasRevisions() {
        // Check if the document has revisions
        // This is a placeholder for actual implementation
        return false;
    }

    public int getPageCount() {
        // Get the number of pages in the document
        // This is a placeholder for actual implementation
        return 1;
    }

    public void cleanup() {
        // Clean up unused styles and lists
        // This is a placeholder for actual implementation
    }

    public Document clone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.write(baos);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            return new Document(bais);
        } catch (IOException e) {
            throw new RuntimeException("Failed to clone document", e);
        }
    }

    public void close() throws IOException {
        if (document != null) {
            document.close();
        }
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    // Enum
    public enum ProtectionType {
        NO_PROTECTION, ALLOW_ONLY_REVISIONS, ALLOW_ONLY_COMMENTS, ALLOW_ONLY_FORM_FIELDS, READ_ONLY
    }
}