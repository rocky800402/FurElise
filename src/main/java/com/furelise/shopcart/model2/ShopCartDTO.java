package com.furelise.shopcart.model2;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ShopCartDTO{

	
	private Integer pID;
	private Integer quantity;
	private String pName;
	private BigDecimal pPrice;
	private byte[] pImage1;

	
}
