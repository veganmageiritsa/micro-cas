package com.example.microcas.dto;

import com.example.microcas.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto<T extends BaseEntity> {

    private String statusCode;
    
    private String statusMsg;
    
    private T entity;
}
