package edu.ycp.cs320.spartaneats.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.spartaneats.model.Account;
import edu.ycp.cs320.spartaneats.model.Drink;
import edu.ycp.cs320.spartaneats.model.Item;
import edu.ycp.cs320.spartaneats.persist.DerbyDatabase.Transaction;

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

	private void loadDrink(Drink drink, ResultSet resultSet, int index) throws SQLException {
		drink.setItemId(resultSet.getInt(index++));
		drink.setItemName(resultSet.getString(index++));
		drink.setPrice(resultSet.getDouble(index++));	
	}

	private void loadItem(Item item, ResultSet resultSet, int index) throws SQLException {
		item.setItemId(resultSet.getInt(index++));
		item.setItemName(resultSet.getString(index++));
		item.setPrice(resultSet.getDouble(index++));	
	}

	public void dropTables() throws SQLException {
		doExecuteTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement dropAccounts = null;
				PreparedStatement dropDrinks = null;
				PreparedStatement dropItems = null;
				try { 

					dropAccounts = conn.prepareStatement 
							( "drop table accounts" ); 
					dropAccounts.execute(); 
					dropAccounts.close(); 
					dropDrinks = conn.prepareStatement 
							( "drop table drink" ); 
					dropDrinks.execute(); 
					dropDrinks.close(); 
					dropItems = conn.prepareStatement 
							( "drop table item" ); 
					dropItems.execute(); 
					dropItems.close(); 
					return true;
				} catch (Exception ex) {
					return true;
				} finally {
					DBUtil.closeQuietly(dropAccounts);
					DBUtil.closeQuietly(dropDrinks);
					DBUtil.closeQuietly(dropItems);
				}				

			};
		});
	}

	public void createTables() throws SQLException {
		doExecuteTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement createAccountTable = null;
				PreparedStatement createDrinkTable = null;
				PreparedStatement createItemTable = null;

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

					createDrinkTable = conn.prepareStatement(
							"create table drink (" +
									"	drink_id integer," +
									"	itemName varchar(40)," +
									"   price float " +
									")"
							);
					createDrinkTable.executeUpdate();

					createItemTable= conn.prepareStatement(
							"create table item (" +
									"	item_id integer, " +
									"	itemName varchar(40)," +
									"   price float " +
									")"
							);
					createItemTable.executeUpdate(); 
					return true;
				} finally {
					DBUtil.closeQuietly(createAccountTable);
					DBUtil.closeQuietly(createDrinkTable);
					DBUtil.closeQuietly(createItemTable);
				}
			}
		});
	}

	public void loadInitialData() throws SQLException {
		doExecuteTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<Account> accountList;
				List<Drink> drinkList;
				List<Item> itemList;

				try {
					accountList = InitialData.getAccount();
					drinkList = InitialData.getDrink();
					itemList = InitialData.getItem();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertAccount = null;
				PreparedStatement insertDrink   = null;
				PreparedStatement insertItem   = null;

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
					}

					// populate drink table 
					insertDrink = conn.prepareStatement("insert into drink (drink_id, itemName, price) values (?, ?, ?)");
					for (Drink drink : drinkList) {
						insertDrink.setInt(1, drink.getItemId());		// removed auto-primary key insert this
						insertDrink.setString(2, drink.getItemName());
						insertDrink.setDouble(3, drink.getPrice());
						insertDrink.addBatch();
					}
					insertDrink.executeBatch();

					// populate item table 
					insertItem = conn.prepareStatement("insert into item (item_id, itemName, price) values (?, ?, ?)");
					for (Item item : itemList) {
						insertItem.setInt(1, item.getItemId());		// removed auto-primary key insert this
						insertItem.setString(2, item.getItemName());
						insertItem.setDouble(3, item.getPrice());
						insertItem.addBatch();
					}
					insertItem.executeBatch();


					return true;
				} finally {
					DBUtil.closeQuietly(insertAccount);
					DBUtil.closeQuietly(insertDrink);
					DBUtil.closeQuietly(insertItem);
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

					//check if the title was found
					if (!found) {
						System.out.println("<" +status+ "> wasn't found in the accounts table");

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
	
	public List<Drink> findDrinkbyName(String firstname) throws SQLException {
		return doExecuteTransaction(new Transaction<List<Drink>>() {
			public List<Drink> execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"select drink.*"+
									" from drink " +
									"where drink.itemName = ?"
							);
					stmt.setString(1, firstname);


					List<Drink> result = new ArrayList<Drink>();
					resultSet = stmt.executeQuery();

					//for testing that a result was returned
					Boolean found = false;

					while (resultSet.next()) {
						found = true;

						//retrieve attributes from resultSet starting with index 1
						Drink drink = new Drink();
						loadDrink(drink, resultSet, 1);
						result.add(drink);

					}

					//check if the title was found
					if (!found) {
						System.out.println("<" +firstname+ "> -Drink Name wasn't found in the drink table");

					}
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	public List<Drink> findAllDrinks() throws SQLException {
		return doExecuteTransaction(new Transaction<List<Drink>>() {
			public List<Drink> execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"select drink.*"+
									" from drink "
							);



					List<Drink> result = new ArrayList<Drink>();
					resultSet = stmt.executeQuery();

					//for testing that a result was returned
					Boolean found = false;

					while (resultSet.next()) {
						found = true;

						//retrieve attributes from resultSet starting with index 1
						Drink drink = new Drink();
						loadDrink(drink, resultSet, 1);
						result.add(drink);
					}
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public List<Item> findItembyName(String name) throws SQLException {
		return doExecuteTransaction(new Transaction<List<Item>>() {
			public List<Item> execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes 
					stmt = conn.prepareStatement(
							"select item.*"+
									" from item " +
									"where item.itemName = ?"
							);
					stmt.setString(1, name);


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
						System.out.println("<" + name+ "> -Item name wasn't found in the item table");

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
							"select item.*"+
									" from item "
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