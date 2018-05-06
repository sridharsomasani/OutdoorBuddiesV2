package com.outdoor.buddies.repository;

import org.springframework.data.repository.CrudRepository;

import com.outdoor.buddies.jpa.entity.Activity;

public interface ActivityRepository extends CrudRepository<Activity, Long>{

}
