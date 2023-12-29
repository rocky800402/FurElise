package com.furelise.ecpay.payment.integration;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EcpayQueryDto {

	private String customField1;
	private String customField2;
	private String customField3;
	private String customField4;
	private String handlingCharge;
	private String itemName;
	private String merchantID;
	private String merchantTradeNo;
	private String paymentDate;
	private String paymentType;
	private String paymentTypeChargeFee;
	private String storeID;
	private String tradeAmt;
	private String tradeDate;
	private String tradeNo;
	private String tradeStatus;
	private String checkMacValue;

	public static EcpayQueryDto fromMap(Map<String, String> formDataMap) {
		EcpayQueryDto dto = new EcpayQueryDto();
		dto.setCustomField1(formDataMap.get("CustomField1"));
		dto.setCustomField2(formDataMap.get("CustomField2"));
		dto.setCustomField3(formDataMap.get("CustomField3"));
		dto.setCustomField4(formDataMap.get("CustomField4"));
		dto.setHandlingCharge(formDataMap.get("HandlingCharge"));
		dto.setItemName(formDataMap.get("ItemName"));
		dto.setMerchantID(formDataMap.get("MerchantID"));
		dto.setMerchantTradeNo(formDataMap.get("MerchantTradeNo"));
		dto.setPaymentDate(formDataMap.get("PaymentDate"));
		dto.setPaymentType(formDataMap.get("PaymentType"));
		dto.setPaymentTypeChargeFee(formDataMap.get("PaymentTypeChargeFee"));
		dto.setStoreID(formDataMap.get("StoreID"));
		dto.setTradeAmt(formDataMap.get("TradeAmt"));
		dto.setTradeDate(formDataMap.get("TradeDate"));
		dto.setTradeNo(formDataMap.get("TradeNo"));
		dto.setTradeStatus(formDataMap.get("TradeStatus"));
		dto.setCheckMacValue(formDataMap.get("CheckMacValue"));
		return dto;
	}

}
