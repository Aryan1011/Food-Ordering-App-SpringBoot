package com.pkware.foodapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Item {
	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int itemId;
	@Column(nullable = false,unique = true)
	private String itemName;
	private String itemDesc;
	private int itemCost;
	private boolean itemStatus;
	private String itemImage;
	
	public boolean getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(boolean itemStatus) {
		this.itemStatus = itemStatus;
	}

	@OneToOne(cascade = CascadeType.ALL)
	private Category category;



	public Item(String itemName, String itemDesc, int itemCost, boolean itemStatus, String itemImage,
			Category category) {
		super();
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.itemCost = itemCost;
		this.itemStatus = itemStatus;
		this.itemImage = itemImage;
		this.category = category;
	}

	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

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
