package org.blog.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity 
@Data
public class User {   //Bean validation
	
	@Id
	private long userId;
	
	@Column(length = 100, nullable = false)
	private String userName;
	
	@Column(length = 20, updatable = true)
	@Size(min = 8, max =20,message = "password must be between 8 to 20 characters long.")
	private String userPassword;
	
	
	@Column(length = 50, updatable = false, unique = true)
	@Email(message = "Email id format is incorrect.")
	private String userEmail;
	
	@Column(updatable = true)
	private Date dob;
	
	@Column(length = 150, updatable = true)
	private String about;
	
	@Column(length = 20, nullable = false, updatable = true)
	private String role;
	

}
