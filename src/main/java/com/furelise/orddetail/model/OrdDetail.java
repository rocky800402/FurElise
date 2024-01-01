package com.furelise.orddetail.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.Table;

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
	 
	@EmbeddedId
	private OrdDetailPK ordDetailPK;
	
	@MapsId("ordID")
    @JoinColumn(name = "ordID", insertable = false, updatable = false)
    private Integer ordID;

	@MapsId("pID")
    @JoinColumn(name = "pID", insertable = false, updatable = false)
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
		
		this.ordID = ordDetailPK.getOrdID();
		this.pID = ordDetailPK.getPID();
		this.detaQty = detaQty;
		this.feedback = feedback;
		this.level = level;
		this.fbTime = fbTime;
	}



	
}
