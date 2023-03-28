package com.library.management.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class MemberShip {

	public MemberShip() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

//	Silver-Gold-Diamond
	@Column(nullable = false)
	private String type;

//	300 - 800 - 3000
	@Column(nullable = false)
	private int price;

//	2 - 3 - 4
	@Column(nullable = false)
	private int max_book_issue;

//	1 month - 3month - 1year
	@Column(nullable = false)
	private String validity;

	@OneToMany(mappedBy = "memberShip")
	private List<User> members = new ArrayList<>();

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
