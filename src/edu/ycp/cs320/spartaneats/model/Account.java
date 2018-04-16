package edu.ycp.cs320.spartaneats.model;

public class Account {
	private String userName;
	private String firstName;
	private String lastName;
	private String studentID;
	private String password;
	private String email;
	private String phoneNumber;
	private String adminStatus;
	private Integer account_id;
	private Double flex;
	private Double dining;

	public Account() {
		
	}

	public Account(String userName, String firstName, String lastName, String studentID, String password, String email, String phoneNumber, String adminStatus, Integer account_id, Double flex, Double dining) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = studentID;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.adminStatus = adminStatus;
		this.account_id = account_id;
		this.flex = flex;
		this.dining = dining;
	}
	//Get and Set Methods
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAdminStatus() {
		return adminStatus;
	}
	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}
	public Integer getAccountId() {
		return account_id;
	}
	public void setAccountId(Integer account_id) {
		this.account_id = account_id;
	}
	public Double getFlex() {
		return flex;
	}
	public void setFlex(Double flex) {
		this.flex = flex;
	}
	public Double getDining() {
		return dining;
	}
	public void setDining(Double dining) {
		this.dining = dining;
	}	
	public Boolean isPasswordCorrect(String password) {
		if (this.password.equals(password)) {
			return true;
		} else {
			return false;
		}
	}
	public Boolean isAdminValidated() {
		if (this.adminStatus.equals("admin")) {
			return true;
		} else {
			return false;
		}
	}
}