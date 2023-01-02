package org.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.blog.model.Category;
import org.blog.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/blog")
public class CategoryController {

	
	private CategoryRepository repository;
	
	@PostMapping("/category")
	public ResponseEntity<Map<String,String>> addCategory(@RequestBody Category category)
	{
		try
		{
			this.repository.save(category);
			Map<String,String> response=new HashMap<String,String>();
			response.put("status", "success");
			response.put("message", "Category added!!");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			Map<String,String> response=new HashMap<String,String>();
			response.put("status", "failed");
			response.put("message", "Problem in adding category!!");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategories()
	{
		return new  ResponseEntity<List<Category>>(this.repository.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<?> getAllCategories(@PathVariable("categoryId")long categoryId)
	{
		if(this.repository.findById(categoryId).isPresent())
		{
			return new  ResponseEntity<Category>(this.repository.findById(categoryId).get(),HttpStatus.OK);
		}
		else
		{
			Map<String,String> response=new HashMap<String,String>();
			response.put("status", "failed");
			response.put("message", "Category id not found!!");
			return new  ResponseEntity<Category>(this.repository.findById(categoryId).get(),HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PutMapping("/category")
	public ResponseEntity<Map<String,String>> updateCategory(@RequestBody Category category)
	{
		Map<String,String> response=new HashMap<String,String>();
		
		if(this.repository.findById(category.getCategoryId()).isPresent())
		{
			Category existingObj=this.repository.findById(category.getCategoryId()).get();
			existingObj.setCategoryName(category.getCategoryName());
			this.repository.save(existingObj);
			response.put("status", "success");
			response.put("message", "Category name updated!!");
			return new  ResponseEntity<Map<String,String>>(response,HttpStatus.OK);

		}
		else
		{
			response.put("status", "failed");
			response.put("message", "Category id not found!!");
			return new  ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
		}
		
	}
	
	
}