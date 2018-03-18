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
		order1 = new Order(true, false, 1);
		order2 = new Order(null, null, 0);
		item1 = new Item("Water", 1.59, 1);
		item2 = new Item("Gold Peak Tea", 2.19, 2);
		order1.addItem(item1);
		
		
	}
	
	@Test
	public void testgets() {
		assertTrue(order1.getDelivery().equals(true));
		assertTrue(order1.getPickup().equals(false));
		assertTrue(order1.getOrderNumber() == 1);
		assertTrue(order1.getItem(item1) == item1);
	}
	
	@Test
	public void testsetDelivery() {
		order2.setDelivery(false);
		assertTrue(order2.getDelivery().equals(false));
	}
	
	@Test
	public void testsetPickUp() {
		order2.setPickup(true);
		assertTrue(order2.getPickup().equals(true));
	}
	
	@Test
	public void testSetOrderNumber() {
		order2.setOrderNumber(2);
		assertTrue(order2.getOrderNumber() == 2);
	}
	
	@Test
	public void testAddItem() {
		order2.addItem(item2);
		assertTrue(order2.getItem(item2) == item2);
	}
	
	public void testRemoveItem() {
		order2.removeItem(item2);
		assertTrue(order2.getItem(item2).equals(null));
	}
}

