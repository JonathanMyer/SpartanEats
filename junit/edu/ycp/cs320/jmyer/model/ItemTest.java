 package edu.ycp.cs320.jmyer.model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.spartaneats.model.Item;
//Junit Check
public class ItemTest {

	private Item Water;
	private Item Soda;
	private Item placeholder;
	//Initial setup of Item types
	@Before
	public void setUp() {
		Water = new Item("Drink", "Dasani Water", 1.49, "false", 1);
		Soda = new Item();
	}
	//Test all get methods, mixture of True, False, and Equals assertions
	@Test
	public void testGets() {
		assertTrue(Water.getItemId() == 1);
		assertTrue(Water.getItemType().equals("Drink"));
		assertTrue(Water.getItemName().equals("Dasani Water"));
		assertTrue(Water.getPrice() == 1.49);
		assertTrue(Water.getCondiments().equals("false"));
		//assertTrue(Soda.getItemName().equals("Mug Root Beer"));
	}
	@Test
	public void testSetItemID() {
		Soda.setItemId(2);
		assertTrue(Soda.getItemId() == 2);
	}
	
	@Test
	public void testSetItemType() {
		Soda.setItemType("Drink");
		assertTrue(Soda.getItemType().equals("Drink"));
	}
	
	@Test
	public void testSetItemName() {
		Soda.setItemName("Mug Root Beer");
		assertTrue(Soda.getItemName().equals("Mug Root Beer"));
	}
	
	@Test
	public void testSetPrice() {
		Soda.setPrice(1.49);
		assertTrue(Soda.getPrice() == 1.49);
	}
	
	@Test
	public void testSetCondiments() {
		Soda.setCondiments("false");
		assertTrue(Soda.getCondiments().equals("false"));
	}
}