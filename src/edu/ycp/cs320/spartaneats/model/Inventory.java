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
	
	// returns the item with given itemname
	public Item getItem(String item) {
		for (Item i: selectedItems) {
			if (i.getItemName().equals(item)) {
				System.out.println("found Item");
				return i;
			}
		}	
		return null;
	}
	
	public ArrayList<Item> getItemList(){
		return this.selectedItems;
	}

}