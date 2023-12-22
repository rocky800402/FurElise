package com.furelise.period.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furelise.city.model.*;
import com.furelise.period.model.*;
import com.furelise.pickupway.model.*;

@RestController
@RequestMapping("/period")
public class PeriodRESTCon {

	@Autowired
	PeriodService periodSvc;

	//驗證
//	
//	@PostMapping("/adding")
//	public Period addPeriod(@RequestBody Period req, BindingResult result) {
//		if (!result.hasErrors())
//			return periodSvc.addPeriod(req);
//		else
//			return null;
//	}

	
	@PostMapping("/adding")
	public Period addPeriod(@Valid @RequestBody Period req) {
		return periodSvc.addPeriod(req);
	}

	
	@PutMapping("/updating")
	public Period updatePeriod(@Valid @RequestBody Period req) {
		return periodSvc.updatePeriod(req);
	}

	
	@GetMapping("/all")
	public List<Period> getAllPeriods() {
		List<Period> periodList = periodSvc.getAllPeriod();
		return periodList;
	}

	
	@DeleteMapping("/deleting")
	public String deletePeriod(@RequestBody Period req) {
		String result = periodSvc.deletePeriod(req.getPeriodID());
		return result;
	}

//	
//	@GetMapping("{periodID}")
//	public Period getPeriodById(@PathVariable("periodID") Integer periodID) {
//		return periodSvc.getPeriodById(periodID);
//	}
}
