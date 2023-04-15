package com.pkware.foodapp.requestObject;

public class TwoDatesObject {
	private String startDate;
	private String endDate;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public TwoDatesObject(String startDate, String endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public TwoDatesObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
