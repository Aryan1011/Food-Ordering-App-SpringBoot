package com.pkware.foodapp.dao;

import java.util.ArrayList;
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
import com.pkware.foodapp.response.View;

@Repository
public class ItemDao {

	 @Autowired
	 private SessionFactory factory;
	 
	 @Autowired
	 private CategoryDao categoryDao;
	
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
			String path = "../../../assets/Images/dishes/"+itemRequest.getItemImage();
			item = new Item(itemRequest.getItemName(),itemRequest.getItemDesc(),itemRequest.getItemCost(),true,path, category);
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

	public Item deleteById(Integer integer) {
		Session session = factory.openSession();
		Transaction tx = null;
		Item item=null;
		try {
			tx = session.beginTransaction();
			item = this.findById(integer);
			item.setItemStatus(!item.getItemStatus());
			session.update(item);
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

	public List<Item> getByCategory(String id) {
		Session s=factory.openSession();
		Transaction tx=null;
		List<Item> items = null;
		try {
			tx = s.beginTransaction();
			Query q = s.createQuery("from Item");
			List<Item> temp=q.list();
			items = new ArrayList<>();
			for(Item item:temp) {
				if(item.getCategory().getCategoryName().equals(id)) {
					items.add(item);
				}
			}
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

	public List<View> getByView() {
		Session s=factory.openSession();
		Transaction tx=null;
		List<View> views=new ArrayList<>();
		try {
			tx=s.beginTransaction();
			List<Category> categories = categoryDao.findAll();
			for(Category c : categories) {
				List<Item> items = this.getByCategory(c.getCategoryName());
				View v = new View(c.getCategoryId(), c.getCategoryName(), items);
				views.add(v);
			}
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
		return views;
	}



	public List<Item> getTrueItemByCategory(String id) {
		Session s=factory.openSession();
		Transaction tx=null;
		List<Item> items = null;
		try {
			tx = s.beginTransaction();
			Query q = s.createQuery("from Item where itemStatus=:x");
			q.setParameter("x", true);
			List<Item> temp=q.list();
			items = new ArrayList<>();
			for(Item item:temp) {
				if(item.getCategory().getCategoryName().equals(id)) {
					items.add(item);
				}
			}
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
	
}
