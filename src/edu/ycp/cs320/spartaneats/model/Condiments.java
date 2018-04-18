package edu.ycp.cs320.spartaneats.model;

public class Condiments {
	private String condType;
	private String condName;
	private int CondID;
	
	public Condiments() {
		
	}
	public Condiments(String condType, String CondName, int CondID) {
		this.setCondType(condType);
		this.setCondName(CondName);
		this.setCondID(CondID);
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
	public int getCondID() {
		return CondID;
	}
	public void setCondID(int CondID) {
		this.CondID = CondID;
	}
}
