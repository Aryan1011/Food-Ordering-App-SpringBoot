package com.pkware.foodapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkware.foodapp.dao.ItemDao;
import com.pkware.foodapp.entity.Item;

@Service
public class ItemService {

	@Autowired
	private ItemDao itemDao;
	
	public Item addItem(Item item) {
		return itemDao.save(item);
	}

	public Item updateItem(Item item) {
		return itemDao.save(item);
	}

	public Item getItem(int parseInt) {
		Item item = itemDao.findById(new Integer(parseInt));
		return item;
	}

	public List<Item> getAllItem() {
		return itemDao.findAll();
	}

	public Item deleteItem(int parseInt) {
		Item item = itemDao.findById(new Integer(parseInt));
		itemDao.deleteById(new Integer(parseInt));
		return item;
	}

}
