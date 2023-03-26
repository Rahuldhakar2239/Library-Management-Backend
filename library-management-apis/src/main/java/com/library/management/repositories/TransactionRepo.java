package com.library.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.management.entities.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

}
