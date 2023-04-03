package com.pkware.foodapp.dao;

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
import com.pkware.foodapp.entity.OrderDetails;
import com.pkware.foodapp.entity.OrderItem;
import com.pkware.foodapp.requestObject.CartCreateRequest;
import com.pkware.foodapp.requestObject.StatusChangeRequest;

@Repository
public class OrderDao {

	@Autowired
	private SessionFactory factory;
	
	@Autowired
	private FoodcartDao foodcartDao;
	
	@Autowired
	private CustomerDao customerDao;
	

	// customer orders
	public List<OrderDetails> getCustomerOrders(int customerId) {
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



	public String changeOrderStatus(StatusChangeRequest statusChangeRequest) {
		Session session = null;
		Transaction tx=null;
		try {
			session = factory.openSession();
			tx=session.beginTransaction();
			OrderDetails orderDetails=session.get(OrderDetails.class, statusChangeRequest.getOrderId());
			orderDetails.setStatus(statusChangeRequest.getOrderStatus());
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
		return statusChangeRequest.getOrderStatus();
	}

	//make order from cart
	public OrderDetails makeOrder(String id) {
		Session session = null;
		Transaction tx=null;
		OrderDetails details=null;
		try {
			session = factory.openSession();
			tx=session.beginTransaction();
			FoodCart foodCart= foodcartDao.findById(Integer.parseInt(id));
			List<OrderItem> items = foodCart.getOrderItems();
			String it="";
			for(OrderItem item:items) {
				
				it+=item.getItem().getItemName();
				it+="(quantity-"+item.getQuantity()+")";
				it+=", ";
			}
			int total = foodCart.getCost();
			String comment = foodCart.getCustomerMail() + " Ordered "+it +"Worth Ruppees - " + total;
			Customer cus=customerDao.getByMail(foodCart.getCustomerMail());
			details = new OrderDetails(comment,new Date(),foodCart,"Pending",foodCart.getCustomerMail());
			session.save(details);
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

//	get by order id
	public OrderDetails getOrderDetails(int parseInt) {
		Session session = factory.openSession();
		Transaction tx = null;
		OrderDetails orderDetails = null;
		try {
			tx = session.beginTransaction();
			Query query=session.createQuery("from OrderDetails where orderId=:x");
			query.setParameter("x", parseInt);
			orderDetails=(OrderDetails)query.uniqueResult();
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return orderDetails;
	}

	public List<OrderDetails> getAllFromStatus(String id) {
		Session session = null;
		Transaction tx=null;
		List<OrderDetails> details=null;
		try {
			session = factory.openSession();
			tx=session.beginTransaction();
			Query q = session.createQuery("from OrderDetails where status=:x");
			q.setParameter("x", id);
			details=q.list();
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



	public List<OrderDetails> findAll() {
		Session session = null;
		Transaction tx=null;
		List<OrderDetails> details=null;
		try {
			session = factory.openSession();
			tx=session.beginTransaction();
			Query q = session.createQuery("from OrderDetails");
			details=q.list();
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
