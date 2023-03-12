package com.pkware.foodapp.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pkware.foodapp.entity.Category;
import com.pkware.foodapp.entity.Item;
import com.pkware.foodapp.requestObject.ItemRequest;

@Repository
public class ItemDao {

	 @Autowired
	 private SessionFactory factory;
	
	public Item save(ItemRequest itemRequest) {
		Session s=factory.openSession();
		Transaction tx=null;
		Item item=null;
		try {
			tx=s.beginTransaction();
			String query = "from Category where categoryName=:x";
			Query q= s.createQuery(query);
			q.setParameter("x", itemRequest.getItemCategory());
			Category category= (Category) q.getSingleResult();
			item = new Item(itemRequest.getItemName(),itemRequest.getItemDesc(),itemRequest.getItemCost(),category);
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
			items = s.createQuery("from Item order by category").list();
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
		try {
			tx = session.beginTransaction();
			Query query=session.createQuery("delete from Item where itemId=:x");
			query.setParameter("x", integer);
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

	public Item update(ItemRequest itemRequest) {
		Session s=factory.openSession();
		Transaction tx=null;
		Item item=null;
		try {
			tx=s.beginTransaction();
			String query = "from Item where itemName=:x";
			Query q= s.createQuery(query);
			q.setParameter("x", itemRequest.getItemName());
			item=(Item) q.getSingleResult();
			item.setItemCost(itemRequest.getItemCost());
			item.setItemDesc(itemRequest.getItemDesc());
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
	
}
