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

		List<ActivityParticipant> dbActivityParticipants = (List<ActivityParticipant>) activityParticipantRepo.saveAll(activityParticipant);
		List<ActivityGallery> activityGallery = details.getGallery();
		
		List<ActivityGallery> dbActivityGallery = (List<ActivityGallery>) activityGalleryRepo.saveAll(activityGallery);
		dbActivityDetails.setGallery(dbActivityGallery);
		dbActivityDetails.setParticipants(dbActivityParticipants);
		return dbActivityDetails;
	}
	
	@Override
	public ActivityDetails getActivityDetails(Long activityId) {
		Optional<ActivityDetails> activityDetails = activityDetailsRepo.findById(activityId);
		if(!activityDetails.isPresent()) {
			LOGGER.info("Could not find scheduled activities for activity id: " + activityId);
			throw new EntityNotFoundException("Could not find scheduled activities for activity id: " + activityId);
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
	public Set<ActivityParticipant> getActivityParticipant(Long activityId) {
		ActivityDetails activityDetails = new ActivityDetails();
		activityDetails.setActivityDetailsId(activityId);
		return activityParticipantRepo.findByActivityDetailsId(activityDetails);
	}


	@Override
	public Set<ActivityGallery> getActivityGallery(Long activityId) {
		ActivityDetails activityDetails = new ActivityDetails();
		activityDetails.setActivityDetailsId(activityId);
		return activityGalleryRepo.findByActivityDetailsId(activityDetails);
	}


	@Override
	public ActivityDetails removeActivityDetails(Long activityId) {
		LOGGER.info("Deleting ActivityDetails with id: " + activityId);
		ActivityDetails dbActivityDetails = activityDetailsRepo.findById(activityId).orElse(null);
		if(dbActivityDetails == null) {
			LOGGER.info("Could not find ActivityDetails with id: " + activityId);
			throw new EntityNotFoundException("Could not find ActivityDetails with id: " + activityId);
		}
		activityDetailsRepo.delete(dbActivityDetails);
		LOGGER.info("Deleted ActivityDetails with id:" + activityId);
		return dbActivityDetails;
	}


	@Override
	public ActivityDetails updateActivityDetails(Long activityId, ActivityDetails activityDetails) {
		LOGGER.info("Updating ActivityDetails with id: " + activityId);
		ActivityDetails dbActivityDetails = activityDetailsRepo.findById(activityId).orElse(null);
		if(dbActivityDetails == null) {
			LOGGER.info("Could not find ActivityDetails with id: " + activityId);
			throw new EntityNotFoundException("Could not find ActivityDetails with id: " + activityId);
		}
		// Complete 
		return null;
	}


	@Override
	public Set<ActivityParticipant> addParticipant(Long activityId, List<ActivityParticipant> activityParticipants) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<ActivityParticipant> removeParticipants(Long activityId,
			List<ActivityParticipant> activityParticipants) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<ActivityGallery> addGallery(Long activityId, List<ActivityGallery> activityGallery) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<ActivityGallery> removeGallery(Long activityId, List<ActivityGallery> activityGallery) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<ActivityDetails> findActivityByUser(Long userId) {
		Set<ActivityDetails> activityDetails= activityDetailsRepo.findActivityByOwner(userId);
		if(activityDetails == null) {
			LOGGER.info("Could not find scheduled activities for user id: " + userId);
			throw new EntityNotFoundException("Could not find scheduled activities for user id: " + userId);
		}
		activityDetails.forEach(this::loadActivityDetailsRelations);
		
		return activityDetails;
	}
	
	


}
