package com.furelise.estabcase.model;

import java.io.Serializable;
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

//import com.furelise.complaint.model.Complaint;
//import com.furelise.emp.model.Emp;
//import com.furelise.planord.model.PlanOrd;

import lombok.Data;



@Entity
@Table(name = "estabcase")
@Data
public class EstabCase implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "estabCaseID", updatable = false)
	private Integer estabCaseID;
	
	@Column(name = "empID")
	private Integer empID;
	
//	@ManyToOne
//	@JoinColumn(name = "empID", referencedColumnName = "empID")
//	private Emp emp;
	
	@Column(name = "planOrdID")
	private Integer planOrdID;
	
//	@ManyToOne
//	@JoinColumn(name = "planOrdID", referencedColumnName = "planOrdID")
//	private PlanOrd planOrd;
	
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
	
//	@OneToMany(mappedBy = "estabCase", cascade = CascadeType.ALL)
//	@OrderBy("estabCaseID asc")
//	private Set<Complaint> complaints;
	
	public EstabCase() {
		
	}

	
}
	



	