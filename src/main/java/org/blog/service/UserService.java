package org.blog.service;

import java.util.Optional;

import org.blog.model.User;

public interface UserService {
	
	public void addUser(User user);
	public void updateUser(User user);
	public Optional<User> getUserByEmail(String email);

}
