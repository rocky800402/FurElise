package com.furelise.productclass.model;

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

@Entity
@Table(name = "ProductClass")
public class ProductClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pClassID", updatable = false)
	private Integer pClassID;
	
	@Column(name = "pClassName")
	private String pClassName;
	
	@OneToMany(mappedBy="productClass", cascade= CascadeType.ALL)
	private Set<Product> products;
	
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Integer getpClassID() {
		return pClassID;
	}

	public void setpClassID(Integer pClassID) {
		this.pClassID = pClassID;
	}

	public String getpClassName() {
		return pClassName;
	}

	public void setpClassName(String pClassName) {
		this.pClassName = pClassName;
	}

	public ProductClass() {
		super();
	}

	

	public ProductClass(String pClassName, Set<Product> products) {
		super();
		this.pClassName = pClassName;
		this.products = products;
	}

	public ProductClass(Integer pClassID, String pClassName, Set<Product> products) {
		super();
		this.pClassID = pClassID;
		this.pClassName = pClassName;
		this.products = products;
	}

	@Override
	public String toString() {
		return "ProductClass [pClassID=" + pClassID + ", pClassName=" + pClassName + ", products=" + products + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(pClassID, pClassName, products);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductClass other = (ProductClass) obj;
		return Objects.equals(pClassID, other.pClassID) && Objects.equals(pClassName, other.pClassName)
				&& Objects.equals(products, other.products);
	}

}
