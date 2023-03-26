package com.library.management.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transaction {
	public Transaction() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transaction_id;

	@Column(nullable = false)
	private String user_name;

	@Column(nullable = false)
	private int user_id;

	@Column(nullable = false)
	private int amount;

	@Column(nullable = false)
	private String time;

	public int getTransaction_id() {
		return transaction_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public int getAmount() {
		return amount;
	}

	public String getTime() {
		return time;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}