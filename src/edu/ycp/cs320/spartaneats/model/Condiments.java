package edu.ycp.cs320.spartaneats.model;

public class Condiments {
	private String condType;
	private String condName;
	private int contID;
	
	public Condiments() {
		
	}
	public Condiments(String condType, String CondName, int ContID) {
		this.setCondType(condType);
		this.setCondName(CondName);
		this.setContID(ContID);
	}
	public String getCondType() {
		return condType;
	}
	public void setCondType(String condType) {
		this.condType = condType;
	}
	public String getCondName() {
		return condName;
	}
	public void setCondName(String condName) {
		this.condName = condName;
	}
	public int getContID() {
		return contID;
	}
	public void setContID(int contID) {
		this.contID = contID;
	}
}
