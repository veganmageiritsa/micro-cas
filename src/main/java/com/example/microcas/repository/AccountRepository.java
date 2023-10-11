package com.example.microcas.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import com.example.microcas.domain.Account;


public interface AccountRepository extends CassandraRepository<Account, UUID> {
    
    @AllowFiltering
    Optional<Account> findByCustomerId(UUID customerId);
    
    void deleteByCustomerId(UUID customerId);
    
}
