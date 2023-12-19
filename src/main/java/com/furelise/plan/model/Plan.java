package com.furelise.plan.model;

import java.io.Serializable;
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
import javax.validation.constraints.Min;

import lombok.Data;



@Entity
@Table(name = "plan")
@Data
public class Plan implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "planID", updatable = false)
	private Integer planID;

	@Column(name = "planName")
	private String planName;

	@Min(value=1, message="垃圾容量請填正整數")
	@Column(name = "liter")
	private Integer liter;

	@Min(value=1, message="方案價格請填正整數")
	@Column(name = "planPrice")
	private BigDecimal planPrice;

	@Min(value=1, message="單筆案件報酬請填正整數")
	@Column(name = "planPricePerCase")
	private BigDecimal planPricePerCase;

	@Column(name = "times")
	private Integer times;

	@Column(name = "planUpload")
	private Date planUpload;
	
//	@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
//	@OrderBy("planOrdID asc")
//	private Set<PlanOrd> planOrds;

	public Plan() {
		super();
	}
	
	public Plan(String planName, Integer liter, BigDecimal planPrice, BigDecimal planPricePerCase, Integer times, Date planUpload) {
		this.planName = planName;
		this.liter = liter;
		this.planPrice = planPrice;
		this.planPricePerCase = planPricePerCase;
		this.times = times;
		this.planUpload = planUpload;		
	}

	public Plan(Integer planID, String planName, Integer liter, BigDecimal planPrice, BigDecimal planPricePerCase,
			Integer times, Date planUpload) {
		super();
		this.planID = planID;
		this.planName = planName;
		this.liter = liter;
		this.planPrice = planPrice;
		this.planPricePerCase = planPricePerCase;
		this.times = times;
		this.planUpload = planUpload;
	}
}
