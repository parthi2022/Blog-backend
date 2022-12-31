package org.blog.service;

import java.util.List;

import org.blog.model.Comment;

public interface CommentService {

	public void addComment(Comment comment);
	public void updateComment(Comment comment);
	public List<Comment> findCommentsByPostId(long postId);
	public List<Comment> findCommentsByUserId(long userId);
	public void deleteComment(long commentId);
}
