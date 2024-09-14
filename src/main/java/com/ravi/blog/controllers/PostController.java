package com.ravi.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import com.ravi.blog.config.AppsConstant;
import com.ravi.blog.payloads.ApiResponse;
import com.ravi.blog.payloads.PostDto;
import com.ravi.blog.payloads.PostResponse;
import com.ravi.blog.services.FileService;
import com.ravi.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	
	@Autowired
	private PostService postservice;
	
	@Autowired
	private FileService fileservice;
	
	@Value("project.image")
	private String path;
	
	//create
	@PostMapping("user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createpost(@RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){
		PostDto createPost=this.postservice.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}


	//get by user id
	@GetMapping("user/{userid}/posts")
	public ResponseEntity<List<PostDto>> getuser(@PathVariable Integer userid){
		
		List<PostDto> posts=this.postservice.getAllPostByUser(userid);
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//get post by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getpostByCategory(@PathVariable Integer categoryId){
		
		
		List<PostDto> posts=this.postservice.getPostBycategory(categoryId);
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam (value="pageNumber",defaultValue= AppsConstant.PAGE_NUMBER ,required=false ) Integer pageNumber,
			@RequestParam (value="pageSize",defaultValue=AppsConstant.PAGE_SIZE ,required=false ) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppsConstant.SORT_BY,required=false) String sortBy,
			@RequestParam(value="sortDir",defaultValue=AppsConstant.SORT_DIR,required=false) String sortDir
			
){
	PostResponse getallPost=	this.postservice.getAllPost(pageNumber,pageSize,sortBy,sortDir);
	return new ResponseEntity<PostResponse> (getallPost,HttpStatus.OK);
	}
	
	
	//getPostByID
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getpostbyId(@PathVariable Integer postId){
     PostDto getPostById=this.postservice.getPostByid(postId);
		return new ResponseEntity<PostDto>(getPostById,HttpStatus.OK);
	}
	
	
	//Delete Post
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletebyId(@PathVariable Integer postId){
    this.postservice.deletepost(postId);
	return new ResponseEntity<> (new ApiResponse("Post deleted succesfully",true),HttpStatus.OK);
	}
	
	//Update Post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
		
		PostDto updatepost=this.postservice.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatepost,HttpStatus.OK);
		
		
	}
	//Search Post By title
	
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle( @PathVariable String keyword){
				
		List<PostDto> results=this.postservice.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(results,HttpStatus.OK);
		
		
	}
	
	//Search Post By content
	
		@GetMapping("/posts/searchByContent/{keyword}")
		public ResponseEntity<List<PostDto>> searchPostByContent( @PathVariable String keyword){
					
			List<PostDto> results=this.postservice.searchPostsByContent(keyword);
			return new ResponseEntity<List<PostDto>>(results,HttpStatus.OK);
			
			
		}
	
		
		//Search Post By User
		
			@GetMapping("/posts/searchByUser/{keyword}")
			public ResponseEntity<List<PostDto>> searchPostByUser( @PathVariable String keyword){
						
				List<PostDto> results=this.postservice.searchPostsByUser(keyword);
				return new ResponseEntity<List<PostDto>>(results,HttpStatus.OK);
				
				
			}
			
			
			
			//Post Image upload 
			
			
			@PostMapping("/post/image/upload/{postId}")
			public ResponseEntity<PostDto> uploadPostImage(@RequestParam MultipartFile image , @PathVariable Integer postId) throws IOException{
				
			PostDto postDtos=this.postservice.getPostByid(postId);
			String fileName=this.fileservice.uploadImage(path, image);
			
			
			postDtos.setImageName(fileName);
			PostDto updatedPost=this.postservice.updatePost(postDtos, postId);
			
			return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
			
			
			
		}
			
			
			
			
		
	
}
