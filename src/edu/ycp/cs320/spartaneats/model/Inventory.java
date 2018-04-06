package edu.ycp.cs320.spartaneats.model;

import java.util.ArrayList;
//Inventory Class

public class Inventory {
	private ArrayList<Item> selectedItems;
	
	public Inventory() {
		selectedItems = new ArrayList<Item>();
	}

	
	//add an item from an order
	public void addItem(Item Item) {
		selectedItems.add(Item);	
	}
	
	//remove an item from an order
	public void removeItem(Item Item) {
		selectedItems.remove(Item);
	}
	
	// returns the item with given itemname
	public Item getItemNameMethod(String item) {
		for (Item i: selectedItems) {
			if (i.getItemName().equals(item)) {
				System.out.println("found Item");
				return i;
			}
		}	
		return null;
	}
	// returns the item with given itemPrice
	public double getItemPriceMethod(double price) {
		for (Item i: selectedItems) {
			if (i.getPrice() == price) {
				System.out.println("Found Price: " + price);
				return price;
			}
		}	
		return 0.00;
	}
	// returns the item with given itemPrice
	public int getItemIDMethod(int ID) {
		for (Item i: selectedItems) {
			if (i.getItemId() == ID) {
				System.out.println("Found ID: " + ID);
				return ID;
			}
		}	
		return 0;
	}
	public ArrayList<Item> getItemList(){
		return this.selectedItems;
	}
}