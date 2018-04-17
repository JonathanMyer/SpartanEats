package edu.ycp.cs320.spartaneats.model;

public class Sandwich {
	private String itemName;
	
	public Sandwich() {

	}
	
	public Sandwich(String itemName) {
		this.itemName = itemName;
	}
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
