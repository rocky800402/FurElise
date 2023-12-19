package com.furelise.pickuptime.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.furelise.city.model.*;
import com.furelise.pickuptime.model.*;

@RestController
@RequestMapping("/pickuptime")
public class PickupTimeRESTCon {
		
	@Autowired
	PickupTimeService pickupTimeSvc;

	
	@PostMapping("/adding")
	public PickupTime addPickupTime(@Valid @RequestBody PickupTime req) {
		return pickupTimeSvc.addPickupTime(req);
	}

	
	@PutMapping("/updating")
	public PickupTime updatePickupTime(@Valid @RequestBody PickupTime req) {
		return pickupTimeSvc.updatePickupTime(req);
	}
	
//	
//	@RequestMapping("/update")
//	public PickupTime updatePickupTime() {
//		PickupTime pickupTime = new PickupTime();
//		pickupTime.setTimeID(240011);
//		pickupTime.setTimeRange("08:00-12:00");
//		return pickupTimeSvc.updatePickupTime(pickupTime);
//	}
	
	
	@DeleteMapping("deleting")
	public String deletePickupTime(@RequestBody PickupTime req) {
		pickupTimeSvc.deletePickupTime(req.getTimeID());
		return "deleted seccessfully";
	}
	
	
	@GetMapping("/all")
	public List<PickupTime> getAllPickupTimes(){
		List<PickupTime> pickupTimeList = pickupTimeSvc.getAllPickupTime();
		return pickupTimeList;
	}
	
//	
//	@GetMapping("{timeID}")
//	// 參數名和@PathVariable名一樣時可省略
//	public PickupTime getPickupTimeById(@PathVariable("timeID") Integer timeID) {
//		return pickupTimeSvc.getPickupTimeById(timeID);
//	}
}
