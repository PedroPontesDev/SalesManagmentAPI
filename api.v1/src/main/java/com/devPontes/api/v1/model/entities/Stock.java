package com.devPontes.api.v1.model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_stock")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "capacity")
	private Integer capacity;
	@Column(name = "total_price_in_stock")
	private Double totalPriceInStock;

	@OneToMany(mappedBy = "stock")
	private List<Product> productsInStock = new ArrayList<>();

	public Stock(Long id, Integer capacity, Double totalPriceInStock, List<Product> products) {
		this.id = id;
		this.capacity = capacity;
		this.totalPriceInStock = totalPriceInStock;
		this.productsInStock = products;
	}

	public Stock() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Double getTotalPriceInStock() {
		return totalPriceInStock;
	}

	public void setTotalPriceInStock(Double totalPriceInStock) {
		this.totalPriceInStock = totalPriceInStock;
	}

	public List<Product> getProductsInStock() {
		return productsInStock;
	}

	public void setProductsInStock(List<Product> productsInStock) {
		this.productsInStock = productsInStock;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", capacity=" + capacity + ", totalPriceInStock=" + totalPriceInStock
				+ ", productsInStock=" + productsInStock + "]";
	}

	
}
