package com.library.management.payloads;

import jakarta.validation.constraints.NotEmpty;

public class MemberShipDto {
	public MemberShipDto() {

	}

	private int id;

//	Silver-Gold-Diamond
	@NotEmpty
	private String type;

//	300 - 800 - 3000
	private int price;

//	2 - 3 - 4
	private int max_book_issue;

//	1 month - 3month - 1year
	private String validity;

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public int getPrice() {
		return price;
	}

	public int getMax_book_issue() {
		return max_book_issue;
	}

	public String getValidity() {
		return validity;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setMax_book_issue(int max_book_issue) {
		this.max_book_issue = max_book_issue;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

}
