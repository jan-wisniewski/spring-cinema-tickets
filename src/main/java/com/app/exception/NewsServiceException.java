package com.app.exception;

public class NewsServiceException extends RuntimeException {
    public NewsServiceException(String message) {
        super(message);
    }
}
