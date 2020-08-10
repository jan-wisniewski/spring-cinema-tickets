package com.app.exception;

public class TicketServiceException extends RuntimeException {
    public TicketServiceException(String message) {
        super(message);
    }
}
