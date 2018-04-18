package edu.ycp.cs320.spartaneats.model;

public class Item {

	private String itemType;
	private String itemName;
	private double price;
	private String condiments;
	private int itemId;
	
	public Item() {

	}
	
	public Item(String itemType, String itemName, double price, String condiments, int itemID) {
		this.itemType = itemType;
		this.itemName = itemName;
		this.price = price;
		this.condiments = condiments;
		this.itemId = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getCondiments() {
		return condiments;
	}

	public void setCondiments(String condiments) {
		this.condiments = condiments;
	}
	
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	
}