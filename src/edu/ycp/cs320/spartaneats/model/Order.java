package edu.ycp.cs320.spartaneats.model;

import java.util.ArrayList;

public class Order  {
	private ArrayList<Item> selectedItems;
	private Boolean delivery = false;
	private Boolean pickup = false;
	private int orderNumber;
	
	public Order(ArrayList<Item> selectedItems, Boolean Delivery, Boolean pickup, int OrderNumber) {
		this.selectedItems = selectedItems;
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
		return this.OrderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.OrderNumber = orderNumber;
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

	public int getOrderNumber() {
		return orderNumber;
	}
}