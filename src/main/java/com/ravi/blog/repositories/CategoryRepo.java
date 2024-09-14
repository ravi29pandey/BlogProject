package com.ravi.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ravi.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

	
	
}
