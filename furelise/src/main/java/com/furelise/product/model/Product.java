package com.furelise.product.model;

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
import com.furelise.productclass.model.ProductClass;
import com.furelise.shopcart.model.ShopCart;

@Entity
@Table(name = "Product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pID", updatable = false)
	private Integer pID;

	@Column(name = "pName")
	private String pName;

	@Column(name = "pPrice")
	private BigDecimal pPrice;

	
	@ManyToOne
	@JoinColumn(name = "pClassID", referencedColumnName = "pClassID")
	private ProductClass productClass;

	public ProductClass getProductClass() {
		return productClass;
	}

	public void setProductClass(ProductClass productClass) {
		this.productClass = productClass;
	}

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
	
	@OneToMany( mappedBy = "product", cascade= CascadeType.ALL)
	private Set<OrdDetail> ordDetails;

	@OneToMany( mappedBy = "product", cascade= CascadeType.ALL)
	private Set<ShopCart> shopCarts;
	
	
	
	public Set<ShopCart> getShopCarts() {
		return shopCarts;
	}

	public void setShopCarts(Set<ShopCart> shopCarts) {
		this.shopCarts = shopCarts;
	}

	public Set<OrdDetail> getOrdDetails() {
		return ordDetails;
	}

	public void setOrdDetails(Set<OrdDetail> ordDetails) {
		this.ordDetails = ordDetails;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Product(String pName, BigDecimal pPrice, ProductClass productClass, Integer pStock, Boolean pStatus,
			Date pUpload, String pDetail, byte[] pImage1, byte[] pImage2, byte[] pImage3, Set<OrdDetail> ordDetails,
			Set<ShopCart> shopCarts) {
		super();
		this.pName = pName;
		this.pPrice = pPrice;
		this.productClass = productClass;
		this.pStock = pStock;
		this.pStatus = pStatus;
		this.pUpload = pUpload;
		this.pDetail = pDetail;
		this.pImage1 = pImage1;
		this.pImage2 = pImage2;
		this.pImage3 = pImage3;
		this.ordDetails = ordDetails;
		this.shopCarts = shopCarts;
	}

	public Product(Integer pID, String pName, BigDecimal pPrice, ProductClass productClass, Integer pStock,
			Boolean pStatus, Date pUpload, String pDetail, byte[] pImage1, byte[] pImage2, byte[] pImage3,
			Set<OrdDetail> ordDetails, Set<ShopCart> shopCarts) {
		super();
		this.pID = pID;
		this.pName = pName;
		this.pPrice = pPrice;
		this.productClass = productClass;
		this.pStock = pStock;
		this.pStatus = pStatus;
		this.pUpload = pUpload;
		this.pDetail = pDetail;
		this.pImage1 = pImage1;
		this.pImage2 = pImage2;
		this.pImage3 = pImage3;
		this.ordDetails = ordDetails;
		this.shopCarts = shopCarts;
	}

	public Integer getpID() {
		return pID;
	}

	public void setpID(Integer pID) {
		this.pID = pID;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public BigDecimal getpPrice() {
		return pPrice;
	}

	public void setpPrice(BigDecimal pPrice) {
		this.pPrice = pPrice;
	}

	public Integer getpStock() {
		return pStock;
	}

	public void setpStock(Integer pStock) {
		this.pStock = pStock;
	}

	public Boolean getpStatus() {
		return pStatus;
	}

	public void setpStatus(Boolean pStatus) {
		this.pStatus = pStatus;
	}

	public Date getpUpload() {
		return pUpload;
	}

	public void setpUpload(Date pUpload) {
		this.pUpload = pUpload;
	}

	public String getpDetail() {
		return pDetail;
	}

	public void setpDetail(String pDetail) {
		this.pDetail = pDetail;
	}

	public byte[] getpImage1() {
		return pImage1;
	}

	public void setpImage1(byte[] pImage1) {
		this.pImage1 = pImage1;
	}

	public byte[] getpImage2() {
		return pImage2;
	}

	public void setpImage2(byte[] pImage2) {
		this.pImage2 = pImage2;
	}

	public byte[] getpImage3() {
		return pImage3;
	}

	public void setpImage3(byte[] pImage3) {
		this.pImage3 = pImage3;
	}

	@Override
	public String toString() {
		return "Product [pID=" + pID + ", pName=" + pName + ", pPrice=" + pPrice + ", productClass=" + productClass
				+ ", pStock=" + pStock + ", pStatus=" + pStatus + ", pUpload=" + pUpload + ", pDetail=" + pDetail
				+ ", pImage1=" + Arrays.toString(pImage1) + ", pImage2=" + Arrays.toString(pImage2) + ", pImage3="
				+ Arrays.toString(pImage3) + ", ordDetails=" + ordDetails + ", shopCarts=" + shopCarts + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(pImage1);
		result = prime * result + Arrays.hashCode(pImage2);
		result = prime * result + Arrays.hashCode(pImage3);
		result = prime * result + Objects.hash(ordDetails, pDetail, pID, pName, pPrice, pStatus, pStock, pUpload,
				productClass, shopCarts);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(ordDetails, other.ordDetails) && Objects.equals(pDetail, other.pDetail)
				&& Objects.equals(pID, other.pID) && Arrays.equals(pImage1, other.pImage1)
				&& Arrays.equals(pImage2, other.pImage2) && Arrays.equals(pImage3, other.pImage3)
				&& Objects.equals(pName, other.pName) && Objects.equals(pPrice, other.pPrice)
				&& Objects.equals(pStatus, other.pStatus) && Objects.equals(pStock, other.pStock)
				&& Objects.equals(pUpload, other.pUpload) && Objects.equals(productClass, other.productClass)
				&& Objects.equals(shopCarts, other.shopCarts);
	}

	

	




}
