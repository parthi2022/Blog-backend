package org.blog.service;

import java.util.Optional;

import org.blog.model.User;
import org.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void addUser(User user) {
		
		this.userRepo.save(user);
		
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		
		return this.userRepo.findByuserEmail(email);
		
	}

	@Override
	public void updateUser(User user) {
		this.userRepo.save(user);
		
	}

}
