package com.furelise.ord.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.furelise.orddetail.model.OrdDetail;
import com.furelise.product.model.Product;

import lombok.Data;

@Data
public class OrdDetailBO {
	
	private Integer pID;
    private String pName;
    private BigDecimal pPrice;
    private Integer detaQty;
    private Integer level;
    private String feedback;
    private Timestamp fbTime;
    private byte[] pImage;

    public OrdDetailBO() {
    }

    public OrdDetailBO(Product product, OrdDetail ordDetail) {
    	this.pID = product.getPID();
        this.pName = product.getPName();
        this.pPrice = product.getPPrice();
        this.detaQty = ordDetail.getDetaQty();
        this.level = ordDetail.getLevel();
        this.feedback = ordDetail.getFeedback();
        this.fbTime = ordDetail.getFbTime();
        this.pImage = product.getPImage1();
    }
}
