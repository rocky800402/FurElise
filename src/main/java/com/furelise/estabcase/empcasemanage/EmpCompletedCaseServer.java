package com.furelise.estabcase.empcasemanage;

import com.furelise.city.model.City;
import com.furelise.city.model.CityRepository;
import com.furelise.estabcase.model.EstabCase;
import com.furelise.estabcase.model.EstabCaseRepository;
import com.furelise.pickuptime.model.PickupTime;
import com.furelise.pickuptime.model.PickupTimeRepository;
import com.furelise.planord.model.PlanOrd;
import com.furelise.planord.model.PlanOrdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;

@Service
public class EmpCompletedCaseServer {

    @Autowired
    EstabCaseRepository estabCaseRepository;
    @Autowired
    PickupTimeRepository pickupTimeRepository;
    @Autowired
    PlanOrdRepository planOrdRepository;
    @Autowired
    CityRepository cityRepository;

    public EmpCompletedCaseVO showCompletedCase(Integer estabCaseID){
        EmpCompletedCaseVO empCCVO = new EmpCompletedCaseVO();

        EstabCase estabCase = estabCaseRepository.findById(estabCaseID).orElseThrow();
        PlanOrd planOrd = planOrdRepository.findById(estabCase.getPlanOrdID()).orElseThrow();
        PickupTime pickupTime = pickupTimeRepository.findById(planOrd.getTimeID()).orElseThrow();
        City city = cityRepository.findByCityCode(planOrd.getCityCode());

        //欄位名稱 對應表格
        empCCVO.setEstabCaseID(estabCaseID);//案件編號 estabCase
        empCCVO.setEstabCaseDate(estabCase.getEstabCaseDate());// 收取日期 estabCase
        empCCVO.setTimeRange(pickupTime.getTimeRange());//收取區間 pickupTime
        empCCVO.setPlanPricePerCase(estabCase.getPlanPricePerCase());//收入 estabCase
        empCCVO.setCityCode(planOrd.getCityCode());//郵遞區號 planOrd
        empCCVO.setFloor(planOrd.getFloor());//街道樓層 planOrd
        empCCVO.setPickupStop(planOrd.getPickupStop());//擺放位置planOrd
        empCCVO.setCityName(city.getCityName());//地址 city
        empCCVO.setTakeStatus(estabCase.getTakeStatus());//接單狀態 estabCase
        empCCVO.setEstabCaseStatus(estabCase.getEstabCaseStatus());//案件狀態 estabCase
        empCCVO.setEstabCasePic(estabCase.getEstabCasePic());//回報照片 estabCase


        return empCCVO;
    }
}
