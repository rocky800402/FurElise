package com.furelise.emp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furelise.emp.model.EmpLoginDTO;
import com.furelise.emp.model.Emp;
import com.furelise.emp.model.EmpRepository;
import com.furelise.exception.UnauthorizedException;

@Service
public class EmpAuthService {

	@Autowired
	private EmpRepository empRepository;
	
	
	public Emp addEmp(Emp emp) {
		empRepository.save(emp);
		return emp;
	}
	
	public Emp updateEmp(Emp emp) {
		empRepository.save(emp);
		return emp;
	}
	
	public Emp findByEmpMail(String empMail) {
		return this.empRepository.findByEmpMail(empMail);
	}
	
	public Emp getOneEmp(Integer empID) {
		return empRepository.getReferenceById(empID);
	}
	
	
	public Emp verify(EmpLoginDTO dto) {
		Emp emp = this.findByEmpMail(dto.getEmail());
		// 判斷有無emp存在，或已被停權，或密碼輸入錯誤，或帳號尚未審核通過
		if (emp == null 
				|| emp.getEmpIsSuspended() 
				|| !dto.getPassword().equals(emp.getEmpPass())) {
			throw new UnauthorizedException("The account or password is incorrect");
		} else if (emp.getEmpStatus() != 1) {
			throw new UnauthorizedException("The account hasn't been approved");
		}
		return emp;
	}
	
}
