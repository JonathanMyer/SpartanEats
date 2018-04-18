 package edu.ycp.cs320.jmyer.model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.spartaneats.model.Condiments;

//Junit Check
public class CondimentsTest {

	private Condiments Rice;
	private Condiments Beans;
	//Initial setup of Condiments types
	@Before
	public void setUp() {
		Rice = new Condiments("Mexican","Cilantro Lime Rice", 2);
		Beans = new Condiments();
	}
	//Test all get methods, mixture of True, False, and Equals assertions
	@Test
	public void testGets() {
		assertTrue(Rice.getCondType().equals("Mexican"));
		assertTrue(Rice.getCondName().equals("Cilantro Lime Rice"));
		assertTrue(Rice.getCondID() == 2);
		assertFalse(Rice.getCondType().equals("Pizza"));
		assertFalse(Rice.getCondName().equals("Gluten Free Dough"));
		assertFalse(Rice.getCondID() == 29);
		//assertTrue(Beans.getCondimentsName().equals("Mug Root Beer"));
	}
	@Test
	public void testSetCondID() {
		Beans.setCondID(2);
		assertTrue(Beans.getCondID() == 2);
		assertFalse(Beans.getCondID() == 29);
	}
	
	@Test
	public void testSetCondType() {
		Beans.setCondType("Mexican");
		assertTrue(Beans.getCondType().equals("Mexican"));
		assertFalse(Beans.getCondType().equals("Pizza"));
	}
	
	@Test
	public void testSetCondName() {
		Beans.setCondName("Pinto Beans");
		assertTrue(Beans.getCondName().equals("Pinto Beans"));
		assertFalse(Beans.getCondName().equals("Gluten Free Dough"));
	}
}