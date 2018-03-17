package edu.ycp.cs320.jmyer.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.spartaneats.controller.AccountController;
import edu.ycp.cs320.spartaneats.model.Account;

public class AccountControllerTest {
	private AccountController controller;
	private Account account1;
	private Account account2;
	private Account account3;
	private String accountName;
	
	@Before
	public void setUp() {
		controller = new AccountController();
		account2 = new Account("BillyBones","12345");
		account1 = new Account("BigBob","1234");
		account3 = new Account("BillyBones","123");
		controller.addAccount(account2);
		controller.addAccount(account1);
		controller.addAccount(account3);
		accountName = "BillyBones";
	}
	
	
	@Test
	public void testAddAccount() {
		assertTrue(controller.getAccount("BigBob").equals(account1));
	}
	
	@Test
	public void testGetAccount() {
		assertTrue(controller.getAccount(accountName).equals(account2));
	}
	
	@Test 
	public void testDoesAccountExist() {
		assertTrue(controller.doesAccountExist(account1.getAccountName()));
	}
	
}