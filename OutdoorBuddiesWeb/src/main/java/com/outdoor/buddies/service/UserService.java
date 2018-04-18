package com.outdoor.buddies.service;

import java.util.List;
import java.util.Optional;

import com.outdoor.buddies.jpa.entity.UserProfile;

public interface UserService {

	String authenticateUser(String username, String passwd);

	UserProfile createUser(UserProfile user);

	UserProfile updateUser(UserProfile user, Long userId);

	Optional<UserProfile> findUser(Long userId);

	void deleteUser(Long userId);

	Iterable<UserProfile> updateUsers(List<UserProfile> users);

	UserProfile findByUserName(String userName);

	UserProfile findByEmail(String emailId);

}