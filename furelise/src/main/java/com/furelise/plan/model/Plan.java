package com.furelise.plan.model;

import java.math.BigDecimal;
import java.sql.Date;
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

import com.furelise.planord.model.PlanOrd;

@Entity
@Table(name = "plan")
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "planID", updatable = false)
	private Integer planID;

	@Column(name = "planName")
	private String planName;

	@Column(name = "liter")
	private Integer liter;

	@Column(name = "planPrice")
	private BigDecimal planPrice;

	@Column(name = "planPricePerCase")
	private BigDecimal planPricePerCase;

	@Column(name = "times")
	private Integer times;

	@Column(name = "planUpload")
	private Date planUpload;
	
	@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
	@OrderBy("planOrdID asc")
	private Set<PlanOrd> planOrds;


	public Plan() {
		super();
	}

	

	public Plan(String planName, Integer liter, BigDecimal planPrice, BigDecimal planPricePerCase, Integer times,
			Date planUpload, Set<PlanOrd> planOrds) {
		super();
		this.planName = planName;
		this.liter = liter;
		this.planPrice = planPrice;
		this.planPricePerCase = planPricePerCase;
		this.times = times;
		this.planUpload = planUpload;
		this.planOrds = planOrds;
	}



	public Plan(Integer planID, String planName, Integer liter, BigDecimal planPrice, BigDecimal planPricePerCase,
			Integer times, Date planUpload, Set<PlanOrd> planOrds) {
		super();
		this.planID = planID;
		this.planName = planName;
		this.liter = liter;
		this.planPrice = planPrice;
		this.planPricePerCase = planPricePerCase;
		this.times = times;
		this.planUpload = planUpload;
		this.planOrds = planOrds;
	}



	public Integer getPlanID() {
		return planID;
	}

	public void setPlanID(Integer planID) {
		this.planID = planID;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getLiter() {
		return liter;
	}

	public void setLiter(Integer liter) {
		this.liter = liter;
	}

	public BigDecimal getPlanPrice() {
		return planPrice;
	}

	public void setPlanPrice(BigDecimal planPrice) {
		this.planPrice = planPrice;
	}

	public BigDecimal getPlanPricePerCase() {
		return planPricePerCase;
	}

	public void setPlanPricePerCase(BigDecimal planPricePerCase) {
		this.planPricePerCase = planPricePerCase;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Date getPlanUpload() {
		return planUpload;
	}

	public void setPlanUpload(Date planUpload) {
		this.planUpload = planUpload;
	}
	
	public Set<PlanOrd> getPlanOrds() {
		return planOrds;
	}
	
	public void setPlanOrds(Set<PlanOrd> planOrds) {
		this.planOrds = planOrds;
	}



	@Override
	public String toString() {
		return "Plan [planID=" + planID + ", planName=" + planName + ", liter=" + liter + ", planPrice=" + planPrice
				+ ", planPricePerCase=" + planPricePerCase + ", times=" + times + ", planUpload=" + planUpload
				+ ", planOrds=" + planOrds + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(liter, planID, planName, planOrds, planPrice, planPricePerCase, planUpload, times);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plan other = (Plan) obj;
		return Objects.equals(liter, other.liter) && Objects.equals(planID, other.planID)
				&& Objects.equals(planName, other.planName) && Objects.equals(planOrds, other.planOrds)
				&& Objects.equals(planPrice, other.planPrice)
				&& Objects.equals(planPricePerCase, other.planPricePerCase)
				&& Objects.equals(planUpload, other.planUpload) && Objects.equals(times, other.times);
	}

	
	

}
