package com.furelise.emp.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;

import com.furelise.emp.model.Emp;

import lombok.Data;

@Data
public class EmpVO {

    private Integer empID;
    private String empName;
    private String empMail;
    private String empTel;
    private LocalDate empBirth;
    private String IDNumber;
    private boolean empLicense;
    private String empArea1;
    private String empArea2;
    private String empArea3;
    private Integer timeID;
    private byte[] IDF;
    private byte[] IDB;
    private byte[] licenseF;
    private String bankCode;
    private String accountNo;
    private byte[] bankPic;
    private Integer workSum;
    private LocalDateTime empRegiDate;
//    private Integer empStatus;
    
    
	public EmpVO() {
	}
	
	public EmpVO(Emp emp) {
		this.empID = emp.getEmpID();
		this.empName = emp.getEmpName();
		this.empMail = emp.getEmpMail();
		this.empTel = emp.getEmpTel();
		this.empBirth = emp.getEmpBirth();
		this.IDNumber = emp.getIDNumber();
		this.empLicense = emp.isEmpLicense();
		this.empArea1 = emp.getEmpArea1();
		this.empArea2 = emp.getEmpArea2();
		this.empArea3 = emp.getEmpArea3();
		this.timeID = emp.getTimeID();
		this.IDF = emp.getIDF();
		this.IDB = emp.getIDB();
		this.licenseF = emp.getLicenseF();
		this.bankCode = emp.getBankCode();
		this.accountNo = emp.getAccountNo();
		this.bankPic = emp.getBankPic();
		this.workSum = emp.getWorkSum();
		this.empRegiDate = emp.getEmpRegiDate();
//		this.empStatus = emp.getEmpStatus();
	}

	
}
