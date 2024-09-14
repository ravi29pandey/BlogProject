package com.ravi.blog.services;

import com.ravi.blog.payloads.CommentDto;

public interface CommentService {

	public CommentDto createComment(CommentDto commentDto,Integer postId);
	void deleteComment(Integer commentId);
	
	
	
}
