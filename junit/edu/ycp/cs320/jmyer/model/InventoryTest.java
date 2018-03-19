package edu.ycp.cs320.jmyer.model;

import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.spartaneats.model.Inventory;
import edu.ycp.cs320.spartaneats.model.Item;

public class InventoryTest {
	private Inventory inventory1;
	private Item item1;
	private Item item2;
	private Item item3; 
	
	@Before
	public void SetUp() {
		inventory1 = new Inventory();
		item1 = new Item("Baja Chicken", 6.49, 1);
		item2 = new Item("Chicken Pesto", 6.49, 2);
		item3 = new Item("Aggie Club", 6.49, 4);
		inventory1.addItem(item1);
		inventory1.addItem(item3);
	}
	
	@Test
	public void testGetItem() {
		assertTrue(inventory1.getItem(item1.getItemName()).equals(item1));
		assertTrue(inventory1.getItem(item3.getItemName()).equals(item3));
	}
	
	@Test
	public void testAddItem() {
		inventory1.addItem(item2);
		assertTrue(inventory1.getItem(item2.getItemName()) == item2);
	}
	
	@Test
	public void testRemoveItem() {
		inventory1.removeItem(item3);
		assertTrue(inventory1.getItem(item3.getItemName()) == null);
	}
}
