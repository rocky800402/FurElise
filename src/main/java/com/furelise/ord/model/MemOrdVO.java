package com.furelise.ord.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
public class MemOrdVO {
    private Integer ordID;
    private String cityCode;
    private String cityName;
    private String address;

    private Integer payment;
    private Integer deliver;
    private Timestamp ordDate;
    private Date deliverDate;

    private BigDecimal sum;
    private BigDecimal shipping;
    private Integer saleID;
    private BigDecimal total;

    private List<MemOrdDetailBO> memOrdDetailBOs;



}
