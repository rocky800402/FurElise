package com.furelise.product.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

	@Autowired
	ProductRepository dao;

	public boolean addProduct(Product product) {
		byte[] imageBytes = null;
		try {
			Path imagePath = Path.of("src/main/resources/static/images/noimage.png");
			imageBytes = Files.readAllBytes(imagePath);

		} catch (IOException e) {

			e.printStackTrace();
		}

		boolean isPass = false;

		if (!dao.existsBypName(product.getPName())) {
			if (product.getPImage2() == null || product.getPImage2().length == 0) {
				product.setPImage2(imageBytes);
			}

			if (product.getPImage3() == null || product.getPImage3().length == 0) {
				product.setPImage3(imageBytes);
			}

			dao.save(product);
			isPass = true;
		}
		return isPass;
	}

	public Product updateProduct(Product product) {
		if (product.getPStock() < 1)
			product.setPStatus(false);

		return dao.save(product);
	}

	public List<Product> getAllProduct() {
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
