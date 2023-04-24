package com.pkware.foodapp.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pkware.foodapp.entity.OrderDetails;
import com.pkware.foodapp.entity.OrderItem;
import com.pkware.foodapp.requestObject.TwoDatesObject;
import com.pkware.foodapp.response.ItemQuantity;
import com.pkware.foodapp.response.OrderRevenueObject;

@Repository
public class MetricsDao {
	@Autowired
	private SessionFactory factory;

	public OrderRevenueObject getTodayMetrics() {
		Session s = factory.openSession();
		Transaction tx = null;
		OrderRevenueObject orderRevenueObject = new OrderRevenueObject(0,0);
		try {
			tx = s.beginTransaction();
			Query q= s.createQuery("from OrderDetails");
			List<OrderDetails> orders=q.list();
			for(OrderDetails order:orders) {
				SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
				String orderDate=fmt.format(order.getOrderDate());
				String todayDate=fmt.format(new Date());
				if(orderDate.equals(todayDate)) {
					orderRevenueObject.setTotalOrders(orderRevenueObject.getTotalOrders()+1);
					orderRevenueObject.setTotalRevenue(orderRevenueObject.getTotalRevenue()+order.getFoodCart().getCost());
				}
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return orderRevenueObject;
	}

	public OrderRevenueObject getDateMetrics(TwoDatesObject twoDatesObject) {
		Session s = factory.openSession();
		Transaction tx = null;
		OrderRevenueObject orderRevenueObject = new OrderRevenueObject(0,0);
		try {
			tx = s.beginTransaction();
			Query q= s.createQuery("from OrderDetails");
			List<OrderDetails> orders=q.list();
			for(OrderDetails order:orders) {
				SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
				String orderDate=fmt.format(order.getOrderDate());
				if(orderDate.compareTo(twoDatesObject.getStartDate())>=0  && orderDate.compareTo(twoDatesObject.getEndDate())<=0 ) {
					orderRevenueObject.setTotalOrders(orderRevenueObject.getTotalOrders()+1);
					orderRevenueObject.setTotalRevenue(orderRevenueObject.getTotalRevenue()+order.getFoodCart().getCost());
				}
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return orderRevenueObject;
	}

	public HashMap<String, Integer> getTodayItemQuantity() {
		Session s = factory.openSession();
		Transaction tx = null;
		HashMap<String, Integer> mp=new HashMap<>();
		try {
			tx = s.beginTransaction();
			Query q= s.createQuery("from OrderDetails");
			List<OrderDetails> orders=q.list();
			for(OrderDetails order:orders) {
				SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
				String orderDate=fmt.format(order.getOrderDate());
				String todayDate=fmt.format(new Date());
				if(orderDate.equals(todayDate)) {
					
					List<OrderItem> items= order.getFoodCart().getOrderItems();
					for(OrderItem item:items) {
						String cat= item.getItem().getItemName();
						if(mp.containsKey(cat)) {
							mp.put(cat, mp.get(cat)+item.getQuantity());
						}
						else {
							mp.put(cat, item.getQuantity());
						}
					}
				}
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return mp;
	}

	public HashMap<String, Integer> getDateItemQuantity(TwoDatesObject twoDatesObject) {
		Session s = factory.openSession();
		Transaction tx = null;
		HashMap<String, Integer> mp=new HashMap<>();
		try {
			tx = s.beginTransaction();
			Query q= s.createQuery("from OrderDetails");
			List<OrderDetails> orders=q.list();
			for(OrderDetails order:orders) {
				SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
				String orderDate=fmt.format(order.getOrderDate());
				if(orderDate.compareTo(twoDatesObject.getStartDate())>=0  && orderDate.compareTo(twoDatesObject.getEndDate())<=0 ) {
					List<OrderItem> items= order.getFoodCart().getOrderItems();
					for(OrderItem item:items) {
						String cat= item.getItem().getItemName();
						if(mp.containsKey(cat)) {
							mp.put(cat, mp.get(cat)+item.getQuantity());
						}
						else {
							mp.put(cat, item.getQuantity());
						}
					}
				}
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return mp;
	}

	public HashMap<String, Integer> getTodayCategoryPercentage() {
		Session s = factory.openSession();
		Transaction tx = null;
		HashMap<String, Integer> mp=new HashMap<>();
		try {
			tx = s.beginTransaction();
			Query q= s.createQuery("from OrderDetails");
			List<OrderDetails> orders=q.list();
			for(OrderDetails order:orders) {
				SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
				String orderDate=fmt.format(order.getOrderDate());
				String todayDate=fmt.format(new Date());
				if(orderDate.equals(todayDate)) {
					
					List<OrderItem> items= order.getFoodCart().getOrderItems();
					for(OrderItem item:items) {
						String cat= item.getItem().getCategory().getCategoryName();
						if(mp.containsKey(cat)) {
							mp.put(cat, mp.get(cat)+(item.getQuantity()*item.getItem().getItemCost()));
						}
						else {
							mp.put(cat, (item.getQuantity()*item.getItem().getItemCost()));
						}
					}
				}
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return mp;
	}

	public HashMap<String, Integer> getDateCategoryPercentage(TwoDatesObject twoDatesObject) {
		Session s = factory.openSession();
		Transaction tx = null;
		HashMap<String, Integer> mp=new HashMap<>();
		try {
			tx = s.beginTransaction();
			Query q= s.createQuery("from OrderDetails");
			List<OrderDetails> orders=q.list();
			for(OrderDetails order:orders) {
				SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
				String orderDate=fmt.format(order.getOrderDate());
				if(orderDate.compareTo(twoDatesObject.getStartDate())>=0  && orderDate.compareTo(twoDatesObject.getEndDate())<=0 ) {
					List<OrderItem> items= order.getFoodCart().getOrderItems();
					for(OrderItem item:items) {
						String cat= item.getItem().getCategory().getCategoryName();
						if(mp.containsKey(cat)) {
							mp.put(cat, mp.get(cat)+(item.getQuantity()*item.getItem().getItemCost()));
						}
						else {
							mp.put(cat, (item.getQuantity()*item.getItem().getItemCost()));
						}
					}
				}
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return mp;
	}

}
