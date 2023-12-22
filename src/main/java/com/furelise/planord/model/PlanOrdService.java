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
		//狀態碼210003=待付款
		Integer planID = getPlanId(req.getPlanName(), req.getTimes());
		String day = getPickupDay(req.getWeekDay());

		PlanOrd planOrd = new PlanOrd(planID, req.getTimeID(), req.getPeriodID(), day, req.getWayID(), memID,
				req.getPlanStart(), req.getPlanEnd(), req.getCityCode(), req.getFloor(), req.getPickupStop(),
				new BigDecimal(req.getAfterTotal()), 0, 210003, req.getContact(), req.getContactTel());

		return dao.save(planOrd);
	}
//		// planStatusID、amendLog寫死
//
//		
//		if (payment) {
//			PlanOrd planOrd = new PlanOrd();
//
//			Integer planID = getPlanId(req.getPlanName(), req.getTimes());
//			planOrd.setPlanID(planID);
//
//			planOrd.setTimeID(req.getTimeID());
//			planOrd.setPeriodID(req.getPeriodID());
//
//			String day = getPickupDay(req.getWeekDay());
//			planOrd.setDay(day);
//
//			planOrd.setWayID(req.getWayID());
//			planOrd.setMemID(memID);
//			planOrd.setPlanStart(req.getPlanStart());
//
//			planOrd.setPlanEnd(req.getPlanEnd());
////			Date planEnd = getEndDate(req.getPlanStart(), Integer.valueOf(req.getPeriodID()));
////			planOrd.setPlanEnd(planEnd);
//
//			planOrd.setCityCode(req.getCityCode());
//			planOrd.setFloor(req.getFloor());
//			planOrd.setPickupStop(req.getPickupStop());
//			planOrd.setAmendLog(0);
//			planOrd.setPlanStatusID(210001);
//			
//			planOrd.setTotal(new BigDecimal(req.getAfterTotal()));
//			System.out.println(req.getAfterTotal());
////			BigDecimal total = getPlanPrice(planID, Integer.valueOf(req.getPeriodID()));
////			planOrd.setTotal(total);
//			
//					
//			planOrd.setContact(req.getContact());
//			planOrd.setContactTel(req.getContactTel());
//
//			return dao.save(planOrd);
//		} else {
//			return null;
//		}

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

	// 取得月數 //沒用到
	private Integer getPeriod(Integer periodID) {
		return periodDao.findById(periodID).get().getPlanPeriod();
	}

//	// 取得方案價格 //沒用到
//	private BigDecimal getPlanPrice(Integer planID, Integer periodID) {
//		// 拿月數
//		Integer planPeriod = getPeriod(periodID);
//		System.out.println("planPeriod: " + planPeriod);
//		// 拿到方案
//		Plan plan = planDao.findById(planID).get();
//		System.out.println(plan);
//		// 方案價格*月數
//		return plan.getPlanPrice().multiply(new BigDecimal(planPeriod));
//	}
//
//	// 計算結束日期 //沒用到
//	private Date getEndDate(Date planStart, Integer periodID) {
//		Integer planPeriod = getPeriod(periodID);
//		LocalDate ld = planStart.toLocalDate();
//		Date planEnd = Date.valueOf(ld.plusDays(planPeriod * 28 - 1));
//		return planEnd;
//	}

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

	// 修改
//	public PlanOrd updatePlanOrd(PlanOrd req) {
//		PlanOrd planOrd = new PlanOrd();
//		planOrd.setPlanOrdID(req.getPlanOrdID());
//		planOrd.setPlanID(req.getPlanID());
//		planOrd.setTimeID(req.getTimeID());
//		planOrd.setPeriodID(req.getPeriodID());
//		planOrd.setDay(req.getDay());
//		planOrd.setWayID(req.getWayID());
//		planOrd.setMemID(req.getMemID());
//		planOrd.setPlanStart(req.getPlanStart());
//		planOrd.setPlanEnd(req.getPlanEnd());
//		planOrd.setCityCode(req.getCityCode());
//		planOrd.setFloor(req.getFloor());
//		planOrd.setPickupStop(req.getPickupStop());
//		planOrd.setPlanStatusID(210001);
//		planOrd.setTotal(req.getTotal());
//		planOrd.setContact(req.getContact());
//		planOrd.setContactTel(req.getContactTel());
//		return dao.save(planOrd);
//	}

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

	// 找當前使用者memID的訂單是否有planEndDate晚於三天後的
	public boolean verifyPlanOrdPurchase(Integer memID, String planStart) {
		// find planOrd of a member
		boolean proceed = false;
		List<PlanOrd> planOrdList = dao.findByMemID(memID);
		// no purchase record
		if (planOrdList.isEmpty()) {
			proceed = true;
		} else {
			for (PlanOrd p : planOrdList) {
				// planStart later than existing planEnd
				if (Date.valueOf(planStart).compareTo(p.getPlanEnd()) > 0) // 可以買
					proceed = true;
				else {
					proceed = false;
					break;
				}
			}
		}
		return proceed;
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
