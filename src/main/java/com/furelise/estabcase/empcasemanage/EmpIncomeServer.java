package com.furelise.estabcase.empcasemanage;

import com.furelise.estabcase.model.EstabCase;
import com.furelise.estabcase.model.EstabCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class EmpIncomeServer {

    @Autowired
    EstabCaseRepository estabCaseRepository;

    public List<EstabCase> getIncomeInformatin(Integer empID, Integer estabCaseStatus) {
        List<EstabCase> estabCase = new ArrayList<>();

        estabCase = estabCaseRepository.findByEmpIDAndEstabCaseStatusOrderByEstabCaseEndDesc(empID, estabCaseStatus);

        return estabCase;
    }

    public Map<String, Object> getTotalInf(Integer empID, Integer estabCaseStatus) {

        Integer totalCount = estabCaseRepository.countByEmpIDAndEstabCaseStatus(empID, estabCaseStatus);
        Double totalIncome = estabCaseRepository.sumPlanPricePerCaseByEmpIDAndStatus(empID, estabCaseStatus);


        //把數據寫入map
        Map<String, Object> mappingInf = new HashMap<>();
        mappingInf.put("totalCount", totalCount);
        mappingInf.put("totalIncome", totalIncome);


        return mappingInf;
    }

    public List<EstabCase> getEstabCasesByMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1, 0, 0, 0); // 設定月份的第一天 00:00:00
        java.sql.Date startOfMonth = new java.sql.Date(calendar.getTimeInMillis());


        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.SECOND, -1); // 設定月分的最後一秒 23:59:59
        java.sql.Date endOfMonth = new java.sql.Date(calendar.getTimeInMillis());


        return estabCaseRepository.findByMonthAndYear(startOfMonth, endOfMonth);
    }
}
