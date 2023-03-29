package com.pkware.foodapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pkware.foodapp.entity.FoodCart;
import com.pkware.foodapp.entity.OrderItem;
import com.pkware.foodapp.requestObject.CartCreateRequest;
import com.pkware.foodapp.services.FoodcartService;

@RestController
@RequestMapping({"/foodcart"})
public class FoodcartController {

	@Autowired
	private FoodcartService foodcartService;
	
	
	//cart from cart id 
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping({"/getcart/{id}"})
	public FoodCart getFoodcart(@PathVariable String id) {
		return this.foodcartService.getFoodcart(Integer.parseInt(id));
	}
	
//	carts from customer id
//	Not using in application
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping({"/getcustomercarts/{id}"})
	public List< FoodCart> getCustomerFoodcarts(@PathVariable String id) {
		return this.foodcartService.getCustomerFoodcarts(Integer.parseInt(id));
	}
	
//	Make a Cart 
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping({"/makecart"})
	public FoodCart makeFoodCart(@RequestBody CartCreateRequest cartCreateRequest) {
		return this.foodcartService.makeFoodCart(cartCreateRequest);
	}
}
