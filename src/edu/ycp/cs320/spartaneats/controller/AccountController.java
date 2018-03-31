package edu.ycp.cs320.spartaneats.controller;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.spartaneats.model.Account;

public class AccountController {
	List<Account> accountList;
	
	public AccountController() {
		accountList = new ArrayList<Account>();
	}
	// adds account to the list
	public void addAccount(Account account) {
		accountList.add(account);
	}
	
	
	// returns the account with given username
	public Account getAccount(String username) {
		for (Account account: accountList) {
			String user = account.getUserName();
			if (user == username) {
				return account;
			}
		}		
		return null;
	}
	
	
	public boolean doesAccountExist(Integer integer) {
		// TODO Auto-generated method stub
		return false;
	}
	
}