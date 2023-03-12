package com.pkware.foodapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pkware.foodapp.entity.OrderDetails;
import com.pkware.foodapp.services.OrderService;

@RestController
@RequestMapping({"/order"})
public class OrderController {

	@Autowired
	private OrderService orderService;
	
//	make order from customer id
	@PostMapping({"/add/{id}"})
	public OrderDetails makeOrder(@PathVariable String id) {
		return this.orderService.makeOrder(Integer.parseInt(id));
	}
//	get all orders of customers
	@GetMapping({"/get/{id}"})
	public List<OrderDetails> getAllOrder(@PathVariable String id) {
		return this.orderService.getAllOrder(Integer.parseInt(id));
	}
	
}
