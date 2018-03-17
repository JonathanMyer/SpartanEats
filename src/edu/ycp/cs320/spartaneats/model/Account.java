package edu.ycp.cs320.spartaneats.model;




public class Account {
	private String accountName;
	private String password;
	
	
	// create an account with given nanme and password
	public Account(String accountName, String password) {
		this.accountName = accountName;
		this.password = password;
	}
	
	
	// checks to see if the given password is correct for this account
	public Boolean isPasswordCorrect(String password) {
		if (this.password.equals(password)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	// used to get accountName
	public String getAccountName() {
		return this.accountName;
	}
}
