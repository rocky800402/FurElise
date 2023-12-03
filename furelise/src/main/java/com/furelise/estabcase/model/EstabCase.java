package com.furelise.estabcase.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import com.furelise.emp.model.Emp;
import com.furelise.planord.model.PlanOrd;



@Entity
@Table(name = "estabCase")
public class EstabCase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "estabCaseID", updatable = false)
	private Integer estabCaseID;
	
	@Column(name = "empID")
	private Integer empID;
	
	@ManyToOne
	@JoinColumn(name = "empID", referencedColumnName = "empID")
	private Emp emp;
	
	@Column(name = "planOrdID")
	private Integer planOrdID;
	
	@ManyToOne
	@JoinColumn(name = "planOrdID", referencedColumnName = "planOrdID")
	private PlanOrd planOrd;
	
	@Column(name = "estabCaseDate")
	private Date estabCaseDate;
	
//	@CreationTimestamp
	@UpdateTimestamp
	@Column(name = "estabCaseStart")
	private Timestamp estabCaseStart;
	
	@Column(name = "estabCaseEnd")
	private Timestamp estabCaseEnd;
	
	@Column(name = "planPricePerCase")
	private BigDecimal planPricePerCase;
	
	@Column(name = "estabCasePic", columnDefinition = "longblob")
	private byte[] estabCasePic;
	
	@Column(name = "takeStatus",nullable = true)
	private boolean takeStatus;
	
	@Column(name = "estabCaseStatus" ,nullable = true)
	private Integer estabCaseStatus;
	
	@Column(name = "estabCaseLevel",nullable = true)
	private Integer estabCaseLevel;
	
	@Column(name = "estabCaseFeedback",nullable = true)
	private String estabCaseFeedback;
	
	@Column(name = "estabCaseFBTime",nullable = true)
	private Timestamp estabCaseFBTime;
	
	@OneToMany(mappedBy = "estabCase", cascade = CascadeType.ALL)
	@OrderBy("estabCaseID asc")
	private Set<complaint> complaints;
	
	public EstabCase() {
		
	}

	

	public EstabCase(Integer empID, Emp emp, Integer planOrdID, PlanOrd planOrd, Date estabCaseDate,
			Timestamp estabCaseStart, Timestamp estabCaseEnd, BigDecimal planPricePerCase, byte[] estabCasePic,
			boolean takeStatus, Integer estabCaseStatus, Integer estabCaseLevel, String estabCaseFeedback,
			Timestamp estabCaseFBTime, Set<complaint> complaints) {
		super();
		this.empID = empID;
		this.emp = emp;
		this.planOrdID = planOrdID;
		this.planOrd = planOrd;
		this.estabCaseDate = estabCaseDate;
		this.estabCaseStart = estabCaseStart;
		this.estabCaseEnd = estabCaseEnd;
		this.planPricePerCase = planPricePerCase;
		this.estabCasePic = estabCasePic;
		this.takeStatus = takeStatus;
		this.estabCaseStatus = estabCaseStatus;
		this.estabCaseLevel = estabCaseLevel;
		this.estabCaseFeedback = estabCaseFeedback;
		this.estabCaseFBTime = estabCaseFBTime;
		this.complaints = complaints;
	}



	public EstabCase(Integer estabCaseID, Integer empID, Emp emp, Integer planOrdID, PlanOrd planOrd,
			Date estabCaseDate, Timestamp estabCaseStart, Timestamp estabCaseEnd, BigDecimal planPricePerCase,
			byte[] estabCasePic, boolean takeStatus, Integer estabCaseStatus, Integer estabCaseLevel,
			String estabCaseFeedback, Timestamp estabCaseFBTime, Set<complaint> complaints) {
		super();
		this.estabCaseID = estabCaseID;
		this.empID = empID;
		this.emp = emp;
		this.planOrdID = planOrdID;
		this.planOrd = planOrd;
		this.estabCaseDate = estabCaseDate;
		this.estabCaseStart = estabCaseStart;
		this.estabCaseEnd = estabCaseEnd;
		this.planPricePerCase = planPricePerCase;
		this.estabCasePic = estabCasePic;
		this.takeStatus = takeStatus;
		this.estabCaseStatus = estabCaseStatus;
		this.estabCaseLevel = estabCaseLevel;
		this.estabCaseFeedback = estabCaseFeedback;
		this.estabCaseFBTime = estabCaseFBTime;
		this.complaints = complaints;
	}



	public Emp getEmp() {
		return emp;
	}



	public void setEmp(Emp emp) {
		this.emp = emp;
	}



	public PlanOrd getPlanOrd() {
		return planOrd;
	}



	public void setPlanOrd(PlanOrd planOrd) {
		this.planOrd = planOrd;
	}



	public Set<complaint> getComplaints() {
		return complaints;
	}



	public void setComplaints(Set<complaint> complaints) {
		this.complaints = complaints;
	}



	public Integer getEstabCaseID() {
		return estabCaseID;
	}

	public void setEstabCaseID(Integer estabCaseID) {
		this.estabCaseID = estabCaseID;
	}

	public Integer getEmpID() {
		return empID;
	}

	public void setEmpID(Integer empID) {
		this.empID = empID;
	}

	public Integer getPlanOrdID() {
		return planOrdID;
	}

	public void setPlanOrdID(Integer planOrdID) {
		this.planOrdID = planOrdID;
	}

	public Date getEstabCaseDate() {
		return estabCaseDate;
	}

	public void setEstabCaseDate(Date estabCaseDate) {
		this.estabCaseDate = estabCaseDate;
	}

	public Timestamp getEstabCaseStart() {
		return estabCaseStart;
	}

	public void setEstabCaseStart(Timestamp estabCaseStart) {
		this.estabCaseStart = estabCaseStart;
	}

	public BigDecimal getPlanPricePerCase() {
		return planPricePerCase;
	}

	public void setPlanPricePerCase(BigDecimal planPricePerCase) {
		this.planPricePerCase = planPricePerCase;
	}

	public Timestamp getEstabCaseEnd() {
		return estabCaseEnd;
	}

	public void setEstabCaseEnd(Timestamp estabCaseEnd) {
		this.estabCaseEnd = estabCaseEnd;
	}

	public byte[] getEstabCasePic() {
		return estabCasePic;
	}

	public void setEstabCasePic(byte[] estabCasePic) {
		this.estabCasePic = estabCasePic;
	}

	public boolean getTakeStatus() {
		return takeStatus;
	}

	public void setTakeStatus(boolean takeStatus) {
		this.takeStatus = takeStatus;
	}

	public Integer getEstabCaseStatus() {
		return estabCaseStatus;
	}

	public void setEstabCaseStatus(Integer estabCaseStatus) {
		this.estabCaseStatus = estabCaseStatus;
	}

	public Integer getEstabCaseLevel() {
		return estabCaseLevel;
	}

	public void setEstabCaseLevel(Integer estabCaseLevel) {
		this.estabCaseLevel = estabCaseLevel;
	}

	public String getEstabCaseFeedback() {
		return estabCaseFeedback;
	}

	public void setEstabCaseFeedback(String estabCaseFeedback) {
		this.estabCaseFeedback = estabCaseFeedback;
	}

	public Timestamp getEstabCaseFBTime() {
		return estabCaseFBTime;
	}

	public void setEstabCaseFBTime(Timestamp estabCaseFBTime) {
		this.estabCaseFBTime = estabCaseFBTime;
	}



	@Override
	public String toString() {
		return "EstabCase [estabCaseID=" + estabCaseID + ", empID=" + empID + ", planOrdID=" + planOrdID + ", planOrd="
				+ planOrd + ", estabCaseDate=" + estabCaseDate + ", estabCaseStart=" + estabCaseStart
				+ ", estabCaseEnd=" + estabCaseEnd + ", planPricePerCase=" + planPricePerCase + ", estabCasePic="
				+ Arrays.toString(estabCasePic) + ", takeStatus=" + takeStatus + ", estabCaseStatus=" + estabCaseStatus
				+ ", estabCaseLevel=" + estabCaseLevel + ", estabCaseFeedback=" + estabCaseFeedback
				+ ", estabCaseFBTime=" + estabCaseFBTime + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(estabCasePic);
		result = prime * result + Objects.hash(empID, estabCaseDate, estabCaseEnd, estabCaseFBTime, estabCaseFeedback,
				estabCaseID, estabCaseLevel, estabCaseStart, estabCaseStatus, planOrd, planOrdID, planPricePerCase,
				takeStatus);
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstabCase other = (EstabCase) obj;
		return Objects.equals(empID, other.empID) && Objects.equals(estabCaseDate, other.estabCaseDate)
				&& Objects.equals(estabCaseEnd, other.estabCaseEnd)
				&& Objects.equals(estabCaseFBTime, other.estabCaseFBTime)
				&& Objects.equals(estabCaseFeedback, other.estabCaseFeedback)
				&& Objects.equals(estabCaseID, other.estabCaseID)
				&& Objects.equals(estabCaseLevel, other.estabCaseLevel)
				&& Arrays.equals(estabCasePic, other.estabCasePic)
				&& Objects.equals(estabCaseStart, other.estabCaseStart)
				&& Objects.equals(estabCaseStatus, other.estabCaseStatus) && Objects.equals(planOrd, other.planOrd)
				&& Objects.equals(planOrdID, other.planOrdID)
				&& Objects.equals(planPricePerCase, other.planPricePerCase) && takeStatus == other.takeStatus;
	}

	
	
	

}