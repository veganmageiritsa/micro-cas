package com.example.microcas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto<T extends BaseDto> {
    
    private String statusCode;
    
    private String statusMsg;
    
    private T entity;
    
    public ResponseDto(final String statusCode, final String message) {
        this.statusCode = statusCode;
        this.statusMsg = message;
    }
    
}
