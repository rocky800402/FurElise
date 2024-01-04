package com.furelise.estabcase.service;

import com.furelise.emp.model.Emp;
import com.furelise.emp.model.EmpRepository;
import com.furelise.estabcase.model.EstabCase;
import com.furelise.estabcase.model.EstabCaseRepository;
import com.furelise.planord.model.PlanOrd;
import com.furelise.planord.model.PlanOrdRepository;
import com.furelise.vacation.model.entity.Vacation;
import com.furelise.vacation.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class DispatchService {

    @Autowired
    EstabCaseRepository estabCaseRepository;
    @Autowired
    EmpRepository empRepository;
    @Autowired
    PlanOrdRepository planOrdRepository;
    @Autowired
    VacationRepository vacationRepository;

    public void assignWorkToEmp() {
//        System.out.println("請動排程器");
        List<EstabCase> estabCaseList = estabCaseRepository.findEstabCasesWithinLastTwoDaysAndTakeStatusZero();
//        System.out.println(estabCaseList);
        bb:
        for (EstabCase estabCase : estabCaseList) {
//            System.out.println("尋找到案件"+estabCase);
            PlanOrd planOrd = planOrdRepository.findById(estabCase.getPlanOrdID()).orElseThrow();
            if(estabCase.getEmpID()!=null){

                Emp emp = empRepository.findById(estabCase.getEmpID()).orElseThrow();
                emp.setWorkSum(emp.getWorkSum()-1);
                empRepository.save(emp);
            }
            aa:
            for (int workSum = 0; workSum < 5; workSum++) {
                List<Emp> empWorkSum = empRepository.findByWorkSum(workSum);
                Collections.shuffle(empWorkSum);
//                System.out.println("workSum"+workSum);
                System.out.println("empWorkSum" + empWorkSum);
//                boolean a = false;
                cc:
                for (Emp emp : empWorkSum) {
                    if (!getEmpDateRange(emp).contains(estabCase.getEstabCaseDate().toLocalDate())) {
//                        System.out.println("contains" + emp.getEmpID());
//                        System.out.println("planOrd.getTimeID()" + planOrd.getTimeID());
//                        System.out.println("emp.getTimeID()" + emp.getTimeID());
                        if (Objects.equals(planOrd.getTimeID(), planOrd.getTimeID())&& (Objects.equals(planOrd.getCityCode(), emp.getEmpArea1()) || Objects.equals(planOrd.getCityCode(), emp.getEmpArea2()) || Objects.equals(planOrd.getCityCode(), emp.getEmpArea3()))) {
                            estabCase.setEmpID(emp.getEmpID());
                            emp.setWorkSum((emp.getWorkSum()+1));
//                            System.out.println("Area" + estabCase.getEstabCaseID());
//                            System.out.println("Area" + emp.getEmpID());
//                            System.out.println("Area" +(emp.getWorkSum()+1));
//                            a = true;
                            empRepository.save(emp);
                            estabCaseRepository.save(estabCase);
                            break aa;

                        }
                    }
                }
//                if(a){
//                    break ;
//                }
            }
        }

    }

    private List<LocalDate> getEmpDateRange(Emp emp) {
        List<LocalDate> dateList = new ArrayList<>();
        List<Vacation> vacationList = vacationRepository.findByEmpID(emp.getEmpID());
        for (Vacation vacation : vacationList) {
            List<LocalDate> dateRanges = getDateRange(vacation.getVaStart(), vacation.getVaEnd());
            dateList.addAll(dateRanges);
        }
        return dateList;
    }

    private List<LocalDate> getDateRange(Date startDate, Date endDate) {
        List<LocalDate> dateList = new ArrayList<>();

        // 將開始日期和結束日期轉換為 LocalDate 物件
        LocalDate startLocalDate = startDate.toLocalDate();
        LocalDate endLocalDate = endDate.toLocalDate();

        // 使用 while 迴圈進行日期範圍的遍歷
        while (!startLocalDate.isAfter(endLocalDate)) {
            // 使用 DateTimeFormatter.ISO_DATE 將日期格式化為 ISO-8601 格式
            dateList.add(startLocalDate);

            // 將日期增加一天
            startLocalDate = startLocalDate.plusDays(1);
        }

        // 回傳包含日期的清單
        return dateList;
    }
}
