package com.furelise.ord.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.furelise.orddetail.model.OrdDetail;
import com.furelise.orddetail.model.OrdDetailPK;

//import com.furelise.mem.model.Mem;

//import com.furelise.sale.model.Sale;

import lombok.Data;

@Entity
@Table(name = "ord")
@Data
public class Ord implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ordID", updatable = false)
	private Integer ordID;
	
	@Column(name = "ordDate")
	private Timestamp ordDate;
	
	@Column(name = "memID")
	private Integer memID;
	
	@Column(name = "payment")
	private Integer payment;
	
	@Column(name = "deliver")
	private Integer deliver;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "cityCode", columnDefinition = "CHAR")
	private String cityCode;
	
	@Column(name = "deliverDate")
	private Date deliverDate;
	
	@Column(name = "sum")
	private BigDecimal sum;
	
	@Column(name = "shipping")
	private BigDecimal shipping;
	
	@Column(name = "total")
	private BigDecimal total;
	
	@Column(name = "saleID")
	private Integer saleID;
	
	@Column(name = "ordStatus")
	private Integer ordStatus;
	
	@Column(name = "arrival")
	private Date arrival;
	
//	@ManyToOne
//	@JoinColumn(name = "saleID", referencedColumnName = "saleID")
//	private Sale sale;
	
//	@ManyToOne
//	@JoinColumn(name = "memID", referencedColumnName = "memID")
//	private Mem mem;
	
	@OneToMany(mappedBy = "ordID", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrdDetail> ordDetails;


//	public Set<OrdDetail> getOrdDetails() {
//		return ordDetails;
//	}
//
//	public void setOrdDetails(Set<OrdDetail> ordDetails) {
//		this.ordDetails = ordDetails;
//	}

	public Ord() {
		super();
	}


	public Ord(Timestamp ordDate, Integer memID, Integer payment, Integer deliver, String address, String cityCode,
			Date deliverDate, BigDecimal sum, BigDecimal shipping, BigDecimal total, Integer saleID, Integer ordStatus,
			Date arrival, List<OrdDetail> ordDetails) {
		super();
		this.ordDate = ordDate;
		this.memID = memID;
		this.payment = payment;
		this.deliver = deliver;
		this.address = address;
		this.cityCode = cityCode;
		this.deliverDate = deliverDate;
		this.sum = sum;
		this.shipping = shipping;
		this.total = total;
		this.saleID = saleID;
		this.ordStatus = ordStatus;
		this.arrival = arrival;
		this.ordDetails = ordDetails;
	}

	

	
}