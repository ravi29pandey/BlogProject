package com.ravi.blog.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ravi.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer>{

}
