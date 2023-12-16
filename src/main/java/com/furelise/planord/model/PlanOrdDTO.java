package com.furelise.planord.model;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;

@Data
public class PlanOrdDTO {

	// 結帳用
	private String planName;
	private Integer timeID;
	private Integer periodID;
	private String times;
	private String[] weekDay;
	private Integer wayID;
	private Date planStart;
	private String contact;
	private String contactTel;
	private String cityCode;
	private String floor;
	private String pickupStop;
	
	//方案訂單列表用
	private Integer planOrdID;
	private String memName;
//	private String planName;
//	private Date planStart;
	private Date planEnd;
	private BigDecimal total;
	

}
