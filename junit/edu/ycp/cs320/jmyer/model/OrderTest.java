package edu.ycp.cs320.jmyer.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.spartaneats.model.Condiments;
import edu.ycp.cs320.spartaneats.model.Item;
import edu.ycp.cs320.spartaneats.model.Order;

public class OrderTest {
	private Order SampleOrder1;
	private Order SampleOrder2;
	private Order SampleOrder3;
	private Item SampleItem1;
	private Item SampleItem2;
	
	@Before
	public void setUp() {

		SampleOrder1 = new Order(true, 1, 1);
		SampleItem1 = new Item("Drink", "Water", 1.49, "false", 1);
		SampleOrder1.addItem(SampleItem1);

		
		SampleItem2 = new Item("Drink", "Mug", 1.49, "false", 2);
		SampleOrder2 = new Order();
		
		SampleOrder3 = new Order(true, 1, 1);		
		SampleOrder3.addItem(SampleItem1);
		SampleOrder3.addItem(SampleItem2);
	}
	@Test
	public void testgets() {
		assertTrue(SampleOrder1.getDelivery().equals(true));
		assertTrue(SampleOrder1.getOrderId() == 1);
		assertTrue(SampleOrder1.getAccountId() == 1);
		assertTrue(SampleOrder1.getItem("Water") == SampleItem1);
		assertFalse(SampleOrder1.getDelivery().equals(false));
		assertFalse(SampleOrder1.getOrderId() == 2);
		assertFalse(SampleOrder1.getItem("Water") == SampleItem2);
	}
	@Test
	public void testSetDelivery() {
		SampleOrder2.setDelivery(false);
		assertTrue(SampleOrder2.getDelivery().equals(false));
		assertFalse(SampleOrder2.getDelivery().equals(true));
	}
	@Test
	public void testSetOrderId() {
		SampleOrder1.setOrderId(1);
		assertTrue(SampleOrder1.getOrderId() == 1);
		assertFalse(SampleOrder1.getOrderId() == 2);
	}
	@Test
	public void testAddItem() {
		SampleOrder1.addItem(SampleItem1);
		assertTrue(SampleOrder1.getItem("Water") == SampleItem1);
		SampleOrder2.addItem(SampleItem1);
		assertTrue(SampleOrder2.getItem("Water") == SampleItem1);
		SampleOrder2.addItem(SampleItem2);
		assertTrue(SampleOrder2.getItem("Mug") == SampleItem2);
		SampleOrder2.addItem(SampleItem2);
		assertFalse(SampleOrder2.getItem("Mug") == SampleItem1);
	}
	@Test
	public void testRemoveItem() {
		int actionCheck = SampleOrder3.getItemList().size();
		System.out.println(actionCheck);
		SampleOrder3.removeItem(SampleItem1);
		int removeCheck = SampleOrder3.getItemList().size();
		System.out.println(removeCheck);
		//Remove next item
		SampleOrder3.removeItem(SampleItem2);
		removeCheck = SampleOrder3.getItemList().size();
		System.out.println(removeCheck);
	}
	@Test 
	public void testGetTotalPrice(){
		SampleOrder2.addItem(SampleItem1);
		SampleOrder2.addItem(SampleItem2);
		assertTrue(SampleOrder2.getTotalPrice() == 2.98);
		assertFalse(SampleOrder2.getTotalPrice() == 3.00);
		SampleOrder2.removeItem(SampleItem1);
		assertTrue(SampleOrder2.getTotalPrice() == 1.49);
		SampleOrder2.addItem(SampleItem2);
		SampleOrder2.addItem(SampleItem2);
		assertTrue(SampleOrder2.getTotalPrice() == 4.47);
		assertFalse(SampleOrder2.getTotalPrice() == 5.00);
		
	}
	
	@Test
	public void testRemoveItemAndCond() {
		Order sampleOrder3 = new Order();
		sampleOrder3.addItem(new Item());
		sampleOrder3.addItem(new Item());
		sampleOrder3.addItem(new Item());
		sampleOrder3.addItem(new Item());
		sampleOrder3.addCondArrayList(new ArrayList<Condiments>());
		sampleOrder3.addCondArrayList(new ArrayList<Condiments>());
		sampleOrder3.addCondArrayList(new ArrayList<Condiments>());
		sampleOrder3.addCondArrayList(new ArrayList<Condiments>());
		assertTrue(sampleOrder3.getItemList().size() == 4);
		assertTrue(sampleOrder3.getCondArray().size() == 4);
		sampleOrder3.removeItemAndCond(2);
		assertTrue(sampleOrder3.getItemList().size() == 3);
		assertTrue(sampleOrder3.getCondArray().size() == 3);
	}
}

