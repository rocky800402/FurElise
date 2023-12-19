package com.furelise.mem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.furelise.mem.model.entity.Mem;
import com.furelise.mem.service.MemService;
import com.furelise.util.JedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Controller
@RequestMapping
public class VerificationController {

	@Autowired
	private MemService memSvc;
	
	@Autowired
	private JedisPool jedisPool = null;

	@PostMapping("/verification")
	protected String doVerify(HttpServletRequest req,
							  Model model,
							  HttpSession session)
			throws ServletException, IOException {
		
		
		// ===獲得請求參數===
		String action = req.getParameter("action");
		String enteredCode = req.getParameter("enteredCode");
//		String email = (String) req.getAttribute("email");
//		String name = (String) req.getAttribute("name");
//		Mem newMem = (Mem) req.getAttribute("newMem");
		String email = (String) session.getAttribute("email");
		String name = (String) session.getAttribute("name");
		Mem newMem = (Mem) session.getAttribute("newMem");
		
		String errMsgs = "";
		
		
		// ===1. action為按下驗證按鈕===
		if ("get_EnteredCode".equals(action)) {
			
			jedisPool = JedisUtil.getJedisPool(); // 取得jedis連線池
			Jedis jedis = jedisPool.getResource();
			jedis.select(2);
			// 到Redis資料庫確認驗證碼還在不在
			String verifyCode = jedis.get("MemMail:" + email); 
			jedis.close();
			
			// 比對驗證碼是否輸入正確
			if (enteredCode == null || (enteredCode.trim().length()) == 0) {
				// 未輸入
				errMsgs = "請輸入驗證碼！";
				model.addAttribute("errMsgs", errMsgs);
				return "verificationCode_page";
			} else if (verifyCode == null) {
				// 驗證碼已過期
				errMsgs = "驗證碼已過期，請點選下方「重發驗證碼」按鈕獲取新驗證碼。";
				model.addAttribute("errMsgs", errMsgs);
				return "verificationCode_page";
			} else if (!verifyCode.equals(enteredCode)) {
				// 驗證失敗
				errMsgs = "驗證碼錯誤，請再試一次。";
				model.addAttribute("errMsgs", errMsgs);
				return "verificationCode_page";
			} else if (verifyCode.equals(enteredCode)) {
				// 驗證成功
				// 將newMem新增至資料庫
				memSvc.addMem( newMem.getMemName(),
							   newMem.getMemMail(), 
							   newMem.getMemTel(),
							   newMem.getMemBirth(), 
							   newMem.getMemPass() );
				
				session.removeAttribute("email");
				session.removeAttribute("name");
				session.removeAttribute("newMem");
				
				// 新增完成，forward至註冊成功頁面
				return "verificationCode_page_success";
			}
			
		}	
			
		
		
		// ===2. action為按下重發驗證碼按鈕===
		if ("reSend_VerificationCode".equals(action)) {
			
			jedisPool = JedisUtil.getJedisPool(); // 取得jedis連線池
			Jedis jedis = jedisPool.getResource();
			jedis.select(2);
			
			// 到Redis資料庫確認驗證碼還在不在
			String verifyCode = jedis.get("MemMail:" + email); 
			// 如果原本的驗證碼尚有效，將其刪除
			if (verifyCode != null) {
				jedis.del("MemMail:" + email);
			}
			
			String code = memSvc.returnAuthCode(); // 產生驗證碼
			System.out.println("Auth code is: " + code);
			
			// 存入Redis
			jedis.set("MemMail:" + email, code); // 格式：Email : code
			jedis.expire("MemMail:" + email, 600); // TTL 10分鐘
			
			memSvc.sendVerificationCode(email, name, code);
			jedis.close();
			
			// ===forward至驗證頁完成驗證===
			errMsgs = "新驗證碼已重新寄發至您的email信箱！請至信箱收信並盡快完成驗證。";
			model.addAttribute("errMsgs", errMsgs);
			return "verificationCode_page";
			
		}
		
		return null;
		
	}
	
}
