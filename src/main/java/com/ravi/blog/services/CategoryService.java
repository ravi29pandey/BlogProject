package com.ravi.blog.services;

import java.util.List;

import com.ravi.blog.payloads.CategoryDto;
import com.ravi.blog.repositories.CategoryRepo;

public interface CategoryService {
//In interface all methods are by default public and abstract 
	
	//update category
	 CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//create category
   CategoryDto createCategory(CategoryDto categoryDto);
	
	//getuser
	 CategoryDto getCategory(Integer categoryId);
	
	
	//All user
	 List<CategoryDto> getCategories();
	
	//delete category
	 void deleteCategory(Integer categoryId);
	
}
