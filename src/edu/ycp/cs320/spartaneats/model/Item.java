package edu.ycp.cs320.spartaneats.model;

public class Item {
	
	private int itemId;
	private String itemName;
	private double price;
	
	public Item() {

	}
	
	public Item(int itemID, String itemName, double price) {
		this.itemId = itemID;
		this.itemName = itemName;
		this.price = price;
	}
	
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public int getItemId() {
		return itemId;
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
}