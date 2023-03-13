package com.pkware.foodapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pkware.foodapp.entity.Customer;
import com.pkware.foodapp.entity.Item;
import com.pkware.foodapp.entity.OrderItem;
import com.pkware.foodapp.requestObject.ItemToCartRequest;

@Repository
public class OrderItemDao {

	@Autowired
	private SessionFactory factory;
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	public OrderItem addItem(ItemToCartRequest itemToCartRequest) {
		Session s=factory.openSession();
		Transaction tx=null;
		OrderItem orderItem=null;
		try {
			tx=s.beginTransaction();
			Item item= itemDao.findById(itemToCartRequest.getItemId());
			Customer customer=customerDao.findById(itemToCartRequest.getCustomerId());
			String mail = customer.getCustomerMail();
			orderItem=new OrderItem(item, itemToCartRequest.getItemQuantity(), mail) ;
			s.save(orderItem);
			tx.commit();
		}
		catch(Exception e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		finally {
			s.close();
		}
		return orderItem;
	}
}
