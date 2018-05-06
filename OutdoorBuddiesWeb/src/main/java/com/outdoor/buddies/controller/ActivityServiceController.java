package com.outdoor.buddies.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/event")
public class EventServiceController {

	@RequestMapping(value = "/schedule", method = RequestMethod.POST)
	public String scheduleEvent(@RequestParam String data){
		System.out.println( data);
		
		return "";
	}
	
	@RequestMapping(value = "/getSchedule", method = RequestMethod.GET)
	public String getScheduledEvent(@RequestParam("userid") int userid){
		return ""; 
	}
	
}
