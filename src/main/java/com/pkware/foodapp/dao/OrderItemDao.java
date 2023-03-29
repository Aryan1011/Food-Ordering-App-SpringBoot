package com.pkware.foodapp.dao;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.pkware.foodapp.entity.FoodCart;

import com.pkware.foodapp.entity.OrderItem;


@Repository
public class OrderItemDao {

	@Autowired
	private SessionFactory factory;
	

	public OrderItem save(OrderItem orderItem) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(orderItem);
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return orderItem;
	}
	
}
