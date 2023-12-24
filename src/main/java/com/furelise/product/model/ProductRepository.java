package com.furelise.product.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	boolean existsBypName(String pName);
	
	List<Product> findByProductClass(Integer pClassID);
}
