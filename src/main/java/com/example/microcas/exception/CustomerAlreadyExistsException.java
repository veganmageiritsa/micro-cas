package com.example.microcas.exception;

public class CustomerAlreadyExistsException extends RuntimeException{
    
    public CustomerAlreadyExistsException(final String message) {
        super(message);
    }
    
}
