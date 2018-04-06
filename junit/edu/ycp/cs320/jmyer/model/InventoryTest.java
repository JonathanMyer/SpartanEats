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
		item1 = new Item(1, "Baja Chicken", 6.49);
		item2 = new Item(2, "Chicken Pesto", 6.49);
		item3 = new Item(3, "Aggie Club", 6.49);

		inventory1.addItem(item1);
	}
	
	@Test
	public void testGetItem() {
		assertTrue(inventory1.getItem(item1.getItemName()).equals(item1));
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
