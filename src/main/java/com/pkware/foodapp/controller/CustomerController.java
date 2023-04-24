package com.pkware.foodapp.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pkware.foodapp.entity.Customer;
import com.pkware.foodapp.requestObject.CategoryRequest;
import com.pkware.foodapp.requestObject.CustomerCreateReq;
import com.pkware.foodapp.requestObject.CustomerMail;
import com.pkware.foodapp.requestObject.LoginRequest;
import com.pkware.foodapp.services.CustomerService;

@RestController
@RequestMapping({"/customer"})
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping({"/test"})
	public String test() {
		return "Working Customer";
	}

//	add customer by customer object (id is not used) 
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping({"/add"})
	public ResponseEntity<Customer> addCustomer(@RequestBody CustomerCreateReq customerCreateReq) {
		return this.customerService.addCategory(customerCreateReq);
	}
	
//	gets all customer
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping({"/get"})
	public List<Customer> getAllCustomer(){
		return this.customerService.getAllCustomer();
	}
	
//	gets customer by his id 
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping({"/get/{id}"})
	public Customer getCustomer(@PathVariable String id) {
		return this.customerService.getCustomer(Integer.parseInt(id));
	}
	
//	delete by id
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping({"/delete/{id}"})
	public Customer deleteCustomer(@PathVariable String id) {
		return this.customerService.deleteCustomer(Integer.parseInt(id));
	}
	
//	updates customer by id
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping({"/update"})
	public Customer updateCustomer(@RequestBody CustomerCreateReq customerCreateReq) {
		return this.customerService.updateCustomer(customerCreateReq);
	
	}
	
//	find by customer mail 
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/getbymail")
	public Customer getByMail(@RequestBody CustomerMail customerMail) {
		return this.customerService.getByMail(customerMail.getCustomerMail());
	}
	
	
//	login request
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public Customer login(@RequestBody LoginRequest loginRequest) {
		return this.customerService.login(loginRequest);
	}
	

}
