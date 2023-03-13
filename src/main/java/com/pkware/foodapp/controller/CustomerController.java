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
import com.pkware.foodapp.entity.Customer;
import com.pkware.foodapp.services.CustomerService;

@RestController
@RequestMapping({"/customer"})
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping({"/test"})
	public String test() {
		return "Working Customer";
	}

//	add customer by customer object (id is not used) 
	@PostMapping({"/add"})
	public Customer addCustomer(@RequestBody Customer customer) {
		return this.customerService.addCategory(customer);
	}
	
//	gets all customer
	@GetMapping({"/get"})
	public List<Customer> getAllCustomer(){
		return this.customerService.getAllCustomer();
	}
	
//	gets customer by his id 
	@GetMapping({"/get/{id}"})
	public Customer getCustomer(@PathVariable String id) {
		return this.customerService.getCustomer(Integer.parseInt(id));
	}
	
//	delete by id
	@DeleteMapping({"/delete/{id}"})
	public Customer deleteCustomer(@PathVariable String id) {
		return this.customerService.deleteCustomer(Integer.parseInt(id));
	}
	
//	updates customer by id
	@PutMapping({"/update"})
	public Customer updateCustomer(@RequestBody Customer customer) {
		return this.customerService.updateCustomer(customer);
	
	}
	
//	find by customer mail 
	@GetMapping("/getbymail/{mail}")
	public Customer getByMail(@PathVariable String mail) {
		return this.customerService.getByMail(mail);
	}

}
