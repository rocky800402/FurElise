package com.furelise.product.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	ProductRepository dao;

	public String addProduct(Product product) {
		String page = "b_product_add";
		if(dao.existsBypName(product.getPName())) {
			page = "b_product_add";
		} else {
			dao.save(product);
			page =  "redirect:/product/all";

		}

		return page;
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

}
