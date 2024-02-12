package com.devPontes.api.v1.model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "stocks")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer capacity;
	private Double totalPriceInStock;

	@OneToMany(mappedBy = "stock")
	private List<Product> products = new ArrayList<>();

	public Stock(Long id, Integer capacity, Double totalPriceInStock, List<Product> products) {
		this.id = id;
		this.capacity = capacity;
		this.totalPriceInStock = totalPriceInStock;
		this.products = products;
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
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
		return "Stock [id=" + id + ", capacity=" + capacity + ", totalPriceInStock=" + totalPriceInStock + ", products="
				+ products + "]";
	}

}
