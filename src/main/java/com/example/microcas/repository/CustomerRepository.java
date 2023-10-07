package com.example.microcas.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import com.example.microcas.domain.Customer;

public interface CustomerRepository extends CassandraRepository<Customer, UUID> {

    @AllowFiltering
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
