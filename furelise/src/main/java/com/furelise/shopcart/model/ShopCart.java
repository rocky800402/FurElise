package com.furelise.shopcart.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.furelise.mem.model.Mem;




@Entity
@Table(name = "ShopCart")
//@IdClass(CompositeDetail1.class)
public class ShopCart {

//	先無視購物車!!!!!!!!!!!!!!!!!!!

	@ManyToOne
	@JoinColumn(name = "memID", referencedColumnName = "memID")
	private Mem mem;

	@Id
	@Column(name = "pID")
	private Integer pID;

	@Column(name = "quantity")
	private Integer quantity;

	public ShopCart() {
		super();

	}

	public ShopCart(Mem mem, Integer pID, Integer quantity) {
		super();
		this.mem = mem;
		this.pID = pID;
		this.quantity = quantity;
	}

//	public Integer getMemID() {
//		return memID;
//	}

//	public void setMemID(Integer memID) {
//		this.memID = memID;
//	}

	public Mem getMem() {
		return mem;
	}

	public void setMem(Mem mem) {
		this.mem = mem;
	}

	public Integer getpID() {
		return pID;
	}

	public void setpID(Integer pID) {
		this.pID = pID;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	static class CompositeDetail1 implements Serializable {
		private static final long serialVersionUID = 1L;

		private Integer memID;
		private Integer pID;

		// 一定要有無參數建構子
		public CompositeDetail1() {
			super();
		}

		public CompositeDetail1(Integer memID, Integer pID) {
			super();
			this.memID = memID;
			this.pID = pID;
		}

		public Integer getMemID() {
			return memID;
		}

		public void setMemID(Integer memID) {
			this.memID = memID;
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
			final int prime = 41;
			int result = 1;
			result = prime * result + ((pID == null) ? 0 : pID.hashCode());
			result = prime * result + ((memID == null) ? 0 : memID.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;

			if (obj != null && getClass() == obj.getClass()) {
				CompositeDetail1 compositeKey = (CompositeDetail1) obj;
				if (memID.equals(compositeKey.memID) && pID.equals(compositeKey.pID)) {
					return true;
				}
			}

			return false;
		}

	}

	@Override
	public String toString() {
		return "ShopCart [mem=" + mem + ", pID=" + pID + ", quantity=" + quantity + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(mem, pID, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShopCart other = (ShopCart) obj;
		return Objects.equals(mem, other.mem) && Objects.equals(pID, other.pID)
				&& Objects.equals(quantity, other.quantity);
	}
	
	

}