package com.outdoor.buddies.service;

import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outdoor.buddies.jpa.entity.UserProfile;
import com.outdoor.buddies.repository.UserProfileRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final UserProfileRepository userProfileRepository;
	
	@Autowired
	public UserServiceImpl(UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}

	@Override
	public String authenticateUser(String username, String passwd) {
		return null;
	}

	@Transactional
	@Override
	public UserProfile createUser(UserProfile user) {
		LOGGER.info("Creating new User with username: " + user.getUserName());
		UserProfile dbUser = userProfileRepository.findByUserName(user.getUserName());
		if(dbUser != null) {
			LOGGER.error("User already exists with username: " + user.getUserName());
			throw new EntityExistsException("User already exists with username: " + user.getUserName());
		}
		
		dbUser = userProfileRepository.findByEmailId(user.getEmailId());
		if(dbUser != null) {
			LOGGER.error("User already exists with emaild: " + user.getEmailId());
			throw new EntityExistsException("User already exists with username: " + user.getEmailId());
		}
		
		userProfileRepository.save(user);
		
		LOGGER.info("New User created");
		return user;
	}

	@Transactional
	@Override
	public UserProfile updateUser(UserProfile user, Long userId) {
		LOGGER.info("Updating User with userid: " + user.getUserId());
		
		Optional<UserProfile> dbUser = userProfileRepository.findById(userId);
		if(!dbUser.isPresent()) {
			LOGGER.info("Could not find User with userid: " + userId);
			throw new EntityNotFoundException("Could not find User with userid: " + userId);
		}
		
		UserProfile updatedUser = updateUserHelper(dbUser.get(), user);	
		userProfileRepository.save(updatedUser);
		LOGGER.info("Updated User with userid: " + updatedUser.getUserId());
		
		return updatedUser;
	}
	
	private UserProfile updateUserHelper(UserProfile dbUser, UserProfile current) {
		dbUser.setAddress(current.getAddress());
		dbUser.setDisplayName(current.getDisplayName());
		dbUser.setDob(current.getDob());
		dbUser.setEmailId(current.getEmailId());
		dbUser.setFirstName(current.getFirstName());
		dbUser.setGender(current.getGender());
		dbUser.setLastName(current.getLastName());
		dbUser.setMobileNumber(current.getMobileNumber());
		return dbUser;
	}

	@Transactional
	@Override
	public UserProfile findUser(Long userId) {
		UserProfile dbUser = userProfileRepository.findById(userId).orElse(null);
		if(dbUser == null) {
			LOGGER.info("Could not find User with userid: " + userId);
			throw new EntityNotFoundException("Could not find User with userid: " + userId);
		}
		return dbUser;
	}

	@Transactional
	@Override
	public UserProfile deleteUser(Long userId) {
		LOGGER.info("Deleting User with userid: " + userId);
		UserProfile dbUser = userProfileRepository.findById(userId).orElse(null);
		if(dbUser ==  null) {
			LOGGER.info("Could not find User with userid: " + userId);
			throw new EntityNotFoundException("Could not find User with userid: " + userId);
		}
		
		userProfileRepository.delete(dbUser);
		LOGGER.info("Deleted User with userid: " + userId);
		return dbUser;
	}

	@Transactional
	@Override
	public UserProfile findByUserName(String userName) {
		LOGGER.info("Searching User with username: " + userName);
		UserProfile dbUser = userProfileRepository.findByUserName(userName);
		if(dbUser == null) {
			LOGGER.info("Could not find User with username: " + userName);
			throw new EntityNotFoundException("Could not find User with username: " + userName);
		}
		return dbUser;
	}

	@Transactional
	@Override
	public UserProfile findByEmail(String emailId) {
		LOGGER.info("Searching User with emailId: " + emailId);
		UserProfile dbUser = userProfileRepository.findByEmailId(emailId);
		if(dbUser == null) {
			LOGGER.info("Could not find User with emailId: " + emailId);
			throw new EntityNotFoundException("Could not find User with email id: " + emailId);
		}
		return dbUser;
	}
	
	

}
