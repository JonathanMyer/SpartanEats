package edu.ycp.cs320.spartaneats.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.spartaneats.persist.DerbyDatabase;

public class Inventory {
	private List<Item> selectedItems;
	private List<Drink> selectedDrinks;
	private List<Extras> selectedExtras;
	private List<Sandwich> selectedSandwich;
	private DerbyDatabase db = null;
	
	public Inventory() {
		db = new DerbyDatabase();
		try {
			selectedItems = db.findAllItems();
			
			selectedDrinks = db.findAllDrinks();
			
			selectedExtras = db.findAllExtras();
			
			selectedSandwich = db.findAllSandwiches();
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
	
	public void addExtras(Extras extra) {
		selectedExtras.add(extra);
	}
	
	public void removeExtras(Extras extra) {
		selectedExtras.remove(extra);
	}
	
	public void addSandwich(Sandwich sandwich) {
		selectedSandwich.add(sandwich);
	}
	
	public void removeSandwich(Sandwich sandwich) {
		selectedSandwich.remove(sandwich);
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
	
	public Extras getExtras(String extras) {
		for (Extras i: selectedExtras) {
			if (i.getItemName().equals(extras)) {
				
				System.out.println("found extra");
				return i;
			}
		}
		return null;
	}
	
	public Sandwich getSandwich(String Sandwich) {
		for (Sandwich i: selectedSandwich) {
			if (i.getItemName().equals(Sandwich)) {
				
				System.out.println("found sandwich");
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

	public List<Extras> getExtraList() {
		return selectedExtras;
	}
	
	public List<Sandwich> getSandwichList() {
		return selectedSandwich;
	}




}