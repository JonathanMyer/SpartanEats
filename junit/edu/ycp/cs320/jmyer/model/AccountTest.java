package edu.ycp.cs320.jmyer.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.spartaneats.model.Account;

public class AccountTest {
	private Account account1;
	private Account account2;
	
	
	@Before
	public void setUp() {
		account1 = new Account(1, "cTeich", "Chase", "Teichmann", "903-202-533", "sKFpeVhc", "cteichmann@ycp.edu", "235-256-2783", 141.71, 175.0);
		
	}
	
	@Test
	public void testGets() {
		assertTrue(account1.getAccountId() == 1);
		assertTrue(account1.getUserName().equals("cTeich"));
		assertTrue(account1.getFirstName().equals("Chase"));
		assertTrue(account1.getLastName().equals("Teichmann"));
		assertTrue(account1.getStudentID().equals("903-202-533"));
		assertTrue(account1.getPassword().equals("sKFpeVhc"));
		assertTrue(account1.getEmail().equals("cteichmann@ycp.edu"));
		assertTrue(account1.getPhoneNumber().equals("235-256-2783"));
		assertTrue(account1.getFlex() == 141.71);
		assertTrue(account1.getDining() == 175.0);
	}
	
	@Test
	public void testSetUserName() {
		account2.setUserName("skiser");
		assertTrue(account2.getUserName().equals("skiser"));
	}
	
	@Test
	public void testSetFirstName() {
		account2.setFirstName("Sam");
		assertTrue(account2.getFirstName().equals("Sam"));
	}
	
	@Test
	public void testSetLastName() {
		account2.setFirstName("Kiser");
		assertTrue(account2.getFirstName().equals("Kiser"));
	}
	
	@Test
	public void testSetStudentID() {
		account2.setStudentID("903-208-104");
		assertTrue(account2.getStudentID().equals("903-208-104"));
	}
	
	@Test
	public void testSetPassword() {
		account2.setPassword("kEdP3AAS");
		assertTrue(account2.getPassword().equals("kEdP3AAS"));
	}
	
	@Test
	public void testSetEmail() {
		account2.setEmail("skiser@ycp.edu");
		assertTrue(account2.getEmail().equals("skiser@ycp.edu"));
	}
	
	@Test
	public void testSetPhoneNumber() {
		account2.setPhoneNumber("845-181-2578");
		assertTrue(account2.getPhoneNumber().equals("845-181-2578"));
	}
	
	@Test 
	public void testSetFlex() {
		account2.setFlex(13.25);
		assertTrue(account2.getFlex() == 13.25);
	}
	
	@Test 
	public void testSetDining() {
		account2.setDining(22.0);
		assertTrue(account2.getDining() == 22.0);
	}
	@Test
	public void testIsPasswordCorrect() {
		assertTrue(account1.isPasswordCorrect("sKFpeVhc"));
		assertFalse(account1.isPasswordCorrect("kEdP3AAS"));
	}

	
}