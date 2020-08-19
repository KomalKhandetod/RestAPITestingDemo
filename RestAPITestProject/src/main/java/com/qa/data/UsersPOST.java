package com.qa.data;


//POJO : Plain Old Java Object
public class UsersPOST {
	//Create Variables with Post Variables
	String name;
	String job;
	String id;
	String createdAt;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public UsersPOST() {
		
	}
	
	public UsersPOST(String name, String job) {
		this.name = name;
		this.job = job;
	}
	
	//Generate Getters and Setters for all variables
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	

}
