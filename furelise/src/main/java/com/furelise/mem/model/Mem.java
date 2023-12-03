package com.furelise.mem.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.furelise.city.model.Emp;
import com.furelise.ord.model.Ord;
import com.furelise.planord.model.PlanOrd;
import com.furelise.shopcart.model.ShopCart;

@Entity
@Table(name = "mem")
public class Mem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memID; // 1
	private String memName; // 2
	private String memMail; // 3
	private String memTel; // 4
	private Date memBirth; // 5
	private String memPass; // 6
	private Timestamp memRegiDate; // 7
	private Timestamp memLastModified; // 8
	
	@Column(name = "memIsSuspended", columnDefinition = "BIT(1) DEFAULT 0")
	private Boolean memIsSuspended = false; // 9
	
 	@OneToMany(mappedBy="mem", cascade = CascadeType.ALL)
    @OrderBy("planOrdID asc")
    private Set<PlanOrd> planOrds;
	
 	@OneToMany(mappedBy="mem", cascade = CascadeType.ALL)
 	@OrderBy("memID asc")
 	private Set<ShopCart> shopCarts;

 	@OneToMany(mappedBy="mem", cascade = CascadeType.ALL)
 	@OrderBy("ordID asc")
 	private Set<Ord> ords;
 	
	@OneToMany(mappedBy="mem", cascade = CascadeType.ALL)
 	@OrderBy("complaintID asc")
 	private Set<Complaint> complaints;
 	
	public Mem() {
		super();
	}
	

	public Mem(String memName, String memMail, String memTel, Date memBirth, String memPass, Timestamp memRegiDate,
			Timestamp memLastModified, Boolean memIsSuspended, Set<PlanOrd> planOrds, Set<ShopCart> shopCarts,
			Set<Ord> ords, Set<Complaint> complaints) {
		super();
		this.memName = memName;
		this.memMail = memMail;
		this.memTel = memTel;
		this.memBirth = memBirth;
		this.memPass = memPass;
		this.memRegiDate = memRegiDate;
		this.memLastModified = memLastModified;
		this.memIsSuspended = memIsSuspended;
		this.planOrds = planOrds;
		this.shopCarts = shopCarts;
		this.ords = ords;
		this.complaints = complaints;
	}


	public Mem(Integer memID, String memName, String memMail, String memTel, Date memBirth, String memPass,
			Timestamp memRegiDate, Timestamp memLastModified, boolean memIsSuspended) {
		super();
		this.memID = memID;
		this.memName = memName;
		this.memMail = memMail;
		this.memTel = memTel;
		this.memBirth = memBirth;
		this.memPass = memPass;
		this.memRegiDate = memRegiDate;
		this.memLastModified = memLastModified;
		this.memIsSuspended = memIsSuspended;
	}

	public Boolean getMemIsSuspended() {
		return memIsSuspended;
	}

	public void setMemIsSuspended(Boolean memIsSuspended) {
		this.memIsSuspended = memIsSuspended;
	}

	public Set<PlanOrd> getPlanOrds() {
		return planOrds;
	}

	public void setPlanOrds(Set<PlanOrd> planOrds) {
		this.planOrds = planOrds;
	}

	public Set<ShopCart> getShopCarts() {
		return shopCarts;
	}

	public void setShopCarts(Set<ShopCart> shopCarts) {
		this.shopCarts = shopCarts;
	}

	public Set<Ord> getOrds() {
		return ords;
	}

	public void setOrds(Set<Ord> ords) {
		this.ords = ords;
	}

	public Set<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(Set<Complaint> complaints) {
		this.complaints = complaints;
	}

	public Integer getMemID() {
		return memID;
	}

	public void setMemID(Integer memID) {
		this.memID = memID;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemMail() {
		return memMail;
	}

	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}

	public String getMemTel() {
		return memTel;
	}

	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}

	public Date getMemBirth() {
		return memBirth;
	}

	public void setMemBirth(Date memBirth) {
		this.memBirth = memBirth;
	}

	public String getMemPass() {
		return memPass;
	}

	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}

	public Timestamp getMemRegiDate() {
		return memRegiDate;
	}

	public void setMemRegiDate(Timestamp memRegiDate) {
		this.memRegiDate = memRegiDate;
	}

	public Timestamp getMemLastModified() {
		return memLastModified;
	}

	public void setMemLastModified(Timestamp memLastModified) {
		this.memLastModified = memLastModified;
	}

	public boolean isMemIsSuspended() {
		return memIsSuspended;
	}

	public void setMemIsSuspended(boolean memIsSuspended) {
		this.memIsSuspended = memIsSuspended;
	}

	@Override
	public String toString() {
		return "Mem [memID=" + memID + ", memName=" + memName + ", memMail=" + memMail + ", memTel=" + memTel
				+ ", memBirth=" + memBirth + ", memPass=" + memPass + ", memRegiDate=" + memRegiDate
				+ ", memLastModified=" + memLastModified + ", memIsSuspended=" + memIsSuspended + ", planOrds="
				+ planOrds + ", shopCarts=" + shopCarts + ", ords=" + ords + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(memBirth, memID, memIsSuspended, memLastModified, memMail, memName, memPass, memRegiDate,
				memTel, ords, planOrds, shopCarts);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mem other = (Mem) obj;
		return Objects.equals(memBirth, other.memBirth) && Objects.equals(memID, other.memID)
				&& Objects.equals(memIsSuspended, other.memIsSuspended)
				&& Objects.equals(memLastModified, other.memLastModified) && Objects.equals(memMail, other.memMail)
				&& Objects.equals(memName, other.memName) && Objects.equals(memPass, other.memPass)
				&& Objects.equals(memRegiDate, other.memRegiDate) && Objects.equals(memTel, other.memTel)
				&& Objects.equals(ords, other.ords) && Objects.equals(planOrds, other.planOrds)
				&& Objects.equals(shopCarts, other.shopCarts);
	}

	

}
