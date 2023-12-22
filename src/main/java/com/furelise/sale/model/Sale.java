package com.furelise.sale.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.furelise.common.annotation.BigDecimalFormat;

import lombok.Data;

@Entity
@Table(name = "Sale")
@Data
public class Sale implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "saleID", updatable = false)
	private Integer saleID;
	
	@Pattern(regexp = "^[a-zA-Z0-9]{2,10}$", message = "請填入二至十位的英文字或數字")
	@NotBlank(message = "請勿空白")
	@Column(name = "coupon")
	private String coupon;
	
//	@NotNull(message="不可為空")
	@JsonFormat(pattern="yyyy-MM-dd")
//	@NotBlank(message = "請勿空白")
	@Column(name = "saleStart")
	private Date saleStart;
	
//	@NotNull(message="不可為空")
	@Future(message="日期必須是在今日(不含)之後")
//	@NotBlank(message = "請勿空白")
	@JsonFormat(pattern="yyyy-MM-dd")
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "saleEnd")
	private Date saleEnd;
	
	@BigDecimalFormat
	@NotNull(message="不可為空")
//	@Pattern(regexp = "^[0-9]$", message = "請填入數字")
//	@NotBlank(message = "請勿空白")
	@Column(name = "disRequire")
	private BigDecimal disRequire;
	
	@BigDecimalFormat
	@NotNull(message="不可為空")
//	@Pattern(regexp = "^[0-9]$", message = "請填入數字")
//	@NotBlank(message = "請勿空白")
	@Column(name = "dis")
	private BigDecimal dis;
	
//	@OneToMany(mappedBy="sale", cascade= CascadeType.ALL)
//	private Set<Ord> ords;	
	
	public Sale() {
		super();
	}
	
	public Sale(Integer saleID, String coupon, Date saleStart, 
			Date saleEnd, BigDecimal disRequire, BigDecimal dis) {
		this.saleID = saleID;
		this.coupon = coupon;
		this.saleStart = saleStart;
		this.saleEnd = saleEnd;
		this.disRequire = disRequire;
		this.dis = dis;
	}

	public Sale(String coupon, Date saleStart, 
			Date saleEnd, BigDecimal disRequire, BigDecimal dis) {
		this.coupon = coupon;
		this.saleStart = saleStart;
		this.saleEnd = saleEnd;
		this.disRequire = disRequire;
		this.dis = dis;
	}
	
	
}
