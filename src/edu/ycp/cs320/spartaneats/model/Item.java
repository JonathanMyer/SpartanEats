package edu.ycp.cs320.spartaneats.model;

public class Item {
	
	private Double itemId;
	private String itemType;
	private String itemName;
	private double price;
	private String condiments;
	
	public Item() {

	}
	
	public Item(Double itemID, String itemType, String itemName, double price, String condiments) {
		this.itemId = itemID;
		this.itemType = itemType;
		this.itemName = itemName;
		this.price = price;
		this.condiments = condiments;
	}
	
	public void setItemId(Double itemId) {
		this.itemId = itemId;
	}
	
	public Double getItemId() {
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
}