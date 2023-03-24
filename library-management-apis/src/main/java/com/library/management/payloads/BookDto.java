package com.library.management.payloads;

import jakarta.validation.constraints.NotEmpty;

public class BookDto {
	public BookDto() {
	}

	private int id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String author;
	@NotEmpty
	private String category;
	private Boolean isAvailable = false;
	private int count;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public String getCategory() {
		return category;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
