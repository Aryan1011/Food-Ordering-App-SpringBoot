package com.pkware.foodapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkware.foodapp.dao.OrderDao;
import com.pkware.foodapp.entity.OrderDetails;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	
//	from customer id
	public OrderDetails makeOrder(int parseInt) {
		return orderDao.makeOrder(parseInt);
	}

//	from customer id
	public List<OrderDetails> getAllOrder(int parseInt) {
		return orderDao.getAllOrder(parseInt);
	}

}
