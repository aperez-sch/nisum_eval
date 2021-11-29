
package com.nisum.evaluation.exception;


public class DBException extends RuntimeException {
    
    private String message;
    public DBException(String message) {
        super(message);
        this.message = message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
}
