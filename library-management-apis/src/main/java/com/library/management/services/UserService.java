package com.library.management.services;

import java.util.List;

import com.library.management.payloads.UserDto;

public interface UserService {
//	byDefault all methods are public in interface so it's optional to add public keyword in front of method name
	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	void deleteUser(Integer userId);
}
