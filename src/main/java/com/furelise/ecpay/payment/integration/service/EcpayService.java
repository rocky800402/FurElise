package com.furelise.ecpay.payment.integration.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.furelise.estabcase.model.SplitPlanOrdService;
import com.furelise.period.model.Period;
import com.furelise.period.model.PeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furelise.ecpay.payment.integration.AllInOne;
import com.furelise.ecpay.payment.integration.EcpayQueryDto;
import com.furelise.ecpay.payment.integration.domain.AioCheckOutALL;
import com.furelise.ecpay.payment.integration.domain.QueryTradeInfoObj;
import com.furelise.ecpay.payment.integration.repository.EcpayRepository;
import com.furelise.ecpay.payment.integration.repository.OrdEcpayRepository;
import com.furelise.ecpay.payment.integration.repository.OrdPay;
import com.furelise.ecpay.payment.integration.repository.PlanOrdPay;
import com.furelise.plan.model.Plan;
import com.furelise.plan.model.PlanRepository;
import com.furelise.planord.model.PlanOrd;
import com.furelise.planord.model.PlanOrdRepository;

@Service
public class EcpayService {

	@Autowired
	private EcpayRepository dao;
	
	@Autowired
	private OrdEcpayRepository oDao;

	@Autowired
	private PlanRepository planRepository;
	
	@Autowired
	private PlanOrdRepository planOrdRepository;
	@Autowired
	private SplitPlanOrdService splitPlanOrdService;

	@Autowired
	private PeriodRepository periodRepository;

	public String getPlanName(String planID) {
		Optional<Plan> olanOptional = planRepository.findById(Integer.valueOf(planID));
		return olanOptional.get().getPlanName();

	}

	public PlanOrdPay getOnePlanOrd(Integer planOrdID) {
		return dao.getReferenceById(planOrdID);
	}
	
//	public OrdPay getOneOrd(Integer ordID) {
//		return oDao.getReferenceById(ordID);
//	}

	public Boolean isOrderSuccess(QueryTradeInfoObj queryTradeInfoObj) {
		Boolean flagBoolean = false;

		AllInOne all = new AllInOne("");
		String response = all.queryTradeInfo(queryTradeInfoObj);
		EcpayQueryDto ecpayQueryDto = convertFormDataToDTO(response);
		
		if ("1".equals(ecpayQueryDto.getTradeStatus())) {
			
			flagBoolean = true;
		}
		
		return flagBoolean;

	}

	/**
	 * get pay form
	 */
	public Map<String, String> doPayment(AioCheckOutALL info) {
		AllInOne all = new AllInOne("");
		return parseFormData(all.aioCheckOut(info, null));

	}

	private Map<String, String> parseFormData(String formData) {
		Map<String, String> formMap = new HashMap<>();
		String[] inputs = formData.split("<input type=\"hidden\" name=\"");

		for (String input : inputs) {
			if (input.contains("value=\"")) {
				String[] parts = input.split("value=\"", 2);
				if (parts.length > 1) {
					String name = parts[0].trim().replace("\"", "");
					String value = parts[1].split("\"", 2)[0];
					formMap.put(name, value);
				}
			}
		}
		return formMap;
	}

	private static EcpayQueryDto convertFormDataToDTO(String formDataString) {
		 Map<String, String> map = Arrays.stream(formDataString.split("&"))
                .map(s -> s.split("="))
                .collect(Collectors.toMap(
                        entry -> entry[0],
                        entry -> entry.length > 1 ? entry[1] : ""
                ));
		 return EcpayQueryDto.fromMap(map);

	}

	public void setOrderStatus(String orderID, String status) {
		Optional<PlanOrd> planOrdOptional = planOrdRepository.findById(Integer.valueOf(orderID));
		PlanOrd planOrd = planOrdOptional.orElseThrow(() -> new NoSuchElementException("Order: "+ orderID+" not found")); 
		planOrd.setPlanStatusID(Integer.valueOf(status));
		Plan plan = planRepository.findById(planOrd.getPlanID()).orElseThrow();
		Period period = periodRepository.findById(planOrd.getPeriodID()).orElseThrow();
		//猜單
		splitPlanOrdService.addEstabCases(
				planOrd.getPlanOrdID(),
				String.valueOf(planOrd.getPlanStart()),
				period.getPlanPeriod(),
				planOrd.getDay(),
				plan.getPlanPricePerCase()
		);

		planOrdRepository.save(planOrd);
		
	}
}
