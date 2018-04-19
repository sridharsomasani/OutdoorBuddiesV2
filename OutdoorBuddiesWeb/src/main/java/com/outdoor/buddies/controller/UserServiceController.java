package com.outdoor.buddies.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.outdoor.buddies.jpa.entity.UserProfile;
import com.outdoor.buddies.service.UserService;

@RestController
@RequestMapping(value="/api/v1/users")
public class UserServiceController{
	
	@Autowired
	private UserService userProfileService;

	@RequestMapping(value="/authenticate", method= RequestMethod.GET)
	public String authenticateUser(@RequestParam("username") String username, @RequestParam("passwd") String passwd ) {
		return "Authenticated";
	}
	
	@RequestMapping(method= RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public UserProfile createUser(@RequestBody @Valid UserProfile user) {
		return userProfileService.createUser(user);
		
	}
		
	@RequestMapping(value="/{userId}", method= RequestMethod.PUT)
	public UserProfile updateUser(@RequestBody @Valid UserProfile user, @PathVariable("userId") Long userId) {
		UserProfile dbUser = userProfileService.updateUser(user, userId);
		if(dbUser == null) {
			//handle with appropriate HTTP status code and message
			return null;
		}
		return dbUser;
		
	}
	
	@RequestMapping(value="/{userId}", method= RequestMethod.GET)
	public UserProfile findUser(@PathVariable("userId") Long userId ) {
		UserProfile dbUser = userProfileService.findUser(userId);
		if(dbUser == null) {
			//handle with appropriate HTTP status code and message
			return null;
		}
		return dbUser;
	}
	
	@RequestMapping(value="/{userId}", method= RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public UserProfile deleteUser(@PathVariable("userId") Long userId ) {
		UserProfile dbUser = userProfileService.deleteUser(userId);
		if(dbUser == null) {
			//handle with appropriate HTTP status code and message
			return null;
		}
		return dbUser;
	}
	
	@RequestMapping(value="/username/{uName}", method= RequestMethod.GET)
	public UserProfile findByUserName(@PathVariable("uName") String userName) {
		UserProfile dbUser = userProfileService.findByUserName(userName);
		if(dbUser == null) {
			//handle with appropriate HTTP status code and message
			return null;
		}
		return dbUser;
	}
	
	@RequestMapping(value="/email/{param}", method= RequestMethod.GET)
	public UserProfile findByEmail(@PathVariable("param") String emailId) {
		UserProfile dbUser = userProfileService.findByEmail(emailId);
		if(dbUser == null) {
			//handle with appropriate HTTP status code and message
			return null;
		}
		return dbUser;
	}
	
}
