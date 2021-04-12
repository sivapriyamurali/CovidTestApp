package com.example.codejava;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TransactionRepository extends JpaRepository<Transaction, Long>
{
    Optional<Transaction> findByPatientId(String email);

}
