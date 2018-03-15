package edu.ycp.cs320.jmyer.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.spartaneats.controller.AccountController;
import edu.ycp.cs320.spartaneats.model.Account;

public class AccountControllerTest {
	private AccountController controller;
	private Account account1;
	
	@Before
	public void setUp() {
		controller = new AccountController();
		account1 = new Account("BigBob","1234");
	}
	
	
	@Test
	public void testAddController() {
		controller.addAccount(account1);
		assertTrue(controller.getAccount("BigBob").equals(account1));
	}
	
}