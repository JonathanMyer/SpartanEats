package edu.ycp.cs320.spartaneats.controller;

import java.util.ArrayList;

import edu.ycp.cs320.spartaneats.model.Order;

public class OrderController {
	
	ArrayList<Order> orderList = new ArrayList<Order>();
	
	// adds account to the list
	public void addOrder(Order order) {
		orderList.add(order);
	}
		
	public void removeOrder(Order order) {
		orderList.remove(order);
	}
	
	
}