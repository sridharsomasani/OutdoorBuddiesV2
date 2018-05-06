package com.outdoor.buddies.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.outdoor.buddies.jpa.entity.ActivityDetails;
import com.outdoor.buddies.jpa.entity.ActivityGallery;

public interface ActivityGalleryRepository extends CrudRepository<ActivityGallery, Long>{

	public Set<ActivityGallery> findByActivityDetailsId(ActivityDetails activityDetails);
}
