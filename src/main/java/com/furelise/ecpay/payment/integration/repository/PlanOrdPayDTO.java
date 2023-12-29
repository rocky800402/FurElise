package com.furelise.ecpay.payment.integration.repository;

import java.math.BigDecimal;
import java.sql.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PlanOrdPayDTO {

	// 結帳用
	private String planName;
	private Integer timeID;
	private Integer periodID;
	private String times;
	private String[] weekDay;
	private Integer wayID;
	private Date planStart;
	private String contact;
	@Pattern(regexp="^\\d{10}$", message="手機號碼格式錯誤")
	private String contactTel;
	private String cityCode;
	private String floor;
	private String pickupStop;
	private String afterTotal;
	
	//方案訂單列表用
	private Integer planOrdID;
	private String memName;
//	private String planName;
//	private Date planStart;
	private Date planEnd;
	private BigDecimal total;
	private String planStatus;

}
