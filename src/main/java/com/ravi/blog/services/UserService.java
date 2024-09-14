package com.ravi.blog.services;

import java.util.List;

import com.ravi.blog.payloads.UserDto;

public interface UserService {

	UserDto updateUser(UserDto user, Integer userId);
	UserDto createUser(UserDto user);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteuser(Integer userId);
}
