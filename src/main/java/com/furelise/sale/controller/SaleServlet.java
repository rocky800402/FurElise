package com.sale.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sale.model.Sale;
import com.sale.model.SaleService;

@WebServlet("/sale/sale.do")
public class SaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SaleService saleService;

	@Override
	public void init() throws ServletException {
		saleService = new SaleService();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		/*************************** 查詢單項資料 **********************/
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 查詢單項資料 **********************/

			String str = req.getParameter("saleID");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入優惠編號(共六碼,以26開頭)");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/sale/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer saleID = null;
			try {
				saleID = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("優惠編號格式不正確(共六碼,以26開頭)");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/sale/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.查詢 *****************************************/

			Sale sale = saleService.getOneSale(saleID);
			if (sale == null) {
				errorMsgs.add("查無資料");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/sale/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			/****************************** 3.查詢完成,準備轉交(Send the Success view)************************/

			req.setAttribute("sale", sale);
			String url = "/sale/listOneSale.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			successView.forward(req, res);

		}
		/**************************************
		 * 從查詢到的資料中選取單筆資料進行修改
		 ***************************/

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/******************** 接收請求參數 ******************/
			Integer saleID = Integer.valueOf(req.getParameter("saleID"));

			/******************** 開始查詢 ******************/

			Sale sale = saleService.getOneSale(saleID);

			/******************** 轉交資料 ******************/
			req.setAttribute("sale", sale);
			String url = "/sale/b_sale_update.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		/********************************* 在修改頁面中避免輸入資料不符合資料庫要求的驗證 ******************/

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/******************** 接收請求參數 ******************/

			Integer saleID = Integer.valueOf(req.getParameter("saleID").trim());

			String coupon = req.getParameter("coupon");
			String couponReg = "^[(a-zA-Z0-9)]{2,20}$";
			if (coupon == null || coupon.trim().length() == 0) {
				errorMsgs.add("優惠代碼 : 請勿空白");
			} else if (!coupon.trim().matches(couponReg)) {
				errorMsgs.add("優惠代碼: 格式不正確,請填入英文字母與數字 ");
			}

			java.sql.Date saleStart = null;
			try {
				saleStart = java.sql.Date.valueOf(req.getParameter("saleStart").trim());
			} catch (IllegalArgumentException e) {
				saleStart = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入優惠開始日!");
			}

			java.sql.Date saleEnd = null;
			try {
				saleEnd = java.sql.Date.valueOf(req.getParameter("saleEnd").trim());
			} catch (IllegalArgumentException e) {
				saleEnd = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入優惠結束日!");
			}

			Double disRequire = null;
			try {
				disRequire = Double.valueOf(req.getParameter("disRequire").trim());
			} catch (NumberFormatException e) {
				disRequire = 0.0;
				errorMsgs.add("優惠條件請填數字.");
			}

			Double dis = null;
			try {
				dis = Double.valueOf(req.getParameter("dis").trim());
			} catch (NumberFormatException e) {
				dis = 0.0;
				errorMsgs.add("優惠折抵請填數字.");
			}

			/******************* 都沒問題就下update了 ***********************/

			Sale sale = new Sale();
			sale.setSaleID(saleID);
			sale.setCoupon(coupon);
			sale.setSaleStart(saleStart);
			sale.setSaleEnd(saleEnd);
			sale.setDisRequire(new BigDecimal(disRequire));
			sale.setDis(new BigDecimal(dis));

			/* 當輸入的內容有錯時,沒問題的部分先幫他保存到req */

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("sale", sale);
				RequestDispatcher failureView = req.getRequestDispatcher("/sale/update_sale_input.jsp");
				failureView.forward(req, res);
				return;
			}
			/************************* 開始修改資料 ************************/

			saleService.updateSale(saleID, coupon, saleStart, saleEnd, disRequire, dis);
			/************************* 修改完成 ************************/

			req.setAttribute("sale", sale);
			String url = "/sale/b_sale_list.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		/************************* 新增 ******************/
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/******************** 接收請求參數 *********************/
			String coupon = req.getParameter("coupon");
			String couponReg = "^[(a-zA-Z0-9)]{2,20}$";
			if (coupon == null || coupon.trim().length() == 0) {
				errorMsgs.add("優惠代碼: 請勿空白");
			} else if (!coupon.trim().matches(couponReg)) {
				errorMsgs.add("優惠代碼: 格式不正確,請填入英文字母與數字");
			}

			java.sql.Date saleStart = null;
			try {
				saleStart = java.sql.Date.valueOf(req.getParameter("saleStart"));
			} catch (IllegalArgumentException e) {
				saleStart = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請選擇優惠開始日!");
			}

			java.sql.Date saleEnd = null;
			try {
				saleEnd = java.sql.Date.valueOf(req.getParameter("saleEnd"));
			} catch (IllegalArgumentException e) {
				saleEnd = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請選擇優惠結束日!");
			}

			Double disRequire = null;
			try {
				disRequire = Double.valueOf(req.getParameter("disRequire").trim());
			} catch (NumberFormatException e) {
				disRequire = 0.0;
				errorMsgs.add("優惠條件請輸入數字.");
			}
			Double dis = null;
			try {
				dis = Double.valueOf(req.getParameter("dis").trim());
			} catch (NumberFormatException e) {
				dis = 0.0;
				errorMsgs.add("優惠折抵金額請輸入數字.");
			}

			Sale sale = new Sale();
			sale.setCoupon(coupon);
			sale.setSaleStart(saleStart);
			sale.setSaleEnd(saleEnd);
			sale.setDisRequire(new BigDecimal(disRequire));
			sale.setDis(new BigDecimal(dis));

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/sale/b_sale_add.jsp");
				failureView.forward(req, res);
				return;
			}
			/************************* 開始新增 ********************/

			saleService.addSale(coupon, saleStart, saleEnd, disRequire, dis);

			/************************* 新增完成 進行轉交 ********************/
			req.setAttribute("sale", sale);
			String url = "/sale/b_sale_list.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		/********************* 確認結帳時輸入的優惠碼是否確實存在 ********************/

		if ("use_sale".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String couponToUse = req.getParameter("coupon");
			String couponData = null;
			couponData = saleService.getSaleByCoupon(couponData).getCoupon();
			if (couponToUse == null || couponToUse.trim().length() == 0) {
				errorMsgs.add("請填入優惠代碼");
			} else if (!couponToUse.trim().matches(couponData)) {
				errorMsgs.add("此優惠碼不存在");
			} // 優惠碼是否正確

			/* 確認結帳時輸入的優惠碼是否在有效期限 */
			if (couponToUse.equals(couponData)) {
				Date couponToUseStart = saleService.getSaleByCoupon(couponToUse).getSaleStart();
				Date couponToUseEnd = saleService.getSaleByCoupon(couponToUse).getSaleEnd();
				Date couponDataStart = saleService.getSaleByCoupon(couponData).getSaleEnd();
				Date couponDataEnd = saleService.getSaleByCoupon(couponData).getSaleEnd();

				if (!couponToUseStart.before(couponDataStart)) {
					errorMsgs.add("優惠尚未開始");
				} else if (couponToUseEnd.after(couponDataEnd)) {
					errorMsgs.add("優惠已結束");
				}
			}
		} // 有效期限

	

	}// use_sale的大括號

}// SaleServlet的大括號
