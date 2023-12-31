package com.furelise.ecpay.payment.integration.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class OrdPayDTO {
		//結帳
		private Integer pID;
		private Integer quantity;
		private Integer memID;
		private Integer payment;
		private Timestamp ordDate;
		private Integer deliver;
		private String address;
		private String cityCode;
		private BigDecimal sum;
		private BigDecimal shipping;
		private BigDecimal total;
		private String coupon;
		
		//查看訂單
		private Integer ordID;
		private Date deliverDate;
		private Integer ordStatus;
		private Date arrival;
}
