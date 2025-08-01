package com.boundesu.words.sdk.exception;

/**
 * Boundesu Words SDK 基础异常类
 * 
 * @author Boundesu
 * @version 1.0.0
 */
public class BoundesuWordsException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    private final String errorCode;
    
    public BoundesuWordsException(String message) {
        super(message);
        this.errorCode = "UNKNOWN";
    }
    
    public BoundesuWordsException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "UNKNOWN";
    }
    
    public BoundesuWordsException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public BoundesuWordsException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    @Override
    public String toString() {
        return String.format("BoundesuWordsException[%s]: %s", errorCode, getMessage());
    }
}