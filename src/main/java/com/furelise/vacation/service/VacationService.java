package com.furelise.vacation.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furelise.vacation.model.entity.Vacation;
import com.furelise.vacation.repository.VacationRepository;



@Service
public class VacationService {

	@Autowired
	private VacationRepository vaR;

	public VacationService() {
		super();
	}
	
	public Vacation addVacation(Integer empID, Date vaStart, Date vaEnd) {
		
		Vacation va = new Vacation();
		
		va.setEmpID(empID);
		va.setVaStart(vaStart);
		va.setVaEnd(vaEnd);
		
		vaR.save(va);
		
		return va;
	}
	
	
	
	public Vacation updateVacation(Integer empID, Date vaStart, Date vaEnd) {
		
		Vacation va = new Vacation();
		
		va.setEmpID(empID);
		va.setVaStart(vaStart);
		va.setVaEnd(vaEnd);
		
		vaR.save(va);
		
		return va;
	}
	
	public Vacation updateVacation(Vacation va) {
		vaR.save(va);
		return va;
	}
	
	public Vacation getOneVacation(Integer vaID) {
		return vaR.getReferenceById(vaID);
	}
	
	public List<Vacation> getAllVacation(Integer empID) {
		return vaR.findByEmpID(empID);
	}
	
	public List<Vacation> getAllVacationDesc(Integer empID) {
		return vaR.findByEmpIDOrderByVaIDDesc(empID);
	}

	
}
