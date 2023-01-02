package org.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.blog.model.Comment;
import org.blog.repository.CommentRepository;
import org.blog.repository.UserRepository;
import org.blog.service.CommentService;
import org.blog.service.PostService;
import org.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/blog/")
public class CommentController {

	@Autowired
	private CommentService service;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	//comment/111/234?comment=hi there
	@PostMapping("/comment/{postId}/{userId}")
	public ResponseEntity<Map<String,String>> addComment(
			@PathVariable("userId") long userId,
			@PathVariable("postId") long postId,
			@RequestBody Comment comment)
	{
		
		
		try
		{
			Comment obj=new Comment();
			Random rand = new Random();
			obj.setContent(comment.getContent());
			long commentId=rand.nextInt(11111);
			obj.setCommentId(commentId);
			obj.setPost(this.postService.getPostByPostId(postId).get());
			obj.setUser(this.userRepository.findById(userId).get());
			this.service.addComment(obj);
			
			Map<String,String> response=new HashMap<String,String>();
			response.put("status", "success");
			response.put("message", "Comment added!!");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			Map<String,String> response=new HashMap<String,String>();
			response.put("status", "failed");
			response.put("message", "Comment not added!!");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PutMapping("/comment")
	public ResponseEntity<Map<String,String>> updateComment(@RequestBody Comment comment)
	{
		try
		{
			if(this.commentRepository.findById(comment.getCommentId()).isPresent())
			{
			Comment existingComment=this.commentRepository.findById(comment.getCommentId()).get();
			existingComment.setContent(comment.getContent());
			this.service.addComment(existingComment);
			Map<String,String> response=new HashMap<String,String>();
			response.put("status", "success");
			response.put("message", "Comment updated!!");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.CREATED);
			}
			else
			{
				Map<String,String> response=new HashMap<String,String>();
				response.put("status", "failed");
				response.put("message", "Comment not found!!");
				return new ResponseEntity<Map<String,String>>(response, HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e)
		{
			Map<String,String> response=new HashMap<String,String>();
			response.put("status", "failed");
			response.put("message", "Comment not updated!!");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<Map<String,String>> deleteComment(@PathVariable(name="commentId")long commentId)
	{
		try
		{
			this.service.deleteComment(commentId);
			Map<String,String> response=new HashMap<String,String>();
			response.put("status", "success");
			response.put("message", "Comment deleted!!");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.OK);
		}
		catch(Exception e)
		{
			Map<String,String> response=new HashMap<String,String>();
			response.put("status", "failed");
			response.put("message", "Comment not deleted!!");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/comment/{postId}")
	public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable("postId") long postId)
	{
		return new ResponseEntity<List<Comment>>(this.service.findCommentsByPostId(postId),HttpStatus.OK);
	}
	
	
	@GetMapping("/comment/user/{userId}")
	public ResponseEntity<List<Comment>> getCommentsByUserId(@PathVariable("userId") long userId)
	{
		return new ResponseEntity<List<Comment>>(this.service.findCommentsByUserId(userId),HttpStatus.OK);
	}
	
	@GetMapping("/comments")
	public ResponseEntity<List<Comment>> getAllComments()
	{
		return new ResponseEntity<List<Comment>>(this.commentRepository.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/getcomment/{commentId}")
	public ResponseEntity<Comment> getCommentByCommentId(@PathVariable("commentId") long commentId)
	{
		return new ResponseEntity<Comment>(this.commentRepository.findById(commentId).get(),HttpStatus.OK);
	}
	
	
}
