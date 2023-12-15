package com.furelise.mem.model.entity;

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

import lombok.Data;

//import com.furelise.complaint.model.Complaint;
//import com.furelise.ord.model.Ord;
//import com.furelise.planord.model.PlanOrd;
//import com.furelise.shopcart.model.ShopCart;

@Entity
@Table(name = "mem")
@Data
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
	
// 	@OneToMany(mappedBy="mem", cascade = CascadeType.ALL)
//    @OrderBy("planOrdID asc")
//    private Set<PlanOrd> planOrds;
//	
// 	@OneToMany(mappedBy="mem", cascade = CascadeType.ALL)
// 	@OrderBy("memID asc")
// 	private Set<ShopCart> shopCarts;
//
// 	@OneToMany(mappedBy="mem", cascade = CascadeType.ALL)
// 	@OrderBy("ordID asc")
// 	private Set<Ord> ords;
// 	
//	@OneToMany(mappedBy="mem", cascade = CascadeType.ALL)
// 	@OrderBy("complaintID asc")
// 	private Set<Complaint> complaints;
 	
	public Mem() {
		super();
	}
	
	

}
