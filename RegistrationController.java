package com.app.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.registration.model.User;
import com.app.registration.service.RegistrationService;

@RestController
public class RegistrationController {

	
	@Autowired
	private RegistrationService service;
	
	@PostMapping("/registeruser")
	public User registerUserset(@RequestBody User user) throws Exception
	{
		String tempEmailId= user.getEmailId();
		if(tempEmailId !=null && !"".equals(tempEmailId))
		{
			User userObj =service.fetchUserByEmailId(tempEmailId);
			if(userObj!=null)
			{
				throw new okException("user with  "+tempEmailId+" is alredy exist");
			}
		}
		User userObj=null;
		userObj=service.saveUser(user);
		return userObj;
	}
	
	@PostMapping("/login")
	public User loginUser(@RequestBody User user) throws Exception
	{
		String tempEmailId=user.getEmailId();
		String tempPass=user.getPassword();
		User userobj =null;
		if(tempEmailId != null && tempPass != null)
		{
			userobj =service.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);
		
		}
		if(userobj == null)
		{
			throw new Exception("Bad creadentials");
		}
		
		return userobj;
	}
}
