package edu.ycp.cs320.spartaneats.model;




public class LoginModel {
	private String accountName;
	private String password;
	private String error;
	private Account account;
	private String adminStatus;
	private boolean successUser;
	private boolean successAdmin;
	
	
	// create an account with given name and password
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
	
	public boolean getSuccessForUser() {
		return this.successUser;
	}
	
	public void setSuccessForUser(boolean successUser) {
		this.successUser = successUser;
	}
	public boolean getSuccessForAdmin() {
		return this.successAdmin;
	}
	
	public void setSuccessForAdmin(boolean successAdmin) {
		this.successAdmin = successAdmin;
	}
	public String getAdminStatus() {
		return this.adminStatus;
	}
	
	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}
}
