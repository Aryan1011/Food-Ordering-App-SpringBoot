package com.pkware.foodapp.dao;


import java.util.ArrayList;
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
import com.pkware.foodapp.requestObject.ItemToCartRequest;

@Repository
public class FoodcartDao {

	 @Autowired
	 private SessionFactory factory;
	 
	 @Autowired
	 private ItemDao itemDao;
	
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
//  Item List by cart id
	public List<Item> listItems(int parseInt) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Item> items=null;
		try {
			tx = session.beginTransaction();
			FoodCart foodCart=(FoodCart) session.get(FoodCart.class, parseInt);
			items= foodCart.getItems();
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return items;
	}
	
	
//	has customerId and itemId
	public int addItemToCart(ItemToCartRequest cartRequest) {
		Session session = null;
		Transaction tx = null;
		try {
			// gives item to be added
			Item item = itemDao.findById(cartRequest.getItemId());
			//gives cart in which it is to be added
			int cartId= this.getCartId(cartRequest.getCustomerId());
			session = factory.openSession();
			tx = session.beginTransaction();
			FoodCart foodCart=session.get(FoodCart.class, cartId);
			foodCart.addToList(item);
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cartRequest.getItemId();
	}

	public int deleteCart(int cartId) {
		Session session = null;
		Transaction tx = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			FoodCart foodCart=session.get(FoodCart.class, cartId);
			session.delete(foodCart);
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cartId;
	}
//  total from cart id
	public int getTotal(int parseInt) {
		Session session = factory.openSession();
		Transaction tx = null;
		int total=0;
		try {
			tx = session.beginTransaction();
			FoodCart foodCart=(FoodCart) session.get(FoodCart.class, parseInt);
			List<Item>  items= foodCart.getItems();
			for(Item i:items) {
				total += i.getItemCost();
			}
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return total;
	}
//  make or get cart from customer id
	public int getCartId(int parseInt) {
		Session session = factory.openSession();
		Transaction tx = null;
		FoodCart foodCart=null;
		try {
			tx = session.beginTransaction();
			Query mailQuery =session.createQuery("from Customer where customerId=:a");
			mailQuery.setParameter("a", parseInt);
			Customer cus = (Customer) mailQuery.uniqueResult();
			String mail = cus.getCustomerMail();
			Query query=session.createQuery("from FoodCart where customerMail=:x");
			query.setParameter("x", mail);
			foodCart=(FoodCart)query.uniqueResult();
			if(foodCart==null) {
				List<Item> is = new ArrayList<>();
				foodCart=new FoodCart(is,mail,cus);
				session.save(foodCart);
			}
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return foodCart.getCartId();
	}
	

}
