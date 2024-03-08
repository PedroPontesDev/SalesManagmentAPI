package com.devPontes.api.v1.model.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_products")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private Integer quantity;

    private boolean hasInStock; // If quantity in stock = 0, so we dont have in stock - Logicals

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @JsonIgnore
    @ManyToMany(mappedBy = "items", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Sale> sales = new HashSet<>();
    
    public Product(Long id, String name, double price, Integer quantity, boolean hasInStock, Stock stock,
			Set<Sale> sales) {
		this.id = id;
		this.name = name;
		this.price = price;
		if (quantity == null) throw new IllegalArgumentException("Quantidade do produto não pode ser nula");
	    this.quantity = quantity;
		this.hasInStock = hasInStock;
		this.stock = stock;
		this.sales = sales;
	}

	public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        if (quantity == null) {
            throw new IllegalStateException("Quantidade do produto não definida");
        }
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isHasInStock() {
       if(this.quantity > 0) {
    	   return true;
       } else {
    	   return true;
       }
    }

    public void setHasInStock(boolean hasInStock) {
        this.hasInStock = hasInStock;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
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
        Product other = (Product) obj;
        return Objects.equals(id, other.id);
    }

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", hasInStock="
				+ hasInStock + ", stock=" + stock + ", sales=" + sales + "]";
	}
    

}
