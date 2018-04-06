package edu.ycp.cs320.spartaneats.model;

import java.util.ArrayList;
import java.util.List;

public class Order  {
	private List<Item> selectedItems;
	private List<Drink> selectedDrinks;
	private Boolean delivery = false;
	private Boolean pickup = false;
	private int orderNumber;
	
	public Order(Boolean Delivery, Boolean pickup, int OrderNumber) {
		selectedItems = new ArrayList<Item>();
		selectedDrinks = new ArrayList<Drink>();
		this.delivery = Delivery;
		this.pickup = pickup;
		this.orderNumber = OrderNumber;
	}
	
	//add an item from an order
	public void addItem(Item item) {
		selectedItems.add(item);	
	}
	
	//remove an item from an order
	public void removeItem(Item item) {
		selectedItems.remove(item);
	}
	
	public void addDrink(Drink drink) {
		selectedDrinks.add(drink);
	}
	
	public void removeDrink(Drink drink) {
		selectedDrinks.remove(drink);
	}

	public Boolean getDelivery() {
		return delivery;
	}

	public void setDelivery(Boolean delivery) {
		this.delivery = delivery;
	}

	public Boolean getPickup() {
		return pickup;
	}

	public void setPickup(Boolean pickup) {
		this.pickup = pickup;
	}

	public int getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	
	// returns the account with given username
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
	
	public List<Drink> getDrinkList(){
		return this.selectedDrinks;
	}
	
	public double getTotalPrice() {
		double price = 0;
		for(Item i: selectedItems) {
			price += i.getPrice();
		}
		for(Drink d: selectedDrinks) {
			price += d.getPrice();
		}
		return price;
	}
	
	
}