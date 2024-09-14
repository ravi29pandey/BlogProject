package com.ravi.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.blog.entities.Comment;
import com.ravi.blog.entities.Post;
import com.ravi.blog.exceptions.ResourceNotFoundException;
import com.ravi.blog.payloads.CommentDto;
import com.ravi.blog.payloads.PostDto;
import com.ravi.blog.repositories.CommentRepo;
import com.ravi.blog.repositories.PostRepo;
import com.ravi.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow((()-> new ResourceNotFoundException("post", "postId", postId)));
	    Comment comment=this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		Comment savedComment=this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	public void deleteComment(Integer postId,Integer commentId) {
		Post post=this.postRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));
		 	
	 Comment comment=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","commentId",commentId));
	  this.commentRepo.delete(comment);
	 }

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		
	}
	
	
}
