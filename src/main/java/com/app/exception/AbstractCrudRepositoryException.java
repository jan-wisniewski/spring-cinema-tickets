package com.app.exception;

public class AbstractCrudRepositoryException extends RuntimeException {
    public AbstractCrudRepositoryException(String message) {
        super(message);
    }
}
