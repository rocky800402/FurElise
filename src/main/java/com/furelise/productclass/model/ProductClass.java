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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
	
	@Pattern(regexp = "^[\\u4e00-\\u9fa5]+$", message = "僅能填入中文")
	@NotBlank(message = "請勿空白")
	@Column(name = "pClassName")
	private String pClassName;
	
//	@OneToMany(mappedBy="productClass", cascade= CascadeType.ALL)
//	private Set<Product> products;
//	
	
	public ProductClass() {
		super();
	}

}
