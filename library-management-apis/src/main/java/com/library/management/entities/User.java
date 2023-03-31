package com.library.management.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	public User() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "user_name", nullable = false, length = 100)
	private String name;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	private String about;

//	for checking - maximun no of issued book can not exceed the "max_book_issue" in MemberShip Entity
	private int totalBookIssued = 1;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Record> books = new ArrayList<>();

	@ManyToOne
	private MemberShip memberShip;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getAbout() {
		return about;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public List<Record> getBooks() {
		return books;
	}

	public void setBooks(List<Record> books) {
		this.books = books;
	}

	public MemberShip getMemberShip() {
		return memberShip;
	}

	public void setMemberShip(MemberShip memberShip) {
		this.memberShip = memberShip;
	}

	public int getTotalBookIssued() {
		return totalBookIssued;
	}

	public void setTotalBookIssued(int totalBookIssued) {
		this.totalBookIssued = totalBookIssued;
	}

}
