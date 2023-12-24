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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmpCaseManageService {
    @Autowired
    EstabCaseRepository estabCaseRepository;
    @Autowired
    PlanOrdRepository planOrdRepository;
    @Autowired
    PickupTimeRepository pickupTimeRepository;
    @Autowired
    CityRepository cityRepository;

    public EstabCase updateEstabCase(Integer estabCaseID, Boolean takeStatus, Integer estabCaseStatus) {
        EstabCase estabCase = estabCaseRepository.findById(estabCaseID).orElse(null);
        if (estabCase != null) {
            estabCase.setTakeStatus(takeStatus);
            estabCase.setEstabCaseStatus(estabCaseStatus);
            estabCaseRepository.save(estabCase);
        }
        return estabCase;
    }

    public List<EmpCaseManageVO> getEmpEstabCase(Integer empID, Integer estabCaseStatus){
        List<EmpCaseManageVO> listEc = new ArrayList<EmpCaseManageVO>();


        List<EstabCase> estabCases = estabCaseRepository.findByEmpIDAndEstabCaseStatus(empID, estabCaseStatus);
        for(EstabCase estabCase :estabCases){
            EmpCaseManageVO ecVO = new EmpCaseManageVO();

            PlanOrd planOrd = planOrdRepository.findById(estabCase.getPlanOrdID()).orElseThrow();
            PickupTime pickupTime = pickupTimeRepository.findById(planOrd.getTimeID()).orElseThrow();
            City city = cityRepository.findByCityCode(planOrd.getCityCode());

            ecVO.setEstabCaseID(estabCase.getEstabCaseID());
            ecVO.setEstabCaseDate(estabCase.getEstabCaseDate());
            ecVO.setTimeRange(pickupTime.getTimeRange());
            ecVO.setCityName(city.getCityName());
            ecVO.setTakeStatus(estabCase.getTakeStatus());
            ecVO.setEstabCaseStatus(estabCase.getEstabCaseStatus());
            ecVO.setPlanPricePerCase(estabCase.getPlanPricePerCase());
            listEc.add(ecVO);
        }


        return listEc;
    }




}
