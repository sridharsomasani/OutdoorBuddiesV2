package com.outdoor.buddies.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.outdoor.buddies.jpa.entity.ActivityDetails;
import com.outdoor.buddies.jpa.entity.ActivityParticipant;

public interface ActivityParticipantRepository extends CrudRepository<ActivityParticipant, Long>{

	public Set<ActivityParticipant> findByActivityDetailsId(ActivityDetails activityDetails);
}
