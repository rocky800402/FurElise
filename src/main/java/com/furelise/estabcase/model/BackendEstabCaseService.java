package com.furelise.estabcase.model;

import com.furelise.city.model.City;
import com.furelise.city.model.CityRepository;
import com.furelise.complaint.model.Complaint;
import com.furelise.complaint.model.ComplaintRepository;
import com.furelise.emp.model.Emp;
import com.furelise.emp.model.EmpRepository;
import com.furelise.pickuptime.model.PickupTime;
import com.furelise.pickuptime.model.PickupTimeRepository;
import com.furelise.pickupway.model.PickupWay;
import com.furelise.pickupway.model.PickupWayRepository;
import com.furelise.plan.model.Plan;
import com.furelise.plan.model.PlanRepository;
import com.furelise.planord.model.PlanOrd;
import com.furelise.planord.model.PlanOrdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
    @Autowired
    EmpRepository empR;
    @Autowired
    PickupWayRepository pickupWayR;
    @Autowired
    ComplaintRepository complaintR;

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
    public List<BackendEstabCaseVO> getBackendEstabCaseStatus(int page,int limit,Boolean takeStatus) {
        List<BackendEstabCaseVO> backendEstabCaseVOList=new ArrayList<>();
        List<BackendEstabCaseVO> backendEstabCaseVOs=getBackendEstabCaseTake(page,limit,takeStatus);
        for(BackendEstabCaseVO backendEstabCaseVO:backendEstabCaseVOs){
            if(backendEstabCaseVO.getEstabCaseStatus()!=0){
                backendEstabCaseVOList.add(backendEstabCaseVO);
            }
        }
        return backendEstabCaseVOList;
    }

    public List<BackendEstabCaseVO> getBackendEstabCaseDispatch(int page,int limit,Boolean takeStatus) {
        List<BackendEstabCaseVO> backendEstabCaseVOList=new ArrayList<>();
        List<BackendEstabCaseVO> backendEstabCaseVOs=getBackendEstabCaseTake(page,limit,takeStatus);
        for(BackendEstabCaseVO backendEstabCaseVO:backendEstabCaseVOs){
            if(backendEstabCaseVO.getEstabCaseStatus()==0){
                backendEstabCaseVOList.add(backendEstabCaseVO);
            }
        }
        return backendEstabCaseVOList;
    }

    public BackendEstabCaseDetailVO getBackendEstabCaseDetail(Integer estabCaseID){
        EstabCase estabCase = estabCaseR.findById(estabCaseID).orElseThrow();

        PlanOrd planOrd = planOrdR.findById(estabCase.getPlanOrdID()).orElseThrow();
        PickupTime pickupTime = pickupTimeR.findById(planOrd.getTimeID()).orElseThrow();
        City city = cityR.findByCityCode(planOrd.getCityCode());
        PickupWay pickupWay = pickupWayR.findById(planOrd.getWayID()).orElseThrow();
        Plan plan = planR.findById(planOrd.getPlanID()).orElseThrow();
        List<Complaint> complaints = complaintR.findByEstabCaseID(estabCaseID);
        String empName="";
        if(estabCase.getEmpID()!=null){
            Emp emp = empR.findById(estabCase.getEmpID()).orElseThrow();
            empName=emp.getEmpName();
        }else{
            empName= "暫無收取員";
        }

        BackendEstabCaseDetailVO backendEstabCaseDetailVO = new BackendEstabCaseDetailVO(
                empName,
                estabCaseID,
                estabCase.getEstabCaseDate(),
                pickupTime.getTimeRange(),
                planOrd.getContact(),
                planOrd.getContactTel(),
                city.getCityName(),
                city.getCityCode(),
                planOrd.getFloor(),
                pickupWay.getWayName(),
                estabCase.getEstabCaseStatus(),
                plan.getPlanPricePerCase(),
                complaints,
                estabCase.getEstabCaseEnd(),
                estabCase.getEstabCasePic(),
                estabCase.getTakeStatus()
        );
        return backendEstabCaseDetailVO;
    }

    public EstabCase setEstabCaseReassign(BackendEstabCaseReassignDTO backendEstabCaseReassignDTO){
        EstabCase estabCase = estabCaseR.findById(backendEstabCaseReassignDTO.getEstabCaseID()).orElseThrow();
        estabCase.setEstabCaseDate(Date.valueOf(backendEstabCaseReassignDTO.getEstabCaseDate()));
        estabCase.setTakeStatus(false);
        estabCase.setEmpID(null);
        return estabCaseR.save(estabCase);
    }

}
