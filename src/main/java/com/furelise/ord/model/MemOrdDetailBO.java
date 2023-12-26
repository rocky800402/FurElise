package com.furelise.ord.model;

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
}
