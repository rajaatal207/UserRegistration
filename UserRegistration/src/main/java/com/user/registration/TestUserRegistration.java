package com.user.registration;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;


import org.springframework.web.client.RestTemplate;

import com.user.registration.dao.User;

public class TestUserRegistration {

	public static final String SERVER_URI = "http://localhost:8080/UserRegistration/";
	public static void main(String args[]){
        
        testAddUser();
        System.out.println("*****");
        testGetAllUsers();
        System.out.println("*****");
        testDeleteUser();
    }

	private static void testDeleteUser() {
		// TODO Auto-generated method stub
		
	}

	private static void testGetAllUsers() {
		 RestTemplate restTemplate = new RestTemplate();

	        List<LinkedHashMap> users = restTemplate.getForObject(SERVER_URI+RegistrationConstants.GET_ALL_USERS, List.class);
	        System.out.println("total users found "+users.size());
	        for(LinkedHashMap map : users){
	            System.out.println("userName="+map.get("userName")+",emailId="+map.get("emailId")+",registeredDate="+map.get("registeredDate"));;
	        }
		
	}

	private static void testAddUser() {
		RestTemplate restTemplate = new RestTemplate();
        User user = new User();
        user.setUserName("sachin");
        user.setEmailId("sachin@abc.come");
        //user.setRegisteredDate(new Date());
        User response = restTemplate.postForObject(SERVER_URI+RegistrationConstants.ADD_USER, user, User.class);
        System.out.println(response.toString());
		
	}
}
