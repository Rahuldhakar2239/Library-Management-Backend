package com.library.management.services.Implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.entities.MemberShip;
import com.library.management.entities.User;
import com.library.management.exceptions.ResourceNotFoundException;
import com.library.management.payloads.UserDto;
import com.library.management.repositories.MemberShipRepo;
import com.library.management.repositories.UserRepo;
import com.library.management.services.TransactionService;
import com.library.management.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private MemberShipRepo memberShipRepo;
	@Autowired
	private TransactionService transactionService;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());

		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);

		return userDto1;
	}

//	Purchase MemberShip
	@Override
	public UserDto TakeMemberShip(Integer userId, Integer memberShipTypeId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

		MemberShip memberType = this.memberShipRepo.findById(memberShipTypeId)
				.orElseThrow(() -> new ResourceNotFoundException("MemberShip", "id", memberShipTypeId));

		user.setMemberShip(memberType);
		User updatedUser = this.userRepo.save(user);
		this.transactionService.createTransaction(user, memberType.getPrice(), "Purchase MemberShip");

		return this.modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());

		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

		this.userRepo.delete(user);

	}

//	modelMapper Library is use to convert one class object to another class object
	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}

	private UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

}
