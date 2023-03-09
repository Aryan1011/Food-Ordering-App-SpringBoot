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
	
	@PostMapping({"/add"})
	public Object addCategory(@RequestBody Category category) {
		return this.categoryService.addCategory(category);
	}
	
	@GetMapping({"/get"})
	public List<Category> getAllCategory(){
		return this.categoryService.getAllCategory();
	}
	
	@GetMapping({"/get/{id}"})
	public Category getCategory(@PathVariable String id) {
		return this.categoryService.getCategory((int) Integer.parseInt(id));
	}
	
	@DeleteMapping({"/delete/{id}"})
	public Category deleteCategory(@PathVariable String id) {
		return this.categoryService.deleteCategory((int) Integer.parseInt(id));
	}
	
	@PutMapping({"/update"})
	public Object updateCategory(@RequestBody Category category) {
		return this.categoryService.updateCategory(category);
	}
	
	
}
 