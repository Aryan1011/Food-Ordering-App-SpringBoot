package com.pkware.foodapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkware.foodapp.dao.FoodcartDao;
import com.pkware.foodapp.entity.FoodCart;
import com.pkware.foodapp.requestObject.CartCreateRequest;

@Service
public class FoodcartService {
	
	@Autowired 
	private FoodcartDao foodcartDao;
	
	//cart from cart id
	public FoodCart getFoodcart(int parseInt) {
		return this.foodcartDao.findById(parseInt);
	}

//	all carts of a customer by customer id
	public List<FoodCart> getCustomerFoodcarts(int parseInt) {
		return this.foodcartDao.findCustomerCarts(parseInt);
	}

	public FoodCart makeFoodCart(CartCreateRequest cartCreateRequest) {
		return this.foodcartDao.makeFoodCart(cartCreateRequest);
	}

}
