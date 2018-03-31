package edu.ycp.cs320.jmyer.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.spartaneats.model.Item;

public class ItemTest {

	private Item item1;
	private Item item2;
	
	@Before
	public void setUp() {
		item1 = new Item(1, "Caprese", 6.49);
	}
	
	@Test
	public void testGets() {
		assertTrue(item1.getItemName().equals("Caprese"));
		assertTrue(item1.getPrice() == 6.49);
		assertTrue(item1.getItemId() == 1);
	}
	
	@Test
	public void testSetItemName() {
		item2.setItemName("Turkey Bacon");
		assertTrue(item2.getItemName().equals("Turkey Bacon"));
	}
	
	@Test
	public void testSetPrice() {
		item2.setPrice(6.49);
		assertTrue(item2.getPrice() == 6.49);
	}

	@Test
	public void testSetItemId() {
		item2.setItemId(1);
		assertTrue(item2.getItemId() == 1);
	}
}