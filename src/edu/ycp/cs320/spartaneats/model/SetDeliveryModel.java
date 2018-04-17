package edu.ycp.cs320.spartaneats.model;

public class SetDeliveryModel {
	private boolean Delivery;
	private boolean Pickup;
	
	public SetDeliveryModel() {
		
	}

	public SetDeliveryModel(boolean Delivery, boolean Pickup) {
		this.setDelivery(Delivery);
		this.setPickup(Pickup);

	}
	public boolean getDelivery() {
		return Delivery;
	}

	public void setDelivery(boolean delivery) {
		Delivery = delivery;
	}

	public boolean getPickup() {
		return Pickup;
	}

	public void setPickup(boolean pickup) {
		Pickup = pickup;
	}
}