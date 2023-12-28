package com.furelise.ord.model;

import com.furelise.orddetail.model.OrdDetail;
import com.furelise.product.model.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class MemOrdDetailBO {

    private String pName;
    private BigDecimal pPrice;
    private Integer detaQty;
    private Integer level;
    private String feedback;
    private Timestamp fbTime;
    private byte[] pImage;

    public MemOrdDetailBO() {
    }

    public MemOrdDetailBO(Product product, OrdDetail ordDetail) {
        this.pName = product.getPName();
        this.pPrice = product.getPPrice();
        this.detaQty = ordDetail.getDetaQty();
        this.level = ordDetail.getLevel();
        this.feedback = ordDetail.getFeedback();
        this.fbTime = ordDetail.getFbTime();
        this.pImage = product.getPImage1();
    }
}
