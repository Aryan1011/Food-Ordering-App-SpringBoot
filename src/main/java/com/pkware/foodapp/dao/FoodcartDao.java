package com.pkware.foodapp.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pkware.foodapp.entity.Customer;
import com.pkware.foodapp.entity.FoodCart;
import com.pkware.foodapp.entity.Item;
import com.pkware.foodapp.entity.OrderItem;
import com.pkware.foodapp.requestObject.CartCreateRequest;
import com.pkware.foodapp.requestObject.ItemPair;

@Repository
public class FoodcartDao {

	 @Autowired
	 private SessionFactory factory;
	 
	 @Autowired
	 private CustomerDao customerDao;
	 
	 @Autowired
	 private ItemDao itemDao;
	 
	 @Autowired
	 private OrderItemDao orderItemDao;
	
//	cart by cartid
	public FoodCart findById(int parseInt) {
		Session session = factory.openSession();
		Transaction tx = null;
		FoodCart foodCart=null;
		try {
			tx = session.beginTransaction();
			Query query=session.createQuery("from FoodCart where cartId=:x");
			query.setParameter("x", parseInt);
			foodCart=(FoodCart)query.uniqueResult();
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return foodCart;
	}
	
//	By customer ID
	public List<FoodCart> findCustomerCarts(int parseInt) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<FoodCart> foodCarts=null;
		try {
			tx = session.beginTransaction();
			Customer customer=customerDao.findById(parseInt);
			String mail=customer.getCustomerMail();
			Query query=session.createQuery("from FoodCart where customerMail=:x");
			query.setParameter("x", mail);
			foodCarts=(List<FoodCart>)query.list();
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return foodCarts;
	}
	public FoodCart makeFoodCart(CartCreateRequest cartCreateRequest) {
		Session session = factory.openSession();
		Transaction tx = null;
		FoodCart foodCart=null;
		try {
			tx = session.beginTransaction();
			Customer customer=customerDao.findById(cartCreateRequest.getCustomerId());
			List<OrderItem> orderItems=new ArrayList<>();
			List<ItemPair> itempairs = cartCreateRequest.getCartItems();
			for(ItemPair itemPair: itempairs) {
				Item item=itemDao.findById(itemPair.getItemId());
				OrderItem orderItem=new OrderItem(itemPair.getItemQuantity(), item);
				orderItem=orderItemDao.save(orderItem);
				orderItem.getOrderItemId();
				orderItems.add(orderItem);
			}
			foodCart=new FoodCart(orderItems,customer,new Date(),cartCreateRequest.getCartCost(),customer.getCustomerMail());
			session.save(foodCart);
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return foodCart;
	}
	

}
