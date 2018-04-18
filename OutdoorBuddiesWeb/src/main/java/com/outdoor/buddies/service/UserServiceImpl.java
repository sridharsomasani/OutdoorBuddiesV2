package com.outdoor.buddies.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outdoor.buddies.jpa.entity.UserProfile;
import com.outdoor.buddies.repository.UserProfileRepository;

@Service
final class UserServiceImpl implements UserService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final UserProfileRepository userProfileRepository;
	
	@Autowired
	public UserServiceImpl(UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}

	@Override
	public String authenticateUser(String username, String passwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile createUser(UserProfile user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile updateUser(UserProfile user, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<UserProfile> findUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<UserProfile> updateUsers(List<UserProfile> users) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile findByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile findByEmail(String emailId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
