package com.pkware.foodapp.response;

import java.util.HashMap;

public class CategoryMoney {
	HashMap<String, Integer> categoryMoney;

	public HashMap<String, Integer> getCategoryMoney() {
		return categoryMoney;
	}

	public void setCategoryMoney(HashMap<String, Integer> categoryMoney) {
		this.categoryMoney = categoryMoney;
	}

	public CategoryMoney(HashMap<String, Integer> categoryMoney) {
		super();
		this.categoryMoney = categoryMoney;
	}

	public CategoryMoney() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
