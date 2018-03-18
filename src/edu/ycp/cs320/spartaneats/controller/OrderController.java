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
	// returns order using order number
	public Order getOrder(int orderNumber) {
		for (Order order: orderList) {
			if (order.getOrderNumber() == (orderNumber)) {
				System.out.println("Found Order");
				return order;
			}
		}
	
		return null;
			
	}
}