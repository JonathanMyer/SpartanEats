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
			if (account.getAccountName() == username) {
				System.out.println("What is Happening");
				return account;
			}
		}
		
		return null;
		
	}
	
	public boolean doesAccountExist(String username) {
		for (Account account: accountList) {
			if (account.getAccountName() == username) {
				return true;
			}
		}
		return false;
	}
	
	
}
