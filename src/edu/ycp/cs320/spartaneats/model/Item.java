package edu.ycp.cs320.spartaneats.model;

public class Item {
	private String MainItem;
	private String Side;
	private int count;
	private double price;
	
	//item has a main item and side
	public Item(String MainItem, String Side) {
		this.setMainItem(MainItem);
		this.setSide(Side);
	}

	//get the side
	public String getSide() {
		return Side;
	}

	//set the side
	public void setSide(String side) {
		Side = side;
	}

	//get the main item
	public String getMainItem() {
		return MainItem;
	}

	//set the main item
	public void setMainItem(String mainItem) {
		MainItem = mainItem;
	}
	
	public void getPrice(double price) {
		this.price = price;
	}
	
	public void setPrice
}
