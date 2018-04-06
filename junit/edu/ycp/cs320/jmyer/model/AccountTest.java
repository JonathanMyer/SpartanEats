package edu.ycp.cs320.jmyer.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.spartaneats.model.Account;

public class AccountTest {
	private Account AccountUserChase;
	private Account AccountNull;
	private Account AccountUserAdmin;
	
	
	@Before
	public void setUp() {
		//cTeich,Chase,Teichmann,903-202-533,sKFpeVhc,cteichmann@ycp.edu,235-256-2783,141.71 ,175.75, user, 1
		AccountUserChase = new Account("cTeich", "Chase", "Teichmann", "903-202-533", "sKFpeVhc", "cteichmann@ycp.edu", "235-256-2783","user", 1, 141.71, 175.0);
		//admin	password	null	null	null	null	null	0	0	admin 15
		AccountUserAdmin = new Account("admin", "null", "null", "null", "password", "null", "null", "admin", 15, 0.0, 0.0);
		AccountNull = new Account();
	}
	
	@Test
	public void testGets() {
		//Assert for User: Chase
		assertTrue(AccountUserChase.getUserName().equals("cTeich"));
		assertTrue(AccountUserChase.getFirstName().equals("Chase"));
		assertTrue(AccountUserChase.getLastName().equals("Teichmann"));
		assertTrue(AccountUserChase.getStudentID().equals("903-202-533"));
		assertTrue(AccountUserChase.getPassword().equals("sKFpeVhc"));
		assertTrue(AccountUserChase.getEmail().equals("cteichmann@ycp.edu"));
		assertTrue(AccountUserChase.getPhoneNumber().equals("235-256-2783"));
		assertTrue(AccountUserChase.getFlex() == 141.71);
		assertTrue(AccountUserChase.getDining() == 175.0);
		assertTrue(AccountUserChase.getAdminStatus().equals("user"));
		assertTrue(AccountUserChase.getAccountId() == 1);
		
		//Tests for User Admin
		assertTrue(AccountUserAdmin.getUserName().equals("admin"));
		assertTrue(AccountUserAdmin.getFirstName().equals("null"));
		assertTrue(AccountUserAdmin.getLastName().equals("null"));
		assertTrue(AccountUserAdmin.getStudentID().equals("null"));
		assertTrue(AccountUserAdmin.getPassword().equals("password"));
		assertTrue(AccountUserAdmin.getEmail().equals("null"));
		assertTrue(AccountUserAdmin.getPhoneNumber().equals("null"));
		assertTrue(AccountUserAdmin.getFlex() == 0.0);
		assertTrue(AccountUserAdmin.getDining() == 0.0);
		assertTrue(AccountUserAdmin.getAdminStatus().equals("admin"));
		assertTrue(AccountUserAdmin.getAccountId() == 15);
	}
	
	@Test
	public void testSetUserName() {
		AccountNull.setUserName("skiser");
		assertTrue(AccountNull.getUserName().equals("skiser"));
	}
	
	@Test
	public void testSetFirstName() {
		AccountNull.setFirstName("Sam");
		assertTrue(AccountNull.getFirstName().equals("Sam"));
	}
	
	@Test
	public void testSetLastName() {
		AccountNull.setFirstName("Kiser");
		assertTrue(AccountNull.getFirstName().equals("Kiser"));
	}
	
	@Test
	public void testSetStudentID() {
		AccountNull.setStudentID("903-208-104");
		assertTrue(AccountNull.getStudentID().equals("903-208-104"));
	}
	
	@Test
	public void testSetPassword() {
		AccountNull.setPassword("kEdP3AAS");
		assertTrue(AccountNull.getPassword().equals("kEdP3AAS"));
	}
	
	@Test
	public void testSetEmail() {
		AccountNull.setEmail("skiser@ycp.edu");
		assertTrue(AccountNull.getEmail().equals("skiser@ycp.edu"));
	}
	
	@Test
	public void testSetPhoneNumber() {
		AccountNull.setPhoneNumber("845-181-2578");
		assertTrue(AccountNull.getPhoneNumber().equals("845-181-2578"));
	}
	
	@Test 
	public void testSetFlex() {
		AccountNull.setFlex(13.25);
		assertTrue(AccountNull.getFlex() == 13.25);
	}
	
	@Test 
	public void testSetDining() {
		AccountNull.setDining(22.0);
		assertTrue(AccountNull.getDining() == 22.0);
	}

	@Test
	public void testIsPasswordCorrect() {
		assertTrue(AccountUserChase.isPasswordCorrect("sKFpeVhc"));
		assertFalse(AccountUserChase.isPasswordCorrect("kEdP3AAS"));
	}
}