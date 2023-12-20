package com.furelise.sale.model;

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
import javax.persistence.Table;

import com.furelise.ord.model.Ord;
import com.furelise.product.model.Product;

import lombok.Data;

@Entity
@Table(name = "Sale")
@Data
public class Sale implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "saleID", updatable = false)
	private Integer saleID;
	
	@Column(name = "coupon")
	private String coupon;
	
	@Column(name = "saleStart")
	private Date saleStart;
	
	@Column(name = "saleEnd")
	private Date saleEnd;
	
	@Column(name = "disRequire")
	private BigDecimal disRequire;
	
	@Column(name = "dis")
	private BigDecimal dis;
	
//	@OneToMany(mappedBy="sale", cascade= CascadeType.ALL)
//	private Set<Ord> ords;	
	
	public Sale() {
		super();
	}
	
	public Sale(Integer saleID, String coupon, Date saleStart, 
			Date saleEnd, BigDecimal disRequire, BigDecimal dis) {
		this.saleID = saleID;
		this.coupon = coupon;
		this.saleStart = saleStart;
		this.saleEnd = saleEnd;
		this.disRequire = disRequire;
		this.dis = dis;
	}

	public Sale(String coupon, Date saleStart, 
			Date saleEnd, BigDecimal disRequire, BigDecimal dis) {
		this.coupon = coupon;
		this.saleStart = saleStart;
		this.saleEnd = saleEnd;
		this.disRequire = disRequire;
		this.dis = dis;
	}
	
	
}
