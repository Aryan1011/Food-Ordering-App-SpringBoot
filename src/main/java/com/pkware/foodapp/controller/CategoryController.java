package com.pkware.foodapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pkware.foodapp.entity.Category;
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
	
	
	//saves by sending id(not used) and category Name
	@PostMapping({"/add"})
	public Category addCategory(@RequestBody Category category) {
		return this.categoryService.addCategory(category);
	}
	
	// get all categories
	@GetMapping({"/get"})
	public List<Category> getAllCategory(){
		return this.categoryService.getAllCategory();
	}
	
//	get full by passing category name
	@GetMapping({"/get/{id}"})
	public Category getCategory(@PathVariable String id) {
		return this.categoryService.getCategory(id);
	}
	
//	delete by category name
	@DeleteMapping({"/delete/{id}"})
	public Category deleteCategory(@PathVariable String id) {
		return this.categoryService.deleteCategory(id);
	}
	
//	Send full object to change category name 
	@PutMapping({"/update"})
	public Category updateCategory(@RequestBody Category category) {
		return this.categoryService.updateCategory(category);
	}
	
	
}
 