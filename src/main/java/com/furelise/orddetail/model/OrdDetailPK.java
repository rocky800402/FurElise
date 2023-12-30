package com.furelise.orddetail.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;

//@Embeddable
@Data
public class OrdDetailPK implements Serializable{

	@Id
	private Integer ordID;
	
	@Id
	private Integer pID;
}


