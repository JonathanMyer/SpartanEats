package edu.ycp.cs320.jmyer.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.spartaneats.model.OrderItem;

public class OrderItemTest {
	private OrderItem SampleOrderItem1;
	private ArrayList<Integer>Item1condimentList = new ArrayList<Integer>();
	private ArrayList<Integer>Item2condimentList = new ArrayList<Integer>();
	private OrderItem EmptySample;
	private ArrayList<Integer>EmptySampleList = new ArrayList<Integer>();
	
	@Before
	public void setUp() {
		Item1condimentList.add(1);
		Item1condimentList.add(2);
		SampleOrderItem1 = new OrderItem(1, 1, 1, Item1condimentList);
		Item2condimentList.add(2);
		Item2condimentList.add(4);
		Item2condimentList.add(6);
		EmptySample = new OrderItem();
	}
	@Test
	public void testgets() {
		assertTrue(SampleOrderItem1.getAmount() == 1);
		assertTrue(SampleOrderItem1.getOrder_id() == 1);
		assertTrue(SampleOrderItem1.getItem_id() == 1);
		assertTrue(SampleOrderItem1.getCondiment_id().get(0) == 1);
		assertTrue(SampleOrderItem1.getCondiment_id().get(1) == 2);
	}
	@Test
	public void testSetAmount() {
		EmptySample.setAmount(1);
		assertTrue(EmptySample.getAmount() == 1);
		assertFalse(EmptySample.getAmount() == 2);
	}
	@Test
	public void testSetOrderId() {
		EmptySample.setOrder_id(1);
		assertTrue(EmptySample.getOrder_id() == 1);
		assertFalse(EmptySample.getOrder_id() == 2);
	}
	@Test
	public void testSetItemId() {
		EmptySample.setItem_id(1);
		assertTrue(EmptySample.getItem_id() == 1);
		assertFalse(EmptySample.getItem_id() == 2);
	}
	@Test
	public void testSetCondiment_id() {
		EmptySampleList.add(1);
		EmptySampleList.add(3);
		EmptySample.setCondiment_id(EmptySampleList);
		assertTrue(EmptySample.getCondiment_id().get(0) == 1);
		assertTrue(EmptySample.getCondiment_id().get(1) == 3);
		assertFalse(EmptySample.getCondiment_id().get(1) == 6);
	}
	@Test
	public void testAddCondiment_idToList(){
		EmptySample.addCondiment_idToList(1);
		assertTrue(EmptySample.getCondiment_id().get(0) == 1);
		assertFalse(EmptySample.getCondiment_id().get(0) == 6);
	}
	
	@Test
	public void testRemoveCondiment() {
		OrderItem orderItem = new OrderItem();
		orderItem.addCondiment_idToList(5);
		orderItem.addCondiment_idToList(23);
		orderItem.addCondiment_idToList(54);
		orderItem.addCondiment_idToList(51);
		orderItem.addCondiment_idToList(38);
		System.out.println("Condiments in testRemoveCondiemnt: ");
		for(int i: orderItem.getCondiment_id()) {
			System.out.println(i+",");
		}
		orderItem.removeCondiment(23);
		assertTrue(orderItem.getCondiment_id().size() == 4);
		System.out.println("Condiments in testRemoveCondiemnt: ");
		for(int i: orderItem.getCondiment_id()) {
			System.out.println(i+",");
		}
		
	}

}

