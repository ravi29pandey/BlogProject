package com.ravi.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.blog.entities.Comment;
import com.ravi.blog.payloads.ApiResponse;
import com.ravi.blog.payloads.CommentDto;
import com.ravi.blog.services.CommentService;
import com.ravi.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	
 

    @Autowired
	private CommentService commentService;
    
    
    @PostMapping("/posts/{postId}/comment")
    public 	ResponseEntity<CommentDto> createEntity(@RequestBody CommentDto commentDto ,@PathVariable Integer postId ){
    	CommentDto createComment=this.commentService.createComment(commentDto, postId);
    	return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
    }
    	
    
    @DeleteMapping("/posts/{postId}/comment/{commentId}")
    public ApiResponse 	deleteComment(@PathVariable Integer postId, @PathVariable Integer commentId) {
		this.commentService.deleteComment(commentId);
    	
       return new ApiResponse("Comment deleted Successfully", false);	
    }
    

}
	

