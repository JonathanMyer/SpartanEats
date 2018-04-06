package edu.ycp.cs320.spartaneats.model;

import java.util.ArrayList;

public class Order  {
	private ArrayList<Item> selectedItems;
	private Boolean delivery = false;
	private Boolean pickup = false;
	private int orderNumber;
	
	public Order(Boolean Delivery, Boolean pickup, int OrderNumber) {
		selectedItems = new ArrayList<Item>();
		this.delivery = Delivery;
		this.pickup = pickup;
		this.orderNumber = OrderNumber;
	}
	
	//add an item from an order
	public void addItem(Item Item) {
		selectedItems.add(Item);	
	}
	
	//remove an item from an order
	public void removeItem(Item Item) {
		selectedItems.remove(Item);
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

	public ArrayList<Item> getItemList(){
		return this.selectedItems;
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