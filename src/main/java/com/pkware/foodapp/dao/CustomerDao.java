package com.pkware.foodapp.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pkware.foodapp.entity.Customer;
import com.pkware.foodapp.requestObject.CustomerCreateReq;

@Repository
public class CustomerDao {

	@Autowired
	private SessionFactory factory;
	
	public Customer save(CustomerCreateReq customerCreateReq) {
		Session s=factory.openSession();
		Transaction tx=null;
		Customer customer=null;
		try {
			tx=s.beginTransaction();
			customer=new Customer(customerCreateReq.getCustomerName(),customerCreateReq.getCustomerPhone(),customerCreateReq.getCustomerAddress(),customerCreateReq.getCustomerMail());
			s.save(customer);
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
		
		return customer;
	}

	public List<Customer> getAllCustomer() {
		Session s=factory.openSession();
		Transaction tx=null;
		List<Customer> customers=null;
		try {
			tx = s.beginTransaction();
			customers = s.createQuery("from Customer").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return customers;
	}

	public Customer findById(Integer id) {
		Session session = factory.openSession();
		Transaction tx = null;
		Customer customer=null;
		try {
			tx = session.beginTransaction();
			customer = (Customer) session.get(Customer.class, id);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return customer;
	}

	public void deleteById(Integer id) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query=session.createQuery("delete from Customer where customerId=:x");
			query.setParameter("x", id);
			query.executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public Customer update(CustomerCreateReq customerCreateReq) {
		Session session = factory.openSession();
		Transaction tx = null;
		Customer c=null;
		try {
			tx = session.beginTransaction();
			c = this.getByMail(customerCreateReq.getCustomerMail());
			c.setCustomerAddress(customerCreateReq.getCustomerAddress());
			c.setCustomerMail(customerCreateReq.getCustomerMail());
			c.setCustomerName(customerCreateReq.getCustomerName());
			c.setCustomerPhone(customerCreateReq.getCustomerPhone());
			
			session.update(c);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return c;
	}

	public Customer getByMail(String mail) {
		Session session = factory.openSession();
		Transaction tx = null;
		Customer customer=null;
		try {
			tx = session.beginTransaction();
			Query query=session.createQuery("from Customer where customerMail=:x");
			query.setParameter("x", mail);
			customer=(Customer) query.uniqueResult();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return customer;
	}
	


}
