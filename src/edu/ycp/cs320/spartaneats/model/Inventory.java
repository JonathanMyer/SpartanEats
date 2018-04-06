package edu.ycp.cs320.spartaneats.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.spartaneats.persist.DerbyDatabase;

public class Inventory {
	private List<Item> selectedItems;
	private List<Drink> selectedDrinks;
	private DerbyDatabase db = null;
	
	public Inventory() {
		db = new DerbyDatabase();
		try {
			selectedItems = db.findAllItems();
			selectedDrinks = db.findAllDrinks();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	//add an item from an order
	public void addItem(Item Item) {
		selectedItems.add(Item);	
	}
	
	//remove an item from an order
	public void removeItem(Item Item) {
		selectedItems.remove(Item);
	}
	
	public void addDrink(Drink drink) {
		selectedDrinks.add(drink);
	}
	
	public void removeItem(Drink drink) {
		selectedDrinks.remove(drink);
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
	
	public Drink getDrink(String drink) {
		for (Drink i: selectedDrinks) {
			if (i.getItemName().equals(drink)) {
				System.out.println("found Drink");
				return i;
			}
		}
		return null;
	}
	
	public List<Item> getItemList(){
		return this.selectedItems;
	}


	public List<Drink> getDrinkList() {
		return selectedDrinks;
	}




}