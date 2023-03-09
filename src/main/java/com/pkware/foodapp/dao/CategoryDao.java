 package com.pkware.foodapp.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.pkware.foodapp.entity.Category;

@Repository
public class CategoryDao{
	
	 @Autowired
	    private SessionFactory factory;
	public Object save(Category category) {
		Session s=factory.openSession();
		Transaction tx=null;
		try {
			tx=s.beginTransaction();
			s.saveOrUpdate(category);
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
		return category;
	}
	
	public List<Category> findAll(){
		Session s=factory.openSession();
		Transaction tx=null;
		List<Category> categories = null;
		try {
			tx = s.beginTransaction();
			categories = s.createQuery("from Category").list();
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

	public Category findById(Integer integer) {
		Session session = factory.openSession();
		Transaction tx = null;
		Category category=null;
		try {
			tx = session.beginTransaction();
			category = (Category) session.get(Category.class, integer);
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

	public void deleteById(Integer integer) {
		Session session = factory.openSession();
		Transaction tx = null;
		Category category=null;
		try {
			tx = session.beginTransaction();
			category = (Category) session.get(Category.class, integer);
			session.delete(category);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	
	
}
