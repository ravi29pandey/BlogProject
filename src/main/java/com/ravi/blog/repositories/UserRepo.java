package com.ravi.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ravi.blog.entities.*;
public interface UserRepo extends JpaRepository<User, Integer>{

	
	
	Optional<User> findByEmail(String email);
}
