package edu.ycp.cs320.jmyer.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


import edu.ycp.cs320.spartaneats.model.LoginModel;

public class LoginModelTest {
	private LoginModel model;
	
	
	@Before
	public void setUp() {
		model = new LoginModel();
		model.setAccountName("BillyBones");
		model.setError("There is no Error here");
		model.setPassword("12345");	
		
	}
	
	@Test
	public void testGetAccountName() {
		assertTrue(model.getAccountName().equals("BillyBones"));
		
	}
	
	@Test
	public void testGetError() {
		assertTrue(model.getError().equals("There is no Error here"));
	}
	
	@Test
	public void testGetPassword() {
		assertTrue(model.getPassword().equals("12345"));
	}
	
}