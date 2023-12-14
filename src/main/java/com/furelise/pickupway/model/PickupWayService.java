package com.furelise.pickupway.model;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PickupWayService {
	
	@Autowired
	PickupWayRepository dao;
	
	public PickupWay addPickupWay(PickupWay req) {
		PickupWay pickupWay = new PickupWay();
		pickupWay.setWayName(req.getWayName());
		return dao.save(pickupWay);
	}

	public PickupWay updatePickupWay(PickupWay req) {
		PickupWay pickupWay = new PickupWay();
		pickupWay.setWayID(req.getWayID());
		pickupWay.setWayName(req.getWayName());
		return dao.save(pickupWay);
	}

	public void deletePickupWay(Integer wayID) {
		dao.deleteById(wayID);
	}

	public List<PickupWay> getAllPickupWay() {
		return dao.findAll();
	}
	
	public PickupWay getPickupWayById(Integer wayID) {
		return dao.findById(wayID).orElse(null);
	}
	
}
