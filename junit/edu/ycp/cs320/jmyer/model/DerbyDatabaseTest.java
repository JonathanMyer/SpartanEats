package edu.ycp.cs320.jmyer.model;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.spartaneats.model.Account;
import edu.ycp.cs320.spartaneats.model.Condiments;
import edu.ycp.cs320.spartaneats.model.Item;
import edu.ycp.cs320.spartaneats.model.Order;
import edu.ycp.cs320.spartaneats.model.OrderItem;
import edu.ycp.cs320.spartaneats.persist.DerbyDatabase;


public class DerbyDatabaseTest {
	private DerbyDatabase db;
	private OrderItem SampleOrderItem;
	private ArrayList<Integer>ItemCondimentList = new ArrayList<Integer>();

	@Before
	public void setUp() throws Exception {
		db = new DerbyDatabase();
		ItemCondimentList.add(2);
		ItemCondimentList.add(4);
		ItemCondimentList.add(6);
		SampleOrderItem = new OrderItem(5, 3, 2, ItemCondimentList);
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

		assertTrue(account.getPassword().equals("testing"));
		assertTrue(account.getEmail().equals("skiser@ycp.edu"));
		assertTrue(account.getPhoneNumber().equals("845-181-2578"));
		assertTrue(account.getAdminStatus().equals("user"));
		assertTrue(account.getAccountId() == 3);

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
		assertTrue(account.getFirstName().equals("TEST"));
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
	public void testFindItembyName() throws SQLException {
		// Caprese,6.49
		String name = "Powerade";
		List<Item> itemList;
		Item item =  db.findItembyName(name);
		assertTrue(item.getItemName().equals("Powerade"));
	}

	@Test
	public void testFindAllItems() throws SQLException {
		assertTrue(db.findAllItems().size() > 1);
	}
	@Test
	public void testCreateOrder() throws SQLException{
		db.createOrder(4, "false");
		db.createOrder(4, "false");
		assertTrue(db.findOrdersFromUsername("jMyer").size() > 0);
		System.out.println(db.findOrdersFromUsername("jMyer").size());
	}

	@Test
	public void testFindItemByType() throws SQLException{
		assertTrue(db.findItembyType("Drink").get(0).getItemName().equals("Dasani Water"));
		System.out.println("Testing findItemByType(''Drink'')");
		for(Item i : db.findItembyType("Drink")) {
			System.out.println(i.getItemName());
		}
		assertTrue(db.findItembyType("Mexican").get(0).getItemName().equals("Burrito"));
	}


	@Test
	public void testAddItemToOrder() throws SQLException{
		List<Condiments> conds = null;
		try{
			conds = db.findAllCondiments();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Integer> conds_id = new ArrayList<Integer>();
		conds_id.add(1);
		conds_id.add(2);
		conds_id.add(3);

		try {
			db.addItemToOrder(1, 2, 1, conds_id);
			db.addItemToOrder(2, 3, 1, conds_id);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		db.findOrderItemsFromOrderID(1);
	}

	@Test
	public void testRemoveItemFromOrder() throws SQLException{
		try {
			int order_id = db.createOrder(4, "deliver");
			List<Integer> condList = new ArrayList<Integer>();
			condList.add(3);
			condList.add(4);
			condList.add(7);
			db.addItemToOrder(order_id, 4, 1, condList);
			db.addItemToOrder(order_id, 6, 1, new ArrayList<Integer>(0));
			List<OrderItem> orderItemList = db.findOrderItemsFromOrderID(order_id);
			assertTrue(orderItemList.size() == 2);
			db.removeItemFromOrder(orderItemList.get(1));
			orderItemList = db.findOrderItemsFromOrderID(order_id);
			assertTrue(orderItemList.size() == 1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		db.findOrderItemsFromOrderID(1);
	}

	@Test
	public void testActiveOrders(){
		try {
			int order_id = db.createOrder(4, "delivery");
			int active = db.updateOrderToActive(order_id);
			List<Order> activeOrders = db.findActiveOrders();
			assertTrue(activeOrders.size() == 1);
			db.updateOrderToInActive(order_id);
			activeOrders = db.findActiveOrders();
			assertTrue(activeOrders.size() == 0);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
