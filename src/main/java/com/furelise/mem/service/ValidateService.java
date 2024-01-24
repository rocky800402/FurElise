package com.furelise.mem.service;

import java.time.Duration;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.furelise.emp.controller.EmpAuthService;
import com.furelise.emp.model.Emp;
import com.furelise.mem.model.entity.Mem;
import com.furelise.mem.repository.MemRepository;

@Service
public class ValidateService {

	@Autowired
	private MemRepository memR;
	
	@Autowired
	private MemService memSvc;
	
	@Autowired
	private EmpAuthService empSvc;
	
	@Autowired
    private RedisTemplate<String, String> redisTemplate;

	public ValidateService() {
	}
	
	public void sendResetPWMail (String email) {
		
		Mem resetMem = memR.findByMemMail(email);
		
		if (resetMem != null) {
			// ===1. 創建token並存入資料庫===
			String token = UUID.randomUUID().toString().replace("-", "");
			
			// 存進redis，設定token有效時間30分鐘
			redisTemplate.opsForValue().set("ResetMail:" + email, token, Duration.ofMinutes(30));
			// ===2. 寄發重設密碼信件===
			String urlWithToken = "http://localhost:8080/" + "getpw?token=" + token;
			sendResetPWToken(email, urlWithToken);
			
		} else {
			
		}
		
	}
	
	
	// 重設密碼
	// 設定傳送郵件：至收信人的Email信箱、Email主旨、收件人稱呼
	public void sendResetPWToken(String to, String url) {
		
		String subject = "Für Elise會員重設密碼通知";
		String messageText = "您好，您正在申請重設密碼，請點擊此連結重新設置密碼: \n" + url + "\n" + " （該連結將在30分鐘後失效，請盡快完成密碼重設。）";

		MailService mailService = new MailService();
		mailService.sendMail(to, subject, messageText);
		
	}
	
	
	// 重設密碼
	// 重設密碼頁面驗證token後進行密碼更改
	public String updatePW(String memMail, String empMail, String pw, String token) {
		
		if (memMail != null) {
			// ===修改會員密碼===
			// 1. 取得redis裡已存在的token
			String existToken = redisTemplate.opsForValue().get("ResetMail:" + memMail);
			// 2. 比對url取得的token，token相同則進行密碼修改
			if (token.equals(existToken)) {
				Mem mem = memSvc.findByMemMail(memMail);
				mem.setMemPass(pw);
				memSvc.updateMem(mem);
				// 將token從資料庫刪除
				redisTemplate.delete("ResetMail:" + memMail);
				return "resetpw-resetsuccess";
			}
			
		} else if (empMail != null) {
			// ===修改夥伴密碼===
			// 1. 取得redis裡已存在的token
			String existToken = redisTemplate.opsForValue().get("ResetMail:" + empMail);
			// 2. 比對url取得的token，token相同則進行密碼修改
			if (token.equals(existToken)) {
				Emp emp = empSvc.findByEmpMail(empMail);
				emp.setEmpPass(pw);
				empSvc.updateEmp(emp);
				// 將token從資料庫刪除
				redisTemplate.delete("ResetMail:" + empMail);
				return "resetpw-resetsuccess";
			}
		
		}
		
		return null;
		
	}
	
}
