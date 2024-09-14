	package com.ravi.blog.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.blog.payloads.ApiResponse;
import com.ravi.blog.payloads.UserDto;
import com.ravi.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	
	 //POST-create User
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		System.out.print("Create User");
		UserDto createUserDto=this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	//Put 
	@PutMapping("/{userId}") //URI 
	public ResponseEntity<UserDto> updateuser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
		UserDto updatedUser =this.userService.updateUser(userDto, userId);
		return new ResponseEntity<>(updatedUser,HttpStatus.OK);
		
	}
	
	//Get User
	@GetMapping("/userlist")
	  public ResponseEntity<List<UserDto>> getCustomer() {

		
			
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	
	//Get user by id
	@GetMapping("/{userId}")
	  public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {

		UserDto userbyid=this.userService.getUserById(userId);
		return new ResponseEntity<>(userbyid,HttpStatus.OK);
	}
	
	
	
	
	//DELETE-delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		this.userService.deleteuser(userId);
		
		return new ResponseEntity<> (new ApiResponse("user deleted succesfully",true),HttpStatus.OK);
	}
	
	
	
}



