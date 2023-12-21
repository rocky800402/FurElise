package com.furelise.shopcart.model2;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Id;
import javax.persistence.IdClass;


import lombok.Data;

@IdClass(ShopCartPK.class)
@Data
public class ShopCart implements Serializable{
	
	//購物車是一個HASH,會員編號為key,pID為field,quantity為value
	
	@Id
	private Integer pID;
	
	private Integer quantity;

	
	public ShopCart() {
		
	}


	public ShopCart(Integer pID, Integer quantity) {
		super();
		this.pID = pID;
		this.quantity = quantity;
		
	}


//	public Integer getpID() {
//		return pID;
//	}
//
//
//	public void setpID(Integer pID) {
//		this.pID = pID;
//	}
//
//
//
//	public Integer getQuantity() {
//		return quantity;
//	}
//
//
//	public void setQuantity(Integer quantity) {
//		this.quantity = quantity;
//	}
//
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(pID, quantity);
//	}
//
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		ShopCart other = (ShopCart) obj;
//		return Objects.equals(pID, other.pID) && Objects.equals(quantity, other.quantity);
//	}
//
//
//	@Override
//	public String toString() {
//		return "ShopCart [pID=" + pID + ", quantity=" + quantity +  "]";
//	}


	

	
}

