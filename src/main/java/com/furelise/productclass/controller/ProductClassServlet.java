package com.productclass.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.productclass.model.ProductClass;
import com.productclass.model.ProductClassService;
import com.sale.model.SaleService;


@WebServlet("/productClass/pc.do")
public class ProductClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProductClassService pcSvc;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void init() throws ServletException {
		pcSvc= new ProductClassService();
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		/*************************** 查詢單項資料 **********************/
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 查詢單項資料 **********************/
			String str = req.getParameter("pClassID");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入商品類別編號(共六碼,以16開頭");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/productClass/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer pClassID = null;
			try {
				pClassID = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("商品類別編號格式不正確(共六碼,以16開頭)");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/productClass/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.查詢 *****************************************/
			
			ProductClass productClass = pcSvc.getOneProductClass(pClassID);

			if (productClass == null) {
				errorMsgs.add("查無資料");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/productClass/b_productClass_list.jsp");
				failureView.forward(req, res);
				return;
			}
			/*****************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 ************************/

			req.setAttribute("productClass", productClass);
			String url = "/productClass/b_productClass_list.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		} // getOne_For_Display的大括號
		/****************************** 從查詢到的資料中選取單筆資料進行修改 *********************/

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/******************** 接收請求參數 ******************/

			Integer pClassID = Integer.valueOf(req.getParameter("pClassID"));
			/******************** 開始查詢 ******************/

			ProductClass productClass = pcSvc.getOneProductClass(pClassID);
			/******************** 轉交資料 ******************/
			req.setAttribute("productClass", productClass);
			String url = "/productClass/b_productClass_update.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		} // getOne_For_Update的大括弧

		/********************************* 在修改頁面中避免輸入資料不符合資料庫要求的驗證 ******************/

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/******************** 接收請求參數 ******************/

			Integer pClassID = Integer.valueOf(req.getParameter("pClassID").trim());

			String pClassName = req.getParameter("pClassName");
			String pcnReg = "^[(\u4e00-\u9fa5)]+$";
			if (pClassName == null || pClassName.trim().length() == 0) {
				errorMsgs.add("商品類別 : 請勿空白");
			} else if (!pClassName.trim().matches(pcnReg)) {
				errorMsgs.add("商品類別: 格式不正確,請填入中文,不可填入英文或數字 ");

			}

			ProductClass productClass = new ProductClass();
			productClass.setpClassID(pClassID);
			productClass.setpClassName(pClassName);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("productClass", productClass);
				RequestDispatcher failureView = req.getRequestDispatcher("/productClass/update_productClass_input.jsp");
				failureView.forward(req, res);
				return;
			}
			/************************* 開始修改資料 ************************/

			pcSvc.updateProductClass(pClassID, pClassName);
			/************************* 修改完成 ************************/

			req.setAttribute("productClass", productClass);
			String url = "/productClass/b_productClass_list.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		} // update大括號
		
		/************************* 新增 ******************/
		
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/******************** 接收請求參數 *********************/
			String pClassName = req.getParameter("pClassName");
			String pClassNameReg = "^[(\u4e00-\u9fa5)]+$";
			if (pClassName == null || pClassName.trim().length() == 0) {
				errorMsgs.add("商品類別名稱: 請勿空白");
			} else if (!pClassName.trim().matches(pClassNameReg)) {
				errorMsgs.add("商品別名稱: 格式不正確,請填入中文,不可填入英文或數字");
			}

			ProductClass  productClass = new ProductClass();
			productClass.setpClassName(pClassName);
			

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/productClass/b_productClass_add.jsp");
				failureView.forward(req, res);
				return;
			}
			/************************* 開始新增 ********************/

			productClass = pcSvc.addProductClass(pClassName);

			/************************* 新增完成 進行轉交 ********************/
			req.setAttribute("productClass", productClass);
			String url = "/productClass/b_productClass_list.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		} // insert大括號
	}

}
