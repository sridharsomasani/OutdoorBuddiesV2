package com.outdoor.buddies.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.outdoor.buddies.jpa.entity.ActivityDetails;

public interface ActivityDetailsRepository extends CrudRepository<ActivityDetails, Long>{

	@Query(value="SELECT * FROM activity_details t WHERE t.user_id = ?1", nativeQuery=true)
	Set<ActivityDetails> findActivityByOwner(Long userId);
}
