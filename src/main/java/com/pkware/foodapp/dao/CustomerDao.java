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

@Repository
public class CustomerDao {

	@Autowired
	private SessionFactory factory;
	
	public Customer save(Customer customer) {
		Session s=factory.openSession();
		Transaction tx=null;
		try {
			tx=s.beginTransaction();
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

	public Customer update(Customer customer) {
		Session session = factory.openSession();
		Transaction tx = null;
		int id=customer.getCustomerId();
		try {
			tx = session.beginTransaction();
			Customer c = this.findById(id);
			c.setCustomerAddress(customer.getCustomerAddress());
			c.setCustomerMail(customer.getCustomerMail());
			c.setCustomerName(customer.getCustomerName());
			c.setCustomerPhone(customer.getCustomerPhone());
			
			session.update(c);
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
