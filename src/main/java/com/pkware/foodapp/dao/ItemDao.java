package com.pkware.foodapp.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.pkware.foodapp.entity.Item;

@Repository
public class ItemDao {

	 @Autowired
	 private SessionFactory factory;
	
	public Item save(Item item) {
		Session s=factory.openSession();
		Transaction tx=null;
		try {
			tx=s.beginTransaction();
			s.saveOrUpdate(item);
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
		return item;
	}

	public Item findById(Integer integer) {
		Session session = factory.openSession();
		Transaction tx = null;
		Item item=null;
		try {
			tx = session.beginTransaction();
			item = (Item) session.get(Item.class, integer);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return item;
	}

	public List<Item> findAll() {
		Session s=factory.openSession();
		Transaction tx=null;
		List<Item> items = null;
		try {
			tx = s.beginTransaction();
			items = s.createQuery("from Item").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return items;
	}

	public void deleteById(Integer integer) {
		Session session = factory.openSession();
		Transaction tx = null;
		Item item=null;
		try {
			tx = session.beginTransaction();
			item = (Item) session.get(Item.class, integer);
			session.delete(item);
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
