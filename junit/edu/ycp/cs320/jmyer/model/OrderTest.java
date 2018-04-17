package edu.ycp.cs320.jmyer.model;

import static org.junit.Assert.assertEquals;
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
		item1 = new Item("Drink", "Water", 1.49, "false", 1.0);
		order1.addItem(item1);
		item2 = new Item("Drink", "Mug", 1.49, "false", 2.0);
		order2 = new Order(null, 2);
		
		
	}
	
	@Test
	public void testgets() {
		assertTrue(order1.getDelivery().equals(true));
		assertTrue(order1.getOrderId() == 1);
		assertTrue(order1.getItem("Water") == item1);
	}
	
	
	@Test
	public void testSetDelivery() {
		order2.setDelivery(false);
		assertTrue(order2.getDelivery().equals(false));
	}

	
	@Test
	public void testAddItem() {
		order1.addItem(item2);
		assertTrue(order1.getItem("Mug") == item2);
	}
	
	public void testRemoveItem() {
		order2.removeItem(item2);
		assertTrue(order2.getItem(item2.getItemName()).equals(null));
	}
}

