package edu.ycp.cs320.jmyer.model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.spartaneats.model.Item;
import edu.ycp.cs320.spartaneats.model.Order;

public class OrderTest {
	private Order order1;
	private Order order2;
	private ArrayList<Item> selectedItems;
	
	@Before
	public void setUp() {
		order1 = new Order(selectedItems, true, false, 1);
		
	}
	
	@Test
	public void testgets() {
		assertTrue(order1.getDelivery().equals(true));
		assertTrue(order1.getPickup().equals(false));
		assertTrue(order1.getOrderNumber() == 1);
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

}
