package com.sale.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;


public class SaleService {
	
	
	private SaleDAO dao;

	public SaleService() {
		dao = new SaleDAOImpl();
	}
	
	public Sale addSale(String coupon, Date saleStart, Date saleEnd, Double disRequire, Double dis) {
		
		Sale sale = new Sale();
		
		sale.setCoupon(coupon);
		sale.setSaleStart(saleStart);
		sale.setSaleEnd(saleEnd);
		sale.setDisRequire(new BigDecimal(disRequire));
		sale.setDis(new BigDecimal(dis));
		dao.insert(sale);
		
		return sale;
	}
	
	public Sale updateSale(Integer saleID, String coupon, Date saleStart, Date saleEnd, Double disRequire, Double dis) {
		Sale sale = new Sale();
		
		sale.setSaleID(saleID);
		sale.setCoupon(coupon);
		sale.setSaleStart(saleStart);
		sale.setSaleEnd(saleEnd);
		sale.setDisRequire(new BigDecimal(disRequire));
		sale.setDis(new BigDecimal(dis));
		dao.update(sale);
		
		return sale;
		
	}
	
	public void deleteSale(Integer saleID) {
		dao.delete(saleID);
	}
	
	public Sale getOneSale(Integer saleID) {
		return dao.findByPK(saleID);
	}
	
	public List<Sale> getAll(){
		return dao.getAll();
	}
	
	public Sale getSaleByCoupon(String coupon) {
		
		return dao.findByCoupon(coupon);
		
	}
}