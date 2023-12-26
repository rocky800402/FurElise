package com.furelise.emp.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmpService {
	
	private EmpDAO dao;
	

	
	
	// 故意去掉圖片
	public Emp addEmp(String empName,String empMail,String empTel,LocalDate empBirth,String empPass,String IDNumber,
			Boolean empLicense,String empArea1,String empArea2,String empArea3,Integer timeID,
			String bankCode,String accountNo,Integer workSum,LocalDateTime empRegiDate,Integer empStatus,Boolean empIsSuspended) {
		
		Emp emp = new Emp();
		
		emp.setEmpName(empName);
		emp.setEmpMail(empMail);
		emp.setEmpTel(empTel);
		emp.setEmpBirth(empBirth);
		emp.setEmpPass(empPass);
		emp.setIDNumber(IDNumber);
		emp.setEmpLicense(empLicense);
		emp.setEmpArea1(empArea1);
		emp.setEmpArea2(empArea2);
		emp.setEmpArea3(empArea3);
		emp.setTimeID(timeID);
//		emp.setIDF(IDF);
//		emp.setIDB(IDB);
//		emp.setLicenseF(licenseF);
		emp.setBankCode(bankCode);
		emp.setAccountNo(accountNo);
//		emp.setBankPic(bankPic);
		emp.setWorkSum(workSum);
		emp.setEmpRegiDate(empRegiDate);
		emp.setEmpStatus(empStatus);
		emp.setEmpIsSuspended(empIsSuspended);
		
		return emp;
	}
	
	public void deleteEmp(Integer empID) {
		dao.delete(empID);
	}
	
	// 故意去掉圖片
	public Emp update(Integer empID,String empName,String empMail,String empTel,LocalDate empBirth,String empPass,String IDNumber,
			Boolean empLicense,String empArea1,String empArea2,String empArea3,Integer timeID,
			String bankCode,String accountNo,Integer workSum,Integer empStatus) {
		
		Emp data = dao.findByPK(empID); // 這個方法專屬註冊時間
		Emp emp = new Emp();
		emp.setEmpID(empID);
		emp.setEmpName(empName);
		emp.setEmpMail(empMail);
		emp.setEmpTel(empTel);
		emp.setEmpBirth(empBirth);
		emp.setEmpPass(empPass);
		emp.setIDNumber(IDNumber);
		emp.setEmpLicense(empLicense);
		emp.setEmpArea1(empArea1);
		emp.setEmpArea2(empArea2);
		emp.setEmpArea3(empArea3);
		emp.setTimeID(timeID);
//		emp.setIDF(IDF);
//		emp.setIDB(IDB);
//		emp.setLicenseF(licenseF);
		emp.setBankCode(bankCode);
		emp.setAccountNo(accountNo);
//		emp.setBankPic(bankPic);
		emp.setWorkSum(workSum);
		emp.setEmpRegiDate(data.getEmpRegiDate());
		emp.setEmpStatus(empStatus);
//		emp.setEmpIsSuspended(empIsSuspended);
		dao.update(emp);
		
		return emp;
	}
	
	public Emp getOneDisplay(Integer empID) {
		return dao.findByPK(empID);
	}
}
