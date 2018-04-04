package edu.ycp.cs320.spartaneats.model;

import edu.ycp.cs320.spartaneats.controller.AccountController;

public class AccountControllerPopulate {
	private AccountController controller;
	
	
	
	public AccountControllerPopulate(AccountController controller) {
		this.controller = controller;
		this.controller.addAccount(new Account("BillyBones","ImaPirate"));
		this.controller.addAccount(new Account("SwashBucket","ImaBucket"));
		this.controller.addAccount(new Account("DeckBroom","ImaBroom"));
	}
	
	
}
