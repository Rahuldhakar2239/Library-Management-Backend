package com.library.management.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {

	public UserDto() {
	}

	private int id;
	@NotEmpty
	@Size(min = 4, message = "Username must be min of 4 characters")
	private String name;
	@Email(message = "Email address is not valid!")
	private String email;
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be between 3 &10 characters")
	private String password;
	@NotEmpty
	private String about;

	private MemberShipDto memberShip;

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

	public MemberShipDto getMemberShip() {
		return memberShip;
	}

	public void setMemberShip(MemberShipDto memberShip) {
		this.memberShip = memberShip;
	}

}
