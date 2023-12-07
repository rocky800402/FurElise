package com.furelise.productclass.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.furelise.product.model.Product;

import lombok.Data;

@Entity
@Table(name = "ProductClass")
@Data
public class ProductClass implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pClassID", updatable = false)
	private Integer pClassID;
	
	@Column(name = "pClassName")
	private String pClassName;
	
//	@OneToMany(mappedBy="productClass", cascade= CascadeType.ALL)
//	private Set<Product> products;
//	
	
	public ProductClass() {
		super();
	}

}
