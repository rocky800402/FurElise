package com.furelise.ecpay.payment.integration;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
public class EcpayDto {
	
	private String action;
	private String method;
	private Map<String, String> formData;

}