package com.ravi.blog.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.blog.payloads.ApiResponse;
import com.ravi.blog.payloads.CategoryDto;
import com.ravi.blog.payloads.UserDto;
import com.ravi.blog.services.CategoryService;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
	
	
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		
		CategoryDto categorydto=this.categoryService.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(categorydto, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updatecategory(@Valid @RequestBody CategoryDto categoryDto , @PathVariable Integer categoryId ){
		
		CategoryDto categoryUpdate=this.categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(categoryUpdate, HttpStatus.CREATED);
		
		
	}
	
	//delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer categoryId) {
		this.categoryService.deleteCategory(categoryId);
		
		return new ResponseEntity<> (new ApiResponse("category deleted succesfully",true),HttpStatus.OK);
	}
	
	//get
	
	@GetMapping("/categorylist")
	  public ResponseEntity<List<CategoryDto>> getCustomer() {

		
			
		return ResponseEntity.ok(this.categoryService.getCategories());
	}
	
	//Get user by id
		@GetMapping("/{categoryId}")
		  public ResponseEntity<CategoryDto> getUserById(@PathVariable Integer categoryId) {

			CategoryDto categoryByid=this.categoryService.getCategory(categoryId);
			return new ResponseEntity<>(categoryByid,HttpStatus.OK);
		}
		
	

}
