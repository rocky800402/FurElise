package com.furelise.plan.model;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanService {

	@Autowired
	PlanRepository dao;

	// 一次產生五筆
	public List<Plan> addPlan(Plan req) {
		List<Plan> pList = new ArrayList<Plan>();
		for (int i = 0; i < 5; i++) {
			Plan plan = new Plan(req.getPlanName(), req.getLiter(), req.getPlanPrice().multiply(new BigDecimal(i + 1)),
					req.getPlanPricePerCase(), (i + 1), req.getPlanUpload());
			dao.save(plan);
			pList.add(plan);
		}
		return pList;
	}

	public Plan updatePlan(Plan req) {
		Plan plan = new Plan(req.getPlanID(), req.getPlanName(), req.getLiter(), req.getPlanPrice(), 
				req.getPlanPricePerCase(), req.getTimes(), req.getPlanUpload());
		return dao.save(plan);
	}

	// 同名方案一次刪除
	public void deletePlan(String planName) {
		// find planID by planName
		List<Integer> planIDList = dao.findIdByPlanName(planName);
		for (Integer planID : planIDList) {
			dao.deleteById(planID);
		}
	}

	public List<Plan> getAllPlan() {
		return dao.findAll();
	}

	public Plan getPlanById(Integer planID) {
		return dao.findById(planID).orElse(null);
	}
}
