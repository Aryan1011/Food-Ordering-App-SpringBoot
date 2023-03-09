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

import com.pkware.foodapp.entity.Item;
import com.pkware.foodapp.services.ItemService;

@RestController
@RequestMapping({"/item"})
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@PostMapping({"/add"})
	public Item addItem(@RequestBody Item item) {
		return this.itemService.addItem(item);
	}
	
	@PutMapping({"/update"})
	public Item updateItem(@RequestBody Item item) {
		return this.itemService.updateItem(item);
	}
	
	@GetMapping({"/get/{id}"})
	public Item getItem(@PathVariable String id) {
		return this.itemService.getItem((int) Integer.parseInt(id));
	}
	
	@GetMapping({"/get"})
	public List<Item> getAllItem() {
		return this.itemService.getAllItem();
	}
	
	@DeleteMapping({"/delete/{id}"})
	public Item deleteItem(@PathVariable String id) {
		return this.itemService.deleteItem((int) Integer.parseInt(id));
	}
	
	
}
