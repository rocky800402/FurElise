package com.furelise.pickuptime.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PickupTimeService {

	@Autowired
	PickupTimeRepository dao;

	public PickupTime addPickupTime(PickupTime req) {
		PickupTime pickupTime = new PickupTime(req.getTimeRange());
		return dao.save(pickupTime);
	}

	public PickupTime updatePickupTime(PickupTime req) {
		PickupTime pickupTime = new PickupTime(req.getTimeID(), req.getTimeRange());
		return dao.save(pickupTime);
	}

	public void deletePickupTime(Integer timeID) {
		dao.deleteById(timeID);
	}

	public List<PickupTime> getAllPickupTime() {
		return dao.findAll();
	}
	
	public PickupTime getPickupTimeById(Integer timeID) {
		return dao.findById(timeID).orElse(null);
	}

}
