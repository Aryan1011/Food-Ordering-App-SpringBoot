package com.pkware.foodapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkware.foodapp.dao.CustomerDao;
import com.pkware.foodapp.entity.Customer;
import com.pkware.foodapp.requestObject.CustomerCreateReq;

@Service
public class CustomerService {
	
	@Autowired
	CustomerDao customerDao;

	public Customer addCategory(CustomerCreateReq customerCreateReq) {
		return customerDao.save(customerCreateReq);
	}

	public List<Customer> getAllCustomer() {
		return customerDao.getAllCustomer();
	}

	public Customer getCustomer(Integer id) {
		return customerDao.findById(id);
	}

	public Customer deleteCustomer(Integer id) {
		Customer customer= customerDao.findById(id);
		customerDao.deleteById(id);
		return customer;
	}

	public Customer updateCustomer(CustomerCreateReq customer) {
		return customerDao.update(customer);
	}

	public Customer getByMail(String mail) {
		return customerDao.getByMail(mail);
	}

}
