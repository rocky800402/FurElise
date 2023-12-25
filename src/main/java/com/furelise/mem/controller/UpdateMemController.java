package com.furelise.mem.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.furelise.mem.model.entity.Mem;
import com.furelise.mem.service.MemService;

@Controller
@RequestMapping("/member_home_alter")
public class UpdateMemController {

	@Autowired
	private MemService memSvc;
	
	@PostMapping("/{memID}/updateMem")
	public String updateMem(@PathVariable("memID")Integer memID, HttpServletRequest req, Model model, HttpSession session) {
		
		// ===1. 獲得請求參數===
		String name = req.getParameter("memName");
		String tel = req.getParameter("memTel");
		
		// 日期轉換: String to Date
		String birth = req.getParameter("memBirth").replaceAll("/", "-");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthDate = LocalDate.parse(birth, formatter);
		
//		String email = req.getParameter("memMail");
		String newPass = req.getParameter("mem_pw_new");
		String cfmPass = req.getParameter("mem_pw_newConfirm");
		
		Mem mem = (Mem) session.getAttribute("mem");
	    model.addAttribute("mem", mem);
		
	    
//	    System.out.println(name);
//	    System.out.println(tel);
//	    System.out.println(birth);
//	    System.out.println(email);
//	    System.out.println(newPass);
//	    System.out.println(cfmPass);
//	    System.out.println(mem);
	    
	    
		
		// ===2. 錯誤處理===
		List<String> errMsgs = new LinkedList<String>();
		
		// name錯誤處理
        /*
         * 使用捕獲組 () 來表示整個名字的模式。
         * 模式包含大小寫字母、底線 [a-zA-Z_] 和漢字的 Unicode 範圍 \u4e00-\u9fa5。
         * {2,15} 指定捕獲組內容的長度應在 2 到 15 個字符之間。
         */
        String nameReg = "^([a-zA-Z_\u4e00-\u9fa5]{2,15})$";
        if (name == null || (name.trim().length()) == 0) {
            // 未輸入
            errMsgs.add(" 請輸入姓名！");
        } else if (!name.trim().matches(nameReg)) {
            errMsgs.add(" 姓名格式有誤：僅能輸入中、英文字母與底線，且長度必需在2到15之間。");
        }
		
        // tel錯誤處理
        // [0-9]+：匹配一個或多個（+）數字（0到9）
        String telReg = "^[0-9]+$";
        if (tel == null || (tel.trim().length()) == 0) {
            // 未輸入
            errMsgs.add(" 請輸入電話！");
        } else if (!tel.trim().matches(telReg)) {
            errMsgs.add(" 電話格式有誤：僅能輸入數字。");
        }
        
        // birth錯誤處理
        long nowMillis = System.currentTimeMillis();
        Date nowDate = new Date(nowMillis);
        if (birth == null || (birth.trim().length()) == 0) {
            errMsgs.add(" 請選擇生日日期！");
        } else {
            // yyyy/mm/dd轉成yyyy-mm-dd，再從String轉成Date
            if (birthDate.isAfter(birthDate)) {
                // 生日不可以選比今天晚的日期
                errMsgs.add(" 生日輸入有誤：不可選擇晚於今日的日期。");
            }
        }
        
        // email因為等於帳號，故不提供更改
        
        // newPass錯誤處理(不一定要修改密碼)
        if (newPass != null && (newPass.trim().length()) != 0) {
	        if (!newPass.equals(cfmPass)) {
	            // 密碼與確認密碼不相同
	            errMsgs.add(" 新密碼與確認密碼輸入不相同，請修正！");
	        }
        }
        // 資料有誤，返回頁面顯示錯誤訊息
        if (!errMsgs.isEmpty()) {
			model.addAttribute("errMsgs", errMsgs);
        	return "member_home_alter";
        } else {
			// ===3. 找到該名會員，並進行資料修改===
			Mem updMem = memSvc.getOneMem(memID);
			
			updMem.setMemName(name);
			updMem.setMemTel(tel);
			updMem.setMemBirth(birthDate); // Date
//			updMem.setMemMail(email);
			 if (newPass != null && (newPass.trim().length()) != 0) {
				 updMem.setMemPass(newPass);
			 }
			
			// 將更新資料存入資料庫
			memSvc.updateMem(updMem);
			String successMsg = "會員資料已更新完成！";
			model.addAttribute("successMsg", successMsg);
			model.addAttribute("mem", updMem); // 立即反映在頁面上
			session.setAttribute("mem", updMem); // 更新session資料
			return "member_home_alter";
        }
        
	}
	
}
