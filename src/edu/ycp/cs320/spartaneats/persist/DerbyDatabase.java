package edu.ycp.cs320.spartaneats.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import edu.ycp.cs320.spartaneats.model.Account;
import edu.ycp.cs320.spartaneats.model.Condiments;
import edu.ycp.cs320.spartaneats.model.Item;
import edu.ycp.cs320.spartaneats.model.Order;
import edu.ycp.cs320.spartaneats.model.OrderItem;
import edu.ycp.cs320.spartaneats.persist.DerbyDatabase.Transaction;
import edu.ycp.cs320.sqldemo.DBUtil;

public class DerbyDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}

	public interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;


	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();

		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;

			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}

			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}

			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");

		// Set autocommit to false to allow execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);

		return conn;
	}

	private void loadAccount(Account account, ResultSet resultSet, int index) throws SQLException {
		account.setUserName(resultSet.getString(index++));
		account.setFirstName(resultSet.getString(index++));
		account.setLastName(resultSet.getString(index++));
		account.setStudentID(resultSet.getString(index++));
		account.setPassword(resultSet.getString(index++));
		account.setEmail(resultSet.getString(index++));
		account.setPhoneNumber(resultSet.getString(index++));
		account.setAdminStatus(resultSet.getString(index++));
		account.setAccountId(resultSet.getInt(index++));
		account.setFlex(resultSet.getDouble(index++));
		account.setDining(resultSet.getDouble(index++));
		
	}

	private void loadItem(Item item, ResultSet resultSet, int index) throws SQLException {
		item.setItemType(resultSet.getString(index++));
		item.setItemName(resultSet.getString(index++));
		item.setPrice(resultSet.getDouble(index++));	
		item.setCondiments(resultSet.getString(index++));
		item.setItemId(resultSet.getInt(index++));
	}
	
	private void loadOrder(Order order, ResultSet resultSet, int index) throws SQLException {
		order.setOrderId(resultSet.getInt(index++));
		order.setDelivery(Boolean.valueOf(resultSet.getString(index++)));
		order.setAccount_id(resultSet.getInt(index++));
		if(resultSet.getInt(index++) == 0) {
			order.setActiveFalse();
		}else {
			order.setActiveTrue();
		}
		order.setDeliveryDest(resultSet.getString(index++));
	}
	
	private void loadCondiment(Condiments condiment, ResultSet resultSet, int index) throws SQLException {
		condiment.setCondType(resultSet.getString(index++));
		condiment.setCondName(resultSet.getString(index++));
		condiment.setCondID(resultSet.getInt(index++));
	}
	
	private void loadOrderItem(OrderItem orderItem, ResultSet resultSet, int index) throws SQLException {
		orderItem.setOrder_id(resultSet.getInt(index++));
		orderItem.setItem_id(resultSet.getInt(index++));
		orderItem.setAmount(resultSet.getInt(index++));
		String temps = resultSet.getString(index++);
		String[] temp = temps.split(",");
		for (String s: temp) {
			if (s.length()>0) {
				orderItem.addCondiment_idToList(Integer.parseInt(s));
			}
			
		}
		
		
	}
	
	public void dropTables() throws SQLException {
		doExecuteTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement dropAccounts = null;
				PreparedStatement dropItems = null;
				PreparedStatement dropCondiments = null;
				PreparedStatement dropOrders = null;
				PreparedStatement dropOrderItems = null;
				PreparedStatement dropOrderAccounts = null;


				try { 

					dropAccounts = conn.prepareStatement 
							( "drop table accounts" ); 
					dropAccounts.execute(); 
					dropAccounts.close(); 
	
					dropItems = conn.prepareStatement 
							( "drop table Items" ); 
					dropItems.execute(); 
					dropItems.close();
					dropCondiments = conn.prepareStatement 
							( "drop table condiments" ); 
					dropCondiments.execute(); 
					dropCondiments.close();
					System.out.println("dropping orders table" );
					dropOrders = conn.prepareStatement
							( "drop table orders" );
					dropOrders.execute();
					dropOrders.close();
					System.out.println("dropping orderitems table" );
					dropOrderItems = conn.prepareStatement
							( "drop table orderitem");
					dropOrderItems.execute();
					dropOrderItems.close();


					return true;
				} catch (Exception ex) {
					return true;
				} finally {
					DBUtil.closeQuietly(dropAccounts);
					DBUtil.closeQuietly(dropItems);
					DBUtil.closeQuietly(dropCondiments);
					DBUtil.closeQuietly(dropOrders);
					DBUtil.closeQuietly(dropOrderAccounts);
					DBUtil.closeQuietly(dropOrderItems);
				}				

			};
		});
	}

	public void createTables() throws SQLException {
		doExecuteTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement createAccountTable = null;
				PreparedStatement createItemTable = null;
				PreparedStatement createCondimentTable = null;
				PreparedStatement createOrdersTable = null;
				PreparedStatement createOrderItemTable = null;
				PreparedStatement createOrderAccountTable = null;

				try {
					
					
					createAccountTable = conn.prepareStatement(
							"create table accounts (" +									
									"	userName varchar(40)," +
									"	firstName varchar(40)," +
									"	lastName varchar(40)," +
									"   studentID varchar(40)," +
									"   password varchar(40)," +
									"   email varchar(40)," +
									"   phoneNumber varchar(40)," +
									"	adminStatus varchar(10)," +
									"	account_id integer, " +
									"   flex double," +
									"   dining double" +
									")"
							);	
					createAccountTable.executeUpdate();
					
					createItemTable= conn.prepareStatement(
							"create table Items (" +
									"   itemType varchar(40)," +
									"	itemName varchar(40)," +
									"   price float, " +
									"   condiments varchar(40)," +
									"	itemId int " +
									")"
							);
					createItemTable.executeUpdate(); 	
					
					createCondimentTable= conn.prepareStatement(
							"create table condiments (" +
									"   condType varchar(40)," +
									"	condName varchar(40)," +
									"	condID integer " +
									")"
							);

					
					createCondimentTable.executeUpdate(); 
					createOrdersTable = conn.prepareStatement(
							"create table orders (" +
									" order_id integer not null  "  + 
									"		generated always as identity (start with 1, increment by 1), " +
									
									" delivery varChar(40)," +
									" account_id integer, " +
									" CONSTRAINT primary_key PRIMARY KEY (order_id)," +
									" active integer," +
									" deliveryDest varChar(40)" +
									")"
							);
					createOrdersTable.executeUpdate();
					
					createOrderItemTable = conn.prepareStatement(
							"create table orderitem ("+
									" order_id int, " +
									" item_id int, " +
									" amount int, " + 
									" condiments varChar(1000) " +
									")"
							);
					createOrderItemTable.executeUpdate();
					return true;
				} finally {
					DBUtil.closeQuietly(createAccountTable);
					DBUtil.closeQuietly(createItemTable);
					DBUtil.closeQuietly(createCondimentTable);
					DBUtil.closeQuietly(createOrdersTable);
					DBUtil.closeQuietly(createOrderItemTable);
					DBUtil.closeQuietly(createOrderAccountTable);
				}
			}
		});
	}

	public void loadInitialData() throws SQLException {
		doExecuteTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<Account> accountList;
				List<Item> itemList;
				List<Condiments> condimentList;
				
				try {
					accountList = InitialData.getAccount();
					itemList = InitialData.getItem();
					condimentList = InitialData.getCondiment();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertAccount = null;
				PreparedStatement insertItem   = null;
				PreparedStatement insertCondiment = null;

				try {
					// populate accounts table (do accounts first, since account_id is foreign key in drink table)
					insertAccount = conn.prepareStatement("insert into accounts (userName, firstname, lastName, studentID, password, email, phoneNumber, adminStatus, account_Id, flex, dining) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (Account account : accountList) {

						System.out.println(account.getUserName());

						insertAccount.setString(1, account.getUserName());
						insertAccount.setString(2, account.getFirstName());
						insertAccount.setString(3, account.getLastName());
						insertAccount.setString(4, account.getStudentID());
						insertAccount.setString(5, account.getPassword());
						insertAccount.setString(6, account.getEmail());
						insertAccount.setString(7, account.getPhoneNumber());
						insertAccount.setString(8, account.getAdminStatus());
						insertAccount.setInt(9, account.getAccountId());
						insertAccount.setDouble(10, account.getFlex());
						insertAccount.setDouble(11, account.getDining());
						

						insertAccount.addBatch();
					}
					insertAccount.executeBatch();

					DatabaseMetaData databaseMetaData = conn.getMetaData();
					ResultSet set = databaseMetaData.getColumns(null, null,"ACCOUNTS", null);

					while(set.next()){
						System.out.println(set.getString("COLUMN_NAME"));
						System.out.printf(", ");

					}

					// populate item table 
					insertItem = conn.prepareStatement("insert into items (itemType, itemName, price, condiments, itemId) values (?, ?, ?, ?, ?)");
					for (Item item : itemList) {
						insertItem.setString(1, item.getItemType());
						insertItem.setString(2, item.getItemName());
						insertItem.setDouble(3, item.getPrice());
						insertItem.setString(4, item.getCondiments());
						insertItem.setInt(5, item.getItemId());
						insertItem.addBatch();
					}
					insertItem.executeBatch();
					
					// populate item table 
					insertCondiment = conn.prepareStatement("insert into condiments (CondType, CondName, CondID) values (?, ?, ?)");
					for (Condiments condiment : condimentList) {
						insertCondiment.setString(1, condiment.getCondType());
						insertCondiment.setString(2, condiment.getCondName());
						insertCondiment.setInt(3, condiment.getCondID());
						insertCondiment.addBatch();
					}
					insertCondiment.executeBatch();
					return true;
				} finally {
					DBUtil.closeQuietly(insertAccount);
					DBUtil.closeQuietly(insertItem);
					DBUtil.closeQuietly(insertCondiment);
				}
			}
		});
	}
	public List<Account> findAllAccounts() throws SQLException {
		return doExecuteTransaction(new Transaction<List<Account>>() {
			public List<Account> execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement("select * from accounts");
					List<Account> result = new ArrayList<Account>();
					resultSet = stmt.executeQuery();

					//for testing that a result was returned
					Boolean found = false;

					while (resultSet.next()) {
						found = true;

						//retrieve attributes from resultSet starting with index 1
						Account account = new Account();
						loadAccount(account, resultSet, 1);
						result.add(account);
					}
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public List<Account> findAccountbyUserName(String userName) throws SQLException {
		return doExecuteTransaction(new Transaction<List<Account>>() {
			public List<Account> execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes from both Books and Authors tables

					stmt = conn.prepareStatement(
							"select accounts.* from accounts where accounts.userName = ?"
							);
					stmt.setString(1, userName);

					List<Account> result = new ArrayList<Account>();
					resultSet = stmt.executeQuery();

					//for testing that a result was returned
					Boolean found = false;

					while (resultSet.next()) {
						found = true;

						//retrieve attributes from resultSet starting with index 1
						Account account = new Account();
						loadAccount(account, resultSet, 1);
						result.add(account);
					}

					//check if the title was found
					if (!found) {
						System.out.println("<" +userName+ "> - UserName wasn't found in the accounts table");

					}
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public List<Account> findAccountbyFirstName(String firstName) throws SQLException {
		return doExecuteTransaction(new Transaction<List<Account>>() {
			public List<Account> execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes from both Books and Authors tables

					stmt = conn.prepareStatement(
							"select accounts.* from accounts where accounts.firstName = ?"
							);
					stmt.setString(1, firstName);
					

					List<Account> result = new ArrayList<Account>();
					resultSet = stmt.executeQuery();

					//for testing that a result was returned
					Boolean found = false;

					while (resultSet.next()) {
						found = true;

						//retrieve attributes from resultSet starting with index 1
						Account account = new Account();
						loadAccount(account, resultSet, 1);
						result.add(account);
					}

					//check if the title was found
					if (!found) {
						System.out.println("< " + firstName + "> -Firstname wasn't found in the accounts table");

					}
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	public List<Account> findAccountbyLastName(String lastName) throws SQLException {
		return doExecuteTransaction(new Transaction<List<Account>>() {
			public List<Account> execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes from both Books and Authors tables

					stmt = conn.prepareStatement(
							"select accounts.* from accounts where accounts.lastName = ?"
							);
					stmt.setString(1, lastName);
					

					List<Account> result = new ArrayList<Account>();
					resultSet = stmt.executeQuery();

					//for testing that a result was returned
					Boolean found = false;

					while (resultSet.next()) {
						found = true;

						//retrieve attributes from resultSet starting with index 1
						Account account = new Account();
						loadAccount(account, resultSet, 1);
						result.add(account);
					}

					//check if the title was found
					if (!found) {
						System.out.println("< " +lastName + "> -Lastname wasn't found in the accounts table");

					}
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	public List<Account> findAccountbyStudentID(String ID) throws SQLException {
		return doExecuteTransaction(new Transaction<List<Account>>() {
			public List<Account> execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes from both Books and Authors tables

					stmt = conn.prepareStatement(
							"select accounts.* from accounts where accounts.studentID = ? "
							);
					stmt.setString(1, ID);

					List<Account> result = new ArrayList<Account>();
					resultSet = stmt.executeQuery();

					//for testing that a result was returned
					Boolean found = false;

					while (resultSet.next()) {
						found = true;

						//retrieve attributes from resultSet starting with index 1
						Account account = new Account();
						loadAccount(account, resultSet, 1);
						result.add(account);
					}

					//check if the title was found
					if (!found) {
						System.out.println("<" + ID + "> wasn't found in the accounts table");

					}
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public List<Account> findAccountbyAdminStatus(String status) throws SQLException {
		return doExecuteTransaction(new Transaction<List<Account>>() {
			public List<Account> execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes from both Books and Authors tables
					stmt = conn.prepareStatement(

							"select accounts.*"+
									" from accounts " +
									"where accounts.adminStatus = ?"
							);
					stmt.setString(1, status);


					List<Account> result = new ArrayList<Account>();
					resultSet = stmt.executeQuery();

					//for testing that a result was returned
					Boolean found = false;

					while (resultSet.next()) {
						found = true;

						//retrieve attributes from resultSet starting with index 1
						Account account = new Account();
						loadAccount(account, resultSet, 1);
						result.add(account);
					}

					System.out.printf("Testing Admin output:", status);
					//check if the title was found
					if (!found) {
						System.out.println("<" +status+ "> - AdminStatus wasn't found in the accounts table");



					}
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	public List<Account> findAccountbyAccountID(int iD) throws SQLException {
		return doExecuteTransaction(new Transaction<List<Account>>() {
			public List<Account> execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes from both Books and Authors tables
					stmt = conn.prepareStatement(
							"select accounts.*"+
									" from accounts " +
									"where accounts.account_id = ?"
							);
					stmt.setInt(1, iD);

					List<Account> result = new ArrayList<Account>();
					resultSet = stmt.executeQuery();

					//for testing that a result was returned
					Boolean found = false;

					while (resultSet.next()) {
						found = true;

						//retrieve attributes from resultSet starting with index 1
						Account account = new Account();
						loadAccount(account, resultSet, 1);
						result.add(account);

					}

					//check if the title was found
					if (!found) {
						System.out.println("<" +iD+ "> -Id wasn't found in the accounts table");

					}
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public Item findItembyName(String name) throws SQLException {
		return doExecuteTransaction(new Transaction<Item>() {
			public Item execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"select items.*"+
									" from items " +
									"where items.itemName = ?"
							);
					stmt.setString(1, name);


					resultSet = stmt.executeQuery();

					

					//retrieve attributes from resultSet starting with index 1
					Item item = new Item();
					if(resultSet.next()) {
						loadItem(item, resultSet, 1);
					}
					return item;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public Item findItembyItemID(int item_id) throws SQLException {
		return doExecuteTransaction(new Transaction<Item>() {
			public Item execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"select items.*"+
									" from items " +
									"where items.itemId = ?"
							);
					stmt.setInt(1, item_id);


					resultSet = stmt.executeQuery();

					

					//retrieve attributes from resultSet starting with index 1
					Item item = new Item();
					if (resultSet.next()){
						loadItem(item, resultSet, 1);
					}

					

					

					
					return item;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public Condiments findCondimentsbyCondimentID(int condiment_id) throws SQLException {
		return doExecuteTransaction(new Transaction<Condiments>() {
			public Condiments execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"select condiments.*"+
									" from condiments " +
									"where condiments.condID = ?"
							);
					stmt.setInt(1, condiment_id);

					resultSet = stmt.executeQuery();
					//retrieve attributes from resultSet starting with index 1
					Condiments cond = new Condiments();
					if (resultSet.next()) {
						loadCondiment(cond, resultSet, 1);
					}			
					return cond;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public Condiments findCondimentsbyCondimentName(String condName) throws SQLException {
		return doExecuteTransaction(new Transaction<Condiments>() {
			public Condiments execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"select condiments.*"+
									" from condiments " +
									"where condiments.condName = ?"
							);
					stmt.setString(1, condName);


					resultSet = stmt.executeQuery();

					

					//retrieve attributes from resultSet starting with index 1
					Condiments cond = new Condiments();
					
					if (resultSet.next()) {
						loadCondiment(cond, resultSet, 1);
						System.out.println(cond.getCondName());

					}

					

					

					
					return cond;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public int updateOrderToActive(int order_id) throws SQLException {
		return doExecuteTransaction(new Transaction<Integer>() {
			public Integer execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"update orders"+
									" set orders.active = ? " +
									"where orders.order_id = ?"
							);
					stmt.setInt(1, 1);
					stmt.setInt(2, order_id);

					int updateCount = stmt.executeUpdate();
					return updateCount;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				
				
				
			}
		});
	}
	
	public int updateOrderToInActive(int order_id) throws SQLException {
		return doExecuteTransaction(new Transaction<Integer>() {
			public Integer execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"update orders"+
									" set orders.active = ? " +
									"where orders.order_id = ?"
							);
					stmt.setInt(1, 0);
					stmt.setInt(2, order_id);

					int updateCount = stmt.executeUpdate();
					return updateCount;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				
				
				
			}
		});
	}
	
	public List<Item> findItembyType(String type) throws SQLException {
		return doExecuteTransaction(new Transaction<List<Item>>() {
			public List<Item> execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"select items.*"+
									" from items " +
									"where items.itemType = ?"
							);
					stmt.setString(1, type);


					List<Item> result = new ArrayList<Item>();
					resultSet = stmt.executeQuery();

					//for testing that a result was returned
					Boolean found = false;

					while (resultSet.next()) {
						found = true;

						//retrieve attributes from resultSet starting with index 1
						Item item = new Item();
						loadItem(item, resultSet, 1);
						result.add(item);

					}

					//check if the title was found
					if (!found) {
						System.out.println("<" + type + "> -Item type wasn't found in the item table");

					}
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public List<Condiments> findCondimentbyType(String type) throws SQLException {
		return doExecuteTransaction(new Transaction<List<Condiments>>() {
			public List<Condiments> execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"select condiments.*"+
									" from condiments " +
									"where condiments.condType = ?"
							);
					stmt.setString(1, type);


					List<Condiments> result = new ArrayList<Condiments>();
					resultSet = stmt.executeQuery();

					//for testing that a result was returned
					Boolean found = false;

					while (resultSet.next()) {
						found = true;

						//retrieve attributes from resultSet starting with index 1
						Condiments cond = new Condiments();
						loadCondiment(cond, resultSet, 1);
						result.add(cond);

					}

					//check if the title was found
					if (!found) {
						System.out.println("<" + type + "> -Condiment type wasn't found in the item table");

					}
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public List<Item> findAllItems() throws SQLException {
		return doExecuteTransaction(new Transaction<List<Item>>() {
			public List<Item> execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"select * from items "
							);

					List<Item> result = new ArrayList<Item>();
					resultSet = stmt.executeQuery();

					//for testing that a result was returned
					Boolean found = false;

					while (resultSet.next()) {
						found = true;

						//retrieve attributes from resultSet starting with index 1
						Item item = new Item();
						loadItem(item, resultSet, 1);
						result.add(item);

					}
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public List<Condiments> findAllCondiments() throws SQLException {
		return doExecuteTransaction(new Transaction<List<Condiments>>() {
			public List<Condiments> execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"select condiments.*"+
									" from condiments "
							);

					List<Condiments> result = new ArrayList<Condiments>();
					resultSet = stmt.executeQuery();

					//for testing that a result was returned
					Boolean found = false;

					while (resultSet.next()) {
						found = true;

						//retrieve attributes from resultSet starting with index 1
						Condiments condiment = new Condiments();
						loadCondiment(condiment, resultSet, 1);
						result.add(condiment);

					}
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public int createOrder(int account_id, String delivery, String deliveryDest) throws SQLException {
		return doExecuteTransaction(new Transaction<Integer>() {
			public Integer execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet = null;
				String genId[] = { "order_id" };
				
				
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"insert into orders (delivery, account_id, active, deliveryDest)" +
									"values (?, ?, ?, ?)" 
							, stmt.RETURN_GENERATED_KEYS);
					stmt.setString(1, delivery);
					stmt.setInt(2, account_id);
					int active = 0;
					stmt.setInt(3, active);
					stmt.setString(4, deliveryDest);
					stmt.executeUpdate();
					
					resultSet = stmt.getGeneratedKeys();
					int order_id = 0;
					if(resultSet.next()) {
						order_id = resultSet.getInt(1);
						System.out.println("Order ID:" + order_id);
					}
					
					
					return order_id;
					
					
					
				}	
				finally {
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt);
				}				
			}
			
			
		
		});
	}
	
	public List<Order> findActiveOrders() throws SQLException {
		return doExecuteTransaction(new Transaction<List<Order>>() {
			public List<Order> execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"select orders.* " +
									"from orders " +
									"where orders.active = ?"
							);
					stmt.setInt(1, 1);
					List<Order> result = new ArrayList<Order>();
					resultSet = stmt.executeQuery();
					//for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;

						//retrieve attributes from resultSet starting with index 1
						Order order = new Order();
						loadOrder(order, resultSet, 1);
						result.add(order);
					}
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public List<Order> findOrdersFromUsername(String userName) throws SQLException {
		return doExecuteTransaction(new Transaction<List<Order>>() {
			public List<Order> execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"select orders.* " +
									"from accounts, orders " +
									"where orders.account_id = accounts.account_id and " +
									"accounts.Username = ?"
							);
					stmt.setString(1, userName);
					


					List<Order> result = new ArrayList<Order>();
					resultSet = stmt.executeQuery();
					//for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;

						//retrieve attributes from resultSet starting with index 1
						Order order = new Order();
						loadOrder(order, resultSet, 1);
						result.add(order);
					}
					
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}	
	
	public Order findOrderFromOrderId(int order_id) throws SQLException {
		return doExecuteTransaction(new Transaction<Order>() {
			public Order execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"select orders.*"+
									" from orders " +
									"where orders.order_id = ?"
							);
					stmt.setInt(1, order_id);


					resultSet = stmt.executeQuery();

					

					//retrieve attributes from resultSet starting with index 1
					Order order = new Order();
					if(resultSet.next()) {
						loadOrder(order, resultSet, 1);
					}
					return order;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public List<Order> findOrdersFromAccountID(int account_id) throws SQLException {
		return doExecuteTransaction(new Transaction<List<Order>>() {
			public List<Order> execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"select orders.* " +
									"from accounts, orders " +
									"where orders.account_id = accounts.account_id and " +
									"accounts.account_id = ?"
							);
					stmt.setInt(1, account_id);
					
					


					List<Order> result = new ArrayList<Order>();
					resultSet = stmt.executeQuery();
					//for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;

						//retrieve attributes from resultSet starting with index 1
						Order order = new Order();
						loadOrder(order, resultSet, 1);
						result.add(order);
					}
					
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public List<Order> addItemToOrder(int order_id, int item_id, int amount, List<Integer> conds_id) throws SQLException {
		return doExecuteTransaction(new Transaction<List<Order>>() {
			public List<Order> execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				String condimentsString = new String();
				
				for(Integer i: conds_id) {
					condimentsString += i.toString() + ",";
				}
				
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"insert into orderitem (order_id, item_id, amount, condiments)" +
									"values (?, ?, ?, ?)" 
							);
					stmt.setInt(1, order_id);
					stmt.setInt(2, item_id);
					stmt.setInt(3, amount);
					stmt.setString(4, condimentsString);

					stmt.executeUpdate();

					return null;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public Integer removeItemFromOrder(OrderItem orderItem) throws SQLException {
		return doExecuteTransaction(new Transaction<Integer>() {
			public Integer execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;			
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"delete from orderitem "+
									"where orderitem.order_id = ? and "+
									"orderitem.item_id = ?"
							);
					stmt.setInt(1, orderItem.getOrder_id());
					System.out.println("OrderItem Order ID: " + orderItem.getOrder_id());
					System.out.println("OrderItem Item ID: " + orderItem.getItem_id());
					stmt.setInt(2, orderItem.getItem_id());
					int updateCount = stmt.executeUpdate();
					return updateCount;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public List<OrderItem> findOrderItemsFromOrderID(int order_id) throws SQLException {
		return doExecuteTransaction(new Transaction<List<OrderItem>>() {
			public List<OrderItem> execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"select orderitem.* " +
									"from orderitem " +
									"where orderitem.order_id = ?" 
									

							);
					stmt.setInt(1, order_id);
					


					List<OrderItem> result = new ArrayList<OrderItem>();
					resultSet = stmt.executeQuery();
					//for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;

						//retrieve attributes from resultSet starting with index 1
						OrderItem orderItem = new OrderItem();
						loadOrderItem(orderItem, resultSet, 1);
						result.add(orderItem);
					}
					
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException, SQLException {
		DerbyDatabase db = new DerbyDatabase();
		System.out.println("Attempting to Drop Tables...");
		db.dropTables();
		System.out.println("Successful Table Drop");
		
		System.out.println("Attempting to Create Tables...");
		db.createTables();
		System.out.println("Successful Table Creation");
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		System.out.println("Data Loaded");
		System.out.println("Derby Database Main Complete");

	}
}