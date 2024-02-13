package com.devPontes.api.v1.model.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.devPontes.api.v1.model.entities.Product;

public class StockDTO extends RepresentationModel<StockDTO> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String stockName;
	private Integer currentCapacity;
	private Integer capacityMax;
	private Double totalPriceInStock;
	private List<Product> productsInStock = new ArrayList<>();
	
	public StockDTO(Long id, String stockName, Integer currentCapacity, Integer capacityMax, Double totalPriceInStock,
			List<Product> productsInStock) {
		this.id = id;
		this.stockName = stockName;
		this.currentCapacity = currentCapacity;
		this.capacityMax = capacityMax;
		this.totalPriceInStock = totalPriceInStock;
		this.productsInStock = productsInStock;
	}
	
	public StockDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Integer getCurrentCapacity() {
		return currentCapacity;
	}

	public void setCurrentCapacity(Integer currentCapacity) {
		this.currentCapacity = currentCapacity;
	}

	public Integer getCapacityMax() {
		return capacityMax;
	}

	public void setCapacityMax(Integer capacityMax) {
		this.capacityMax = capacityMax;
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
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(capacityMax, currentCapacity, id, productsInStock, stockName, totalPriceInStock);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockDTO other = (StockDTO) obj;
		return Objects.equals(capacityMax, other.capacityMax) && Objects.equals(currentCapacity, other.currentCapacity)
				&& Objects.equals(id, other.id) && Objects.equals(productsInStock, other.productsInStock)
				&& Objects.equals(stockName, other.stockName)
				&& Objects.equals(totalPriceInStock, other.totalPriceInStock);
	}

	@Override
	public String toString() {
		return "StockDTO [id=" + id + ", stockName=" + stockName + ", currentCapacity=" + currentCapacity
				+ ", capacityMax=" + capacityMax + ", totalPriceInStock=" + totalPriceInStock + ", productsInStock="
				+ productsInStock + "]";
	}
	
	

}
