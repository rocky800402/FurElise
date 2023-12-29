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
	public String addPickupTime(@Valid @RequestBody PickupTime req) {
		return pickupTimeSvc.addPickupTime(req);
	}

	
	@PutMapping("/updating")
	public String updatePickupTime(@Valid @RequestBody PickupTime req) {
		return pickupTimeSvc.updatePickupTime(req);
	}
	
	@DeleteMapping("deleting")
	public String deletePickupTime(@RequestBody PickupTime req) {
		String result = pickupTimeSvc.deletePickupTime(req.getTimeID());
		return result;
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
