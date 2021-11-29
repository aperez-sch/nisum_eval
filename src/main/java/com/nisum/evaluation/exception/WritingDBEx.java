
package com.nisum.evaluation.exception;


public class WritingDBEx extends RuntimeException {
    
    private String message;
    public WritingDBEx(String message) {
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
