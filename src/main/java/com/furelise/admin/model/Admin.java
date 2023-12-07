package com.furelise.admin.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "admin")
@Data
public class Admin implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "adminID")
	private Integer adminID;
	
	@Column(name = "adminAcc")
	private String adminAcc;
	
	@Column(name = "adName")
	private String adName;
	
	@Column(name = "adPass")
	private String adPass;
	
	@Column(name = "adStatus", columnDefinition = "BIT(1) DEFAULT 0")
	private Boolean adStatus = false;
	
	
	public Admin() {
		super();
	}

	// Constructor without PK(AI) & adStatus(default)
	public Admin(String adminAcc, String adName, String adPass) {
		super();
		this.adminAcc = adminAcc;
		this.adName = adName;
		this.adPass = adPass;
	}

	// Constructor without PK(AI)
	public Admin(String adminAcc, String adName, String adPass, Boolean adStatus) {
		super();
		this.adminAcc = adminAcc;
		this.adName = adName;
		this.adPass = adPass;
		this.adStatus = adStatus;
	}

	public Admin(Integer adminID, String adminAcc, String adName, String adPass, Boolean adStatus) {
		super();
		this.adminID = adminID;
		this.adminAcc = adminAcc;
		this.adName = adName;
		this.adPass = adPass;
		this.adStatus = adStatus;
	}

}