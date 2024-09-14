package com.ravi.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ravi.blog.entities.Post;
import com.ravi.blog.entities.User;
import com.ravi.blog.entities.Category;
import com.ravi.blog.exceptions.ResourceNotFoundException;
import com.ravi.blog.payloads.PostDto;
import com.ravi.blog.payloads.PostResponse;
import com.ravi.blog.repositories.CategoryRepo;
import com.ravi.blog.repositories.PostRepo;
import com.ravi.blog.repositories.UserRepo;
import com.ravi.blog.services.FileService;
import com.ravi.blog.services.PostService;


@Service
public class PostServiceImpl implements PostService{

	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User id",userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", categoryId));
	
		Post post=this.modelMapper.map(postDto, Post.class);
		
		
		
		post.setCategory(category);
		post.setUser(user);
		post.setAddedDate(new Date());
		
		Post newPost=this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
	Post posts=	this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "postID", postId));
		posts.setTitle(postDto.getTitle());
		posts.setContent(postDto.getContent());
		posts.setImageName(postDto.getImageName());

		Post updateposts=this.postRepo.save(posts);
		return this.modelMapper.map(updateposts, PostDto.class);
	}

	@Override
	public void deletepost(Integer postId) {
		
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId" ,postId));
			this.postRepo.delete(post);
		
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize ,String sortBy,String sortDir) {
		
		
		//Sorting
		
//		Sort sort=null;
//		if(sortDir.equalsIgnoreCase("asc"))
//		{
//		 sort=  Sort.by(sortBy).ascending();
//		}else if(sortDir.equalsIgnoreCase("desc"))
//		{
//			 sort= Sort.by(sortBy).descending();
//		}
//		
		
		Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		
		
		org.springframework.data.domain.Pageable p=PageRequest.of(pageNumber,pageSize,sort); //.ascending()
		
		
		
		//org.springframework.data.domain.Pageable p=PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).descending()); //.ascending()
		//For pagination
		//	org.springframework.data.domain.Pageable p=PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePosts=this.postRepo.findAll(p);
	    List<Post> allPosts=pagePosts.getContent();
		
		List<PostDto>	postDtos=allPosts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		//for Pagination
		PostResponse postResponse=new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLastPage(pagePosts.isLast());
		
		
		return postResponse;
	}

	@Override
	public PostDto getPostByid(Integer postId) {
		Post getPostById= this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		
		return  this.modelMapper.map(getPostById, PostDto.class);
	}

	@Override
	public List<PostDto> getPostBycategory(Integer categoryId) {
        Category cat= this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("cartegory","categoryID", categoryId));
        List<Post> posts=this.postRepo.findByCategory(cat);
        
        List<PostDto> postDtos=posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        
        return postDtos;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "userId", userId));
		 List<Post> posts=this.postRepo.findByUser(user);
		   List<PostDto> postDtos= posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos ;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts=this.postRepo.searchByTitle("%"+keyword+"%");
		 List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;

	}

	@Override
	public PostDto createPost(PostDto postDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId, Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> searchPostsByContent(String keyword) {
		List<Post> posts=this.postRepo.searchByContent("%"+keyword+"%");
		 List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPostsByUser(String keyword) {
		List<Post> posts=this.postRepo.searchByUser("%"+keyword+"%");
		 List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	

	

}
