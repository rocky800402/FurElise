package com.furelise.productclass.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductClassService {

	@Autowired
	private ProductClassRepository dao;

	public boolean addProductClass(ProductClass productClass) {

		boolean isPass = false;
		if (!dao.existsBypClassName(productClass.getPClassName())) {
			dao.save(productClass);
			isPass = true;
		}
		return isPass;

	}

	public boolean updateProductClass(ProductClass productClass) {
		boolean isPass = false;
		if (!dao.existsBypClassName(productClass.getPClassName())) {
			dao.save(productClass);
			isPass = true;
		}
		return isPass;

	}

	public ProductClass getProductClassByID(Integer pClassID) {
		Optional<ProductClass> optional = dao.findById(pClassID);
		return optional.orElse(null);

	}

	public List<ProductClass> getAll() {
		return dao.findAll();
	}

}
