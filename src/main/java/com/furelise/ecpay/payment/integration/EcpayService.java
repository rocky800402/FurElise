package com.furelise.ecpay.payment.integration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.furelise.ecpay.payment.integration.domain.AioCheckOutALL;

@Service
public class EcpayService {
	
	
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
	

}
