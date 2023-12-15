package com.furelise.estabcase.model;

import java.math.BigDecimal;
import java.sql.Date;
//import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


import com.furelise.city.model.City;
import com.furelise.city.model.CityRepository;
import com.furelise.mem.model.entity.Mem;
import com.furelise.mem.repository.MemRepository;
import com.furelise.period.model.Period;
import com.furelise.period.model.PeriodRepository;
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

@Service
public class EstabCaseService {
	
	@Autowired
	EstabCaseRepository estabCaseR;
	@Autowired
	PlanOrdRepository planOrdR;
	@Autowired
	PlanRepository planR;
	@Autowired
	PickupTimeRepository pickuoTimeR;
	@Autowired
	PeriodRepository periodR;
	@Autowired
	PickupWayRepository pickupWayR;
	@Autowired
	CityRepository cityR;
	@Autowired
    MemRepository memR;

	
	public EstabCase addEstabCase(Integer empID,Integer planOrdID,
			Date estabCaseDate,BigDecimal planPricePerCase) {
		
		EstabCase eCase = new EstabCase();
		
		eCase.setEmpID(empID);
		eCase.setPlanOrdID(planOrdID);
		eCase.setEstabCaseDate(estabCaseDate);
		eCase.setPlanPricePerCase(planPricePerCase);
		eCase.setEstabCaseStatus(0);
		estabCaseR.save(eCase);
		return eCase;
	}
	
	public EstabCase updateEstabCase(Integer estabCaseID,Integer empID,Integer planOrdID,
	Date estabCaseDate,BigDecimal planPricePerCase,Integer estabCaseStatus) {
		EstabCase eCase = new EstabCase();
		eCase.setEstabCaseID(estabCaseID);
		eCase.setEmpID(empID);
		eCase.setPlanOrdID(planOrdID);
		eCase.setEstabCaseDate(estabCaseDate);
		eCase.setPlanPricePerCase(planPricePerCase);
		eCase.setEstabCaseStatus(estabCaseStatus);
		estabCaseR.save(eCase);
		return eCase;
	}

	public MemEstabCaseVO getMemEstabCase(Integer planOrdID){
		MemEstabCaseVO memEC = new MemEstabCaseVO();
		PlanOrd planOrd = planOrdR.findById(planOrdID).orElseThrow();
		Plan plan = planR.findById(planOrd.getPlanID()).orElseThrow();
		PickupTime pickupTime = pickuoTimeR.findById(planOrd.getTimeID()).orElseThrow();
		Period period = periodR.findById(planOrd.getPeriodID()).orElseThrow();
		PickupWay planWay = pickupWayR.findById(planOrd.getWayID()).orElseThrow();
		City city = cityR.findByCityCode(planOrd.getCityCode());
		Mem mem = memR.findById(planOrd.getMemID()).orElseThrow();

		memEC.setPlanOrdID(planOrdID);
		memEC.setPlanName(plan.getPlanName());
		memEC.setTimeRange(pickupTime.getTimeRange());
		memEC.setPlanPeriod(period.getPlanPeriod());
		memEC.setDay(getDaysOfWeek(planOrd.getDay()));
		memEC.setDayCode(planOrd.getDay());
		memEC.setWayName(planWay.getWayName());
		memEC.setMemID(mem.getMemID());
		memEC.setMemName(mem.getMemName());
		memEC.setPlanStart(planOrd.getPlanStart());
		memEC.setPlanEnd(planOrd.getPlanEnd());
		memEC.setTotal(planOrd.getTotal());
		memEC.setCityCode(planOrd.getCityCode());
		memEC.setCityName(city.getCityName());
		memEC.setFloor(planOrd.getFloor());
		memEC.setContact(planOrd.getContact());
		memEC.setContactTel(planOrd.getContactTel());

		memEC.setEstabCaseList(estabCaseR.findByPlanOrdIDOrderByEstabCaseDateDesc(planOrdID));

		return memEC;
	}
	public EstabCase updateEstabCaseLevel(EstabCaseLevelDTO estabCL){
		LocalDateTime currentTime = LocalDateTime.now();
		EstabCase estabCase = estabCaseR.findById(estabCL.getEstabCaseID()).orElseThrow();
		estabCase.setEstabCaseLevel(estabCL.getEstabCaseLevel());
		estabCase.setEstabCaseFeedback(estabCL.getEstabCaseFeedback());
		estabCase.setEstabCaseFBTime(Timestamp.valueOf(currentTime));

		estabCaseR.save(estabCase);

		return estabCase;
	}

	public List<EstabCase> getEstabCaseByPlanOrdID(Integer planOrdID) {
		
		return estabCaseR.findByPlanOrdIDOrderByEstabCaseDateDesc(planOrdID);
	}
	public EstabCase getEstabCasePK(Integer estabCaseID) {
		return estabCaseR.findById(estabCaseID).orElseThrow();
	}

	public List<EstabCase> getAllEstabCase(){
		return estabCaseR.findAll();
	}
	
	
	//建立以成立案件(方案的金額 需要planOrd.plan.planPricePerCase)

	



	public String getDaysOfWeek(String day){
		int dayInt =0;
		String days = "";
		for(int i = 0; i<day.length();i++){

			if(day.substring(i,i+1).equals("1")){
				dayInt++;
			}
		}
		switch (dayInt){
			case 1: days = "一"; break;
			case 2: days = "二"; break;
			case 3: days = "三"; break;
			case 4: days = "四"; break;
			case 5: days = "五"; break;
			case 6: days = "六"; break;
			case 7: days = "七"; break;
		}

		return days;
	}



}
