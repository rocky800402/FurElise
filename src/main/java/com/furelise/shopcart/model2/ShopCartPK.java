package com.furelise.shopcart.model2;

import java.io.Serializable;

import javax.persistence.Embeddable;


import lombok.Data;

@Embeddable
@Data
public class ShopCartPK implements Serializable{
	private Integer memID;
	
	private Integer pID;
}
