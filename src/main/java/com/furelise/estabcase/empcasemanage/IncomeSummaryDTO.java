package com.furelise.estabcase.empcasemanage;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class IncomeSummaryDTO {
    private Integer year;
    private Integer month;
    private BigDecimal totalPlanPrice;

    public IncomeSummaryDTO() {
    }

    public IncomeSummaryDTO(Integer year, Integer month, BigDecimal totalPlanPrice) {//要使用JPA方法 需要應對資料庫可能查出為null的數值 要對null值做處理 否則無法實例化
        this.year = (year != null) ? year : 0;  // 如果 year 為 null，設置為 0
        this.month = (month != null) ? month : 0;  // 如果 month 為 null，設置為 0
        this.totalPlanPrice = (totalPlanPrice != null) ? totalPlanPrice : BigDecimal.ZERO;  // 如果 totalPlanPrice 為 null，設置為 0
    }
}
