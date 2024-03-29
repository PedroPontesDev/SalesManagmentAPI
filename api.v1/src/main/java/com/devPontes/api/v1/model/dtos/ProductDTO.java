package com.devPontes.api.v1.model.dtos;

import java.util.Objects;

import com.devPontes.api.v1.model.entities.Sale;
import com.devPontes.api.v1.model.entities.Stock;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = { "id", "name", "quantity", "price", "hasInStock" })
public class ProductDTO {

	private Long id;

	private String name;
	private Double price;
	private Integer quantity;
	private boolean hasInStock;
	private String stockName;
	private Long frequency;

	public ProductDTO(Long id, String name, Double price, Integer quantity, boolean hasInStock, StockDTO stock,
			Sale sale, Long frequency) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.hasInStock = hasInStock;
		this.stockName = stock.getStockName();
		this.frequency = frequency;
	}

	public ProductDTO() {

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getFrequency() {
		return frequency;
	}

	public void setFrequency(Long frequency) {
		this.frequency = frequency;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public boolean isHasInStock() {
		return hasInStock;
	}

	public void setHasInStock(boolean hasInStock) {
		this.hasInStock = hasInStock;
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
		ProductDTO other = (ProductDTO) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity
				+ ", hasInStock=" + hasInStock + "]";
	}

}
