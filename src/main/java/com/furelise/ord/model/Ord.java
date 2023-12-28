package com.furelise.ord.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import com.furelise.city.model.City;
import com.furelise.orddetail.model.OrdDetail;
import com.furelise.sale.model.Sale;

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
	
	
//	private List<OrdDetailBO> ordDetailBOs;
	
	@OneToMany(mappedBy = "ordID", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrdDetail> ordDetails;

	public Ord() {
		super();
	}


//	public Ord(City city, Ord ord, List<OrdDetailBO> OrdDetailBOList, @Nullable Sale sale) {
//		this.cityCode = ord.getCityCode();
//        this.ordID = ord.getOrdID();
//        this.address = ord.getAddress();
//        this.payment = ord.getPayment();
//        this.deliver = ord.getDeliver();
//        this.ordDate = ord.getOrdDate();
//        this.deliverDate = ord.getDeliverDate();
//        this.sum = ord.getSum();
//        this.shipping = ord.getShipping();
//        this.saleID = ord.getSaleID();
//        this.total = ord.getTotal();
//        this.ordDetailBOs = OrdDetailBOList;
//        this.ordStatus =ord.getOrdStatus();
//	}

	

	
}