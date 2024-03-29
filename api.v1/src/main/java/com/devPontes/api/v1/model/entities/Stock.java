package com.devPontes.api.v1.model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "current_capacity")
    private Long currentCapacity;

    @Column(name = "capacity_max")
    private Integer capacityMax;

    @Column(name = "total_price_in_stock")
    private Double totalPriceInStock;

    @JsonIgnore
    @OneToMany(mappedBy = "stock")
    private List<Product> productsInStock;

    public Stock(Long id, String name, Integer capacityMax, Long currentCapacity, Double totalPriceInStock,
            List<Product> products) {
        this.id = id;
        this.capacityMax = capacityMax;
        this.totalPriceInStock = totalPriceInStock;
        this.productsInStock = products;
        this.stockName = name;
        this.currentCapacity = currentCapacity;
        this.productsInStock = new ArrayList<>();

    }

    public Stock() {

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

    public Integer getCapacityMax() {
        return capacityMax;
    }

    public void setCapacityMax(Integer capacityMax) {
        this.capacityMax = capacityMax;
    }

    public Long getCurrentCapacity() {
        return currentCapacity = this.getProductsInStock()
        								.stream()
        								.map(Product::getQuantity)
        								.collect(Collectors.counting());
    }
        								

    public void setCurrentCapacity(Long currentCapacity) {
        this.currentCapacity = currentCapacity;
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

    public boolean isStockFull() {
        return this.currentCapacity >= this.capacityMax;
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
        return "Stock [id=" + id + ", stockName=" + stockName + ", currentCapacity=" + currentCapacity
                + ", capacityMax=" + capacityMax + ", totalPriceInStock=" + totalPriceInStock + ", productsInStock="
                + productsInStock + "]";
    }

}
