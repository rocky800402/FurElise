package com.furelise.planord.model;

import com.furelise.city.model.City;
import com.furelise.city.model.CityRepository;
import com.furelise.mem.repository.MemRepository;
import com.furelise.period.model.Period;
import com.furelise.period.model.PeriodRepository;
import com.furelise.pickuptime.model.PickupTime;
import com.furelise.pickuptime.model.PickupTimeRepository;
import com.furelise.pickupway.model.PickupWay;
import com.furelise.pickupway.model.PickupWayRepository;
import com.furelise.plan.model.Plan;
import com.furelise.plan.model.PlanRepository;
import com.furelise.planstatus.model.PlanStatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
public class PlanOrdService {

	@Autowired
	PlanOrdRepository dao;

	@Autowired
	MemRepository memDao;

	@Autowired
	PlanRepository planDao;

	@Autowired
	PeriodRepository periodDao;

	@Autowired
	PickupTimeRepository pickupTimeDao;

	@Autowired
	PickupWayRepository pickupWayDao;

	@Autowired
	CityRepository cityDao;

	@Autowired
	PlanStatusRepository planStatusDao;

	public PlanOrd addPlanOrd(PlanOrdDTO req, Integer memID) {
		// planStatusID、amendLog寫死

		// 刷卡是否成功
		boolean payment = true;

		if (payment) {
			PlanOrd planOrd = new PlanOrd();

			Integer planID = getPlanId(req.getPlanName(), req.getTimes());
			planOrd.setPlanID(planID);

			planOrd.setTimeID(req.getTimeID());
			planOrd.setPeriodID(req.getPeriodID());

			String day = getPickupDay(req.getWeekDay());
			planOrd.setDay(day);

			planOrd.setWayID(req.getWayID());
			planOrd.setMemID(memID);
			planOrd.setPlanStart(req.getPlanStart());

			Date planEnd = getEndDate(req.getPlanStart(), Integer.valueOf(req.getPeriodID()));
			planOrd.setPlanEnd(planEnd);

			planOrd.setCityCode(req.getCityCode());
			planOrd.setFloor(req.getFloor());
			planOrd.setPickupStop(req.getPickupStop());
			planOrd.setAmendLog(0);
			planOrd.setPlanStatusID(210001);

			BigDecimal total = getPlanPrice(planID, Integer.valueOf(req.getPeriodID()));
			planOrd.setTotal(total);

			planOrd.setContact(req.getContact());
			planOrd.setContactTel(req.getContactTel());

			return dao.save(planOrd);
		} else {
			return null;
		}
	}

	// 方案名+收取次數取得方案ID
	private Integer getPlanId(String planName, String times) {

		return planDao.findIdByPlanNameAndTimes(planName, Integer.valueOf(times));

//			Integer planID = 0;
//			List<Plan> planList = planDao.findAll();
//			for (Plan p : planList) {
//				if (p.getPlanName().equals(planName) && p.getTimes() == Integer.valueOf(times)) {
//					planID = p.getPlanID();
//				}
//			}
//			return planID;
	}

	// 取得月數
	private Integer getPeriod(Integer periodID) {
		return periodDao.findById(periodID).get().getPlanPeriod();
	}

	// 取得方案價格
	private BigDecimal getPlanPrice(Integer planID, Integer periodID) {
		// 拿月數
		Integer planPeriod = getPeriod(periodID);
		System.out.println("planPeriod: " + planPeriod);
		// 拿到方案
		Plan plan = planDao.findById(planID).get();
		System.out.println(plan);
		// 方案價格*月數
		return plan.getPlanPrice().multiply(new BigDecimal(planPeriod));
	}

	// 計算結束日期
	private Date getEndDate(Date planStart, Integer periodID) {
		Integer planPeriod = getPeriod(periodID);
		LocalDate ld = planStart.toLocalDate();
		Date planEnd = Date.valueOf(ld.plusDays(planPeriod * 28 - 1));
		return planEnd;
	}

	// 取得收取日字串
	private String getPickupDay(String[] weekDay) {
		StringBuilder initDay = new StringBuilder("0000000");
		// checkbox回傳String[] weekDay，長度=checked幾項
		for (int i = 0; i < weekDay.length; i++) {
			// weekDay[0](星期一)="0", weekDay[1](星期二)="1"...
			int dayIndex = Integer.parseInt(weekDay[i]);
			// initDay相對應位置改為1
			initDay.setCharAt(dayIndex, '1');
		}
		return initDay.toString();
	}

//		{
//		    "planName": "商務方案",
//		    "timeID": "240002",
//		    "periodID": "220001",
//		    "times": "3",
//		    "weekDay": [
//		        "0",
//		        "1",
//		        "3"
//		    ],
//		    "wayID": "230004",
//		    "planStart": "2024-12-25",
//		    "contact": "小黃",
//		    "contactTel": "0988587587",
//		    "cityCode": "108",
//		    "floor": "門口",
//		    "pickupStop": "忠孝東路"
//		}

	// 修改
	public PlanOrd updatePlanOrd(PlanOrd req) {
		PlanOrd planOrd = new PlanOrd();
		planOrd.setPlanOrdID(req.getPlanOrdID());
		planOrd.setPlanID(req.getPlanID());
		planOrd.setTimeID(req.getTimeID());
		planOrd.setPeriodID(req.getPeriodID());
		planOrd.setDay(req.getDay());
		planOrd.setWayID(req.getWayID());
		planOrd.setMemID(req.getMemID());
		planOrd.setPlanStart(req.getPlanStart());
		planOrd.setPlanEnd(req.getPlanEnd());
		planOrd.setCityCode(req.getCityCode());
		planOrd.setFloor(req.getFloor());
		planOrd.setPickupStop(req.getPickupStop());
		planOrd.setPlanStatusID(210001);
		planOrd.setTotal(req.getTotal());
		planOrd.setContact(req.getContact());
		planOrd.setContactTel(req.getContactTel());
		return dao.save(planOrd);
	}

	public void deletePlanOrd(Integer planOrdID) {
		dao.deleteById(planOrdID);
	}

	public List<PlanOrd> getAllPlanOrd() {
		return dao.findAll();
	}

	public PlanOrd getPlanOrdById(Integer planOrdID) {
		return dao.findById(planOrdID).orElse(null);
	}

	// join查詢所有方案，以名稱而非ID顯示
	public List<PlanOrdDTO> getPlanOrdInfo() {
		Integer planOrdId = 0;
		String memName = "";
		String planName = "";
		Date planStart = null;
		Date planEnd = null;
		BigDecimal total = new BigDecimal(0);
		String planStatus = "";

		List<PlanOrdDTO> infoList = new ArrayList<PlanOrdDTO>();

		List<PlanOrd> planOrdList = dao.findAll();
		for (PlanOrd p : planOrdList) {
			planOrdId = p.getPlanOrdID();
			memName = getMemNameById(p.getMemID());
			planName = getPlanNameById(p.getPlanID());
			planStart = p.getPlanStart();
			planEnd = p.getPlanEnd();
			total = p.getTotal();
			planStatus = getPlanStatus(p.getPlanStatusID());

			PlanOrdDTO info = new PlanOrdDTO();
			info.setPlanOrdID(planOrdId);
			info.setMemName(memName);
			info.setPlanName(planName);
			info.setPlanStart(planStart);
			info.setPlanEnd(planEnd);
			info.setTotal(total);
			info.setPlanStatus(planStatus);

			infoList.add(info);
		}
		return infoList;
	}

	// ====for list name instead of ID, PlanOrdController====

	// planID找planName
	public String getPlanNameById(Integer planID) {
		return planDao.findById(planID).get().getPlanName();
	}

	// memID找memName
	public String getMemNameById(Integer memID) {
		return memDao.findById(memID).get().getMemName();
	}

	// timeID找timeRange
	public String getTimeRange(Integer timeID) {
		return pickupTimeDao.findById(timeID).get().getTimeRange();
	}

	// wayID找wayName
	public String getWayName(Integer wayID) {
		return pickupWayDao.findById(wayID).get().getWayName();
	}

	// periodID找planPeriod
	public String getPlanPeriod(Integer periodID) {
		return periodDao.findById(periodID).get().getPlanPeriod().toString();
	}

	// planStatusID找planStatus
	public String getPlanStatus(Integer planStatusID) {
		return planStatusDao.findById(planStatusID).get().getPlanStatus();
	}

	// day字串轉換為星期幾"1001100"
	public String getWeekDay(String day) {
		StringBuilder sb = new StringBuilder();
		char mon = day.charAt(0);
		char tue = day.charAt(1);
		char wed = day.charAt(2);
		char thu = day.charAt(3);
		char fri = day.charAt(4);
		char sat = day.charAt(5);
		char sun = day.charAt(6);
		if (mon == '1')
			sb.append("星期一/");
		if (tue == '1')
			sb.append("星期二/");
		if (wed == '1')
			sb.append("星期三/");
		if (thu == '1')
			sb.append("星期四/");
		if (fri == '1')
			sb.append("星期五/");
		if (sat == '1')
			sb.append("星期六/");
		if (sun == '1')
			sb.append("星期日/");
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	// ====for PlanOrdRestCon====

	// 取得不重複方案名 + 1次的價格
	public List<Object[]> findDistinctPlanNamesPriceLiter() {
		return planDao.findDistinctPlanNamesPriceLiter();
	}

	// 取得PickupTime
	public List<PickupTime> getPickupTime() {
		return pickupTimeDao.findAll();
	}

	// 取得Period
	public List<Period> getPeriod() {
		return periodDao.findAll();
	}

	// 取得PickupWay
	public List<PickupWay> getPickupWay() {
		return pickupWayDao.findAll();
	}

	// 取得City
	public List<City> getCity() {
		return cityDao.findAll();
	}
//	
//	//取得各方案可選的收取次數
//	public List<Integer> getTimeByPlanName(){
//		List<Plan> planList = planDao.findAll();
//		for(Plan p : planList) {
//			p.getTimes();
//		}
//		return planDao.findTimeByPlanName(null)
//	}

}
