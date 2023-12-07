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
	
//	@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
//	@OrderBy("planOrdID asc")
//	private Set<PlanOrd> planOrds;


	public Plan() {
		super();
	}

}
