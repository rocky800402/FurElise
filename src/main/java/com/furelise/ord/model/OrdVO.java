package com.furelise.ord.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import org.springframework.lang.Nullable;

import com.furelise.city.model.City;
import com.furelise.sale.model.Sale;

import lombok.Data;

@Data
public class OrdVO {
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
    private BigDecimal couponDis;
    private Integer ordStatus;

    private List<OrdDetailBO> ordDetailBOs;

    public OrdVO() {
    }

    public OrdVO(@Nullable City city, Ord ord, List<OrdDetailBO> OrdDetailBOList, @Nullable Sale sale) {
        this.cityCode = ord.getCityCode();
        this.cityName = city.getCityName();
        this.ordID = ord.getOrdID();
        this.address = ord.getAddress();
        this.payment = ord.getPayment();
        this.deliver = ord.getDeliver();
        this.ordDate = ord.getOrdDate();
        this.deliverDate = ord.getDeliverDate();
        this.sum = ord.getSum();
        this.shipping = ord.getShipping();
        this.saleID = ord.getSaleID();
        this.total = ord.getTotal();
        this.ordDetailBOs = OrdDetailBOList;
        this.couponDis = Objects.isNull(sale)? null :sale.getDis();
        this.ordStatus =ord.getOrdStatus();
    }
}
