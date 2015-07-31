package com.user.registration.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.StringTokenizer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class User implements Serializable{

	private String userName = null;
	private String emailId = null;
	private Date registeredDate = null;
	public User(String userStr) {
		
		StringTokenizer token = new StringTokenizer(userStr, "|");
		 
		while (token.hasMoreElements()) {
			 
			userName = token.nextElement().toString();
			emailId = token.nextElement().toString();
			registeredDate = new Date(token.nextElement().toString());
		}
		
	}
	
	public User()
	{
		
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	@JsonSerialize(using=DateSerializer.class)
	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	public String getUserString() {
		String str = getUserName()+"|"+getEmailId()+"|"+getRegisteredDate();
		return str;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "UserName : "+getUserName()+", emailId : "+getEmailId()+", registrationDate "+getRegisteredDate();
	}
	public static void main(String[] args) {
		User user = new User("aman|abc@xyz.com|"+new Date().toString());
		System.out.println(user.toString());
	}
}
