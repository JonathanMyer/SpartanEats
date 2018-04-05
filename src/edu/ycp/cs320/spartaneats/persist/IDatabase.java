package edu.ycp.cs320.spartaneats.persist;

import java.sql.SQLException;
import java.util.List;

import edu.ycp.cs320.spartaneats.model.Account;
import edu.ycp.cs320.spartaneats.model.Drink;
import edu.ycp.cs320.spartaneats.model.Item;

public interface IDatabase {
	public List<Account> findAccountbyUserName(String userName);
	
	public List<Account> findAccountbyStudentID(String ID);

	public List<Account> findAccountbyName(String lastName);
	
	public List<Account> findAccountbyAccountID(int iD);

	public List<Drink> findDrinkbyName(String name);
	
	public List<Drink> findAllDrinks(); 
	
	public List<Item> findItembyName(String name);
	
	public List<Item> findAllItems();
	
	
}
