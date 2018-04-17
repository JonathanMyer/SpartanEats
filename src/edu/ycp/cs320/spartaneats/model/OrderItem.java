package edu.ycp.cs320.spartaneats.model;

import java.util.ArrayList;

public class OrderItem {
	private int order_id;
	private int item_id;
	private int amount;
	private ArrayList<Integer> condiment_id;
	
	
	public OrderItem() {

	}
	
	


	
	


	public int getOrder_id() {
		return order_id;
	}




	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}




	public int getItem_id() {
		return item_id;
	}




	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}




	public int getAmount() {
		return amount;
	}




	public void setAmount(int amount) {
		this.amount = amount;
	}




	public ArrayList<Integer> getCondiment_id() {
		return condiment_id;
	}




	public void setCondiment_id(ArrayList<Integer> condiment_id) {
		this.condiment_id = condiment_id;
	}
}