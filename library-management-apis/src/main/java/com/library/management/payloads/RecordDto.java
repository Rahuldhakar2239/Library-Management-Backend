package com.library.management.payloads;

public class RecordDto {
	public RecordDto() {
	}

	private int id;

	private String issue_date;

	private String due_date;

	private String return_date;

	private boolean is_book_return = false;

	private int fine_amount = 0;

	private int book_Id;

	private String book_name;

	private UserDto user;

	public int getId() {
		return id;
	}

	public String getIssue_date() {
		return issue_date;
	}

	public String getDue_date() {
		return due_date;
	}

	public String getReturn_date() {
		return return_date;
	}

	public int getFine_amount() {
		return fine_amount;
	}

	public int getBook_Id() {
		return book_Id;
	}

	public String getBook_name() {
		return book_name;
	}

	public UserDto getUser() {
		return user;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIssue_date(String issue_date) {
		this.issue_date = issue_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}

	public void setFine_amount(int fine_amount) {
		this.fine_amount = fine_amount;
	}

	public void setBook_Id(int book_Id) {
		this.book_Id = book_Id;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public boolean isIs_book_return() {
		return is_book_return;
	}

	public void setIs_book_return(boolean is_book_return) {
		this.is_book_return = is_book_return;
	}

}
