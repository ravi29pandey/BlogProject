package com.ravi.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.blog.exceptions.*;
import com.ravi.blog.entities.User;
import com.ravi.blog.payloads.UserDto;
import com.ravi.blog.repositories.UserRepo;
import com.ravi.blog.services.UserService;


@Service
public class UserServiceImpl implements UserService {
	
  
	@Autowired
  private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper ;
	
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
      User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));

      
      user.setName(userDto.getName());
      user.setEmail(userDto.getEmail());
      user.setPassword(userDto.getPassword());
      user.setAbout(userDto.getAbout());
      
		User updateduser=this.userRepo.save(user);
		
		return this.userToDto(updateduser);
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
	
		List<User> users=this.userRepo.findAll();
		
		List<UserDto> userDto =users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		
		return userDto; 
	}

	@Override
	public void deleteuser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Users","Id",userId));

		this.userRepo.delete(user);
		
	}
	
	public User dtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
		//ModelMapper converts object of one class to other which is done below.
		//User user=new User();
		//user.setId(userDto.getId());
		//user.setName(userDto.getName());
		//user.setEmail(userDto.getEmail());
		//user.setPassword(userDto.getPassword());
		//user.setAbout(userDto.getAbout());
		return user;
		}

    public UserDto userToDto(User user) {

    	UserDto userDto=this.modelMapper.map(user, UserDto.class);
    	return userDto;
    	
    }

}
