package com.furelise.plan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furelise.plan.model.*;

@RestController
@RequestMapping("/plan")
public class PlanRESTCon {

	@Autowired
	PlanService planSvc;
	
	@CrossOrigin("*")
	@PostMapping("/adding")
	public Plan addPlan(@RequestBody Plan req) {
		return planSvc.addPlan(req);
	}
	
	@CrossOrigin("*")
	@PutMapping("/updating")
	public Plan updatePlan(@RequestBody Plan req) {
		return planSvc.updatePlan(req);
	}
	
	@CrossOrigin("*")
	@DeleteMapping("deleting")
	public String deletePlan(@RequestBody Plan req) {
		planSvc.deletePlan(req.getPlanID());
		return "deleted seccessfully";
	}
	
	@CrossOrigin("*")
	@GetMapping("/all")
	public List<Plan> getAllPlans(){
		List<Plan> planList = planSvc.getAllPlan();
		return planList;
	}
	
//	@CrossOrigin("*")
//	@GetMapping("/{planID}")
//	public Plan getPlanById(@PathVariable Integer planID) {
//		return planSvc.getPlanById(planID);
//	}
}
