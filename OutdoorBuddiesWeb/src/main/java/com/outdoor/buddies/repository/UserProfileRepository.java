package com.outdoor.buddies.repository;

import org.springframework.data.repository.CrudRepository;

import com.outdoor.buddies.jpa.entity.UserProfile;

public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {

	

}
