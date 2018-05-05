package edu.ycp.cs320.jmyer.model;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.DecimalFormat;
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
	private Order order;
	DecimalFormat df = new DecimalFormat("#.##");

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
	public void testUpdateDining() throws SQLException{
		double price = 7.90;
		int account_id = 4;
		try {
			List<Account> accountList = db.findAccountbyAccountID(account_id);
			double before = accountList.get(0).getDining();
			System.out.println("DINING BEFORE:" + before);
			double flex = db.updateDiningBalance(price, account_id);
			List<Account> accountList2 = db.findAccountbyAccountID(account_id);
			Account account = accountList2.get(0);
			System.out.println("DINING SET BALANCE:" + account.getDining());
			assertTrue(account.getDining() == Double.parseDouble(df.format(before - price)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testUpdateFlex() throws SQLException{
		double price = 7.90;
		int account_id = 4;
		try {
			List<Account> accountList = db.findAccountbyAccountID(account_id);
			double before = accountList.get(0).getFlex();
			double flex = db.updateFlexBalance(price, account_id);
			List<Account> accountList2 = db.findAccountbyAccountID(account_id);
			Account account = accountList2.get(0);
			System.out.println("DINING SET BALANCE:" + account.getFlex());
			assertTrue(account.getFlex() == Double.parseDouble(df.format(before - price)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testfindFlex() throws SQLException{
		int account_id = 4;
		try {
			List<Account> accountList = db.findAccountbyAccountID(account_id);
			double flex = accountList.get(0).getFlex();
			double flexTest = db.findFlexBalance(account_id);
			assertTrue(flex == flexTest);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testfindDining() throws SQLException{
		int account_id = 4;
		try {
			List<Account> accountList = db.findAccountbyAccountID(account_id);
			double dining = accountList.get(0).getDining();
			double diningTest = db.findDiningBalance(account_id);
			assertTrue(dining == diningTest);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		db.createOrder(4, "false", "China");
		db.createOrder(4, "false", "India");
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
			int order_id = db.createOrder(4, "true","China");
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
	public void testFindOrderbyAccountID() {
		try {	
			int sub = db.findOrdersFromAccountID(5).size();
			db.createOrder(5, "true", "China");
			List<Order> orders = db.findOrdersFromAccountID(5);
			System.out.println("Orders size: "+orders.size());
			assertTrue((orders.size()-sub) == 1);
			assertTrue(orders.get(0).getAccountId() == 5);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindOrderbyUserName() {
		try {	
			List<Order> skiser = db.findOrdersFromUsername("skiser");
			System.out.println("skiser size" +skiser.size());
			//assertTrue((orders.size()-sub) == 1);
			assertTrue(skiser.size() == 0);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testActiveOrdersByUsername(){
		try {
			int order_id = db.createOrder(4, "delivery", "China");
			int active = db.updateOrderToActive(order_id);
			List<Order> activeOrders = db.findActiveOrders();
			assertTrue(activeOrders.size() == 1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindAllAcounts() {
		try {
			System.out.println("Number of Accounts" + db.findAllAccounts().size());
			assertTrue(db.findAllAccounts().size() == 15);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindAllCondiments() {
		try {
			assertTrue(db.findAllCondiments().size() == 72);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindItembyID() {
		try {
			assertTrue(db.findItembyItemID(1).getItemName().equals("Burrito"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCondimentsbyCondimentId() {
		try {
			assertTrue(db.findCondimentsbyCondimentID(1).getCondName().equals("Brown Rice"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCondimentsbyCondimentName() {
		try {
			assertTrue(db.findCondimentsbyCondimentName("Chicken").getCondID() == 4);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCondimentsbyType() {
		try {
			List<Condiments> mexican = db.findCondimentbyType("Mexican");
			System.out.println(mexican.size());
			assertTrue(mexican.size() == 26);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindOrderItemsFromOrderID() {
		int order_id;
		try {
			order_id = db.createOrder(4, "true", "China");
			List<Integer> condList = new ArrayList<Integer>();
			condList.add(3);
			condList.add(4);
			condList.add(7);
			db.addItemToOrder(order_id, 4, 1, condList);
			db.addItemToOrder(order_id, 6, 1, new ArrayList<Integer>(0));
			List<OrderItem> orderItemList = db.findOrderItemsFromOrderID(order_id);
			assertTrue(orderItemList.size() == 2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testActiveOrders(){
		try {
			int order_id = db.createOrder(4, "true", "China");
			List<Order> previous = db.findActiveOrders();
			int active = db.updateOrderToActive(order_id);
			List<Order> activeOrders = db.findActiveOrders();
			System.out.println(activeOrders.size());
			assertTrue(activeOrders.size() == previous.size() +1 );
			db.updateOrderToInActive(order_id);
			activeOrders = db.findActiveOrders();
			assertTrue(activeOrders.size() == previous.size());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLoadOrder() {
		try {
			int order_id = db.createOrder(4, "false", "China");
			Order order = db.findOrderFromOrderId(order_id);
			assertTrue(order.getAccountId() == 4);
			assertTrue(order.getDelivery() == false);
			assertTrue(order.getDeliveryDest().equals("China"));
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveCondimentFromOrderItem() {
		try {
			int order_number = db.createOrder(4, "false", "Africa");
			List<Integer> conds = new ArrayList<Integer>();
			conds.add(3);
			conds.add(6);
			conds.add(8);
			conds.add(23);
			db.addItemToOrder(order_number, 3, 1, conds);
			List<OrderItem> orderitem = db.findOrderItemsFromOrderID(order_number);
			System.out.print("testRemoveCondimentFromOrderItem: Current Items: ");
			for(int i: orderitem.get(0).getCondiment_id()) {
				System.out.print(i+",");
			}
			int lengthBefore = orderitem.get(0).getCondiment_id().size();
			System.out.println("");
			db.removeCondFromOrderItem(orderitem.get(0), 6);
			List<OrderItem> orderitem2 = db.findOrderItemsFromOrderID(order_number);
			System.out.print("testRemoveCondimentFromOrderItem: Current Items: ");
			for(int i2: orderitem2.get(0).getCondiment_id()) {
				System.out.print(i2+",");
			}
			int lengthAfter = orderitem2.get(0).getCondiment_id().size();
			System.out.println("");
			assertTrue(lengthBefore > lengthAfter);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}

