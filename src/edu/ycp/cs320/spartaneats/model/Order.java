package edu.ycp.cs320.spartaneats.model;

import java.util.ArrayList;
import java.util.List;

public class Order  {
	private List<Item> selectedItems;
	private Boolean delivery = false;
	private int orderId;
	private int accountId;
	
	public Order(Boolean Delivery, int OrderId) {
		selectedItems = new ArrayList<Item>();
		this.delivery = Delivery;
		this.orderId = OrderId;
	}
	
	//add an item from an order
	public void addItem(Item item) {
		selectedItems.add(item);	
	}
	
	//remove an item from an order
	public void removeItem(Item item) {
		selectedItems.remove(item);
	}


	public Boolean getDelivery() {
		return delivery;
	}

	public void setDelivery(Boolean delivery) {
		this.delivery = delivery;
	}


	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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

	public List<Item> getItemList(){
		return this.selectedItems;
	}

	public double getTotalPrice() {
		double price = 0;
		for(Item i: selectedItems) {
			price += i.getPrice();
		}
		return price;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}	
}