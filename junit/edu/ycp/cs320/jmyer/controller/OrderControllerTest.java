package edu.ycp.cs320.jmyer.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.spartaneats.controller.OrderController;
import edu.ycp.cs320.spartaneats.model.Order;


public class OrderControllerTest {
	private OrderController controller;
	private Order order1;
	private Order order2;
	private Order order3;

	
	@Before
	public void setUp() {
		controller = new OrderController();
		order1 = new Order(true, false, 1);
		order2 = new Order(false, true, 2);
		order3 = new Order(true, false, 3);
		controller.addOrder(order1);
		controller.addOrder(order2);
	}
	
	
	@Test
	public void testAddOrder() {
		controller.addOrder(order3);
		assertTrue(controller.getOrder(3).equals(order3));	
	}
	
	@Test
	public void testGetOrder() {
		assertTrue(controller.getOrder(1).equals(order1));
	}
	
	@Test 
	public void testRemoveOrder() {
		controller.removeOrder(order2);
		assertTrue(controller.getOrder(2) == null);
	}
	
}
