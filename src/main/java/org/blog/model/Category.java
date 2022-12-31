package org.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Category {
	
	@Id
	private long categoryId;
	
	@Column(nullable = false, length = 20, updatable = true, unique = true)
	private String categoryName;

}
