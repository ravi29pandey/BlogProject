package com.ravi.blog.payloads;

import java.util.*;

import javax.persistence.Entity;

import com.ravi.blog.entities.Category;
import com.ravi.blog.entities.Comment;
import com.ravi.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor


public class PostDto {

  public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getAddeddate() {
		return addeddate;
	}

	public void setAddeddate(Date addeddate) {
		this.addeddate = addeddate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	private Integer postId;
public Integer getPostId() {
		return postId;
	}


	public void setPostId(Integer postId) {
		this.postId = postId;
	}
private String title;
  private String content;
  
  private String imageName;
  
  private Date addeddate;
  
  private Category category;
  
  private User user;
  
 private Set<CommentDto> comments=new HashSet<>();
public Set<CommentDto> getComments() {
	return comments;
}

public void setComments(Set<CommentDto> comments) {
	this.comments = comments;
}
  
  
 
  
	
}
