package com.boundesu.words.sdk.exception;

/**
 * 文档验证异常
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class DocumentValidationException extends BoundesuWordsException {
    
    private static final long serialVersionUID = 1L;
    
    public DocumentValidationException(String message) {
        super("VALIDATION_ERROR", message);
    }
    
    public DocumentValidationException(String message, Throwable cause) {
        super("VALIDATION_ERROR", message, cause);
    }
}