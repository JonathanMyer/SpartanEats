package edu.ycp.cs320.jmyer.model;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.spartaneats.model.Account;
import edu.ycp.cs320.spartaneats.model.Drink;
import edu.ycp.cs320.spartaneats.model.Item;
import edu.ycp.cs320.spartaneats.persist.DerbyDatabase;


public class DerbyDatabaseTest {
	private DerbyDatabase db;
	@Before
	public void setUp() throws Exception {
		db = new DerbyDatabase();
	
	}

	@Test
	public void testFindAccountbyUserName() throws SQLException {
		//sKiser,Sam,Kiser,903-208-104,kEdP3AAS,skiser@ycp.edu,845-181-2578,13.25 ,22.0
		String userName = "sKiser";
		List<Account> accountList;
		accountList = db.findAccountbyUserName(userName);
		assertEquals(1, accountList.size());
		Account account = accountList.get(0);
		assertTrue(account.getUserName().equals("sKiser"));
		assertTrue(account.getFirstName().equals("Sam"));
		assertTrue(account.getLastName().equals("Kiser"));
		assertTrue(account.getStudentID().equals("903-208-104"));
		assertTrue(account.getPassword().equals("kEdP3AAS"));
		assertTrue(account.getEmail().equals("skiser@ycp.edu"));
		assertTrue(account.getPhoneNumber().equals("845-181-2578"));
		assertTrue(account.getFlex() == 13.25);
		assertTrue(account.getDining() == 22.0);
		
		
		userName = "cTeich";
		accountList = db.findAccountbyUserName(userName);
		assertEquals(1, accountList.size());

	}
	
	@Test
	public void testFindAccountbyName() throws SQLException {
		//cTeich,Chase,Teichmann,903-202-533,sKFpeVhc,cteichmann@ycp.edu,235-256-2783,141.71,175.75
		String firstName = "Chase";
		String lastName = "Teichmann";
		
		List<Account> accountList;
		accountList = db.findAccountbyName(firstName, lastName);
		assertEquals(1, accountList.size());
		Account account = accountList.get(0);
		assertTrue(account.getUserName().equals("cTeich"));
		assertTrue(account.getFirstName().equals("Chase"));
		assertTrue(account.getLastName().equals("Teichmann"));
		assertTrue(account.getStudentID().equals("903-202-533"));
		assertTrue(account.getPassword().equals("sKFpeVhc"));
		assertTrue(account.getEmail().equals("cteichmann@ycp.edu"));
		assertTrue(account.getPhoneNumber().equals("235-256-2783"));
		assertTrue(account.getFlex() == 141.71);
		assertTrue(account.getDining() == 175.75);
		

	}
		
	@Test
	public void testFindAccountbyAccountID() throws SQLException {
		//cTeich,Chase,Teichmann,903-202-533,sKFpeVhc,cteichmann@ycp.edu,235-256-2783,141.71 ,175.75
		
		List<Account> accountList;
		accountList = db.findAccountbyAccountID(1);
		assertEquals(1, accountList.size());
		Account account = accountList.get(0);
		assertTrue(account.getUserName().equals("cTeich"));
		assertTrue(account.getLastName().equals("Teichmann"));
		assertTrue(account.getStudentID().equals("903-202-533"));
		assertTrue(account.getPassword().equals("sKFpeVhc"));
		assertTrue(account.getEmail().equals("cteichmann@ycp.edu"));
		assertTrue(account.getPhoneNumber().equals("235-256-2783"));
		assertTrue(account.getFlex() == 141.71);
		assertTrue(account.getDining() == 175.75);
	}
	
	@Test
	public void testFindAccountbyStudentID() throws SQLException {
		//cTeich,Chase,Teichmann,903-202-533,sKFpeVhc,cteichmann@ycp.edu,235-256-2783,141.71 ,175.75
		
		List<Account> accountList;
		accountList = db.findAccountbyStudentID("903-202-533");
		assertEquals(1, accountList.size());
		Account account = accountList.get(0);
		assertTrue(account.getUserName().equals("cTeich"));
		assertTrue(account.getLastName().equals("Teichmann"));
		assertTrue(account.getStudentID().equals("903-202-533"));
		assertTrue(account.getPassword().equals("sKFpeVhc"));
		assertTrue(account.getEmail().equals("cteichmann@ycp.edu"));
		assertTrue(account.getPhoneNumber().equals("235-256-2783"));
		assertTrue(account.getFlex() == 141.71);
		assertTrue(account.getDining() == 175.75);
	}
	

	@Test
	public void testFindDrinkbyName() throws SQLException {
		//Powerade,2.79
		String name = "Powerade";
		List<Drink> drinkList;
		drinkList = db.findDrinkbyName(name);
		assertEquals(1, drinkList.size());
		Drink drink = drinkList.get(0);
		assertTrue(drink.getItemName().equals("Powerade"));
		assertTrue(drink.getPrice() == 2.79);
	}


	@Test
	public void testFindItembyName() throws SQLException {
		// Caprese,6.49
		String name = "Caprese";
		List<Item> itemList;
		itemList = db.findItembyName(name);
		assertEquals(1, itemList.size());
		Item item = itemList.get(0);
		assertTrue(item.getItemName().equals("Caprese"));
		assertTrue(item.getPrice() == 6.49);
	}


}
