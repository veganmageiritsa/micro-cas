package com.example.microcas.domain;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.cassandra.core.mapping.Column;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseEntity {
    
    @Version
    private int version;
    @CreatedDate
    @Column(value = "created_at")
    private LocalDateTime createdAt;
    
    @CreatedBy
    @Column(value = "created_by")
    private String createdBy;
    
    @LastModifiedDate
    @Column(value = "update_at")
    private LocalDateTime updatedAt;
    
    @LastModifiedBy
    @Column(value = "update_by")
    private String updatedBy;
}
