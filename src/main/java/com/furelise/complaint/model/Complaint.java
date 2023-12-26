package com.furelise.complaint.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//import com.furelise.emp.model.Emp;
//import com.furelise.estabcase.model.EstabCase;
//import com.furelise.mem.model.Mem;

import lombok.Data;



@Entity
@Table(name = "complaint")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Complaint implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaintID")
	private Integer complaintID;
	private Integer memID;						    
	private Integer empID;
	private Integer estabCaseID;
	private String comDetail;
//	@Pattern(regexp = "^09\\d{2}-\\d{3}-\\d{3}$", message = "電話號碼格式錯誤")
	private String comTel;
	
	@Column(name = "comStatus", columnDefinition = "BIT(1) DEFAULT 0")
	private Boolean comStatus = false;
	
	private Timestamp comStart;
	private Timestamp comEnd;
//	@ManyToOne
//	@JoinColumn(name = "empID", referencedColumnName = "empID")
//	private Emp emp;
//	@ManyToOne
//	@JoinColumn(name = "memID", referencedColumnName = "memID")
//	private Mem mem;
//	@ManyToOne
//	@JoinColumn(name = "estabCaseID", referencedColumnName = "estabCaseID")
//	private EstabCase estabCase;
	
	public Complaint() {
		super();
	}
	
	
}

