package com.pkware.foodapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pkware.foodapp.entity.Item;
import com.pkware.foodapp.requestObject.CategoryRequest;
import com.pkware.foodapp.requestObject.ItemRequest;
import com.pkware.foodapp.response.View;
import com.pkware.foodapp.services.ItemService;

@RestController
@RequestMapping({"/item"})
public class ItemController {

	@Autowired
	private ItemService itemService;
	
//	we send name, desc, cost and category name 
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping({"/add"})
	public Item addItem(@RequestBody ItemRequest itemRequest) {
		return this.itemService.addItem(itemRequest);
	}
	
//	finds by item name and updates desc or cost sent in the object with name desc cost and category name
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping({"/update"})
	public Item updateItem(@RequestBody ItemRequest itemRequest) {
		return this.itemService.updateItem(itemRequest);
	}
	
//	find by item id
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping({"/get/{id}"})
	public Item getItem(@PathVariable String id) {
		return this.itemService.getItem((int) Integer.parseInt(id));
	}
	
//	ordered by category id
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
	@GetMapping({"/get"})
	public List<Item> getAllItem() {
		return this.itemService.getAllItem();
	}
	
//	delete by item id
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping({"/delete/{id}"})
	public Item deleteItem(@PathVariable String id) {
		return this.itemService.deleteItem((int) Integer.parseInt(id));
	}
	
//	Get items by category name 
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping({"/getbycategory"})
	public List<Item> getByCategory(@RequestParam String id){
		return this.itemService.getByCategory(id);
	}
	
//  get by view required
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping({"/getbyview"})
	public List<View> getByView(){
		return this.itemService.getByView();
	}
	
	
}
