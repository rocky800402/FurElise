package com.furelise.plan.model;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanService {

	@Autowired
	PlanRepository dao;
	
	public Plan addPlan(Plan req) {
		Plan plan = new Plan();
		plan.setPlanName(req.getPlanName());
		plan.setLiter(req.getLiter());
		plan.setPlanPrice(req.getPlanPrice());
		plan.setPlanPricePerCase(req.getPlanPricePerCase());
		plan.setTimes(req.getTimes());
		plan.setPlanUpload(req.getPlanUpload());
		return dao.save(plan);
	}

	public Plan updatePlan(Plan req) {
		Plan plan = new Plan();
		plan.setPlanID(req.getPlanID());
		plan.setPlanName(req.getPlanName());
		plan.setLiter(req.getLiter());
		plan.setPlanPrice(req.getPlanPrice());
		plan.setPlanPricePerCase(req.getPlanPricePerCase());
		plan.setTimes(req.getTimes());
		plan.setPlanUpload(req.getPlanUpload());
		return dao.save(plan);
	}
	
	public void deletePlan(Integer planID) {
		dao.deleteById(planID);
	}
	
	public List<Plan> getAllPlan(){
		return dao.findAll();
	}
	
	public Plan getPlanById(Integer planID) {
		return dao.findById(planID).orElse(null);
	}
}
