package com.pkware.foodapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkware.foodapp.dao.OrderDao;
import com.pkware.foodapp.entity.OrderDetails;
import com.pkware.foodapp.requestObject.CartCreateRequest;
import com.pkware.foodapp.requestObject.StatusChangeRequest;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	

//	from customer id
	public List<OrderDetails> getCustomerOrders(int parseInt) {
		return orderDao.getCustomerOrders(parseInt);
	}


	public String changeOrderStatus(StatusChangeRequest statusChangeRequest) {
		return orderDao.changeOrderStatus(statusChangeRequest);
	}

	public OrderDetails makeOrder(String id) {
		return orderDao.makeOrder(id);
	}

	public OrderDetails getOrderDetails(int parseInt) {
		// TODO Auto-generated method stub
		return orderDao.getOrderDetails(parseInt);
	}

	public List<OrderDetails> getAllFromStatus(String id) {
		// TODO Auto-generated method stub
		return orderDao.getAllFromStatus(id);
	}

}
