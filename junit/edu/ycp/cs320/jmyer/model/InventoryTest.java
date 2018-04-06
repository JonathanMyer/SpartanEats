package edu.ycp.cs320.jmyer.model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.spartaneats.model.Inventory;
import edu.ycp.cs320.spartaneats.model.Item;
//Inventory Test 
public class InventoryTest {
	private Inventory inventory;
	private Item BajaChicken;
	private Item ChickenPesto;
	private Item AggieClub; 
	//Initial setup for variables
	@Before
	public void SetUp() {
		inventory = new Inventory();
		BajaChicken = new Item(1, "Baja Chicken", 6.49);
		ChickenPesto = new Item(2, "Chicken Pesto", 6.49);
		AggieClub = new Item(3, "Aggie Club", 6.49);
		//Add All Items
		inventory.addItem(BajaChicken);
		inventory.addItem(ChickenPesto);
		inventory.addItem(AggieClub);
	}
	@Test
	public void testgetItemIdMethod() {
		assertEquals(inventory.getItemIDMethod(BajaChicken.getItemId()), 1, 0.00001);
		assertEquals(inventory.getItemIDMethod(ChickenPesto.getItemId()), 2, 0.00001);
		assertEquals(inventory.getItemIDMethod(AggieClub.getItemId()), 3, 0.00001);
	}
	@Test
	public void testgetItemNameMethod() {
		assertTrue(inventory.getItemNameMethod(BajaChicken.getItemName()).equals(BajaChicken));
		assertTrue(inventory.getItemNameMethod(ChickenPesto.getItemName()).equals(ChickenPesto));
		assertTrue(inventory.getItemNameMethod(AggieClub.getItemName()).equals(AggieClub));
		
		assertFalse(inventory.getItemNameMethod(BajaChicken.getItemName()).equals(ChickenPesto));
	}
	@Test
	public void testgetPriceMethod() {
		assertEquals(inventory.getItemPriceMethod(BajaChicken.getPrice()), 6.49, 0.00001);
		assertEquals(inventory.getItemPriceMethod(AggieClub.getPrice()), 6.49, 0.00001);
	}
	//Add Item in Inventory Class
	@Test
	public void testAddItem() {
		//Assert True for all adds
		inventory.addItem(BajaChicken);
		assertTrue(inventory.getItemNameMethod(BajaChicken.getItemName()) == BajaChicken);
		inventory.addItem(ChickenPesto);
		assertTrue(inventory.getItemNameMethod(ChickenPesto.getItemName()) == ChickenPesto);
		inventory.addItem(AggieClub);
		assertTrue(inventory.getItemNameMethod(AggieClub.getItemName()) == AggieClub);
		//Assert False for adding item for BajaChicken expecting ChickenPesto
		inventory.addItem(BajaChicken);
		assertFalse(inventory.getItemNameMethod(BajaChicken.getItemName()) == ChickenPesto);
	}
	//Remove Item in Inventory Class
	@Test
	public void testRemoveItem() {
		inventory.removeItem(AggieClub);
		assertTrue(inventory.getItemNameMethod(AggieClub.getItemName()) == null);
		inventory.removeItem(BajaChicken);
		assertTrue(inventory.getItemNameMethod(BajaChicken.getItemName()) == null);
	}
}


