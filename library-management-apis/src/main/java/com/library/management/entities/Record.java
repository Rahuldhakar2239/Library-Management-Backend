package com.library.management.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookRecord")
public class Record {
	public Record() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private Date due_date;

	private Date return_date;

	private int book_Id;
	private String book_name;

	@ManyToOne
	private User user;

	public int getId() {
		return id;
	}

	public Date getDue_date() {
		return due_date;
	}

	public int getBook_Id() {
		return book_Id;
	}

	public User getUser() {
		return user;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	public void setBook_Id(int book_Id) {
		this.book_Id = book_Id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getReturn_date() {
		return return_date;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

}
