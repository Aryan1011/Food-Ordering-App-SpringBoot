package com.pkware.foodapp.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Item {

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", itemDesc=" + itemDesc + ", itemCost=" + itemCost
				+ ", category=" + category + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int itemId;
	@Column(nullable = false,unique = true)
	private String itemName;
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Item(String itemName, String itemDesc, int itemCost, Category category) {
		super();
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.itemCost = itemCost;
		this.category = category;
	}

	private String itemDesc;
	private int itemCost;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Category category;
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public int getItemCost() {
		return itemCost;
	}

	public void setItemCost(int itemCost) {
		this.itemCost = itemCost;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
