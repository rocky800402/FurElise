package com.furelise.pickupway.model;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furelise.planord.model.PlanOrd;
import com.furelise.planord.model.PlanOrdRepository;

@Service
public class PickupWayService {

	@Autowired
	PickupWayRepository dao;
	@Autowired
	PlanOrdRepository planOrdDao;

	public PickupWay addPickupWay(PickupWay req) {
		PickupWay pickupWay = new PickupWay(req.getWayName());
		return dao.save(pickupWay);
	}

	public PickupWay updatePickupWay(PickupWay req) {
		PickupWay pickupWay = new PickupWay(req.getWayID(), req.getWayName());
		return dao.save(pickupWay);
	}

	public String deletePickupWay(Integer wayID) {
		String result = "";
		List<PlanOrd> list = planOrdDao.findByWayID(wayID);
		if (list.isEmpty()) {
			dao.deleteById(wayID);
			result = "deleted successfully";
		} else
			result = wayID + " is in use!";
		return result;
	}
//	public void deletePickupWay(Integer wayID) {
//		dao.deleteById(wayID);
//	}

	public List<PickupWay> getAllPickupWay() {
		return dao.findAll();
	}

	public PickupWay getPickupWayById(Integer wayID) {
		return dao.findById(wayID).orElse(null);
	}

}
