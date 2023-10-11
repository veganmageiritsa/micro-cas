package com.example.microcas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException(final String resourceName,final String fieldName, final String fieldValue) {
        super(String.format("%s Not Found for the given input data %s : %s", resourceName,fieldName, fieldValue));
    }
    
}
