package com.furelise.planord.model;

import com.furelise.mem.repository.MemRepository;
import com.furelise.period.model.Period;
import com.furelise.period.model.PeriodRepository;
import com.furelise.pickuptime.model.PickupTimeRepository;
import com.furelise.pickupway.model.PickupWayRepository;
import com.furelise.plan.model.Plan;
import com.furelise.plan.model.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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

//	@Autowired
//	PlanOrd要加@Component
//	PlanOrd planOrd = new PlanOrd();

    private String planName = "商務方案"; // 方案名
    private String times = "3"; // 每週收取次
    private String[] weekDay = {"0", "1", "3"}; // 每週收取日
    // 需要處理：planID, day, memID, planEnd, total

    public PlanOrd addPlanOrd(PlanOrd req, Integer memID) {
        boolean hasOrd = false;
        List<PlanOrd> list = dao.findByMemID(memID);
        // 查詢會員訂購紀錄是否有planStatus=210001待收取;
        for (PlanOrd p : list) {
            if (p.getPlanStatusID() == 210001)
                hasOrd = true;
        }
        // 停止新增動作
        if (hasOrd) {
            System.out.println("尚有未到期的方案");
            return null;
            // 繼續新增
        } else {
            PlanOrd planOrd = new PlanOrd();

            Integer planID = getPlanId(planName, times);
            planOrd.setPlanID(planID);
//		planOrd.setPlanID(req.getPlanID());

            planOrd.setTimeID(req.getTimeID());
            planOrd.setPeriodID(req.getPeriodID());

            String day = getPickupDay(weekDay);
            planOrd.setDay(day);

            planOrd.setWayID(req.getWayID());
            planOrd.setMemID(req.getMemID());
            planOrd.setPlanStart(req.getPlanStart());

            Date planEnd = getEndDate(req.getPlanStart(), Integer.valueOf(req.getPeriodID()));
            planOrd.setPlanEnd(planEnd);

            planOrd.setCityCode(req.getCityCode());
            planOrd.setFloor(req.getFloor());
            planOrd.setPickupStop(req.getPickupStop());
            planOrd.setAmendLog(req.getAmendLog());
            planOrd.setPlanStatusID(req.getPlanStatusID());

            BigDecimal total = getPlanPrice(planID, Integer.valueOf(req.getPeriodID()));
            planOrd.setTotal(total);
//		planOrd.setTotal(new BigDecimal(50));

            planOrd.setContact(req.getContact());
            planOrd.setContactTel(req.getContactTel());
            return dao.save(planOrd);
        }
    }

    // 方案名+收取次數取得方案ID
    private Integer getPlanId(String planName, String times) {

        Integer planID = 0;

        List<Plan> planList = planDao.findAll();
        for (Plan p : planList) {
            if (p.getPlanName().equals(planName) && p.getTimes() == Integer.valueOf(times)) {
                planID = p.getPlanID();
            }
        }
        return planID;
    }

    // 取得月數
    private Integer getPeriod(Integer periodID) {
        Period period = periodDao.findById(periodID).get();
        System.out.println("planPeriod: " + period.getPlanPeriod());
        return period.getPlanPeriod();
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

//	{
//	    "timeID": "240003",
//	    "periodID": "220012",
//	    "wayID": "230007",
//	    "memID": "110003",
//	    "planStart": "2024-01-01",
//	    "cityCode" : "115",
//	    "planStatusID" : "210001",
//	    "contact" : "小華",
//	    "contactTel" : "0988587587",
//	    "amendLog" : "0",
//	    "pickupStop" : "中正路",
//	    "floor" : "門口"
//	}

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
        planOrd.setPlanStatusID(req.getPlanStatusID());
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

    // 回傳會員姓名
    public String getNameById(Integer memID) {
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
            sb.append("星期一");
        if (tue == '1')
            sb.append("/星期二");
        if (wed == '1')
            sb.append("/星期三");
        if (thu == '1')
            sb.append("/星期四");
        if (fri == '1')
            sb.append("/星期五");
        if (sat == '1')
            sb.append("/星期六");
        if (sun == '1')
            sb.append("/星期日");
        return sb.toString();
    }

    //取得不重複方案名 + 1次的價格
    public List<Object[]> findDistinctPlanNamesAndPrice() {
        return planDao.findDistinctPlanNamesAndPrice();
//		for(String s : planNames) 
//		{
//		}
    }

}
