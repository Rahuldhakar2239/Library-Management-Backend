package com.library.management.services;

import java.util.List;

import com.library.management.entities.Transaction;
import com.library.management.entities.User;

public interface TransactionService {

	void createTransaction(User user, Integer amount);

	List<Transaction> getAllTransaction();
}
