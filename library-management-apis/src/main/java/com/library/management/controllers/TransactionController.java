package com.library.management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.entities.Transaction;
import com.library.management.services.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/")
	ResponseEntity<List<Transaction>> getAllTransaction() {
		List<Transaction> trans = this.transactionService.getAllTransaction();
		return new ResponseEntity<List<Transaction>>(trans, HttpStatus.OK);
	}
}
