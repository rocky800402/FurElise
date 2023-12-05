package com.furelise.vacation.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.furelise.emp.model.Emp;

import lombok.Data;

@Entity
@Table(name = "vacation")
@Data
public class Vacation implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer vaID;
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "empID", referencedColumnName = "empID")
	private Integer empID;
	private Date vaStart;
	private Date vaEnd;
	
	
	public Vacation() {
		super();
	}
}
