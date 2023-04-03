package com.pkware.foodapp.response;

import java.util.List;

import com.pkware.foodapp.entity.Item;

public class View {
	private int categoryId;
	private String categoryName;
	private List<Item> items;
	public View(int categoryId, String categoryName, List<Item> items) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.items = items;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public View() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
}
