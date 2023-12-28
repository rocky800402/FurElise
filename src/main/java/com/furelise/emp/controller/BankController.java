package com.furelise.emp.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.furelise.emp.model.Emp;

@Controller
@RequestMapping("/emp")
public class BankController {

	@Autowired
	private EmpAuthService empSvc;

	@GetMapping("/bankinfo")
	public String showPage(Model model, HttpSession session) {
		Emp emp = (Emp) session.getAttribute("emp");
		model.addAttribute("emp", emp);
		return "emp-bankSetting";
	}
	
	@PostMapping("/bankinfo/upload")
	public String uploadPic(HttpServletRequest req, Model model, HttpSession session) throws IOException, ServletException {
		
		// 從session取出已登入的emp物件備用
    	Emp emp = (Emp) session.getAttribute("emp");
    	
		// ===1. 獲得請求參數===
		// 存摺封面
		Part pic_part = req.getPart("emp_uploadBank");
		byte[] pic = null;
		if (pic_part != null) {
			InputStream pic_is = pic_part.getInputStream();
			pic = readInputStream(pic_is);
		}
		
		String bankCode = req.getParameter("emp_bankCode");
		String account = req.getParameter("emp_accountNo");
		
		
		// ===2. 錯誤驗證===
		// ===輸入格式驗證錯誤處理===
        List<String> errMsgs = new LinkedList<String>();
        
        // 可不上傳存摺封面
        
        // bankCode錯誤處理
        String codeReg = "^\\d{3}$";
        if (bankCode == null || bankCode.trim().length() == 0) {
        	// 未輸入
        	errMsgs.add(" 請輸入金融機構代碼（共三碼）。");
        } else if (!bankCode.trim().matches(codeReg)) {
        	// 格式不符合
        	errMsgs.add(" 金融機構代碼格式不符：請輸入共三碼數字。");
        }
        
        // account錯誤處理
        String accReg = "^\\d+$";
        if (account == null || account.trim().length() == 0) {
        	// 未輸入
        	errMsgs.add(" 請輸入銀行帳號。");
        } else if (!account.trim().matches(accReg)) {
        	// 格式不符合
        	errMsgs.add(" 帳號格式不符：僅可輸入數字。");
        }
        
        // 將資料存進Emp物件，如資料有誤可返回頁面時直接顯示
        Emp temporaryEmp = new Emp();
        
        temporaryEmp.setBankPic(pic);
        temporaryEmp.setBankCode(bankCode);
        temporaryEmp.setAccountNo(account);
        
        
        // ===3.1 資料有誤，返回頁面顯示錯誤訊息===
        if (!errMsgs.isEmpty()) {
        	model.addAttribute("temporaryEmp", temporaryEmp);
        	model.addAttribute("errMsgs", errMsgs);
        	model.addAttribute("emp", emp);
        	return "emp-bankSetting";
        } else {
        	// ===3.2 資料正確，進入資料庫===
        	emp.setBankPic(pic);
        	emp.setBankCode(bankCode);
        	emp.setAccountNo(account);
        	empSvc.updateEmp(emp);
        	
        	String successMsg = "薪轉戶設定完成！";
        	// 更新session
        	session.setAttribute("emp", emp);
        	
        	model.addAttribute("emp", emp);
        	model.addAttribute("successMsg", successMsg);
        	return "emp-bankSetting";
        }
        
	}
	
	
	// InputStream to byte array
	private byte[] readInputStream(InputStream is) throws IOException {
		
        byte[] buffer = new byte[1024];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        while ((bytesRead = is.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }

        return output.toByteArray();
    }
}
