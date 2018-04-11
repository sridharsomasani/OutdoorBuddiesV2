package com.outdoor.buddies.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.outdoor.buddies.repository.UserProfileRepository;

@RestController
@RequestMapping(value="/api/user")
public class UserServiceController {
	
	@Autowired
	private UserProfileRepository userProfileRepository;

	@RequestMapping(value="/authenticate", method= RequestMethod.GET)
	public String authenticateUser(@RequestParam("username") String username, @RequestParam("passwd") String passwd ) {
		return "Authenticated";
	}
	
	@RequestMapping(value="/register", method= RequestMethod.POST)
	public String registerUser(@RequestParam Map<String, String> params) {
		
		
		
		return "";
	}
	
	@RequestMapping(value="/searchUser", method= RequestMethod.GET)
	public String searchUsers(@RequestParam Map<String, String> params) {
		return "";
	}
	
}
