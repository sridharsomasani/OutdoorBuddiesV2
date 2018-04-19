package com.outdoor.buddies.service;

import com.outdoor.buddies.jpa.entity.UserProfile;

public interface UserService {

	String authenticateUser(String username, String passwd);

	UserProfile createUser(UserProfile user);

	UserProfile updateUser(UserProfile user, Long userId);

	UserProfile findUser(Long userId);

	UserProfile deleteUser(Long userId);

	UserProfile findByUserName(String userName);

	UserProfile findByEmail(String emailId);

}