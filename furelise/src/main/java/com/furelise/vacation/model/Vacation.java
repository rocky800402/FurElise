package com.furelise.vacation.model;

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

import com.furelise.emp.Emp;

@Entity
@Table(name = "vacation")
public class Vacation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer vaID;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empID", referencedColumnName = "empID")
	private Emp emp;
	private Date vaStart;
	private Date vaEnd;
	
	
	public Vacation() {
		super();
	}

	// Constructor without PK
	public Vacation(Emp emp, Date vaStart, Date vaEnd) {
		super();
		this.emp = emp;
		this.vaStart = vaStart;
		this.vaEnd = vaEnd;
	}

	public Vacation(Integer vaID, Emp emp, Date vaStart, Date vaEnd) {
		super();
		this.vaID = vaID;
		this.emp = emp;
		this.vaStart = vaStart;
		this.vaEnd = vaEnd;
	}

	public Integer getVaID() {
		return vaID;
	}

	public void setVaID(Integer vaID) {
		this.vaID = vaID;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public Date getVaStart() {
		return vaStart;
	}

	public void setVaStart(Date vaStart) {
		this.vaStart = vaStart;
	}

	public Date getVaEnd() {
		return vaEnd;
	}

	public void setVaEnd(Date vaEnd) {
		this.vaEnd = vaEnd;
	}

	@Override
	public String toString() {
		return "Vacation [vaID=" + vaID + ", vaStart=" + vaStart + ", vaEnd=" + vaEnd + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(vaEnd, vaID, vaStart);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vacation other = (Vacation) obj;
		return Objects.equals(vaEnd, other.vaEnd) && Objects.equals(vaID, other.vaID)
				&& Objects.equals(vaStart, other.vaStart);
	}

	

}
