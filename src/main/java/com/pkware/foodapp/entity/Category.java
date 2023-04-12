package com.pkware.foodapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int categoryId;
	@Column(nullable = false,unique = true)
	private String categoryName;
	private boolean categoryStatus;
	
	public Category(String categoryName, boolean categoryStatus) {
		super();
		this.categoryName = categoryName;
		this.categoryStatus = categoryStatus;
	}
	public boolean getCategoryStatus() {
		return categoryStatus;
	}
	public void setCategoryStatus(boolean categoryStatus) {
		this.categoryStatus = categoryStatus;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCategoryId() {
		return categoryId;
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
}
