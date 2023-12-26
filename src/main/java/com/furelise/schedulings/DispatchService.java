package com.furelise.schedulings;

import com.furelise.emp.model.Emp;
import com.furelise.emp.model.EmpRepository;
import com.furelise.estabcase.model.EstabCase;
import com.furelise.estabcase.model.EstabCaseRepository;
import com.furelise.planord.model.PlanOrd;
import com.furelise.planord.model.PlanOrdRepository;
import com.furelise.vacation.model.entity.Vacation;
import com.furelise.vacation.repository.VacationRepository;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class DispatchService {

    @Autowired
    EstabCaseRepository EstabCaseR;
    @Autowired
    EmpRepository empR;

    @Autowired
    PlanOrdRepository planOrdR;
    @Autowired
    VacationRepository vacationR;


    public void getEstabCaseDate(){
        List<EstabCase> estabCaseList= EstabCaseR.findEstabCasesWithinLastTwoDaysAndTakeStatusZero();
//        List<Emp> empList = empR.findAll();
        for (EstabCase estabCase:estabCaseList){
           PlanOrd planOrd = planOrdR.findById(estabCase.getPlanOrdID()).orElseThrow();
           for(int i =0;i<5;i++){
               List<Emp> empWorkSum = empR.findByWorkSum(i);
               Collections.shuffle(empWorkSum);
               System.out.println("empWorkSum"+empWorkSum);
               for (Emp emp : empWorkSum){
                   if(!getEmpDateRange(emp).contains(estabCase.getEstabCaseDate())){
                       System.out.println("contains"+emp.getEmpID());
                       if(planOrd.getCityCode()==emp.getEmpArea1()||planOrd.getCityCode()==emp.getEmpArea2()||planOrd.getCityCode()==emp.getEmpArea3()){
                           estabCase.setEmpID(emp.getEmpID());
                           System.out.println("Area"+emp.getEmpID());
                       }
                   }
               }
           }
        }

    }

    public   List<Date> getEmpDateRange(Emp emp) {
        List<Date> dateList = new ArrayList<>();
        List<Vacation> vacationList = vacationR.findByEmpID(emp.getEmpID());
        for (Vacation vacation:vacationList){
            List<Date> dateRanges =getDateRange(vacation.getVaStart(),vacation.getVaEnd());
            for (Date date:dateRanges){
                dateList.add(date);
            }
        }
        return dateList;
    }
    public  List<Date> getDateRange(Date startDate, Date endDate) {
        List<Date> dateList = new ArrayList<>();

        // 將開始日期和結束日期轉換為 LocalDate 物件
        LocalDate startLocalDate = startDate.toLocalDate();
        LocalDate endLocalDate = endDate.toLocalDate();

        // 使用 while 迴圈進行日期範圍的遍歷
        while (!startLocalDate.isAfter(endLocalDate)) {
            // 使用 DateTimeFormatter.ISO_DATE 將日期格式化為 ISO-8601 格式
            dateList.add(Date.valueOf(startLocalDate));

            // 將日期增加一天
            startLocalDate = startLocalDate.plusDays(1);
        }

        // 回傳包含日期的清單
        return dateList;
    }
}
