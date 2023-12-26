package com.furelise.emp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furelise.emp.model.*;

@Service
public class BackEndEmpService {
	
	@Autowired
	EmpRepository dao;
	
	public Emp updateEmp(Emp emp) {
		dao.save(emp);
		return emp;
	}
	
	public Emp getOneEmp(Integer empID) {
		return dao.getReferenceById(empID);
	}
	
	public java.util.List<Emp> getAllEmp(){
		return dao.findAll();
	}

	public Emp getEmpById(Integer empID) {
		Optional<Emp> optional = dao.findById(empID);
		return optional.orElse(null);
	}

}
