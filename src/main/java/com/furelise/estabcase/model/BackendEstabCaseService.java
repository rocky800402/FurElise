package com.furelise.estabcase.model;

import com.furelise.city.model.City;
import com.furelise.city.model.CityRepository;
import com.furelise.pickuptime.model.PickupTimeRepository;
import com.furelise.plan.model.Plan;
import com.furelise.plan.model.PlanRepository;
import com.furelise.planord.model.PlanOrd;
import com.furelise.planord.model.PlanOrdRepository;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BackendEstabCaseService {

    @Autowired
    PlanOrdRepository planOrdR;
    @Autowired
    EstabCaseRepository estabCaseR;
    @Autowired
    PickupTimeRepository pickupTimeR;
    @Autowired
    CityRepository cityR;
    @Autowired
    PlanRepository planR;

    public List<BackendEstabCaseVO> getBackendEstabCaseTake(int page,int limit,Boolean takeStatus) {
        List<BackendEstabCaseVO> listB = new ArrayList<>();
        PageRequest pageReq = PageRequest.of((page-1), limit, Sort.by("takeStatus").descending());
        Page<EstabCase> listE = estabCaseR.findAllByTakeStatus(takeStatus, pageReq);
//        System.out.println("listE.getTotalElements()");
//        System.out.println(listE.getTotalElements());
//        System.out.println("listE.getTotalPages()");
//        System.out.println(listE.getTotalPages());

        for (EstabCase eC : listE) {
            System.out.println(eC);
            BackendEstabCaseVO backendVO = new BackendEstabCaseVO();
            PlanOrd planOrd = planOrdR.findById(eC.getPlanOrdID()).orElseThrow();
            System.out.println(planOrd);
            Plan plan = planR.findById(planOrd.getPlanID()).orElseThrow();
            City city = cityR.findByCityCode(planOrd.getCityCode());
            backendVO.setEstabCaseID(eC.getEstabCaseID());
            backendVO.setEstabCaseDate(eC.getEstabCaseDate());
            backendVO.setTimeRange(pickupTimeR
                    .findById(planOrd.getTimeID()).orElseThrow()
                    .getTimeRange());
            backendVO.setCityName(city.getCityName());
            backendVO.setEmpID(eC.getEmpID());
            backendVO.setTakeStatus(eC.getTakeStatus());
            if (eC.getTakeStatus()==false&&eC.getEmpID()!=null){
                backendVO.setDispatchStatus(true);
            }else {
                backendVO.setDispatchStatus(false);
            }
            backendVO.setEstabCaseStatus(eC.getEstabCaseStatus());
            backendVO.setPlanPricePerCase(plan.getPlanPricePerCase());
            listB.add(backendVO);
        }

//        System.out.println(listB);

        return listB;
    }


}
