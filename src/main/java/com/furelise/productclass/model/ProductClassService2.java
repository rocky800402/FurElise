package com.productclass.model;

import java.util.List;

public class ProductClassService {
	
	private ProductClassDAO dao;

	public ProductClassService() {
		dao = new ProductClassDAOImpl();
	}
	
	public ProductClass addProductClass(String pClassName) {
		
		ProductClass productClass = new ProductClass();
		
		productClass.setpClassName(pClassName);
		
 		dao.insert(productClass);
 		
		return productClass;
	}
	
	public ProductClass updateProductClass(Integer pClassID, String pClassName) {
		ProductClass productClass = new ProductClass();
		
		productClass.setpClassID(pClassID);
		productClass.setpClassName(pClassName);
		
		dao.update(productClass);
		
		return productClass;
	}
	
	public void deleteProductClass(Integer pClassID) {
		dao.delete(pClassID);
	}
	
	public ProductClass getOneProductClass(Integer pClassID) {
		return dao.findByPK(pClassID);
	}
	
	
	public List<ProductClass> getAll(){
		return dao.getAll();
	}
}
