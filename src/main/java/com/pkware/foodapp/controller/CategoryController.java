package com.pkware.foodapp.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pkware.foodapp.entity.Category;
import com.pkware.foodapp.requestObject.CategoryRequest;
import com.pkware.foodapp.services.CategoryService;

@RestController
@RequestMapping({"/category"})
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping({"/test"})
	public String test() {
		return "Working Category";
	}
	
	
	//saves by sending category Name
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping({"/add"})
	public  ResponseEntity<Category> addCategory(@RequestBody CategoryRequest categoryRequest) {
		return this.categoryService.addCategory(categoryRequest.getCategoryName());
	}
	
	// get all categories
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping({"/get"})
	public List<Category> getAllCategory(){
		return this.categoryService.getAllCategory();
	}
	
//	get true only for user
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping({"/gettrue"})
	public List<Category> getTrueCategory(){
		return this.categoryService.getTrueCategory();
	}
	
//	get full by passing category name
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping({"/get/{id}"})
	public Category getCategory(@PathVariable String id) {
		return this.categoryService.getCategory(id);
	}
	
//	delete by category name
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping({"/delete/{id}"})
	public Category deleteCategory(@PathVariable String id) {
		return this.categoryService.deleteCategory(id);
	}
	
//	Send full object to change category name 
//	Not to be used in the application
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping({"/update"})
	public Category updateCategory(@RequestBody Category category) {
		return this.categoryService.updateCategory(category);
	}
	
	
}
 