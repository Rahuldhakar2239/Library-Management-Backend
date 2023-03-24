package com.library.management.payloads;

import java.util.Date;

public class RecordDto {
	public RecordDto() {
	}

	private int id;

	private Date due_date;

//	format - "return_date": "2023-05-29"
	private Date return_date;

	private int book_Id;

	private String book_name;

	private UserDto user;

	public int getId() {
		return id;
	}

	public Date getDue_date() {
		return due_date;
	}

	public Date getReturn_date() {
		return return_date;
	}

	public int getBook_Id() {
		return book_Id;
	}

	public UserDto getUser() {
		return user;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}

	public void setBook_Id(int book_Id) {
		this.book_Id = book_Id;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

}
