 package com.pkware.foodapp.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.pkware.foodapp.entity.Category;
import com.pkware.foodapp.entity.Customer;

@Repository
public class CategoryDao{
	
	@Autowired
	private SessionFactory factory;
	 
	 
	public ResponseEntity<Category> save(String category) {
		Session s=factory.openSession();
		Transaction tx=null;
		Category c=null;
		try {
			tx=s.beginTransaction();
			try {				
				Category cat = this.findById(category);
				if(cat!=null) {
					return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			c=new Category(category, true);
			s.save(c);
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
		return ResponseEntity.status(HttpStatus.CREATED).body(c);
	}
	
	public List<Category> findAll(){
		Session s=factory.openSession();
		Transaction tx=null;
		List<Category> categories = null;
		try {
			tx = s.beginTransaction();
			Query q= s.createQuery("from Category");
			categories=q.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return categories;
	}

	public Category findById(String c) {
		Session session = factory.openSession();
		Transaction tx = null;
		Category category=null;
		try {
			tx = session.beginTransaction();
			Query query=session.createQuery("from Category where categoryName=:x");
			query.setParameter("x", c);
			category= (Category) query.getSingleResult();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return category;
	}

	public void deleteById(String c) {
		Session session = factory.openSession();
		Transaction tx = null;
		Category category=null;
		try {
			tx = session.beginTransaction();
			category= this.findById(c);
			category.setCategoryStatus(!category.getCategoryStatus());
			session.update(category);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

//	Not Used
	public Category update(Category category) {
		Session session = factory.openSession();
		Transaction tx = null;
		int id=category.getCategoryId();
		try {
			tx = session.beginTransaction();
			Category c = (Category) session.get(Category.class, id);
			c.setCategoryName(category.getCategoryName());
			session.update(c);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return category;
	}

	public List<Category> getTrue() {
		Session s=factory.openSession();
		Transaction tx=null;
		List<Category> categories = null;
		try {
			tx = s.beginTransaction();
			Query q= s.createQuery("from Category where categoryStatus=:x");
			q.setParameter("x", true);
			categories=q.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return categories;
	}	
}
