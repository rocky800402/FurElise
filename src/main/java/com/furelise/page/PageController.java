package com.furelise.page;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.furelise.ecpay.payment.integration.AllInOne;
import com.furelise.ecpay.payment.integration.EcpayDto;
import com.furelise.ecpay.payment.integration.domain.AioCheckOutALL;
import com.furelise.ecpay.payment.integration.domain.QueryTradeInfoObj;
import com.furelise.ecpay.payment.integration.service.EcpayService;
import com.furelise.ord.model.OrdDTO;
import com.furelise.planord.model.PlanOrdDTO;

@Controller
@RequestMapping("ecpay")
public class PageController {
	
	
	
//	public PageController() throws UnknownHostException {
//		String ip = InetAddress.getLocalHost().getHostAddress();
//	}
	
	
	@Autowired
	EcpayService ecpayService;
	
	@GetMapping("/status")
	@ResponseBody
	public Boolean isOrderSuccess(String tradeNo) {
		
		QueryTradeInfoObj queryTradeInfoObj = new QueryTradeInfoObj();
		queryTradeInfoObj.setMerchantID("3002607");
		queryTradeInfoObj.setMerchantTradeNo(tradeNo);
		queryTradeInfoObj.setTimeStamp(String.valueOf(System.currentTimeMillis()/1000));
		return ecpayService.isOrderSuccess(queryTradeInfoObj);
	}
	
	@PostMapping("/updateStatus")
	@ResponseBody
	public ResponseEntity<String> updateStatus(String tradeNo,  String status) {
		
		System.out.println("ecpay/updateStatus get request: " + tradeNo + ", " + status);
		try {
			ecpayService.setOrderStatus(tradeNo, status);
			return ResponseEntity.ok("update status success");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("update status fail");
		}
		
		
	}
	
	

//	@GetMapping("/pay")
//	public String home() {
//		return "pay";
//	}

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
	@ResponseBody
	public EcpayDto startPay(@Valid @RequestBody PlanOrdDTO dto, Model model) {
		System.out.println("/ecpay/pay get request: "+dto);
		AllInOne all = new AllInOne("");
		AioCheckOutALL obj = new AioCheckOutALL();
		// 填入必要的資料
		obj.setMerchantTradeNo(String.valueOf(dto.getPlanOrdID()));
		String tradeDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss"));
		obj.setMerchantTradeDate(tradeDate);
		obj.setTotalAmount(String.valueOf(dto.getTotal().intValue()));
		obj.setTradeDesc("test Description");
		obj.setItemName(ecpayService.getPlanName(dto.getPlanID()));
		obj.setReturnURL("localhost:8080/payResult");
		obj.setNeedExtraPaidInfo("N");
		
		Map<String, String> form = ecpayService.doPayment(obj);

		EcpayDto ecpayDto = new EcpayDto();
		ecpayDto.setAction("https://payment-stage.ecPay.com.tw/Cashier/AioCheckOut/V5");
		ecpayDto.setMethod("post");
		ecpayDto.setFormData(form);
		// 回傳form表單的資料
//		model.addAttribute("action", "https://payment-stage.ecPay.com.tw/Cashier/AioCheckOut/V5");
//        model.addAttribute("method", "post");
//        model.addAttribute("formData", form);
        return ecpayDto;
	}
	
//	@PostMapping("/pay-ord")
//	@ResponseBody
//	public EcpayDto startPayForOrdDTO(@Valid @RequestBody OrdDTO dto, Model model) {
//		System.out.println("/ecpay/pay get request: "+dto);
//		AllInOne all = new AllInOne("");
//		AioCheckOutALL obj = new AioCheckOutALL(); 
//		// 填入必要的資料
//		obj.setMerchantTradeNo(String.valueOf(dto.getOrdID()));
//		String tradeDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss"));
//		obj.setMerchantTradeDate(tradeDate);
//		obj.setTotalAmount(String.valueOf(dto.getTotal().intValue()));
//		obj.setTradeDesc("test Description");
////		obj.setItemName(ecpayService.getPlanName(dto.getPlanID())); //訂單沒有名稱
//		obj.setReturnURL("localhost:8080/payResult");
//		obj.setNeedExtraPaidInfo("N");
//		
//		Map<String, String> form = ecpayService.doPayment(obj);
//		
//		EcpayDto ecpayDto = new EcpayDto();
//		ecpayDto.setAction("https://payment-stage.ecPay.com.tw/Cashier/AioCheckOut/V5");
//		ecpayDto.setMethod("post");
//		ecpayDto.setFormData(form);
//		// 回傳form表單的資料
////		model.addAttribute("action", "https://payment-stage.ecPay.com.tw/Cashier/AioCheckOut/V5");
////        model.addAttribute("method", "post");
////        model.addAttribute("formData", form);
//		return ecpayDto;
//	}
	
	@GetMapping("/paymentForm")
	public String finishPay() {
		return "paymentForm";
	}

	
}
