package edu.ycp.cs320.jmyer.model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.spartaneats.model.Item;
import edu.ycp.cs320.spartaneats.model.Order;

public class OrderTest {
	private Order order1;
	private Order order2;
	private Item item1;
	private Item item2;
	
	@Before
	public void setUp() {
		order1 = new Order(true, 1);
		order2 = new Order(null, 0);
		item1 = new Item(1, "Water", 1.59);
		item2 = new Item(2, "Gold Peak Tea", 2.19);
		order1.addItem(item1);
		
		
	}
	
	@Test
	public void testgets() {
		assertTrue(order1.getDelivery().equals(true));
	
		assertTrue(order1.getOrderId() == 1);
		assertTrue(order1.getItem("Water") == item1);
	}
	
	@Test
	public void testsetDelivery() {
		order2.setDelivery(false);
		assertTrue(order2.getDelivery().equals(false));
	}
	

	
	@Test
	public void testSetOrderNumber() {
		order2.setOrderId(2);
		assertTrue(order2.getOrderId() == 2);
	}
	
	@Test
	public void testAddItem() {
		order2.addItem(item2);
		assertTrue(order2.getItem(item2.getItemName()) == item2);
	}
	
	public void testRemoveItem() {
		order2.removeItem(item2);
		assertTrue(order2.getItem(item2.getItemName()).equals(null));
	}
}

