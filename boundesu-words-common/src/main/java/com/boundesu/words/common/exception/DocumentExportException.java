package com.boundesu.words.common.exception;

/**
 * 文档导出异常
 *
 * @author Boundesu
 * @version 1.0.0
 */
public class DocumentExportException extends BoundesuWordsException {

    private static final long serialVersionUID = 1L;

    public DocumentExportException(String message) {
        super("EXPORT_ERROR", message);
    }

    public DocumentExportException(String message, Throwable cause) {
        super("EXPORT_ERROR", message, cause);
    }
}