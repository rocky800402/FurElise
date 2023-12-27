package com.furelise.estabcase.empcasemanage;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class EmpCompletedCaseVO {

    //欄位名稱 對應表格
    private Integer estabCaseID;//案件編號 estabCase
    private Date estabCaseDate; // 收取日期 estabCase
    private String timeRange;//收取區間 pickupTime
    private BigDecimal planPricePerCase;//收入 estabCase
    private String cityCode;//郵遞區號 planOrd
    private String floor;//街道樓層 planOrd
    private String pickupStop;//擺放位置planOrd
    private String cityName;//地址 city
    private Boolean takeStatus;//接單狀態 estabCase
    private Integer estabCaseStatus;//案件狀態 estabCase
    private byte[] estabCasePic;//回報照片 estabCase

    public EmpCompletedCaseVO() {
    }
}
