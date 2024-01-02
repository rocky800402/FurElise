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
	public String addPlan(Plan req) {
		BigDecimal planPrice = req.getPlanPrice();
		BigDecimal planPricePerCase = req.getPlanPricePerCase();
		if (planPrice.compareTo(planPricePerCase) < 0) {
			return "價格不可低於案件報酬";
		} else if (dao.existsByPlanName(req.getPlanName())) {
			return "方案名稱不可重複";
		} else {
			for (int i = 0; i < 5; i++) {
				Plan plan = new Plan(req.getPlanName(), req.getLiter(), planPrice.multiply(new BigDecimal(i + 1)),
						planPricePerCase, (i + 1), req.getPlanUpload());
				try {
					dao.save(plan);
				} catch (Exception e) {
					e.printStackTrace();
					return "發生錯誤";
				}
			}
		}
		return "新增成功";
	}

	@Transactional
	public String updatePlan(Plan req) {
		BigDecimal planPrice = req.getPlanPrice();
		BigDecimal planPricePerCase = req.getPlanPricePerCase();
		String oldName = dao.findById(req.getPlanID()).get().getPlanName();
		if (planPrice.compareTo(planPricePerCase) < 0) {
			return "價格不可低於案件報酬";
		} else if (req.getPlanName().equals(oldName)) {
			Plan plan = new Plan(req.getPlanID(), req.getPlanName(), req.getLiter(), planPrice, planPricePerCase,
					req.getTimes(), req.getPlanUpload());
			dao.save(plan);
			return "更新成功";
		} else if (dao.existsByPlanName(req.getPlanName())) {
			return "方案名稱不可重複";
			// 修改五筆方案名
		} else {
			// 更新中的方案ID
			try {
				Integer thisPlanId = req.getPlanID();
				Plan plan = new Plan(thisPlanId, req.getPlanName(), req.getLiter(), planPrice, planPricePerCase,
						req.getTimes(), req.getPlanUpload());
				dao.save(plan);

				// 找出其餘ID
				List<Integer> pList = dao.findIdByPlanName(oldName);

				for (Integer id : pList) {
					if (id != thisPlanId) {
						Plan other = dao.findById(id).get();
						// 僅更新方案名
						Plan newPlan = new Plan(id, req.getPlanName(), other.getLiter(),
								other.getPlanPrice().stripTrailingZeros(),
								other.getPlanPricePerCase().stripTrailingZeros(), other.getTimes(),
								other.getPlanUpload());
						dao.save(newPlan);
					}
				}
				return "更新成功";
			} catch (Exception e) {
				e.printStackTrace();
				return "錯誤";
			}
		}
	}

	// 同名方案一次刪除
	@Transactional
	public String deletePlan(String planName) {
		String result = "";
		// find planID by planName
		List<Integer> idList = dao.findIdByPlanName(planName);
		// find planOrd with planID mentioned
		for (Integer planID : idList) {
			List<PlanOrd> ordList = planOrdDao.findByPlanID(planID);
			if (ordList.isEmpty()) {
				result = "刪除成功";
			} else {
				result = "不可刪除";
				break;
			}
		}
		if (result.equals("刪除成功")) {
			for (Integer planID : idList) {
				try {
					dao.deleteById(planID);
				} catch (Exception e) {
					e.printStackTrace();
					result = "錯誤";
				}
			}
		}
		return result;
	}

	public List<Plan> getAllPlan() {
		return dao.findAll();
	}

	public Plan getPlanById(Integer planID) {
		return dao.findById(planID).orElse(null);
	}
}
