package com.furelise.orddetail.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.furelise.ord.model.Ord;

import com.furelise.product.model.Product;

@Entity
@Table(name = "OrdDetail")
//@IdClass(CompositeDetail2.class)
public class OrdDetail {
	@Id
	@ManyToOne
	@JoinColumn(name = "ordID", referencedColumnName = "ordID")
	private Ord ord;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "pID", referencedColumnName = "pID")
	private Product product;
	
	@Column(name = "detaQty")
	private Integer detaQty;
	
	@Column(name = "feedback")
	private String feedback;
	
	@Column(name = "level")
	private Integer level;
	
	@Column(name = "fbTime")
	private Timestamp fbTime;

	public OrdDetail() {
		super();
	}

	

	public OrdDetail(Ord ord, Product product, Integer detaQty, String feedback, Integer level, Timestamp fbTime) {
		super();
		this.ord = ord;
		this.product = product;
		this.detaQty = detaQty;
		this.feedback = feedback;
		this.level = level;
		this.fbTime = fbTime;
	}



	public Ord getOrd() {
		return ord;
	}

	public void setOrd(Ord ord) {
		this.ord = ord;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getDetaQty() {
		return detaQty;
	}

	public void setDetaQty(Integer detaQty) {
		this.detaQty = detaQty;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Timestamp getFbTime() {
		return fbTime;
	}

	public void setFbTime(Timestamp fbTime) {
		this.fbTime = fbTime;
	}

	static class CompositeDetail2 implements Serializable {
		private static final long serialVersionUID = 1L;

		private Integer ordID;
		private Integer pID;

		// 一定要有無參數建構子
		public CompositeDetail2() {
			super();
		}

		public CompositeDetail2(Integer ordID, Integer pID) {
			super();
			this.ordID = ordID;
			this.pID = pID;
		}

		public Integer getOrdID() {
			return ordID;
		}

		public void setOrdID(Integer ordID) {
			this.ordID = ordID;
		}

		public Integer getpID() {
			return pID;
		}

		public void setpID(Integer pID) {
			this.pID = pID;
		}

		// 一定要 override 此類別的 hashCode() 與 equals() 方法！
		@Override
		public int hashCode() {
			final int prime = 37;
			int result = 1;
			result = prime * result + ((ordID == null) ? 0 : ordID.hashCode());
			result = prime * result + ((pID == null) ? 0 : pID.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;

			if (obj != null && getClass() == obj.getClass()) {
				CompositeDetail2 compositeKey = (CompositeDetail2) obj;
				if (ordID.equals(compositeKey.ordID) && pID.equals(compositeKey.pID)) {
					return true;
				}
			}

			return false;
		}

	}

	@Override
	public String toString() {
		return "OrdDetail [ord=" + ord + ", product=" + product + ", detaQty=" + detaQty + ", feedback=" + feedback
				+ ", level=" + level + ", fbTime=" + fbTime + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(detaQty, fbTime, feedback, level, ord, product);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdDetail other = (OrdDetail) obj;
		return Objects.equals(detaQty, other.detaQty) && Objects.equals(fbTime, other.fbTime)
				&& Objects.equals(feedback, other.feedback) && Objects.equals(level, other.level)
				&& Objects.equals(ord, other.ord) && Objects.equals(product, other.product);
	}

	

}
