package com.furelise.admin.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "`admin`")
public class Admin {

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


	public Integer getAdminID() {
		return adminID;
	}


	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}


	public String getAdminAcc() {
		return adminAcc;
	}


	public void setAdminAcc(String adminAcc) {
		this.adminAcc = adminAcc;
	}


	public String getAdName() {
		return adName;
	}


	public void setAdName(String adName) {
		this.adName = adName;
	}


	public String getAdPass() {
		return adPass;
	}


	public void setAdPass(String adPass) {
		this.adPass = adPass;
	}


	public Boolean getAdStatus() {
		return adStatus;
	}


	public void setAdStatus(Boolean adStatus) {
		this.adStatus = adStatus;
	}


	@Override
	public String toString() {
		return "Admin [adminID=" + adminID + ", adminAcc=" + adminAcc + ", adName=" + adName + ", adPass=" + adPass
				+ ", adStatus=" + adStatus + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(adName, adPass, adStatus, adminAcc, adminID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		return Objects.equals(adName, other.adName) && Objects.equals(adPass, other.adPass)
				&& Objects.equals(adStatus, other.adStatus) && Objects.equals(adminAcc, other.adminAcc)
				&& Objects.equals(adminID, other.adminID);
	}
	
	
}