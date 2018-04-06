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
		//sKiser,Sam,Kiser,903-208-104,kEdP3AAS,skiser@ycp.edu,845-181-2578,13.25 ,22.0, user
		String userName = "sKiser";
		List<Account> accountList;
		accountList = db.findAccountbyUserName(userName);
		assertEquals(userName, "sKiser");
		Account account = accountList.get(0);
		assertTrue(account.getUserName().equals("sKiser"));
		assertTrue(account.getFirstName().equals("Sam"));
		assertTrue(account.getLastName().equals("Kiser"));
		assertTrue(account.getStudentID().equals("903-208-104"));
		assertTrue(account.getPassword().equals("kEdP3AAS"));
		assertTrue(account.getEmail().equals("skiser@ycp.edu"));
		assertTrue(account.getPhoneNumber().equals("845-181-2578"));
		assertTrue(account.getAdminStatus().equals("user"));
		assertTrue(account.getAccountId() == 2);
		assertTrue(account.getFlex() == 13.25);
		assertTrue(account.getDining() == 22.0);
	}
	
	@Test
	public void testFindAccountbyFirstName() throws SQLException {
		//cTeich,Chase,Teichmann,903-202-533,sKFpeVhc,cteichmann@ycp.edu,235-256-2783,141.71,175.75, user
		String firstName = "Chase";
		
		List<Account> accountList;
		accountList = db.findAccountbyFirstName(firstName);
		assertEquals(1, accountList.size());
		Account account = accountList.get(0);
		assertTrue(account.getFirstName().equals("Chase"));
	}
	@Test
	public void testFindAccountbyLastName() throws SQLException {
		//cTeich,Chase,Teichmann,903-202-533,sKFpeVhc,cteichmann@ycp.edu,235-256-2783,141.71,175.75, user
		String lastName = "Teichmann";
		List<Account> accountList;
		accountList = db.findAccountbyLastName(lastName);
		assertEquals(1, accountList.size());
		Account account = accountList.get(0);
		assertTrue(account.getLastName().equals("Teichmann"));
	}
		
	@Test
	public void testFindAccountbyAccountID() throws SQLException {
		//cTeich,Chase,Teichmann,903-202-533,sKFpeVhc,cteichmann@ycp.edu,235-256-2783,141.71 ,175.75, user
		
		List<Account> accountList;
		accountList = db.findAccountbyAccountID(1);
		assertEquals(1, accountList.size());
		Account account = accountList.get(0);
		assertTrue(account.getAccountId() == 1);
	}
	
	@Test
	public void testFindAccountbyStudentID() throws SQLException {
		//cTeich,Chase,Teichmann,903-202-533,sKFpeVhc,cteichmann@ycp.edu,235-256-2783,141.71 ,175.75, user,1
		List<Account> accountList;
		accountList = db.findAccountbyStudentID("903-202-533");
		assertEquals(1, accountList.size());
		Account account = accountList.get(0);
		assertTrue(account.getStudentID().equals("903-202-533"));
	}
	
	@Test
	public void testFindAccountbyAdminStatus() throws SQLException {
		//admin	password	nulll	null	null	null	null	0	0	admin, 15
		List<Account> accountList;
		accountList = db.findAccountbyAdminStatus("admin");
		assertEquals(1, accountList.size());
		Account account = accountList.get(0);
		assertTrue(account.getUserName().equals("admin"));
		assertTrue(account.getFirstName().equals("null"));
		assertTrue(account.getLastName().equals("null"));
		assertTrue(account.getStudentID().equals("null"));
		assertTrue(account.getPassword().equals("password"));
		assertTrue(account.getEmail().equals("null"));
		assertTrue(account.getPhoneNumber().equals("null"));
		assertTrue(account.getAdminStatus().equals("admin"));
		assertTrue(account.getAccountId() == 15);
		assertTrue(account.getFlex() == 0.0);
		assertTrue(account.getDining() == 0.0);
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
	
	@Test
	public void testFindAllItems() throws SQLException {
		assertTrue(db.findAllItems().size() > 1);
	}
	
	@Test
	public void testFindAllDrinks() throws SQLException {
		assertTrue(db.findAllDrinks().size() > 1);
	}


}
