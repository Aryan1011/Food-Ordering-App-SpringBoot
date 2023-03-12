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

	@PostMapping({"/add"})
	public Customer addCustomer(@RequestBody Customer customer) {
		return this.customerService.addCategory(customer);
	}
	
	@GetMapping({"/get"})
	public List<Customer> getAllCustomer(){
		return this.customerService.getAllCustomer();
	}
	
	@GetMapping({"/get/{id}"})
	public Customer getCustomer(@PathVariable String id) {
		return this.customerService.getCustomer(Integer.parseInt(id));
	}
	
	@DeleteMapping({"/delete/{id}"})
	public Customer deleteCustomer(@PathVariable String id) {
		return this.customerService.deleteCustomer(Integer.parseInt(id));
	}
	
	@PutMapping({"/update"})
	public Customer updateCustomer(@RequestBody Customer customer) {
		return this.customerService.updateCustomer(customer);
	}

}
