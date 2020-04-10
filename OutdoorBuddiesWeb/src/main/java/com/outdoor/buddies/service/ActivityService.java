package com.outdoor.buddies.service;

import java.util.List;
import java.util.Set;

import com.outdoor.buddies.jpa.entity.ActivityDetails;
import com.outdoor.buddies.jpa.entity.ActivityGallery;
import com.outdoor.buddies.jpa.entity.ActivityParticipant;

public interface ActivityService {

	public ActivityDetails getActivityDetails(Long scheduleId);
	
	public ActivityDetails removeActivityDetails(Long scheduleId);
	
	public ActivityDetails updateActivityDetails(Long scheduleId, ActivityDetails activityDetails);
	
	public ActivityDetails createActivity(ActivityDetails details);
	
	public Set<ActivityParticipant> getActivityParticipant(Long scheduleId);
	
	public Set<ActivityParticipant> addParticipant(Long scheduleId, List<ActivityParticipant> activityParticipants);
	
	public Set<ActivityParticipant> removeParticipants(Long scheduleId, List<ActivityParticipant> activityParticipants);
	
	public Set<ActivityGallery> getActivityGallery(Long scheduleId);
	
	public Set<ActivityGallery> addGallery(Long scheduleId, List<ActivityGallery> activityGallery);
	
	public Set<ActivityGallery> removeGallery(Long scheduleId, List<ActivityGallery> activityGallery);
	
	public Set<ActivityDetails> findActivityByUser(Long userId);
}
