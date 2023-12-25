package com.furelise.emp.controller;

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

import com.furelise.emp.model.Emp;

@Controller
@RequestMapping("/emp_home_alter")
public class UpdateEmpController {

	@Autowired
	private EmpAuthService empSvc;
	
	@PostMapping("/{empID}/updateEmp")
	public String updateEmp(@PathVariable("empID")Integer empID, HttpServletRequest req, Model model, HttpSession session) {
		
		// ===1. 獲得請求參數===
		String name = req.getParameter("empName");
		String tel = req.getParameter("empTel");
		Integer timeID = Integer.parseInt(req.getParameter("emp_workTime"));
		String workArea1 = req.getParameter("emp_workArea1");
		String workArea2 = req.getParameter("emp_workArea2");
		String workArea3 = req.getParameter("emp_workArea3");
		String newPass = req.getParameter("emp_pw_new");
		String cfmPass = req.getParameter("emp_pw_newConfirm");
		// 註：因夥伴申請帳號時有身分驗證，故不得更改身分證字號、出生日期
		
		Emp emp = (Emp) session.getAttribute("emp");
		model.addAttribute("emp", emp);
		
		
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
            errMsgs.add(" 請輸入手機號碼！");
        } else if (!tel.trim().matches(telReg)) {
            errMsgs.add(" 手機號碼格式有誤：僅能輸入數字。");
        }
        
        // 工作時段錯誤處理
        if (timeID == 240000) {
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
        	return "emp_home_alter";
        } else {
        	// ===3. 找到該名夥伴，並進行資料修改===
        	Emp updEmp = empSvc.getOneEmp(empID);
        	
        	updEmp.setEmpName(name);
        	updEmp.setEmpTel(tel);
        	updEmp.setTimeID(timeID);
        	updEmp.setEmpArea1(workArea1);
        	updEmp.setEmpArea2(workArea2);
        	updEmp.setEmpArea3(workArea3);
        	if (newPass != null && (newPass.trim().length()) != 0) {
				 updEmp.setEmpPass(newPass);
			 }
        	
        	// 將更新資料存入資料庫
			empSvc.updateEmp(updEmp);
			String successMsg = "會員資料已更新完成！";
			model.addAttribute("successMsg", successMsg);
			model.addAttribute("emp", updEmp); // 立即反映在頁面上
			session.setAttribute("emp", updEmp); // 更新session資料
			return "emp_home_alter";
        }
        
	}
	
}
