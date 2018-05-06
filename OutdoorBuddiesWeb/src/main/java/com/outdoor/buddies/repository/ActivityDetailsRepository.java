package com.outdoor.buddies.repository;

import java.util.Set;

import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.outdoor.buddies.jpa.entity.ActivityDetails;
import com.outdoor.buddies.jpa.entity.UserProfile;

public interface ActivityDetailsRepository extends CrudRepository<ActivityDetails, Long>{

	public Set<ActivityDetails> findByActivityOwner(UserProfile userId);
	@Query(value="SELECT * FROM activity_details t WHERE t.activity_owner_user_id = ?1", nativeQuery=true)
	//@Query("SELECT * FROM activity_details t WHERE t.activity_owner_user_id = :userId", nativeQuery = true)
	public Set<ActivityDetails> findByActivityOwnerCustom(Long userId);
}
