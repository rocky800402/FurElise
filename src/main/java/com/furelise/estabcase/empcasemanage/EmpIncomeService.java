package com.furelise.estabcase.empcasemanage;

import com.furelise.estabcase.model.EstabCase;
import com.furelise.estabcase.model.EstabCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;


@Service
public class EmpIncomeService {

    @Autowired
    EstabCaseRepository estabCaseRepository;

    public List<EstabCase> getIncomeInformatin(Integer empID, Integer estabCaseStatus) {
        List<EstabCase> estabCase = new ArrayList<>();

        estabCase = estabCaseRepository.findByEmpIDAndEstabCaseStatusOrderByEstabCaseEndDesc(empID, estabCaseStatus);

        return estabCase;
    }


    public Map<String, Object> getTotalInf(Integer empID, int year, int month) {

        LocalDate startOfMonth = LocalDate.of(year, month, 1);
        Timestamp startTimestamp = Timestamp.valueOf(startOfMonth.atStartOfDay());

        // 設定月份的最後一秒 23:59:59
        LocalDate endOfMonth = startOfMonth.plusMonths(1).minusDays(1);
        Timestamp endTimestamp = Timestamp.valueOf(endOfMonth.atTime(23, 59, 59));

        BigDecimal totalPlanPrice = estabCaseRepository.findTotalPlanPriceByEmpIDAndStatus(empID, startTimestamp, endTimestamp);

        //把數據寫入map
        Map<String, Object> mappingInf = new HashMap<>();
        if (totalPlanPrice != null) {
            mappingInf.put("totalPlanPrice", totalPlanPrice.toBigInteger());
        } else {
            mappingInf.put("totalPlanPrice", 0);
        }

        return mappingInf;
    }

    public List<EstabCase> getEstabCasesByMonth(int year, int month) {
        // 設定月份的第一天 00:00:00
        LocalDate startOfMonth = LocalDate.of(year, month, 1);
        Timestamp startTimestamp = Timestamp.valueOf(startOfMonth.atStartOfDay());

        // 設定月份的最後一秒 23:59:59
        LocalDate endOfMonth = startOfMonth.plusMonths(1).minusDays(1);
        Timestamp endTimestamp = Timestamp.valueOf(endOfMonth.atTime(23, 59, 59));

        return estabCaseRepository.findByEstabCaseEndBetweenAndStatus(startTimestamp, endTimestamp);
    }


    public List<IncomeSummaryDTO> getIncomeSummary(Integer empID) {


        return estabCaseRepository.findTotalPlanPriceByEmpIDAndStatusGroupByMonth(empID);
    }

}
