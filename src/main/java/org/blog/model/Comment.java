package org.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Comment {

	@Id
	private long commentId;
	
	@Column(length = 500, updatable = true, nullable = false)
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "postId")
	private Post post;
	
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
}