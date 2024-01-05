package com.furelise.estabcase.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Service
public class SplitPlanOrdService {
    @Autowired
    EstabCaseRepository estabCaseR;
    public List<EstabCase> addEstabCases(Integer planOrdID, String planStart, Integer period, String day, BigDecimal planPricePerCase) {

        List<EstabCase> eCases = new ArrayList<>();
        List<java.util.Date> list = getSplitPlanOrd(planStart, period, day);

        for (java.util.Date date : list) {
            EstabCase eCase = new EstabCase();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            eCase.setEstabCaseDate(sqlDate);
            eCase.setPlanOrdID(planOrdID);
            eCase.setPlanPricePerCase(planPricePerCase);
            eCase.setTakeStatus(false);
            eCase.setEstabCaseStatus(0);
            estabCaseR.save(eCase);
        }

        return eCases;
    }
    /**
     * 方案猜單系統，取得指定日期範圍內符合指定星期幾的日期列表
     *
     * @param planStart 起始日期的 String 物件
     * @param period    訂閱時長 int 物件
     * @param day       代表星期幾的字串，每個字元代表一天，7位數依序禮拜一至禮拜日的字串，數字1代表有服務需求 (EX:"1010000"表示禮拜一、禮拜三有服務需求)
     * @return 篩選後的日期集合
     */
    public List<Date> getSplitPlanOrd(String planStart, int period, String day) {
        // 將字串轉換String → util.Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date planStartDate = null;
        try {
            // ------------util.Date開始時間(planStartDate)
            planStartDate = sdf.parse(planStart);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // util.Date → util.Calendar
        Calendar calStart = Calendar.getInstance();
        calStart.setTime(planStartDate);

        // 顯示開始計劃日期
//		String sdfstr2 = sdf.format(planStartDate);
//		System.out.println(sdfstr2);

        // 複製起始日期並設定結束日期
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(planStartDate);
        calEnd.add(Calendar.DATE, (period*(28)));

        // 測試區間日期指定禮拜產製日期
//		getWeekBetweenDates(calStart, calEnd, "2");

        // ------------util.Date結束時間(planEndDate)
        // util.Calendar →util.Date
        java.util.Date planEndDate = calEnd.getTime();

        // 顯示結束計劃日期
//		String sdfstr = sdf.format(planEndDate);
//		System.out.println(sdfstr);

        // 取得指定星期的日期列表
        List<java.util.Date> list = getWeekBetweenDates(calStart, calEnd, day);
//		for (java.util.Date date : list) {
//			System.out.println(date);
//		}

        return list;
    }

    /**
     * 取得指定日期範圍內符合指定星期幾的日期列表
     *
     * @param calStart 起始日期的 Calendar 物件
     * @param calEnd   結束日期的 Calendar 物件
     * @param week     代表星期幾的字串，每個字元代表一天，使用數字表示 (e.g., "1234567")
     * @return 符合條件的日期列表
     */
    public  List<java.util.Date> getWeekBetweenDates(Calendar calStart, Calendar calEnd, String week) {
        // 取得目標星期幾的整數陣列
        int[] targetDays = getTargetDays(week);

        // 建立一個存放符合條件的日期的列表
        List<java.util.Date> list = new ArrayList<>();

        // 複製起始日期，以便在迴圈中修改 currentDate 不影響原始 calStart
        Calendar currentDate = (Calendar) calStart.clone();

        // 將結束日期加一天，以便包含結束日期
//		calEnd.add(Calendar.DAY_OF_YEAR, 1);

        // 遍歷日期範圍
        while (currentDate.before(calEnd)) {
            // 如果當前日期是目標星期幾之一，將其加入到列表中
            if (isTargetDay(currentDate, targetDays)) {
                list.add(currentDate.getTime());
            }

            // 前進到下一天
            currentDate.add(Calendar.DAY_OF_YEAR, 1);
        }

        // 返回符合條件的日期列表
        return list;
    }

    /**
     * 檢查指定日期是否為目標星期幾之一
     *
     * @param date       要檢查的日期
     * @param targetDays 包含目標星期幾的整數陣列
     * @return 如果指定日期為目標星期幾之一，返回 true；否則返回 false
     */
    public  boolean isTargetDay(Calendar date, int[] targetDays) {
        // 取得指定日期的星期幾
        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);

        // 遍歷目標星期幾的整數陣列
        for (int targetDay : targetDays) {
            // 如果指定日期的星期幾等於目標星期幾之一，返回 true
            if (targetDay == dayOfWeek) {
                return true;
            }
        }

        // 如果指定日期的星期幾不在目標星期幾之列，返回 false
        return false;
    }

    /**
     * 將代表星期幾的字串轉換成整數陣列
     *
     * @param week 代表星期幾的字串，每個字元代表一天，使用數字表示 (e.g., "1234567")
     * @return 一個整數陣列，包含了每個星期幾的數字
     */
    public  int[] getTargetDays(String week) {
        //把代碼最後一碼換到前面EX:1000000更換成0100000 讓禮拜日放第一個位置配合Calendar.DAY_OF_WEEK
        String weekChangSun = week.substring(6) + week.substring(0, 6);
        // 建立一個整數陣列，其大小為星期幾字串的長度
        int[] weeks = new int[weekChangSun.length()];
        int targetDaysLength = 0;
        // 遍歷星期幾字串的每一個字元
        for (int i = 0; i < weekChangSun.length(); i++) {
            // 將字元轉換成對應的數字，並存入陣列中
            weeks[i] = Integer.parseInt(weekChangSun.substring(i, i + 1));
            if (weeks[i] == 1) {
                targetDaysLength++;
            }
        }
        int index = 0;
        int[] targetDays = new int[targetDaysLength];
        for (int i = 0; i < weeks.length; i++) {
            if (weeks[i] == 1) {
                for (int j = 0; j < targetDays.length; j++)
                    targetDays[index] = i + 1;
                index++;
            }
        }
        return targetDays;
    }
}
