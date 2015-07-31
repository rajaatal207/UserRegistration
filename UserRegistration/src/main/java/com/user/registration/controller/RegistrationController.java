package com.user.registration.controller;


	 
	import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.user.registration.RegistrationConstants;
import com.user.registration.dao.User;
import com.user.registration.dao.UserDAO;
	 
	@Controller
	public class RegistrationController {
		String message = "Welcome to Spring MVC!";
	 
		//Map to store users
	    Map<String, User> userData = new HashMap<String, User>();
		
		@RequestMapping("/registration")
		public ModelAndView showMessage(
				@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
			System.out.println("in Registraton controller");
	 
			ModelAndView mv = new ModelAndView("userRegistration");
			mv.addObject("message", message);
			mv.addObject("name", name);
			return mv;
		}
		
		
	    @RequestMapping(value = RegistrationConstants.ADD_USER, method = RequestMethod.POST)
	    public @ResponseBody User addUser(@RequestBody User user) {
	        System.out.println("[RegistrationController].addUser Start .");
	        user.setRegisteredDate(new Date());
	        userData.put(user.getUserName(), user);
	        
	        List<User> users = new ArrayList<User>(userData.values());
	        //persist users
	        new UserDAO().saveUser(users);
	        return user;
	    }
	     
	    @RequestMapping(value = RegistrationConstants.GET_ALL_USERS, method = RequestMethod.GET)
	    public @ResponseBody List<User> getAllUsers() {
	        System.out.println("[RegistrationController] getAllUsers Start.");
	        List<User> users = new ArrayList<User>();
	        Set<String> userIdKeys = userData.keySet();
	        for(String i : userIdKeys){
	            users.add(userData.get(i));
	        }
	        return users;
	    }
	    
	    @RequestMapping(value = RegistrationConstants.DELETE_USER, method = RequestMethod.PUT)
	    public @ResponseBody User deleteUser(@PathVariable("userName") String userName) {
	        System.out.println("[RegistrationController] deleteUser Start.");
	        User user = userData.get(userName);
	        userData.remove(userName);
	        List<User> users = new ArrayList<User>(userData.values());
	        //update users in permanent memory
	        new UserDAO().saveUser(users);
	        return user;
	    }
	}