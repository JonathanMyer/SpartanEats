package edu.ycp.cs320.spartaneats.model;

import java.util.ArrayList;

public class Order  {
	private ArrayList<Item> selectedItems;
	private Boolean Delivery;
	private Boolean Pickup;
	private int OrderNumber;
	
	public Order(ArrayList<Item> selectedItems, Boolean Delivery) {
		this.selectedItems = selectedItems;
		this.setDelivery(Delivery);
	}
	
	//return the order
	public ArrayList<Item> getOrder() {
		return this.selectedItems;
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
		return Delivery;
	}

	public void setDelivery(Boolean delivery) {
		Delivery = delivery;
	}

	public Boolean getPickup() {
		return Pickup;
	}

	public void setPickup(Boolean pickup) {
		Pickup = pickup;
	}

	public int getOrderNumber() {
		return this.OrderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.OrderNumber = orderNumber;
	}
	
	
	
}
