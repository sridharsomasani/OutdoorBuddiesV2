package com.outdoor.buddies.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.outdoor.buddies.jpa.entity.ActivityDetails;
import com.outdoor.buddies.jpa.entity.ActivityGallery;
import com.outdoor.buddies.jpa.entity.ActivityParticipant;
import com.outdoor.buddies.service.ActivityService;

@RestController
@RequestMapping(value="/api/v1/activities")
public class ActivityServiceController {
	
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ActivityDetails createActivity(@RequestBody ActivityDetails activityDetails){
		System.out.println( activityDetails);
		return activityService.createActivity(activityDetails);
	}
	
	@RequestMapping(value = "/{activityId}", method = RequestMethod.GET)
	public ActivityDetails getActivityDetails(@PathVariable("activityId") Long activityId){
		return activityService.getActivityDetails(activityId);
	}
	
	@RequestMapping(value = "/{activityId}", method = RequestMethod.DELETE)
	public ActivityDetails removeActivityDetails(@PathVariable("activityId") Long activityId){
		return activityService.removeActivityDetails(activityId);
	}
	
	@RequestMapping(value = "/{activityId}", method = RequestMethod.PUT)
	public ActivityDetails updateActivityDetails(@PathVariable("activityId") Long activityId, 
				@RequestBody ActivityDetails activityDetails){
		
		return activityService.updateActivityDetails(activityId, activityDetails);
	}
	
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public Set<ActivityDetails> getActivityByUser(@PathVariable("userId") Long userId){
		return activityService.findActivityByUser(userId);
	}
	
	@RequestMapping(value = "/{activityId}/participants", method = RequestMethod.GET)
	public Set<ActivityParticipant> getParticipant(@PathVariable("activityId") Long activityId){
		return activityService.getActivityParticipant(activityId);
	}
	
	@RequestMapping(value = "/{activityId}/participants", method = RequestMethod.POST)
	public Set<ActivityParticipant> addParticipant(@PathVariable("activityId") Long activityId, 
				@RequestBody List<ActivityParticipant> activityParticipants){
		
		return activityService.addParticipant(activityId, activityParticipants);
	}
	
	@RequestMapping(value = "/{activityId}/participants", method = RequestMethod.DELETE)
	public Set<ActivityParticipant> removeParticipant(@PathVariable("activityId") Long activityId, 
				@RequestBody List<ActivityParticipant> activityParticipants){
		return activityService.removeParticipants(activityId, activityParticipants);
	}
	
	@RequestMapping(value = "/{activityId}/gallery", method = RequestMethod.GET)
	public Set<ActivityGallery> getGallery(@PathVariable("activityId") Long activityId){
		return activityService.getActivityGallery(activityId);
	}
	
	@RequestMapping(value = "/{activityId}/gallery", method = RequestMethod.POST)
	public Set<ActivityGallery> addGallery(@PathVariable("activityId") Long activityId, 
				@RequestBody List<ActivityGallery> gallery){
		return activityService.addGallery(activityId, gallery);
	}
	
	@RequestMapping(value = "/{activityId}/gallery", method = RequestMethod.DELETE)
	public Set<ActivityGallery> removeGallery(@PathVariable("activityId") Long activityId, 
				@RequestBody List<ActivityGallery> gallery){
		return activityService.removeGallery(activityId, gallery);
	}
	
}
