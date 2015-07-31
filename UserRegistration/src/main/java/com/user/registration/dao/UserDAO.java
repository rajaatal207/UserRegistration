package com.user.registration.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.user.registration.RegistrationConstants;

public class UserDAO {

	/**
	 * this method persists all the users in the memory
	 * @param users
	 */
	public void saveUser(List<User> users)
	{
		if(users == null || users.size() == 0)
		{
			return;
		}
		
		BufferedWriter out;
		try {
			out = new BufferedWriter( new FileWriter( RegistrationConstants.FILE_NAME ) );
			
		for (User user : users) {
			saveUser(user,out);
		}
		out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveUser(User user, BufferedWriter out)
	{
			
		//BufferedWriter out;
		boolean writerCreated = false;
		try {
			if(out == null)
			{
				out = new BufferedWriter( new FileWriter( RegistrationConstants.FILE_NAME ) );
				writerCreated = true;
			}
			out.write( user.getUserString() );
			out.newLine();
			if(writerCreated)
			{
				out.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println( "User saved in file "+RegistrationConstants.FILE_NAME+" "+user.toString() );
	}
	
	
	public List<User> loadUsers()
	{
		
		BufferedReader  in;
		List<User> users = new ArrayList<User>();
		try {
			in = new BufferedReader( new FileReader( RegistrationConstants.FILE_NAME ) );
			String userStr;
			while( (userStr = in.readLine()) != null )
	        {
	            users.add(new User(userStr));
	        }
	        in.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
         
        System.out.println( "Users loaded from the file "+RegistrationConstants.FILE_NAME+" "+users );
        return users;
	}
	
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		System.out.println(dao.loadUsers());
		List<User> users = new ArrayList<User>();
		users.add(new User("aman|abc@xyz.com|"+new Date().toString()));
		users.add(new User("raman|abc123@xyz.com|"+new Date().toString()));
		users.add(new User("deepa|abc456@xyz.com|"+new Date().toString()));
		
		dao.saveUser(users);
		
		List<User> loadedUsers = dao.loadUsers();
		
		System.out.println(loadedUsers);
	}
	
}
