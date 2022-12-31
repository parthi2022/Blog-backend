package org.blog.service;

import java.util.List;
import java.util.Optional;

import org.blog.model.Post;
import org.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	
	@Override
	public void addPost(Post post) {
		
	  this.postRepository.save(post);
	}

	@Override
	public List<Post> getAllPosts() {
		
		return this.postRepository.findAll();
	}

	@Override
	public List<Post> getPostsByCategory(long categoryId) {
		
		return this.postRepository.findByCategoryId(categoryId);
	}

	@Override
	public Optional<Post> getPostByPostId(long postId) {
		
		return this.postRepository.findById(postId);
	}

	@Override
	public Optional<Post> getPostByPostTitle(String title) {
	
		return this.postRepository.findPostBypostTitle(title);
	}

	@Override
	public List<Post> getPostsByUserId(long userId) {
		// TODO Auto-generated method stub
		return this.postRepository.findPostsByUserId(userId);
	}

}
