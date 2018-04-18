package com.outdoor.buddies.repository;

import org.springframework.data.repository.CrudRepository;

import com.outdoor.buddies.jpa.entity.UserProfile;

public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {

	public UserProfile findByUserName(String userName);
	
	public UserProfile findByEmailId(String emailId);
	
	
	
	
	

}
