package com.pkware.foodapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkware.foodapp.dao.FoodcartDao;
import com.pkware.foodapp.entity.FoodCart;
import com.pkware.foodapp.entity.Item;
import com.pkware.foodapp.entity.OrderItem;
import com.pkware.foodapp.requestObject.ItemToCartRequest;

@Service
public class FoodcartService {
	
	@Autowired 
	private FoodcartDao foodcartDao;
	
	//cart from cart id
	public FoodCart getFoodcart(int parseInt) {
		return this.foodcartDao.findById(parseInt);
	}
	
//  items from cart id
	public List<OrderItem> getCartItems(int parseInt) {
		return this.foodcartDao.listItems(parseInt);
	}

	public int addItemToCart(ItemToCartRequest cartRequest) {
		return this.foodcartDao.addItemToCart(cartRequest);
	}

	public int deleteCart(int i) {
		return this.foodcartDao.deleteCart(i);
	}

//	total from cart id
	public int getTotalAmount(int parseInt) {
		return this.foodcartDao.getTotal(parseInt);
	}

	//cart id from customer id 
	public int getFoodcartId(int parseInt) {
		return this.foodcartDao.getCartId(parseInt);
	}

}
