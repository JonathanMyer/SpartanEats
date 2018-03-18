package edu.ycp.cs320.spartaneats.model;

public class Item {
	
	private String itemName;
	private double price;
	private int count;
	
	//item has a main item and side
	public Item(String itemName, double price, int count) {
		this.setItemName(itemName);
		this.setPrice(price);
		this.setCount(count);
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
