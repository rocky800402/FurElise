package com.furelise.productclass.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductClassRepository extends JpaRepository<ProductClass, Integer>{
	
	boolean  existsBypClassName(String pClassName);
}
