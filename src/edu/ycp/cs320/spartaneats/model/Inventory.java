package edu.ycp.cs320.spartaneats.model;

import java.util.ArrayList;

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
	
	// returns the account with given username
	public Item getItem(Item item) {
		for (Item selectedItems: selectedItems) {
			if (item.getItemName().equals(selectedItems.getItemName())) {
				System.out.println("found Item");
				return item;
			}
		}	
		return null;
	}

}