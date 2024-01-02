package com.furelise.estabcase.empcasemanage;

import com.furelise.city.model.City;
import com.furelise.city.model.CityRepository;
import com.furelise.emp.model.Emp;
import com.furelise.emp.model.EmpRepository;
import com.furelise.estabcase.model.EstabCase;
import com.furelise.estabcase.model.EstabCaseRepository;
import com.furelise.pickuptime.model.PickupTime;
import com.furelise.pickuptime.model.PickupTimeRepository;
import com.furelise.pickupway.model.PickupWay;
import com.furelise.pickupway.model.PickupWayRepository;
import com.furelise.plan.model.Plan;
import com.furelise.plan.model.PlanRepository;
import com.furelise.planord.model.PlanOrd;
import com.furelise.planord.model.PlanOrdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class EmpOngoingCaseService {
    @Autowired
    EstabCaseRepository estabCaseRepository;
    @Autowired
    PickupTimeRepository pickupTimeRepository;
    @Autowired
    PlanOrdRepository planOrdRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    PickupWayRepository pickupWayRepository;
    @Autowired
    PlanRepository planRepository;
    @Autowired
    EmpRepository empRepository;
    /*
    estabCase
    pickupTime
    planOrd
    city
    pickupWay
    plan
    emp
    */
    public void CompleteCase(Integer estabCaseID){

        boolean allMatchCondition = false;

        EstabCase estabCase = estabCaseRepository.findById(estabCaseID).orElseThrow();
        estabCase.setTakeStatus(true);
        estabCase.setEstabCaseStatus(1);

        //存入系統時間作為完成時間
        java.util.Date currentDate = new java.util.Date();
        Timestamp estabCaseEnd = new Timestamp(currentDate.getTime());
        estabCase.setEstabCaseEnd(estabCaseEnd);
        estabCaseRepository.save(estabCase);


        //整個方案是否完結的判斷
        PlanOrd planOrd = planOrdRepository.findById(estabCase.getPlanOrdID()).orElseThrow();
        List<EstabCase> allPlanOrd = estabCaseRepository.findByPlanOrdID(planOrd.getPlanOrdID());

        for(EstabCase estabCases :allPlanOrd){
            if(estabCases.getTakeStatus() && estabCases.getEstabCaseStatus() == 1){
                allMatchCondition = true;
            }else {
                allMatchCondition = false;
            }
        }

        if(allMatchCondition){
            planOrd.setPlanStatusID(210004);
            planOrdRepository.save(planOrd);
        }
    }

    public EmpOngoingCaseVO getEmpOngoingCase(Integer estabCaseID){
        EmpOngoingCaseVO empOC = new EmpOngoingCaseVO();

        EstabCase estabCase = estabCaseRepository.findById(estabCaseID).orElseThrow();
        PlanOrd planOrd = planOrdRepository.findById(estabCase.getPlanOrdID()).orElseThrow();
        PickupTime pickupTime = pickupTimeRepository.findById(planOrd.getTimeID()).orElseThrow();
        City city = cityRepository.findByCityCode(planOrd.getCityCode());
        PickupWay pickupWay = pickupWayRepository.findById(planOrd.getWayID()).orElseThrow();
        Plan plan = planRepository.findById(planOrd.getPlanID()).orElseThrow();
        Emp emp = empRepository.findById(estabCase.getEmpID()).orElseThrow();

        //欄位名稱 對應表格
        empOC.setEstabCaseID(estabCaseID);//案件編號 estabCase
        empOC.setEstabCaseDate(estabCase.getEstabCaseDate());// 收取日期 estabCase
        empOC.setPlanPricePerCase(estabCase.getPlanPricePerCase());//收入 estabCase
        empOC.setTakeStatus(estabCase.getTakeStatus());//接單狀態 estabCase
        empOC.setTimeRange(pickupTime.getTimeRange());//收取區間 pickupTime
        empOC.setCityCode(planOrd.getCityCode());//郵遞區號 planOrd
        empOC.setFloor(planOrd.getFloor());//街道樓層 planOrd
        empOC.setPickupStop(planOrd.getPickupStop());//擺放位置planOrd
        empOC.setCityName(city.getCityName());//地址 city
        empOC.setWayName(pickupWay.getWayName());//收取方式 pickupWay
        empOC.setLiter(plan.getLiter());//垃圾量 plan
        empOC.setEmpName(emp.getEmpName());//夥伴姓名 emp
        empOC.setEmpID(emp.getEmpID());//夥伴ID emp

        return empOC;
    }
}
