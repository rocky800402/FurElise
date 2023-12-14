package com.furelise.pickuptime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.furelise.pickuptime.model.*;

@Controller
@RequestMapping("/pickuptime")
public class PickupTimeController {

	@Autowired
	PickupTimeService pickupTimeSvc;
	
	// return view
		@GetMapping("/")
		public String pickupTimeList() {
			return "pickuptime_list";
		}

		// return view
		@GetMapping("/add")
		public String addPickupTime() {
			return "pickuptime_create";
		}

		// return view
		@GetMapping("/update") 
		public String updatePickupTime(@RequestParam String timeID, Model model) {
			PickupTime pickupTime = pickupTimeSvc.getPickupTimeById(Integer.valueOf(timeID));
			model.addAttribute("timeID", timeID);
			model.addAttribute("timeRange", pickupTime.getTimeRange());
			return "pickuptime_update";
		}
		
}
