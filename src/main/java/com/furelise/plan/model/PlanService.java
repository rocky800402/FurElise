package com.furelise.plan.model;

import java.math.BigDecimal;
import java.util.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furelise.planord.model.*;

@Service
public class PlanService {

	@Autowired
	PlanRepository dao;
	
	@Autowired
	PlanOrdRepository planOrdDao;

	// 一次產生五筆
	@Transactional
	public List<Plan> addPlan(Plan req) {
		BigDecimal planPrice = req.getPlanPrice();
		BigDecimal planPricePerCase = req.getPlanPricePerCase();

		if (planPrice.compareTo(planPricePerCase) < 0 ) {
			return null;
		} else {
			List<Plan> pList = new ArrayList<Plan>();
			for (int i = 0; i < 5; i++) {
				Plan plan = new Plan(req.getPlanName(), req.getLiter(), planPrice.multiply(new BigDecimal(i + 1)),
						planPricePerCase, (i + 1), req.getPlanUpload());
				dao.save(plan);
				pList.add(plan);
			}
			return pList;
		}
	}

	public Plan updatePlan(Plan req) {
		BigDecimal planPrice = req.getPlanPrice();
		BigDecimal planPricePerCase = req.getPlanPricePerCase();
		
		if (planPrice.compareTo(planPricePerCase) < 0) {
			return null;
		} else {
			Plan plan = new Plan(req.getPlanID(), req.getPlanName(), req.getLiter(), planPrice, planPricePerCase,
					req.getTimes(), req.getPlanUpload());
			return dao.save(plan);
		}
	}

	// 同名方案一次刪除
	@Transactional
	public void deletePlan(String planName) {
		// find planID by planName
		List<Integer> planIDList = dao.findIdByPlanName(planName);
		// find planOrd by planID
		for (Integer planID : planIDList) {
			List<PlanOrd> planOrdList = planOrdDao.findByPlanID(planID);
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
