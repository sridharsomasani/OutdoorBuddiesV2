package com.outdoor.buddies.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.outdoor.buddies.jpa.entity.UserProfile;
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
	public UserProfile registerUser(@RequestBody UserProfile user) {
		return userProfileRepository.save(user);
		
	}
	
	@RequestMapping(value="/searchByUsername", method= RequestMethod.GET)
	public UserProfile searchUsers(@RequestParam String userName) {
		return userProfileRepository.findByUserName(userName);
	}
	
	@RequestMapping(value="/{userId}", method= RequestMethod.GET)
	public Optional<UserProfile> findUser(@PathVariable("userId") long userId ) {
		return userProfileRepository.findById(userId);
	}
	
}
