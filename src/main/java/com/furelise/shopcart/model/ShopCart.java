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

import com.furelise.product.model.Product;

import lombok.Data;



@IdClass(ShopCartPK.class)
@Entity
@Table(name = "ShopCart")
@Data
public class ShopCart implements Serializable{

//	先無視購物車!!!!!!!!!!!!!!!!!!!
	@Id
	@Column(name = "memID")
	private Integer memID;

	@Id
	@Column(name = "pID")
	private Integer pID;

	@Column(name = "quantity")
	private Integer quantity;

	public ShopCart() {
		super();

	}

}