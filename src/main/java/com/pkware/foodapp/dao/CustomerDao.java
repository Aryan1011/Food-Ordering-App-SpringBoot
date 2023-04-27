package com.pkware.foodapp.dao;

import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pkware.foodapp.entity.Customer;
import com.pkware.foodapp.requestObject.CustomerCreateReq;
import com.pkware.foodapp.requestObject.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Repository
public class CustomerDao {

	@Autowired
	private SessionFactory factory;
	
	private String hashPassword(String password) throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("SHA-256");
	    md.update(password.getBytes());
	    byte[] digest = md.digest();
	    return bytesToHex(digest);
	}

	private String bytesToHex(byte[] bytes) {
	    StringBuilder sb = new StringBuilder();
	    for (byte b : bytes) {
	        sb.append(String.format("%02x", b));
	    }
	    return sb.toString();
	}
	
	public ResponseEntity<Customer> save(CustomerCreateReq customerCreateReq) {
		Session s=factory.openSession();
		Transaction tx=null;
		Customer customer=null;
		try {
			tx=s.beginTransaction();
			try {
				
			Customer c = this.getByMail(customerCreateReq.getCustomerMail());
			if(c!=null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
			}
			}
			catch(Exception e) {
				System.out.println(e);
			}
			String hashedPassword = hashPassword(customerCreateReq.getCustomerPassword());
			customer=new Customer(customerCreateReq.getCustomerName(),customerCreateReq.getCustomerPhone(),customerCreateReq.getCustomerAddress(),customerCreateReq.getCustomerMail(),hashedPassword);
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
		
		 return ResponseEntity.status(HttpStatus.CREATED).body(customer);
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
			String hashedPassword = hashPassword(customerCreateReq.getCustomerPassword());
			tx = session.beginTransaction();
			c = this.getByMail(customerCreateReq.getCustomerMail());
			c.setCustomerAddress(customerCreateReq.getCustomerAddress());
			c.setCustomerMail(customerCreateReq.getCustomerMail());
			c.setCustomerName(customerCreateReq.getCustomerName());
			c.setCustomerPhone(customerCreateReq.getCustomerPhone());
			c.setCustomerPassword(hashedPassword);
			
			session.update(c);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
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

	public Customer login(LoginRequest loginRequest)  {
		Session session = factory.openSession();
		Transaction tx = null;
		Customer customer=null;
		try {
			tx = session.beginTransaction();
			
			customer = this.getByMail(loginRequest.getEmail());
			
				String hashedPassword = hashPassword(loginRequest.getPassword());
			if(customer!=null && !customer.getCustomerPassword().equals(hashedPassword)) {
				customer=null;
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println(e);
		}finally {
			session.close();
		}
		return customer;
	}
	


}
