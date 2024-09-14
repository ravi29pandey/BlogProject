package com.ravi.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ravi.blog.entities.Category;
import com.ravi.blog.entities.Post;
import com.ravi.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	
	
	//All are the searching method since this is not supported in JPA hence need of method
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key") String title);
	
	@Query("select p from Post p where p.content like :key")
	List<Post> searchByContent(@Param("key") String content);
	
	@Query("select p from Post p where p.user like :key")
	List<Post> searchByUser(@Param("key") String user);

}	 
