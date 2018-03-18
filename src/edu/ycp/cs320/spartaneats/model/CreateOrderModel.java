package edu.ycp.cs320.spartaneats.model;




public class CreateOrderModel {	
	private Order order;
	private boolean success;
	private String error;
	private Inventory inventory;
	
	
	// create an account with given nanme and password
	public CreateOrderModel() {
		
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}
	
	
	
	
}
