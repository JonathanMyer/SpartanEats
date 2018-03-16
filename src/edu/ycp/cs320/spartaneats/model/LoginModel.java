package edu.ycp.cs320.spartaneats.model;




public class LoginModel {
	private String accountName;
	private String password;
	private String error;
	private Account account;
	
	
	// create an account with given nanme and password
	public LoginModel() {
		
	}
	
	public String getAccountName() {
		return this.accountName;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getError() {
		return this.error;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public Account getAccount() {
		return this.account;
	}
}
