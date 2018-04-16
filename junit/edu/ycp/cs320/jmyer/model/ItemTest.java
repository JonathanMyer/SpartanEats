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
		Water = new Item(1, "Dasani Water", 1.49);
		Soda = new Item(2, "Mug Root Beer", 1.99);
		placeholder = new Item();
	}
	//Test all get methods, mixture of True, False, and Equals assertions
	@Test
	public void testGets() {
		assertTrue(Water.getItemName().equals("Dasani Water"));
		assertTrue(Soda.getItemName().equals("Mug Root Beer"));
		//Same Statements Different assertion types
		assertTrue(Water.getPrice() == 1.49);
		assertEquals(Water.getPrice(), 1.49, 0.0000001);
		assertTrue(Water.getItemId() == 1);
		assertEquals(Water.getItemId(), 1, 0.0000001);
		//False assertions
		assertFalse(Soda.getItemName().equals("Coffee"));
		assertFalse(Soda.getItemId() == 1);
		assertFalse(Soda.getPrice() == 1.00);	
	}
	@Test
	public void testSetItemName() {
		placeholder.setItemName("Turkey Bacon");
		assertTrue(placeholder.getItemName().equals("Turkey Bacon"));
	}
	
	@Test
	public void testSetPrice() {
		placeholder.setPrice(6.49);
		assertTrue(placeholder.getPrice() == 6.49);
	}

	@Test
	public void testSetItemId() {
		placeholder.setItemId(1);
		assertTrue(placeholder.getItemId() == 1);
	}
}