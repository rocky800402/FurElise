package com.furelise.emp.model;

import java.io.IOException;
import java.sql.Date;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;




public class EmpServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		

//			 ***************************來自select_page.jsp的請求*****************************
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("empID");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入夥伴編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer empID = null;
			try {
				empID = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("夥伴編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			EmpDAOImpl empSvc = new EmpDAOImpl();
			Emp emp = empSvc.findByPK(empID);
			if (emp == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("emp", emp); // 資料庫取出的empVO物件,存入req
			String url = "/emp/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
		
		
//			***************************來自listAllEmp.jsp的請求*********************************
		if ("getOne_For_Update".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
			Integer empID = Integer.valueOf(req.getParameter("empID"));
			
				/***************************2.開始查詢資料****************************************/
			EmpDAOImpl empSvc = new EmpDAOImpl();
			Emp emp = empSvc.findByPK(empID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("emp", emp); // 資料庫取出的empVO物件,存入req
			String url = "/emp/update_emp_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}
		
		
//			***************************來自update_emp_input.jsp的請求***************************
		if ("update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer empID = Integer.valueOf(req.getParameter("empID").trim());

			String empName = req.getParameter("empName");
			String empNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (empName == null || empName.trim().length() == 0) {
				errorMsgs.add("夥伴姓名: 請勿空白");
			} else if (!empName.trim().matches(empNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("夥伴姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String empMail = req.getParameter("empMail");
			String empMailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
			if (empMail == null || empMail.trim().length() == 0) {
				errorMsgs.add("夥伴信箱: 請勿空白");
			} else if (!empMail.trim().matches(empMailReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("夥伴信箱: 只能是中、英文字母、數字和_ , 且長度必需在2到100之間");
			}

			String empTel = req.getParameter("empTel");
			String empTelReg = "^09\\d{8}$";
			if (empTel == null || empTel.trim().length() == 0) {
				errorMsgs.add("夥伴電話: 請勿空白");
			}else if (!empTel.trim().matches(empTelReg)) {
				errorMsgs.add("請確認電話格式");
			}

			java.sql.Date empBirth = null;
			try {
				empBirth = java.sql.Date.valueOf(req.getParameter("empBirth").trim());
			} catch (IllegalArgumentException e) {
				empBirth = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入生日!");
			}

			String empPass = req.getParameter("empPass");
			String empPassReg = "^[a-zA-Z0-9]{2,100}$";
			if (empPass == null || empPass.trim().length() == 0) {
				errorMsgs.add("夥伴密碼: 請勿空白");
			} else if (!empPass.trim().matches(empPassReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("夥伴密碼: 只能是英文字母、數字 , 且長度必需在2到100之間");
			}

			String IDNumber = req.getParameter("IDNumber");
			String IDNumberReg = "^[A-Z][0-9]{9}$";
			if (IDNumber == null || IDNumber.trim().length() == 0) {
				errorMsgs.add("夥伴身分證字號: 請勿空白");
			} else if (!IDNumber.trim().matches(IDNumberReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("夥伴身分證字號: 開頭必須是大寫英文字母,並必須符合格式");
			}
			
			String empLicense = req.getParameter("empLicense");
			boolean empLicense1;
			try {
			    empLicense1 = Boolean.parseBoolean(empLicense);
			} catch (Exception e) {
			    empLicense1 = false; // 預設值，可以根據你的需求修改
			}
			if (!(empLicense1 == false || empLicense1 == true)) {
			    errorMsgs.add("選擇車種: 請填0(汽車)或1(機車)");
			}
			
			String empArea1 = req.getParameter("empArea1");
			String empAreaReg = "^[\u4e00-\u9fa5]{3}$";
			if (empArea1 == null || empArea1.trim().length() == 0) {
			    errorMsgs.add("工作區: 請勿空白");
			} else if (!empArea1.trim().matches(empAreaReg)) {
			    errorMsgs.add("工作區: 必須是3個中文字");
			}
			
			String empArea2 = req.getParameter("empArea2");
			String empAreaReg2 = "^[\u4e00-\u9fa5]{3}$";
			if (empArea2 == null || empArea2.trim().length() == 0) {
			    errorMsgs.add("工作區: 請勿空白");
			} else if (!empArea2.trim().matches(empAreaReg2)) {
			    errorMsgs.add("工作區: 必須是3個中文字");
			}

			String empArea3 = req.getParameter("empArea3");
			String empAreaReg3 = "^[\u4e00-\u9fa5]{3}$";
			if (empArea3 == null || empArea3.trim().length() == 0) {
			    errorMsgs.add("工作區: 請勿空白");
			} else if (!empArea3.trim().matches(empAreaReg3)) {
			    errorMsgs.add("工作區: 必須是3個中文字");
			}
			
			Integer timeID = Integer.valueOf(req.getParameter("timeID").trim());
			if (timeID == null) {
				errorMsgs.add("收取時段ID: 請勿空白");
			}
			
			Part IDF = req.getPart("IDF"); // "IDF" 是表單中檔案上傳欄位的名稱
			if (IDF != null && IDF.getSize() > 0) {
			    // 此處可以處理圖片的上傳，例如將圖片存儲在伺服器上，或進行其他操作
			} else {
			    errorMsgs.add("夥伴身分證正面: 請選擇檔案上傳");
			}
			
			Part IDB = req.getPart("IDB"); // "IDF" 是表單中檔案上傳欄位的名稱
			if (IDB != null && IDB.getSize() > 0) {
			    // 此處可以處理圖片的上傳，例如將圖片存儲在伺服器上，或進行其他操作
			} else {
			    errorMsgs.add("夥伴身分證背面: 請選擇檔案上傳");
			}
			
			Part licenseF = req.getPart("licenseF"); // "IDF" 是表單中檔案上傳欄位的名稱
			if (licenseF != null && licenseF.getSize() > 0) {
			    // 此處可以處理圖片的上傳，例如將圖片存儲在伺服器上，或進行其他操作
			} else {
			    errorMsgs.add("夥伴駕照正面: 請選擇檔案上傳");
			}
			
			String bankCode = req.getParameter("bankCode");
			String bankCodeReg = "^\\d{3,10}$";
			if (bankCode == null || bankCode.trim().length() == 0) {
			    errorMsgs.add("薪資戶金融機構: 請勿空白");
			} else if (!bankCode.trim().matches(bankCodeReg)) {
			    errorMsgs.add("薪資戶金融機構: 必須是3-10個數字");
			}
			
			String accountNo = req.getParameter("accountNo");
			String accountNoReg = "^\\d{5,20}$";
			if (accountNo == null || accountNo.trim().length() == 0) {
			    errorMsgs.add("薪資戶帳號: 請勿空白");
			} else if (!accountNo.trim().matches(accountNoReg)) {
			    errorMsgs.add("薪資戶帳號: 必須是5-20個數字");
			}
			
			Part bankPic = req.getPart("bankPic"); // "IDF" 是表單中檔案上傳欄位的名稱
			if (bankPic != null && bankPic.getSize() > 0) {
			    // 此處可以處理圖片的上傳，例如將圖片存儲在伺服器上，或進行其他操作
			} else {
			    errorMsgs.add("存摺封面: 請選擇檔案上傳");
			}
			
			Integer workSum = Integer.valueOf(req.getParameter("workSum").trim());
			if (workSum == null) {
				errorMsgs.add("目前接單數量: 請勿空白");
			}
			
			Integer empStatus = Integer.valueOf(req.getParameter("empStatus").trim());
			if (empStatus == null) {
				errorMsgs.add("審核狀態: 請勿空白");
			}
			
			Emp emp = new Emp();
			emp.setEmpID(empID);
			emp.setEmpName(empName);
			emp.setEmpMail(empMail);
			emp.setEmpTel(empTel);
			emp.setEmpBirth(empBirth);
			emp.setEmpPass(empPass);
			emp.setIDNumber(IDNumber);
			emp.setEmpLicense(empLicense1);
			emp.setEmpArea1(empArea1);
			emp.setEmpArea2(empArea2);
			emp.setEmpArea3(empArea3);
			emp.setTimeID(timeID);
//			emp.setIDF(IDF);
//			emp.setIDB(IDB);
//			emp.setLicenseF(licenseF);
			emp.setBankCode(bankCode);
			emp.setAccountNo(accountNo);
//			emp.setBankPic(bankPic);
			emp.setWorkSum(workSum);
			emp.setEmpRegiDate(new java.sql.Timestamp(System.currentTimeMillis()));
			emp.setEmpStatus(empStatus);
//			emp.setEmpIsSuspended(empIsSuspended);


			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("emp", emp); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/update_emp_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			EmpService empSvc = new EmpService();
			emp = empSvc.update(empID,empName,empMail,empTel,empBirth,empPass,IDNumber,
					false,empArea1,empArea2,empArea3,timeID,
					bankCode,accountNo,workSum,empStatus);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("emp", emp); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/emp/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		

//			*********************************來自addEmp.jsp的請求*********************************		
		if ("insert".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			
//			empID因為設成自動增加，故不給人工新增了

			String empName = req.getParameter("empName");
			String empNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (empName == null || empName.trim().length() == 0) {
				errorMsgs.add("夥伴姓名: 請勿空白");
			} else if (!empName.trim().matches(empNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("夥伴姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String empMail = req.getParameter("empMail");
			String empMailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
			if (empMail == null || empMail.trim().length() == 0) {
				errorMsgs.add("夥伴信箱: 請勿空白");
			} else if (!empMail.trim().matches(empMailReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("夥伴信箱: 只能是中、英文字母、數字和_ , 且長度必需在2到100之間");
			}

			String empTel = req.getParameter("empTel");
			String empTelReg = "^09\\d{8}$";
			if (empTel == null || empTel.trim().length() == 0) {
				errorMsgs.add("夥伴電話: 請勿空白");
			}else if (!empTel.trim().matches(empTelReg)) {
				errorMsgs.add("請確認電話格式");
			}

			java.sql.Date empBirth = null;
			try {
				empBirth = java.sql.Date.valueOf(req.getParameter("empBirth").trim());
			} catch (IllegalArgumentException e) {
				empBirth = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入生日!");
			}

			String empPass = req.getParameter("empPass");
			String empPassReg = "^[a-zA-Z0-9]{2,100}$";
			if (empPass == null || empPass.trim().length() == 0) {
				errorMsgs.add("夥伴密碼: 請勿空白");
			} else if (!empPass.trim().matches(empPassReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("夥伴密碼: 只能是英文字母、數字 , 且長度必需在2到100之間");
			}

			String IDNumber = req.getParameter("IDNumber");
			String IDNumberReg = "^[A-Z][0-9]{9}$";
			if (IDNumber == null || IDNumber.trim().length() == 0) {
				errorMsgs.add("夥伴身分證字號: 請勿空白");
			} else if (!IDNumber.trim().matches(IDNumberReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("夥伴身分證字號: 開頭必須是大寫英文字母,並必須符合格式");
			}
			
			String empLicense = req.getParameter("empLicense");
			boolean empLicense1;
			try {
			    empLicense1 = Boolean.parseBoolean(empLicense);
			} catch (Exception e) {
			    empLicense1 = false; // 預設值，可以根據你的需求修改
			}
			if (!(empLicense1 == false || empLicense1 == true)) {
			    errorMsgs.add("選擇車種: 請填0(汽車)或1(機車)");
			}
			
			String empArea1 = req.getParameter("empArea1");
			String empAreaReg = "^[\u4e00-\u9fa5]{3}$";
			if (empArea1 == null || empArea1.trim().length() == 0) {
			    errorMsgs.add("工作區: 請勿空白");
			} else if (!empArea1.trim().matches(empAreaReg)) {
			    errorMsgs.add("工作區: 必須是3個中文字");
			}
			
			String empArea2 = req.getParameter("empArea2");
			String empAreaReg2 = "^[\u4e00-\u9fa5]{3}$";
			if (empArea2 == null || empArea2.trim().length() == 0) {
			    errorMsgs.add("工作區: 請勿空白");
			} else if (!empArea2.trim().matches(empAreaReg2)) {
			    errorMsgs.add("工作區: 必須是3個中文字");
			}

			String empArea3 = req.getParameter("empArea3");
			String empAreaReg3 = "^[\u4e00-\u9fa5]{3}$";
			if (empArea3 == null || empArea3.trim().length() == 0) {
			    errorMsgs.add("工作區: 請勿空白");
			} else if (!empArea3.trim().matches(empAreaReg3)) {
			    errorMsgs.add("工作區: 必須是3個中文字");
			}
			
			Integer timeID = Integer.valueOf(req.getParameter("timeID").trim());
			if (timeID == null) {
				errorMsgs.add("收取時段ID: 請勿空白");
			}
			
			Part IDF = req.getPart("IDF"); // "IDF" 是表單中檔案上傳欄位的名稱
			if (IDF != null && IDF.getSize() > 0) {
			    // 此處可以處理圖片的上傳，例如將圖片存儲在伺服器上，或進行其他操作
			} else {
			    errorMsgs.add("夥伴身分證正面: 請選擇檔案上傳");
			}
			
			Part IDB = req.getPart("IDB"); // "IDF" 是表單中檔案上傳欄位的名稱
			if (IDB != null && IDB.getSize() > 0) {
			    // 此處可以處理圖片的上傳，例如將圖片存儲在伺服器上，或進行其他操作
			} else {
			    errorMsgs.add("夥伴身分證背面: 請選擇檔案上傳");
			}
			
			Part licenseF = req.getPart("licenseF"); // "IDF" 是表單中檔案上傳欄位的名稱
			if (licenseF != null && licenseF.getSize() > 0) {
			    // 此處可以處理圖片的上傳，例如將圖片存儲在伺服器上，或進行其他操作
			} else {
			    errorMsgs.add("夥伴駕照正面: 請選擇檔案上傳");
			}
			
			String bankCode = req.getParameter("bankCode");
			String bankCodeReg = "^\\d{3,10}$";
			if (bankCode == null || bankCode.trim().length() == 0) {
			    errorMsgs.add("薪資戶金融機構: 請勿空白");
			} else if (!bankCode.trim().matches(bankCodeReg)) {
			    errorMsgs.add("薪資戶金融機構: 必須是3-10個數字");
			}
			
			String accountNo = req.getParameter("accountNo");
			String accountNoReg = "^\\d{5,20}$";
			if (accountNo == null || accountNo.trim().length() == 0) {
			    errorMsgs.add("薪資戶帳號: 請勿空白");
			} else if (!accountNo.trim().matches(accountNoReg)) {
			    errorMsgs.add("薪資戶帳號: 必須是5-20個數字");
			}
			
			Part bankPic = req.getPart("bankPic"); // "IDF" 是表單中檔案上傳欄位的名稱
			if (bankPic != null && bankPic.getSize() > 0) {
			    // 此處可以處理圖片的上傳，例如將圖片存儲在伺服器上，或進行其他操作
			} else {
			    errorMsgs.add("存摺封面: 請選擇檔案上傳");
			}
			
			Integer workSum = Integer.valueOf(req.getParameter("workSum").trim());
			if (workSum == null) {
				errorMsgs.add("目前接單數量: 請勿空白");
			}
			
			Integer empStatus = Integer.valueOf(req.getParameter("empStatus").trim());
			if (empStatus == null) {
				errorMsgs.add("審核狀態: 請勿空白");
			}
			
			Emp emp = new Emp();
			emp.setEmpName(empName);
			emp.setEmpMail(empMail);
			emp.setEmpTel(empTel);
			emp.setEmpBirth(empBirth);
			emp.setEmpPass(empPass);
			emp.setIDNumber(IDNumber);
			emp.setEmpLicense(empLicense1);
			emp.setEmpArea1(empArea1);
			emp.setEmpArea2(empArea2);
			emp.setEmpArea3(empArea3);
			emp.setTimeID(timeID);
//			emp.setIDF(IDF);
//			emp.setIDB(IDB);
//			emp.setLicenseF(licenseF);
			emp.setBankCode(bankCode);
			emp.setAccountNo(accountNo);
//			emp.setBankPic(bankPic);
			emp.setWorkSum(workSum);
			emp.setEmpRegiDate(new java.sql.Timestamp(System.currentTimeMillis()));
			emp.setEmpStatus(empStatus);
			emp.setEmpIsSuspended(false);

				/***************************2.開始新增資料***************************************/
			EmpService empSvc = new EmpService();
			emp = empSvc.addEmp(empName,empMail,empTel,empBirth,empPass,IDNumber,
					false,empArea1,empArea2,empArea3,timeID,
					bankCode,accountNo,workSum,new java.sql.Timestamp(System.currentTimeMillis()),empStatus,false);

				/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/emp/listAllEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
		

//		**************************************來自listAllEmp.jsp******************************
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數***************************************/
			Integer empID = Integer.valueOf(req.getParameter("empID"));
				
				/***************************2.開始刪除資料***************************************/
			EmpDAOImpl empSvc = new EmpDAOImpl();
			empSvc.delete(empID);
				
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
			String url = "/emp/listAllEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}

}
