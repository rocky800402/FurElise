package com.furelise.productclass.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class ProductClassService {

	@Autowired
	private ProductClassRepository dao;

	public ProductClass addProductClass(ProductClass productClass) {

		dao.save(productClass);

		return productClass;

	}

	public ProductClass updateProductClass(ProductClass productClass) {

		return dao.save(productClass);

	}

	public ProductClass getProductClassByID(Integer pClassID) {

		return dao.findById(pClassID).orElse(null);

	}

	public List<ProductClass> getAll() {
		return dao.findAll();
	}


}
