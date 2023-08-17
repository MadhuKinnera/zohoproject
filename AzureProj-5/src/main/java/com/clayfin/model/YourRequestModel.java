package com.clayfin.model;


public class YourRequestModel {
	 private String status;
	 private String date;
	 private String time;
	public YourRequestModel() {
		super();
		
	}
	public YourRequestModel(String status, String date,String time) {
		super();
		this.status = status;
		this.date = date;
		this.time=time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	 
	 
}
