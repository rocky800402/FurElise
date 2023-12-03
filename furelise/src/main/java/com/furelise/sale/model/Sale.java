package com.furelise.sale.model;

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

@Entity
@Table(name = "Sale")
public class Sale {
	
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
	
	@OneToMany(mappedBy="sale", cascade= CascadeType.ALL)
	private Set<Ord> ords;	
	
	public Sale() {
		super();
	}
	

	


	public Sale(String coupon, Date saleStart, Date saleEnd, BigDecimal disRequire, BigDecimal dis, Set<Ord> ords) {
		super();
		this.coupon = coupon;
		this.saleStart = saleStart;
		this.saleEnd = saleEnd;
		this.disRequire = disRequire;
		this.dis = dis;
		this.ords = ords;
	}





	public Sale(Integer saleID, String coupon, Date saleStart, Date saleEnd, BigDecimal disRequire, BigDecimal dis,
			Set<Ord> ords) {
		super();
		this.saleID = saleID;
		this.coupon = coupon;
		this.saleStart = saleStart;
		this.saleEnd = saleEnd;
		this.disRequire = disRequire;
		this.dis = dis;
		this.ords = ords;
	}





	public Set<Ord> getOrds() {
		return ords;
	}


	public void setOrds(Set<Ord> ords) {
		this.ords = ords;
	}



	public Integer getSaleID() {
		return saleID;
	}


	public void setSaleID(Integer saleID) {
		this.saleID = saleID;
	}


	public String getCoupon() {
		return coupon;
	}


	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}


	public Date getSaleStart() {
		return saleStart;
	}


	public void setSaleStart(Date saleStart) {
		this.saleStart = saleStart;
	}


	public Date getSaleEnd() {
		return saleEnd;
	}


	public void setSaleEnd(Date saleEnd) {
		this.saleEnd = saleEnd;
	}


	public BigDecimal getDisRequire() {
		return disRequire;
	}


	public void setDisRequire(BigDecimal disRequire) {
		this.disRequire = disRequire;
	}


	public BigDecimal getDis() {
		return dis;
	}


	public void setDis(BigDecimal dis) {
		this.dis = dis;
	}

	@Override
	public String toString() {
		return "Sale [saleID=" + saleID + ", coupon=" + coupon + ", saleStart=" + saleStart + ", saleEnd=" + saleEnd
				+ ", disRequire=" + disRequire + ", dis=" + dis + ", ords=" + ords + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(coupon, dis, disRequire, ords, saleEnd, saleID, saleStart);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sale other = (Sale) obj;
		return Objects.equals(coupon, other.coupon) && Objects.equals(dis, other.dis)
				&& Objects.equals(disRequire, other.disRequire) && Objects.equals(ords, other.ords)
				&& Objects.equals(saleEnd, other.saleEnd) && Objects.equals(saleID, other.saleID)
				&& Objects.equals(saleStart, other.saleStart);
	}

	
}
