package edu.ycp.cs320.spartaneats.model;

import java.util.ArrayList;

public class Administrator {
	public ArrayList<Order> currentOrders;
	
	public void addOrder(Order order) {
		currentOrders.add(order);
	}
	
	public void removeOrder(Order order) {
		currentOrders.remove(order);
	}
}
