package edu.ycp.cs320.spartaneats.model;

public class Item {
	private String mainItem;
	private String Side;
	private String drink;
	private int count;
	private double price;
	
	//item has a main item and side
	public Item(String mainItem, String Side, double price, int count) {
		this.setMainItem(mainItem);
		this.setSide(Side);
		this.setPrice(price);
		this.setCount(count);
	}

	//number of items
	private void setCount(int Count) {
		this.count = Count;
	}

	//price of items
	private void setPrice(double Price) {
		this.price = Price;
	}

	//get the side
	public String getSide(String side) {
		return side;
	}

	//set the side
	public void setSide(String side) {
		this.Side = side;
	}

	//get the main item
	public String getmainItem() {
		return mainitem;
	}

	//set the main item
	public void setMainItem(String mainItem) {
		this.mainItem = mainItem;
	}
	
	public void getPrice(double price) {
		this.price = price;
	}

	public String getDrink() {
		return drink;
	}

	public void setDrink(String drink) {
		this.drink = drink;
	}
	
}
