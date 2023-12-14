package com.furelise.period.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PeriodService {

	@Autowired
	PeriodRepository dao;

	public Period addPeriod(Period req) {
		Period period = new Period();
		period.setPlanPeriod(req.getPlanPeriod());
		return dao.save(period);
	}

	public Period updatePeriod(Period req) {
		Period period = new Period();
		period.setPeriodID(req.getPeriodID());
		period.setPlanPeriod(req.getPlanPeriod());
		return dao.save(period);
	}

	public void deletePeriod(Integer periodID) {
		dao.deleteById(periodID);
	}

	public List<Period> getAllPeriod() {
		return dao.findAll();
	}

	public Period getPeriodById(Integer periodID) {
		return dao.findById(periodID).orElse(null);
	}

}
