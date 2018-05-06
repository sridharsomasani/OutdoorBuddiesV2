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
import com.outdoor.buddies.repository.ActivityDetailsRepository;
import com.outdoor.buddies.service.ActivityService;

@RestController
@RequestMapping(value="/api/v1/activities")
public class ActivityServiceController {
	
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ActivityDetails scheduleActivity(@RequestBody ActivityDetails activityDetails){
		System.out.println( activityDetails);
		ActivityDetails dbActivityDetails = activityService.createActivity(activityDetails);
		return dbActivityDetails;
	}
	
	@RequestMapping(value = "/{scheduleId}", method = RequestMethod.GET)
	public ActivityDetails getScheduledActivity(@PathVariable("scheduleId") Long scheduleId){
		return activityService.getScheduledActivity(scheduleId);
	}
	
	@RequestMapping(value = "/{scheduleId}", method = RequestMethod.DELETE)
	public ActivityDetails removeScheduledActivity(@PathVariable("scheduleId") Long scheduleId){
		return activityService.removeScheduledActivity(scheduleId);
	}
	
	@RequestMapping(value = "/{scheduleId}", method = RequestMethod.PUT)
	public ActivityDetails updateScheduledActivity(@PathVariable("scheduleId") Long scheduleId, 
				@RequestBody ActivityDetails activityDetails){
		
		return activityService.updateScheduledActivity(scheduleId, activityDetails);
	}
	
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public Set<ActivityDetails> getUserScheduledActivity(@PathVariable("userId") Long userId){
//		return activityService.getUserScheduledActivity(userId);
		return activityService.findByUserActivityCustom(userId);
	}
	
	@RequestMapping(value = "/{scheduleId}/participants", method = RequestMethod.GET)
	public Set<ActivityParticipant> getScheduleParticipant(@PathVariable("scheduleId") Long scheduleId){
		return activityService.getScheduleParticipant(scheduleId);
	}
	
	@RequestMapping(value = "/{scheduleId}/participants", method = RequestMethod.POST)
	public Set<ActivityParticipant> addParticipant(@PathVariable("scheduleId") Long scheduleId, 
				@RequestBody List<ActivityParticipant> activityParticipants){
		
		return activityService.addParticipant(scheduleId, activityParticipants);
	}
	
	@RequestMapping(value = "/{scheduleId}/participants", method = RequestMethod.DELETE)
	public Set<ActivityParticipant> removeParticipant(@PathVariable("scheduleId") Long scheduleId, 
				@RequestBody List<ActivityParticipant> activityParticipants){
		return activityService.removeParticipants(scheduleId, activityParticipants);
	}
	
	@RequestMapping(value = "/{scheduleId}/gallery", method = RequestMethod.GET)
	public Set<ActivityGallery> getGallery(@PathVariable("scheduleId") Long scheduleId){
		return activityService.getScheduleGallery(scheduleId);
	}
	
	@RequestMapping(value = "/{scheduleId}/gallery", method = RequestMethod.POST)
	public Set<ActivityGallery> addGallery(@PathVariable("scheduleId") Long scheduleId, 
				@RequestBody List<ActivityGallery> gallery){
		return activityService.addGallery(scheduleId, gallery);
	}
	
	@RequestMapping(value = "/{scheduleId}/gallery", method = RequestMethod.DELETE)
	public Set<ActivityGallery> removeGallery(@PathVariable("scheduleId") Long scheduleId, 
				@RequestBody List<ActivityGallery> gallery){
		return activityService.removeGallery(scheduleId, gallery);
	}
	
}
