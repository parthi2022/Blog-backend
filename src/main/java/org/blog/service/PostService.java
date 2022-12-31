package org.blog.service;

import java.util.List;
import java.util.Optional;

import org.blog.model.Post;

public interface PostService {

	public void addPost(Post post);
	public List<Post> getAllPosts();
	public List<Post> getPostsByCategory(long categoryId);
	public Optional<Post> getPostByPostId(long postId);
	public List<Post> getPostsByUserId(long userId);
	public Optional<Post> getPostByPostTitle(String title);
	
}
