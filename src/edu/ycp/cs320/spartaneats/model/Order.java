package edu.ycp.cs320.spartaneats.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Order  {
	private List<Item> selectedItems;
	private List<List<Condiments>> condArray;
	private Boolean delivery;
	private int orderId;
	private int accountId;
	private Boolean active;
	private String deliveryDest;
	private String orderName;
	
	
	public Order() {
	selectedItems = new ArrayList<Item>();
	condArray = new ArrayList<List<Condiments>>();
	}
	public Order(Boolean Delivery, int OrderId, int AccountId) {
		selectedItems = new ArrayList<Item>();
		condArray = new ArrayList<List<Condiments>>();
		this.delivery = Delivery;
		this.orderId = OrderId;
		this.accountId = AccountId;
	}
	//Test Get and Set Methods
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
	public int getAccountId() {
		return accountId;
	}
	public void setAccount_id(int accountId) {
		this.accountId = accountId;
	}	
	//add an item from an order
	public void addItem(Item item) {
		selectedItems.add(item);	
	}
	//remove an item from an order
	public void removeItem(Item item) {
		selectedItems.remove(item);
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
		DecimalFormat df = new DecimalFormat("#.##");
		return Double.parseDouble(df.format(price));
	}
	
	public List<List<Condiments>> getCondArray() {
		return condArray;
	}
	
	public void addCondArrayList(List<Condiments> condList) {
		this.condArray.add(condList);
	}
	
	public void setCondArray(List<List<Condiments>> condArray) {
		this.condArray = condArray;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public void setActiveTrue() {
		this.active = true;
	}
	
	public void setActiveFalse() {
		this.active = false;
	}
	
	public void removeItemAndCond(int removeItem) {
		condArray.remove(removeItem);
		selectedItems.remove(removeItem);
	}
	
	public OrderItem getOrderItem(int removeItem) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItem_id(selectedItems.get(removeItem).getItemId());
		orderItem.setOrder_id(this.orderId);
		for(Condiments c: condArray.get(removeItem)) {
			orderItem.addCondiment_idToList(c.getCondID());
		}
		return orderItem;
	}
	public String getDeliveryDest() {
		return deliveryDest;
	}
	public void setDeliveryDest(String deliveryDest) {
		this.deliveryDest = deliveryDest;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName= orderName;
	}
}