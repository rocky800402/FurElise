package com.furelise.complaint.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furelise.city.model.City;

@Service
public class ComplaintService {

	@Autowired
	private ComplaintRepository dao;
	
	public java.util.List<Complaint> getAllComplaint(){
		return dao.findAll();
	}
	
	public Complaint updateComplaint(Complaint complaint) {
		dao.save(complaint);
		return complaint;
	}
	
	public Complaint getOneComplaint(Integer complaintID) {
		return dao.getReferenceById(complaintID);
	}
	
	public Complaint getComplaintById(Integer complaintID) {
		Optional<Complaint> optional = dao.findById(complaintID);
		return optional.orElse(null);
	}
}
