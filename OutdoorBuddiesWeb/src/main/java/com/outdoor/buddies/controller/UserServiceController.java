package com.outdoor.buddies.controller;

import java.util.List;
import java.util.Optional;

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
import com.outdoor.buddies.repository.UserProfileRepository;

@RestController
@RequestMapping(value="/api/v1/users")
public class UserServiceController{
	
	@Autowired
	private UserProfileRepository userProfileRepository;

	@RequestMapping(value="/authenticate", method= RequestMethod.GET)
	public String authenticateUser(@RequestParam("username") String username, @RequestParam("passwd") String passwd ) {
		return "Authenticated";
	}
	
	@RequestMapping(method= RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public UserProfile createUser(@RequestBody UserProfile user) {
		return userProfileRepository.save(user);
		
	}
		
	@RequestMapping(value="/{userId}", method= RequestMethod.PUT)
	public UserProfile updateUser(@RequestBody UserProfile user, @PathVariable("userId") Long userId) {
		return userProfileRepository.save(user);
		
	}
	
	@RequestMapping(value="/{userId}", method= RequestMethod.GET)
	public Optional<UserProfile> findUser(@PathVariable("userId") Long userId ) {
		return userProfileRepository.findById(userId);
	}
	
	@RequestMapping(value="/{userId}", method= RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("userId") Long userId ) {
		userProfileRepository.deleteById(userId);
		return;
	}
	
	@RequestMapping(method= RequestMethod.PUT)
	public Iterable<UserProfile> updateUsers(@RequestBody List<UserProfile> users) {
		return userProfileRepository.saveAll(users);
		
	}
	
	@RequestMapping(value="/username/{uName}", method= RequestMethod.GET)
	public UserProfile findByUserName(@PathVariable("uName") String userName) {
		return userProfileRepository.findByUserName(userName);
	}
	
	@RequestMapping(value="/email/{param}", method= RequestMethod.GET)
	public UserProfile findByEmail(@PathVariable("param") String emailId) {
		return userProfileRepository.findByEmailId(emailId);
	}
	
}
