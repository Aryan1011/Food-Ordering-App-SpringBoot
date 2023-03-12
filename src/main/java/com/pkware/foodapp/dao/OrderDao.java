package com.pkware.foodapp.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pkware.foodapp.entity.Customer;
import com.pkware.foodapp.entity.FoodCart;
import com.pkware.foodapp.entity.Item;
import com.pkware.foodapp.entity.OrderDetails;

@Repository
public class OrderDao {

	@Autowired
	private SessionFactory factory;
	
	@Autowired
	private FoodcartDao foodcartDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	
//	customerId
	public OrderDetails makeOrder(int customerId) {
		Session session = null;
		Transaction tx=null;
		OrderDetails details=null;
		try {
			session = factory.openSession();
			tx=session.beginTransaction();
			
			int cartId = foodcartDao.getCartId(customerId);
			FoodCart foodCart = foodcartDao.findById(cartId);
			
			String it="";
			List<Item> items = foodCart.getItems();
			for(Item item:items) {
				it+=item.getItemName();
				it+=", ";
			}
			int total = foodcartDao.getTotal(cartId);
			String comment = foodCart.getCustomerMail()+ "Ordered "+it +"Worth Ruppees - " + total;
			
			details = new OrderDetails(new Date(),comment,foodCart.getCustomerMail());
			session.save(details);
			tx.commit();
			foodcartDao.deleteCart(cartId);
		}
		catch(Exception e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return details;
	}

	public List<OrderDetails> getAllOrder(int customerId) {
		Session session = null;
		Transaction tx=null;
		List<OrderDetails> details=null;
		try {
			session = factory.openSession();
			tx=session.beginTransaction();
			Customer customer=customerDao.findById(customerId);
			Query q = session.createQuery("from OrderDetails where customerMail=:x");
			q.setParameter("x", customer.getCustomerMail());
			details= q.list();
			tx.commit();
		}
		catch(Exception e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return details;
	}

}
