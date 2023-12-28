package com.furelise.emp.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.furelise.emp.model.Emp;

@Controller
public class BecomeEmpController {

	@Autowired
	public EmpAuthService empSvc;
	
	@GetMapping("/becomeEmp")
	public String showPage() {
		return "emp-becomeEmp";
	}

	@PostMapping("/registerEmp")
	public String doRegister(HttpServletRequest req, Model model) throws IOException, ServletException {
		
		// ===1. 獲得請求參數===
		String name = req.getParameter("becomeEmp_name");
		String birth = req.getParameter("becomeEmp_birth");
		String tel = req.getParameter("becomeEmp_tel").trim();
		String email = req.getParameter("becomeEmp_email");
		String password = req.getParameter("becomeEmp_pass");
		String confirmPassword = req.getParameter("becomeEmp_cfmPass");
		String IdNo = req.getParameter("becomeEmp_idNo").trim();
		
		// String轉Integer
		String workTimeStr = req.getParameter("becomeEmp_workTime");
		Integer workTime = Integer.parseInt(workTimeStr);
		
		// String轉boolean
		String vehicleValue = req.getParameter("becomeEmp_vehicle");
		boolean vehicle = true; // 預設選機車
		if (vehicleValue != null) {
			int intValue = Integer.parseInt(vehicleValue);
			vehicle = (intValue == 1);
		}
		
		
		String workArea1 = req.getParameter("emp_workArea1");
		String workArea2 = req.getParameter("emp_workArea2");
		String workArea3 = req.getParameter("emp_workArea3");
		
		Part IdF_part = req.getPart("becomeEmp_IDF");
		byte[] IdFront = null;
		if (IdF_part != null) {
			InputStream IdF_is = IdF_part.getInputStream();
			IdFront = readInputStream(IdF_is);
		}
		
		Part IdB_part = req.getPart("becomeEmp_IDB");
		byte[] IdBehind = null;
		if (IdB_part != null) {
			InputStream IdB_is = IdB_part.getInputStream();
			IdBehind = readInputStream(IdB_is);
		}
		
		Part lic_part = req.getPart("becomeEmp_license");
		byte[] license = null;
		if (lic_part != null) {
			InputStream lic_is = lic_part.getInputStream();
			license = readInputStream(lic_is);
		}
		
		
		
		// ===2. 錯誤驗證===
		
		// ===輸入格式驗證錯誤處理===
        List<String> errMsgs = new LinkedList<String>();
		
        // name錯誤處理
        /*
         * 使用捕獲組 () 來表示整個名字的模式。
         * 模式包含大小寫字母、底線 [a-zA-Z_] 和漢字的 Unicode 範圍 \u4e00-\u9fa5。
         * {2,15} 指定捕獲組內容的長度應在 2 到 15 個字符之間。
         */
        String nameReg = "^([a-zA-Z_\u4e00-\u9fa5]{2,20})$";
        if (name == null || (name.trim().length()) == 0) {
            // 未輸入
            errMsgs.add(" 請輸入姓名！");
        } else if (!name.trim().matches(nameReg)) {
            errMsgs.add(" 姓名格式有誤：僅能輸入中、英文字母與底線，且長度必需在2到20之間。");
        }
        
        // birth錯誤處理
        LocalDate birthDate = null;
        LocalDate nowDate = LocalDate.now();
        if (birth == null || (birth.trim().length()) == 0) {
            errMsgs.add(" 請選擇生日日期！");
        } else {
            // yyyy/mm/dd轉成yyyy-mm-dd，再從String轉成Date
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    		birthDate = LocalDate.parse(birth, formatter);
            if (birthDate.isAfter(nowDate)) {
                // 生日不可以選比今天晚的日期
                errMsgs.add(" 生日輸入有誤：不可選擇晚於今日的日期。");
            }
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
        
        // email錯誤處理
        /*
         * ^[a-zA-Z0-9._%+-]+：以字母、數字、點（.）、下劃線（_）、百分比（%）、加號（+）、減號（-）開頭。
         * @[a-zA-Z0-9.-]+：然後是@符號，後面可以包含字母、數字、點（.）或減號（-）。
         * \\.：然後是一個點（.），需要使用雙斜槓轉義。
         * [a-zA-Z]{2,}$：最後是至少兩個字母結尾。
         */
        String memMailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (email == null || (email.trim().length()) == 0) {
            // 未輸入
            errMsgs.add(" 請輸入電子信箱！");
        } else if (!email.trim().matches(memMailReg)) {
            errMsgs.add(" 電子信箱格式有誤，請修正！");
        } else if (empSvc.findByEmpMail(email) != null) {
            // 該電子信箱(帳號)已有註冊紀錄
            errMsgs.add(" 己有帳號使用此電子信箱，請選擇其他電子信箱。");
        }
        
        // password錯誤處理
        if (password == null || password.trim().length() == 0) {
            errMsgs.add(" 請輸入密碼！");
        }

        // confirmPassword錯誤處理
        if (confirmPassword == null || confirmPassword.trim().length() == 0) {
            errMsgs.add(" 請再次輸入確認密碼！");
        } else if (!confirmPassword.equals(password)) {
            // 密碼與確認密碼不相同
            errMsgs.add(" 密碼與確認密碼輸入不相同，請修正！");
        }
        
        // vehicle錯誤處理
        if (vehicleValue == null || vehicleValue.trim().length() == 0) {
        	errMsgs.add(" 請選擇收取車種！");
        }
        
        // IdNo錯誤處理
        /* ^[A-Za-z]: 以一個英文字母開頭。
        * [12]: 接著是數字1或2，代表性別。
        * \d{8}: 隨後是八位數字。
        */
        String IdReg = "^[A-Za-z][12]\\d{8}$";
        if (IdNo == null || IdNo.trim().length() == 0) {
        	errMsgs.add(" 請填寫身分證號碼！");
        } else if (!IdNo.trim().matches(IdReg)) {
        	errMsgs.add(" 身分證輸入格式有誤，請修正。");
        }
        
        // workTime錯誤處理
        if (workTime == 240000) {
        	// 沒有選擇時段
        	errMsgs.add(" 請選擇工作時段！");
        }
        
        // 工作區域錯誤處理
        if ("0".equals(workArea1)) {
        	// 沒有選第一順位工作區域
        	errMsgs.add(" 請至少選擇第一順位工作區域！");
        } else if ("0".equals(workArea2) && !"0".equals(workArea3)) {
        	// 沒有照順序選取工作順位，只選一和三
        	errMsgs.add("請按照順位順序選擇工作區域！");
        }
        
        // 上傳圖片錯誤處理
        // 但前端已經有加required了
        if (IdF_part == null) {
        	errMsgs.add(" 請上傳身分證正面照片。");
        }
        if (IdB_part == null) {
        	errMsgs.add(" 請上傳身分證背面照片。");
        }
        if (lic_part == null) {
        	errMsgs.add(" 請上傳駕照證明照片。");
        }
        
        
        // 將資料存進Emp物件，如資料有誤可返回頁面時直接顯示
        Emp newEmp = new Emp();
        
        newEmp.setEmpName(name);
        newEmp.setEmpMail(email);
        newEmp.setEmpTel(tel);
        newEmp.setEmpBirth(birthDate);
        newEmp.setEmpPass(password);
        newEmp.setIDNumber(IdNo);
        newEmp.setEmpLicense(vehicle);
        newEmp.setEmpArea1(workArea1);
        newEmp.setEmpArea2(workArea2);
        newEmp.setEmpArea3(workArea3);
        newEmp.setTimeID(workTime);
        newEmp.setIDF(IdFront);
        newEmp.setIDB(IdBehind);
        newEmp.setLicenseF(license);
        
        
        
        
        // ===3.1 資料有誤，返回頁面顯示錯誤訊息===
        if (!errMsgs.isEmpty()) {
        	model.addAttribute("errMsgs", errMsgs);
        	model.addAttribute("emp", newEmp);
        	return "emp-becomeEmp";
        } else {
        	// ===3.2 資料正確，進入資料庫待後台審核===
        	empSvc.addEmp(newEmp);
        	return "emp-becomeEmp-success";
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
