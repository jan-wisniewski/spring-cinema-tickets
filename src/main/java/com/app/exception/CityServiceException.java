package com.app.exception;

/**
 * Custom exception to provide information about problem with CityService method execution
 */
public class CityServiceException extends RuntimeException {
    public CityServiceException(String message) {
        super(message);
    }
}
