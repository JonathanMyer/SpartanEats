package edu.ycp.cs320.jmyer.model;

import static org.junit.Assert.assertEquals;
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
		System.out.println("Setup " + inventory1.getItem("Baja Chicken"));
		inventory1.addItem(item1);
	}
	
	@Test
	public void testGetItem() {
		//System.out.println(item1.getItemName());
		assertEquals(inventory1.getItem(item1.getItemName()), item1);
	}
	
	@Test
	public void testAddItem() {
		inventory1.addItem(item2);
		assertEquals(inventory1.getItem(item2.getItemName()), item2);
	}
	
	@Test
	public void testRemoveItem() {
		inventory1.removeItem(item3);
		assertEquals(inventory1.getItem(item3.getItemName()), null);
	}
}
