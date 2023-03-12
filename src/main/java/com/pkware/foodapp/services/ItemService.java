package com.pkware.foodapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkware.foodapp.dao.ItemDao;
import com.pkware.foodapp.entity.Item;
import com.pkware.foodapp.requestObject.ItemRequest;

@Service
public class ItemService {

	@Autowired
	private ItemDao itemDao;
	
	public Item addItem(ItemRequest itemRequest) {
		return itemDao.save(itemRequest);
	}

	public Item updateItem(ItemRequest itemRequest) {
		return itemDao.update(itemRequest);
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
