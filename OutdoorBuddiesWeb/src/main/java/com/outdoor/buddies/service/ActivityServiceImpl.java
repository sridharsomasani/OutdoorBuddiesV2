package com.outdoor.buddies.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outdoor.buddies.jpa.entity.ActivityDetails;
import com.outdoor.buddies.jpa.entity.ActivityGallery;
import com.outdoor.buddies.jpa.entity.ActivityParticipant;
import com.outdoor.buddies.jpa.entity.UserProfile;
import com.outdoor.buddies.repository.ActivityDetailsRepository;
import com.outdoor.buddies.repository.ActivityGalleryRepository;
import com.outdoor.buddies.repository.ActivityParticipantRepository;

@Transactional(rollbackOn= {Exception.class})
@Service
public class ActivityServiceImpl implements ActivityService{
	
	private ActivityDetailsRepository activityDetailsRepo;
	private ActivityParticipantRepository activityParticipantRepo;
	private ActivityGalleryRepository activityGalleryRepo;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityServiceImpl.class);
	
	@Autowired
	public ActivityServiceImpl(ActivityDetailsRepository activityDetailsRepo
			,ActivityParticipantRepository activityParticipantRepo
			,ActivityGalleryRepository activityGalleryRepo) {
		
		this.activityDetailsRepo = activityDetailsRepo;
		this.activityParticipantRepo = activityParticipantRepo;
		this.activityGalleryRepo = activityGalleryRepo;
	}
	

	@Override
	public ActivityDetails createActivity(ActivityDetails details) {
		ActivityDetails dbActivityDetails = activityDetailsRepo.save(details);
		
		List<ActivityParticipant> activityParticipant = details.getParticipants();
		activityParticipant.forEach(
				participant -> {
					participant.setActivityDetailsId(dbActivityDetails);
				});
		
		List<ActivityParticipant> dbActivityParticipants = (List<ActivityParticipant>) activityParticipantRepo.saveAll(activityParticipant);
		List<ActivityGallery> activityGallery = details.getGallery();
		activityGallery.forEach(
				gallery -> {
					gallery.setActivityDetailsId(dbActivityDetails);
				});
		
		List<ActivityGallery> dbActivityGallery = (List<ActivityGallery>) activityGalleryRepo.saveAll(activityGallery);
		dbActivityDetails.setGallery(dbActivityGallery);
		dbActivityDetails.setParticipants(dbActivityParticipants);
		return dbActivityDetails;
	}
	
	@Override
	public Set<ActivityDetails> getUserScheduledActivity(Long userId) {
		UserProfile userObject = new UserProfile();
		userObject.setUserId(userId);
		Set<ActivityDetails> activityDetails= activityDetailsRepo.findByActivityOwner(userObject);
		if(activityDetails == null) {
			LOGGER.info("Could not find scheduled activities for user id: " + userId);
			throw new EntityNotFoundException("Could not find scheduled activities for user id: " + userId);
		}
		activityDetails.forEach(this::loadActivityDetailsRelations);
		
		return activityDetails;
	}


	@Override
	public ActivityDetails getScheduledActivity(Long scheduleId) {
		Optional<ActivityDetails> activityDetails = activityDetailsRepo.findById(scheduleId);
		if(!activityDetails.isPresent()) {
			LOGGER.info("Could not find scheduled activities for schedule id: " + scheduleId);
			throw new EntityNotFoundException("Could not find scheduled activities for schedule id: " + scheduleId);
		}
		ActivityDetails dbActivityDetails = activityDetails.get();
		loadActivityDetailsRelations(dbActivityDetails);
		return dbActivityDetails;
	}
	
	private void loadActivityDetailsRelations(ActivityDetails activityDetails) {
		//activityDetails.getActivityOwner();
		activityDetails.getGallery().size();
		activityDetails.getParticipants().size();
	}


	@Override
	public Set<ActivityParticipant> getScheduleParticipant(Long scheduleId) {
		ActivityDetails activityDetails = new ActivityDetails();
		activityDetails.setActivityDetailsId(scheduleId);
		return activityParticipantRepo.findByActivityDetailsId(activityDetails);
	}


	@Override
	public Set<ActivityGallery> getScheduleGallery(Long scheduleId) {
		ActivityDetails activityDetails = new ActivityDetails();
		activityDetails.setActivityDetailsId(scheduleId);
		return activityGalleryRepo.findByActivityDetailsId(activityDetails);
	}


	@Override
	public ActivityDetails removeScheduledActivity(Long scheduleId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ActivityDetails updateScheduledActivity(Long scheduleId, ActivityDetails activityDetails) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<ActivityParticipant> addParticipant(Long scheduleId, List<ActivityParticipant> activityParticipants) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<ActivityParticipant> removeParticipants(Long scheduleId,
			List<ActivityParticipant> activityParticipants) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<ActivityGallery> addGallery(Long scheduleId, List<ActivityGallery> activityGallery) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<ActivityGallery> removeGallery(Long scheduleId, List<ActivityGallery> activityGallery) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<ActivityDetails> findByUserActivityCustom(Long userId) {
		return activityDetailsRepo.findByActivityOwnerCustom(userId);
	}
	
	


}
