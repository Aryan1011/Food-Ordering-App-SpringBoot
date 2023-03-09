package com.pkware.foodapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkware.foodapp.dao.CategoryDao;
import com.pkware.foodapp.entity.Category;

@Service
public class CategoryService {

	@Autowired
	CategoryDao categoryDao;

	public Object addCategory(Category category) {
		return categoryDao.save(category);
	}

	public List<Category> getAllCategory() {
		return (List<Category>) categoryDao.findAll();
	}

	public Category getCategory(int parseInt) {
		Category category = categoryDao.findById(new Integer(parseInt));
		return category;
	}

	public Category deleteCategory(int parseInt) {
		Category category = categoryDao.findById(new Integer(parseInt));
		categoryDao.deleteById(new Integer(parseInt));
		return category;
	}

	public Object updateCategory(Category category) {
		return categoryDao.save(category);
	}

}
