package edu.ycp.cs320.jmyer.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.spartaneats.model.Item;

public class ItemTest {

	private Item item;
	private Item item2;
	
	@Before
	public void setUp() {
		item = new Item("Caprese", 6.49, 1);
		
	}
	
	@Test
	public void testgets() {
		assertTrue(item.getItemName().equals("Caprese"));
		assertTrue(item.getPrice() == 6.49);
		assertTrue(item.getCount() == 1);
	}
	
	@Test
	public void testsetItemName() {
		item2.setItemName("Turkey Bacon");
		assertTrue(item2.getItemName().equals("Turkey Bacon"));
	}
	
	@Test
	public void testsetPrice() {
		item2.setPrice(6.49);
		assertTrue(item2.getPrice() == 6.49);
	}
	
	@Test
	public void testSetCount() {
		item2.setCount(1);
		assertTrue(item2.getCount() == 1);
	}
}
