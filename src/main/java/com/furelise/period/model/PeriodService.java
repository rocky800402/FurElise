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

	public boolean deletePeriod(Integer periodID) {
		boolean inUse = false;
		for (PlanOrd p : planOrdDao.findAll()) {
			if (p.getPeriodID().equals(periodID)) { // check if is used
				inUse = true;
			}
		}
		if (inUse == false)
			dao.deleteById(periodID);
		return inUse;
	}

	public List<Period> getAllPeriod() {
		return dao.findAll();
	}

	public Period getPeriodById(Integer periodID) {
		return dao.findById(periodID).orElse(null);
	}

}
