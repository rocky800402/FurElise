package com.furelise.orddetail.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.furelise.ord.model.Ord;
import com.furelise.product.model.Product;

import lombok.Data;

//import com.furelise.product.model.Product;
@IdClass(OrdDetailPK.class)
@Entity
@Table(name = "orddetail")
@Data
public class OrdDetail implements Serializable{
//	@Id
//	@ManyToOne
//	@JoinColumn(name = "ordID", referencedColumnName = "ordID")
//	private Ord ord;
	
//	@Id
//	@ManyToOne
//	@JoinColumn(name = "pID", referencedColumnName = "pID")
//	private Product product;
	 
//	@EmbeddedId
//	private OrdDetailPK ordDetailPK;
	
	@Id
	@Column(name = "ordID")
	private Integer ordID;
	
	@Id
	@Column(name = "pID")
	private Integer pID;
	
	@Column(name = "detaQty")
	private Integer detaQty;
	
	@Column(name = "feedback")
	private String feedback;
	
	@Column(name = "level")
	private Integer level;
	
	@Column(name = "fbTime")
	private Timestamp fbTime;

	public OrdDetail() {
		super();
	}
	
	
	public OrdDetail(Integer ordID, Integer pID, Integer detaQty, String feedback, Integer level, Timestamp fbTime) {
		super();
		this.ordID = ordID;
		this.pID = pID;
		this.detaQty = detaQty;
		this.feedback = feedback;
		this.level = level;
		this.fbTime = fbTime;
	}

	
}
