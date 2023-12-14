package com.furelise.pickupway.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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

import com.furelise.pickupway.model.*;

@RestController
@RequestMapping("/pickupway")
public class PickupWayRESTCon {

	@Autowired
	PickupWayService pickupWaySvc;

//	// add data, for ajax using
//	@CrossOrigin("*")
//	@PostMapping("/adding")
//	public PickupWay addPickupWay(@RequestBody PickupWay req) {
//		return pickupWaySvc.addPickupWay(req);
//	}

//	//  amend data, for ajax using
//	@CrossOrigin("*")
//	@PutMapping("/updating")
//	public PickupWay updatePickupWay(@RequestBody PickupWay req) {
//		return pickupWaySvc.updatePickupWay(req);
//	}

//	// delete data, for ajax using
//	@CrossOrigin("*")
//	@DeleteMapping("/deleting")
//	public String deletePickupWay(@RequestBody PickupWay req) {
//		pickupWaySvc.deletePickupWay(req.getWayID());
//		return "deleted seccessfully";
//	}

//	@CrossOrigin("*")
//	@DeleteMapping("/{wayID}")
//	public String deletePickupWay(@PathVariable Integer wayID) {
//		pickupWaySvc.deletePickupWay(wayID);
//		return "delete seccessfully";
//	}

//	// list data, for ajax using
//	@CrossOrigin("*")
//	@GetMapping("/all")
//	public List<PickupWay> getAllPickupWays() {
//		List<PickupWay> pickupWayList = pickupWaySvc.getAllPickupWay();
//		return pickupWayList;
//	}

//	和pickupwaycon導向pickupway_update衝突
//	@CrossOrigin("*")
//	@GetMapping("/{wayID}")
//	public PickupWay getPickupWayById(@PathVariable("wayID") Integer wayID) {
//		return pickupWaySvc.getPickupWayById(wayID);
//	}

}
