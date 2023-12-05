package com.furelise.product.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
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
import javax.persistence.Table;

import com.furelise.orddetail.model.OrdDetail;
//import com.furelise.productclass.model.ProductClass;
//import com.furelise.shopcart.model.ShopCart;

import lombok.Data;


@Entity
@Table(name = "product")
@Data
public class Product implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pID", updatable = false)
	private Integer pID;

	@Column(name = "pName")
	private String pName;

	@Column(name = "pPrice")
	private BigDecimal pPrice;
	
	private Integer pClassID;
	
//	@ManyToOne
//	@JoinColumn(name = "pClassID", referencedColumnName = "pClassID")
//	private ProductClass productClass;

	

	@Column(name = "pStock")
	private Integer pStock;

	@Column(name = "pStatus")
	private Boolean pStatus;

	@Column(name = "pUpload")
	private Date pUpload;

	@Column(name = "pDetail")
	private String pDetail;

	@Column(name = "pImage1", columnDefinition = "longblob")
	private byte[] pImage1;

	@Column(name = "pImage2", columnDefinition = "longblob")
	private byte[] pImage2;

	@Column(name = "pImage3", columnDefinition = "longblob")
	private byte[] pImage3;
	
//	@OneToMany( mappedBy = "pID", cascade= CascadeType.ALL)
//	private Set<OrdDetail> ordDetails;


//	@OneToMany( mappedBy = "product", cascade= CascadeType.ALL)
//	private Set<ShopCart> shopCarts;
	
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
}
