package com.example.microcas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.config.EnableCassandraAuditing;

@EnableCassandraAuditing(auditorAwareRef = "auditAwareImpl" )
@SpringBootApplication
public class MicroCasApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(MicroCasApplication.class, args);
        
    }
    
}
