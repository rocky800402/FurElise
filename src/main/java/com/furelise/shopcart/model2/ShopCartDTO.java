package com.furelise.shopcart.model2;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ShopCartDTO {

	private Integer memID;
	private Integer pID;
	private Integer pClassID;
	private Integer quantity;
	private String pName;
	private BigDecimal pPrice;
	private byte[] pImage1;
	private Integer payment;
	private Integer deliver;
	private String address;
	private String cityCode;
	private BigDecimal sum;
	private BigDecimal shipping;
	private BigDecimal total;
	private String coupon;

	public ShopCartDTO(Integer memID, Integer pID, Integer pClassID, Integer quantity, String pName, BigDecimal pPrice,
			byte[] pImage1, Integer payment, Integer deliver, String address, String cityCode, BigDecimal sum,
			BigDecimal shipping, BigDecimal total, String coupon) {
		super();
		this.memID = memID;
		this.pID = pID;
		this.pClassID = pClassID;
		this.quantity = quantity;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pImage1 = pImage1;
		this.payment = payment;
		this.deliver = deliver;
		this.address = address;
		this.cityCode = cityCode;
		this.sum = sum;
		this.shipping = shipping;
		this.total = total;
		this.coupon = coupon;
	}

	public ShopCartDTO() {

	}

}
