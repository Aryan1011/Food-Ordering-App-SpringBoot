package com.pkware.foodapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pkware.foodapp.entity.FoodCart;
import com.pkware.foodapp.entity.Item;
import com.pkware.foodapp.entity.OrderItem;
import com.pkware.foodapp.requestObject.ItemToCartRequest;
import com.pkware.foodapp.services.FoodcartService;

@RestController
@RequestMapping({"/foodcart"})
public class FoodcartController {

	@Autowired
	private FoodcartService foodcartService;
	
	//cart id from customer id 
	@GetMapping({"/getcartid/{id}"})
	public int getFoodcartId(@PathVariable String id) {
		return this.foodcartService.getFoodcartId(Integer.parseInt(id));
	}
	
	//cart from cart id 
	@GetMapping({"/getcart/{id}"})
	public FoodCart getFoodcart(@PathVariable String id) {
		return this.foodcartService.getFoodcart(Integer.parseInt(id));
	}
	
	//items from cart id
	@GetMapping({"/getcartitems/{id}"})
	public List<OrderItem> getCartItems(@PathVariable String id) {
		return this.foodcartService.getCartItems(Integer.parseInt(id));
	}
	
//	send customer id and item id
	@PostMapping({"/additemtocart"})
	public int addItemToCart(@RequestBody ItemToCartRequest cartRequest) {
		return this.foodcartService.addItemToCart(cartRequest);
	}
	
//	deletes whole cart from cart id
	@GetMapping({"/deletecart/{id}"})
	public int deleteCart(@PathVariable String id) {
		return this.foodcartService.deleteCart(Integer.parseInt(id));
	}
	
	//getting total from cart id
	@GetMapping({"/gettotal/{id}"})
	public int getTotalAmount(@PathVariable String id) {
		return this.foodcartService.getTotalAmount(Integer.parseInt(id));
	}
}
