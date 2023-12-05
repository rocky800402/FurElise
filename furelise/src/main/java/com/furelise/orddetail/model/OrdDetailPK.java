package com.furelise.orddetail.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furelise.ord.model.Ord;
import com.furelise.product.model.Product;

import lombok.Data;

@Embeddable
@Data
public class OrdDetailPK implements Serializable{
	
//	@JsonIgnore
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name = "ordID", referencedColumnName = "ordID")
//	private Ord ord;
//	
//	@ManyToOne
//	@JoinColumn(name = "pID", referencedColumnName = "pID")
//	private Product product;
	
	private Integer ordID;
	
	private Integer pID;
}


