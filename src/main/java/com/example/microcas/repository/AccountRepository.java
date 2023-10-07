package com.example.microcas.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.example.microcas.domain.Account;


public interface AccountRepository extends CassandraRepository<Account, UUID> {

}
