package edu.ycp.cs320.spartaneats.model;

import java.util.ArrayList;
import java.util.List;

public class Order  {
	private List<Item> selectedItems;
	private List<Drink> selectedDrinks;
	private List<Extras> selectedExtras;
	private Boolean delivery = false;
	private int orderId;
	private int account_id;
	
	public Order(Boolean Delivery, int OrderId) {
		selectedItems = new ArrayList<Item>();
		selectedDrinks = new ArrayList<Drink>();
		selectedExtras = new ArrayList<Extras>();
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
	
	public void addDrink(Drink drink) {
		selectedDrinks.add(drink);
	}
	
	public void removeDrink(Drink drink) {
		selectedDrinks.remove(drink);
	}
	
	public void addExtra(Extras extra) {
		selectedExtras.add(extra);
	}
	
	public void removeExtra(Extras extra) {
		selectedExtras.remove(extra);
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
	
	public Drink getDrink(String drink) {
		for (Drink i: selectedDrinks) {
			if (i.getItemName().equals(drink)) {
				System.out.println("found Drink");
				return i;
			}
		}	
		return null;
	}
	
	public Extras getExtra(String extra) {
		for (Extras i: selectedExtras) {
			if (i.getItemName().equals(extra)) {
				System.out.println("found extra");
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
	
	public List<Extras> getExtraList(){
		return this.selectedExtras;
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

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	
	
}