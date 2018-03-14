package edu.ycp.cs320.spartaneats.controller;

import java.util.ArrayList;
import java.util.List;



import edu.ycp.cs320.spartaneats.model.Account;

public class AccountController {
	List<Account> accountList = new ArrayList<Account>();
	
	public void addAccount(Account account) {
		accountList.add(account);
	}
	
	public Account getAccount(String username) {
		for (Account account: accountList) {
			if (account.getAccountName() == username) {
				return account;
			}
		}
		
		return null;
		
	}
	
	
}
