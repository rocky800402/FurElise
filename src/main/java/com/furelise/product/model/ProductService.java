package com.furelise.product.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furelise.productclass.model.ProductClass;

@Service
public class ProductService {

	@Autowired
	ProductRepository dao;

	public boolean addProduct(Product product) {
		
		boolean isPass = false;
		if(!dao.existsBypName(product.getPName())) {
			dao.save(product);
			isPass = true;
		}
		return isPass;
	}

	public Product updateProduct(Product product) {
		return dao.save(product);
	}

	public List<Product> getAllProduct(){
		return dao.findAll();
	}

	public Product getProductById(Integer pID) {
		Optional<Product> optional = dao.findById(pID);
		return optional.orElse(null);
	}
	
	public List<Product> getProductsByClass(Integer pClassID) {
		List<Product> list = dao.findByProductClass(pClassID);
		return list;
		
	}

}
