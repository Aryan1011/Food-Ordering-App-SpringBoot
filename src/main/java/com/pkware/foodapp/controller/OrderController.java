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

import com.pkware.foodapp.entity.OrderDetails;
import com.pkware.foodapp.requestObject.CartCreateRequest;
import com.pkware.foodapp.requestObject.StatusChangeRequest;
import com.pkware.foodapp.services.OrderService;

@RestController
@RequestMapping({"/order"})
public class OrderController {

	@Autowired
	private OrderService orderService;
	
//	make order from cart id
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping({"/add/{id}"})
	public OrderDetails makeOrder(@PathVariable String id) {
		return this.orderService.makeOrder(id);
	}
//	get all orders of customers
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping({"/getcustomerorders/{id}"})
	public List<OrderDetails> getCustomerOrders(@PathVariable String id) {
		return this.orderService.getCustomerOrders(Integer.parseInt(id));
	}
//	get order from orderId
	@GetMapping({"/get/{id}"})
	public OrderDetails getOrderDetails(@PathVariable String id) {
		return this.orderService.getOrderDetails(Integer.parseInt(id));
	}
	
//	get orders from status
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping ({"/getorderwithstatus/{id}"})
	public List<OrderDetails> getAll(@PathVariable String id){
		return this.orderService.getAllFromStatus(id);
	}
	
//	update status of order by order ID
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping({"/updatestatus"})
	public String changeOrderStatus(@RequestBody StatusChangeRequest statusChangeRequest) {
		return this.orderService.changeOrderStatus(statusChangeRequest);
	}
}
