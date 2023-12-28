package com.furelise.page;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.furelise.ecpay.payment.integration.AllInOne;
import com.furelise.ecpay.payment.integration.EcpayService;
import com.furelise.ecpay.payment.integration.domain.AioCheckOutALL;

@Controller
public class PageController {
	
//	public PageController() throws UnknownHostException {
//		String ip = InetAddress.getLocalHost().getHostAddress();
//	}
	
	
	@Autowired
	EcpayService ecpayService;

	@GetMapping("/pay")
	public String home() {
		return "pay";
	}

	@PostMapping("/payResult")
	public String getPayResult(@RequestParam Map<String, Object> params) {
		String merchantID = (String) params.get("MerchantID");
		String merchantTradeNo = (String) params.get("MerchantTradeNo ");
		Integer rtnCode = (int) params.get("RtnCode");
		String rtnMsg = (String) params.get("RtnMsg ");
		String tradeNo = (String) params.get("TradeNo");
		String paymentDate = (String) params.get("PaymentDate");
		String paymentType = (String) params.get("PaymentType");
		String tradeDate = (String) params.get("TradeDate");
		Integer simulatePaid = (int) params.get("SimulatePaid");

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("MerchantID: ").append(merchantID);
		stringBuilder.append("MerchantTradeNo: ").append(merchantTradeNo);
		stringBuilder.append("RtnCode: ").append(rtnCode);
		stringBuilder.append("RtnMsg: ").append(rtnMsg);
		stringBuilder.append("TradeNo: ").append(tradeNo);
		stringBuilder.append("PaymentDate: ").append(paymentDate);
		stringBuilder.append("PaymentType: ").append(paymentType);
		stringBuilder.append("TradeDate: ").append(tradeDate);
		stringBuilder.append("SimulatePaid: ").append(simulatePaid);

		System.out.println(stringBuilder.toString());

		return "1|OK";
	}

	@PostMapping("/pay")
	public String startPay(Model model) {
		AllInOne all = new AllInOne("");
		AioCheckOutALL obj = new AioCheckOutALL();
		// 填入必要的資料
		String string = String.valueOf((int) (Math.random() * 10000000 + 1)) + "202312171651";
		obj.setMerchantTradeNo(string);
		obj.setMerchantTradeDate("2023/12/17 08:05:23");
		obj.setTotalAmount("50");
		obj.setTradeDesc("test Description");
		obj.setItemName("垃圾袋#掃把#酒精");
		obj.setReturnURL("localhost:8080/payResult");
		obj.setNeedExtraPaidInfo("N");
		
		Map<String, String> form = ecpayService.doPayment(obj);

		// 回傳form表單的資料
		model.addAttribute("action", "https://payment-stage.ecPay.com.tw/Cashier/AioCheckOut/V5");
        model.addAttribute("method", "post");
        model.addAttribute("formData", form);
        return "paymentForm";
	}

	
}
