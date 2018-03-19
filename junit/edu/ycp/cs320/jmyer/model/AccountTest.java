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
		account1 = new Account("JimBob", "1234");
		account2 = new Account("BillyBob", "12bfe9");
		
	}
	
	@Test
	public void testIsPasswordCorrect() {
		assertTrue(account1.isPasswordCorrect("1234"));
		assertFalse(account1.isPasswordCorrect("3245"));
		assertTrue(account2.isPasswordCorrect("12bfe9"));
		assertFalse(account2.isPasswordCorrect("odsouasd"));
	}
	
	@Test
	public void testGetAccountName() {
		assertTrue(account1.getAccountName().equals("JimBob"));
		assertFalse(account1.getAccountName().equals("NotJimBob"));
		assertTrue(account2.getAccountName().equals("BillyBob"));
		assertFalse(account2.getAccountName().equals("JimBOB"));
	}
	
}