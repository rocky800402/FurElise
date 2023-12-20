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

	public Period addPeriod(Period req) {
		Period period = new Period(req.getPlanPeriod());
		return dao.save(period);
	}

	public Period updatePeriod(Period req) {
		Period period = new Period(req.getPeriodID(), req.getPlanPeriod());
		return dao.save(period);
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
