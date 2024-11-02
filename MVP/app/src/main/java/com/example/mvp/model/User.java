package com.example.mvp.model;


public class User {

	private int id;
	private String avatar;
	private String first_name;
	private String last_name;
	private String email;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAvatar(String avatar){
		this.avatar = avatar;
	}

	public String getAvatar(){
		return avatar;
	}

	public void setFirstName(String firstName){
		this.first_name = firstName;
	}

	public String getFirstName(){
		return first_name;
	}

	public void setLastName(String lastName){
		this.last_name = lastName;
	}

	public String getLastName(){
		return last_name;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
}
