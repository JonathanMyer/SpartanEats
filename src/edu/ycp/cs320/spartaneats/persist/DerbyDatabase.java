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
		account.setAccountId(resultSet.getInt(index++));
		account.setUserName(resultSet.getString(index++));
		account.setFirstName(resultSet.getString(index++));
		account.setLastName(resultSet.getString(index++));
		account.setStudentID(resultSet.getString(index++));
		account.setPassword(resultSet.getString(index++));
		account.setEmail(resultSet.getString(index++));
		account.setPhoneNumber(resultSet.getString(index++));
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
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
			try { 
		        	 				
			        stmt1 = conn.prepareStatement 
			                ( "drop table accounts" ); 
			        stmt1.execute(); 
			        stmt1.close(); 
			        
			        stmt2 = conn.prepareStatement 
			                ( "drop table drink" ); 
			        stmt2.execute(); 
			        stmt2.close(); 
			        stmt3 = conn.prepareStatement 
			                ( "drop table item" ); 
			        stmt3.execute(); 
			        stmt3.close(); 
					return true;
				} catch (Exception ex) {
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
				}				

			};
		});
	}
	
	public void createTables() throws SQLException {
		doExecuteTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				
				try {
					stmt1 = conn.prepareStatement(
						"create table accounts (" +
						"	account_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +									
						"	userName varchar(40)," +
						"	firstName varchar(40)," +
						"	lastName varchar(40)," +
						"   studentID varchar(40)," +
						"   password varchar(40)," +
						"   email varchar(40)," +
						"   phoneNumber varchar(40)," +
						"   flex float," +
						"   dining float" +
 						")"
					);	
					stmt1.executeUpdate();
					
					stmt2 = conn.prepareStatement(
							"create table drink (" +
							"	drink_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	itemName varchar(40)," +
							"   price float " +
							")"
					);
					stmt2.executeUpdate();
					
					stmt3 = conn.prepareStatement(
							"create table item (" +
							"	item_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	itemName varchar(40)," +
							"   price float " +
							")"
					);
					stmt3.executeUpdate(); 
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
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
				PreparedStatement statement = null;

				try {
					// populate accounts table (do accounts first, since account_id is foreign key in drink table)
					insertAccount = conn.prepareStatement("insert into accounts (userName, firstname, lastName, studentID, password, email, phoneNumber, flex, dining) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (Account account : accountList) {
						System.out.println(account.getUserName());
						insertAccount.setInt(1, account.getAccountId());	// auto-generated primary key, don't insert this
						insertAccount.setString(1, account.getUserName());
						insertAccount.setString(2, account.getFirstName());
						insertAccount.setString(3, account.getLastName());
						insertAccount.setString(4, account.getStudentID());
						insertAccount.setString(5, account.getPassword());
						insertAccount.setString(6, account.getEmail());
						insertAccount.setString(7, account.getPhoneNumber());
						insertAccount.setDouble(8, account.getFlex());
						insertAccount.setDouble(9, account.getDining());
					
						insertAccount.addBatch();
					}
					insertAccount.executeBatch();
					/*
					try {
						String query = "SELECT count(*) FROM accounts";
						statement = conn.prepareStatement(query);
						ResultSet result = statement.executeQuery();
						while (result.next()) {
						System.out.println("table accounts has " + result.getInt(1) + " items" );
						}
						} catch (Exception SQLException) {
						}
					try {
						String query = "SELECT * FROM accounts";
						statement = conn.prepareStatement(query);
						ResultSet result = statement.executeQuery();
						while (result.next()) {
							int id = result.getInt("account_id");
							String name = result.getString("userName");
						System.out.println( id + " " + name);
						}
						} catch (Exception SQLException) {
						}
						
						*/
					
					DatabaseMetaData databaseMetaData = conn.getMetaData();
					ResultSet set = databaseMetaData.getColumns(null, null,
					        "ACCOUNTS", null);
					
					while(set.next()){
						System.out.println(set.getString("COLUMN_NAME"));
						}
					
					// populate drink table 
					insertDrink = conn.prepareStatement("insert into drink (itemName, price) values (?, ?)");
					for (Drink drink : drinkList) {
//						insertBook.setInt(1, book.getBookId());		// auto-generated primary key, don't insert this
						insertDrink.setString(1, drink.getItemName());
						insertDrink.setDouble(2, drink.getPrice());
						insertDrink.addBatch();
					}
					insertDrink.executeBatch();
					
					// populate item table 
					insertItem = conn.prepareStatement("insert into item (itemName, price) values (?, ?)");
					for (Item item : itemList) {
//						insertBook.setInt(1, book.getBookId());		// auto-generated primary key, don't insert this
						insertDrink.setString(1, item.getItemName());
						insertDrink.setDouble(2, item.getPrice());
						insertDrink.addBatch();
					}
					insertDrink.executeBatch();

					
				return true;
				} finally {
					DBUtil.closeQuietly(insertAccount);
					DBUtil.closeQuietly(insertDrink);
					DBUtil.closeQuietly(insertItem);
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
						System.out.println("<" + userName+ "> wasn't found in the accounts table");
					
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

	public List<Account> findAccountbyName(String firstName, String lastName) throws SQLException {
		return doExecuteTransaction(new Transaction<List<Account>>() {
			public List<Account> execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					//retrieve all attributes from both Books and Authors tables
					
					stmt = conn.prepareStatement(
						"select accounts.* from accounts where accounts.firstName = ? and accounts.lastName = ?"
					);
					stmt.setString(1, firstName);
					stmt.setString(2, lastName);
				
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
						System.out.println("<" + firstName +" " + lastName + "> wasn't found in the accounts table");
					
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
					System.out.println(iD);
					
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
						System.out.println("<" + iD+ "> wasn't found in the accounts table");
					
					}
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public List<Drink> findDrinkbyName(String name) throws SQLException {
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
					stmt.setString(1, name);
					
					
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
						System.out.println("<" + name+ "> wasn't found in the accounts table");
					
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
						"select drink.*"+
						" from drink " +
						"where drink.itemName = ?"
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
						System.out.println("<" + name+ "> wasn't found in the accounts table");
					
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
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.dropTables();
		System.out.println("after deop");
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Success!");
		
		
	}
	
	
}