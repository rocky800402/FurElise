package com.furelise.mem.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

//import com.furelise.complaint.model.Complaint;
//import com.furelise.ord.model.Ord;
//import com.furelise.planord.model.PlanOrd;
//import com.furelise.shopcart.model.ShopCart;

@Entity
@Table(name = "mem")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Mem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memID; // 1
	private String memName; // 2
	private String memMail; // 3
	private String memTel; // 4
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate memBirth; // 5
	private String memPass; // 6
	
	// 自動創建時間
    @CreatedDate()
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private LocalDateTime memRegiDate; // 7
    
    // 修改時自動創建時間
    @LastModifiedDate
	private LocalDateTime memLastModified; // 8
	
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
