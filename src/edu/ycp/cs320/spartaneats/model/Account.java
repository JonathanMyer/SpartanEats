package edu.ycp.cs320.spartaneats.model;

public class Account {
	private Integer accountId;
	private String userName;
	private String firstName;
	private String lastName;
	private String studentID;
	private String password;
	private String email;
	private String phoneNumber;
	private Double flex;
	private Double dining;

	
	public Account() {
		
	}

	public Account(Integer accountId, String userName, String firstName, String lastName, String studentID, String password, String email, String phoneNumber, Double flex, Double dining) {
		this.accountId = accountId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = studentID;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.flex = flex;
		this.dining = dining;
	}
	
	// checks to see if the given password is correct for this account
	public Boolean isPasswordCorrect(String password) {
		if (this.password.equals(password)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getUserName() {
		// TODO Auto-generated method stub
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	public Integer getAccountId() {
		return accountId;
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


	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}


	public void setLastName(String last) {
		this.lastName = last;
	}
	
	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getStudentID() {
		return studentID;
	}


	public void setStudentID(String string) {
		this.studentID = string;
	}

}