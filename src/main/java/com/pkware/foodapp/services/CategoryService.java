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

	public Category addCategory(String category) {
		return categoryDao.save(category);
	}

	public List<Category> getAllCategory() {
		return (List<Category>) categoryDao.findAll();
	}

	public Category getCategory(String category) {
		Category c = categoryDao.findById(category);
		return c;
	}

	public Category deleteCategory(String category) {
		Category c = categoryDao.findById(category);
		categoryDao.deleteById(category);
		return c;
	}

	public Category updateCategory(Category category) {
		return categoryDao.update(category);
	}

	public List<Category> getTrueCategory() {
		return categoryDao.getTrue();
	}

}
