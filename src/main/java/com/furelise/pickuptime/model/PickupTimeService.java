package com.furelise.pickuptime.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furelise.planord.model.*;

@Service
public class PickupTimeService {

	@Autowired
	PickupTimeRepository dao;

	@Autowired
	PlanOrdRepository planOrdDao;

	public PickupTime addPickupTime(PickupTime req) {
		PickupTime pickupTime = new PickupTime(req.getTimeRange());
		return dao.save(pickupTime);
	}

	public PickupTime updatePickupTime(PickupTime req) {
		PickupTime pickupTime = new PickupTime(req.getTimeID(), req.getTimeRange());
		return dao.save(pickupTime);
	}

	public String deletePickupTime(Integer timeID) {
		String result = "";
		List<PlanOrd> list = planOrdDao.findByTimeID(timeID);
		System.out.println(list);
		if (list.isEmpty()) {
			dao.deleteById(timeID);
			result = "deleted successfully";
		}else
			result = timeID + " is in use!";
		return result;
	}

	public List<PickupTime> getAllPickupTime() {
		return dao.findAll();
	}

	public PickupTime getPickupTimeById(Integer timeID) {
		return dao.findById(timeID).orElse(null);
	}

}
