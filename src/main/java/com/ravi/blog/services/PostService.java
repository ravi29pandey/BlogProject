package com.ravi.blog.services;

import java.util.List;

import com.ravi.blog.entities.Post;
import com.ravi.blog.payloads.PostDto;
import com.ravi.blog.payloads.PostResponse;

public interface PostService {

	
	//create Post
	PostDto createPost(PostDto postDto);
	
	//Update Post
	PostDto updatePost(PostDto postDto,Integer postId,Integer categoryId);
	
	//Delete post
	void deletepost(Integer postId);
	
	
	//getallPost
	List<PostDto> getAllPost( );
	
	
	//getSinglePost
	PostDto getPostByid(Integer postId);
	
	
	//get Post by Category
	List<PostDto> getPostBycategory(Integer categoryId);
	
	
	//getAllPostBy User
	List<PostDto> getAllPostByUser(Integer userId);
	
	//search Post
	List<PostDto> searchPosts(String Keyword);
    
	//search Post By Content
	List<PostDto> searchPostsByContent(String Keyword);
	
	
	//search Post By User
	List<PostDto> searchPostsByUser(String Keyword);
	
	//createPost
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	PostDto updatePost(PostDto postDto, Integer postId);

	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir);

	
	
	
	
	
	
}
