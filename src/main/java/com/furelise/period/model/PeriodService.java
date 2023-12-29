package com.furelise.period.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.furelise.planord.model.*;

@Service
public class PeriodService {

	@Autowired
	PeriodRepository dao;

	@Autowired
	PlanOrdRepository planOrdDao;

	public String addPeriod(Period req) {
		String result = "";
		if (!dao.existsByPlanPeriod(req.getPlanPeriod())) {
			Period period = new Period(req.getPlanPeriod());
			dao.save(period);
			result = "新增成功";
		} else {
			result = "訂購期間已存在";
		}
		return result;
	}

	public String updatePeriod(Period req) {
		String result = "";
		Integer oldPlanPeriod = dao.findById(req.getPeriodID()).get().getPlanPeriod();
		if (!dao.existsByPlanPeriod(req.getPlanPeriod()) || oldPlanPeriod.equals(req.getPlanPeriod())) {
			Period period = new Period(req.getPeriodID(), req.getPlanPeriod());
			dao.save(period);
			result = "更新成功";
		} else
			result = "訂購期間已存在";
		return result;
	}

	public String deletePeriod(Integer periodID) {
		String result = "deleted successfully";
		for (PlanOrd p : planOrdDao.findAll()) {
			if (p.getPeriodID().equals(periodID)) { // check if is used
				result = periodID + " is in use!";
			}
		}
		if (result.equals("deleted successfully"))
			dao.deleteById(periodID);
		return result;
	}

	public List<Period> getAllPeriod() {
		return dao.findAll();
	}

	public Period getPeriodById(Integer periodID) {
		return dao.findById(periodID).orElse(null);
	}

}
