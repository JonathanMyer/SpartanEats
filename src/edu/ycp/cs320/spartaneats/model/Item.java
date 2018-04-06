package edu.ycp.cs320.spartaneats.model;
//Item class check
public class Item {

	private int item_id;
	private String itemName;
	private double price;
	
	public Item() {

	}
	
	public Item(int item_id, String itemName, double price) {
		this.item_id = item_id;
		this.itemName = itemName;
		this.price = price;
	}
	
	public void setItemId(int item_id) {
		this.item_id = item_id;
	}
	
	public int getItemId() {
		return item_id;
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