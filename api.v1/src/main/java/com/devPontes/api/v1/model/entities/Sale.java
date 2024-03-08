package com.devPontes.api.v1.model.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_sales")
public class Sale implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

	@Column(name = "moment_of_sale")
	private Instant moment;

	@ManyToOne
	@JsonIgnore
	private Seller sellerWhoSale;

	@OneToOne
	@JsonIgnore
	private Client clientWhoBuy;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "sale_product", joinColumns = @JoinColumn(name = "sale_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> items = new ArrayList<>();

	@Column(name = "has_completed")
	private Boolean completed;

	@Column(name = "total_value_of_sale")
	private Double totalValueOfsale;

	public Sale(Long id, Instant moment, Seller sellerWhoSale, Client clientWhoBuy, List<Product> items,
			Boolean completed, Double totalValueOfsale) {
		this.id = id;
		this.moment = Instant.now();
		this.sellerWhoSale = sellerWhoSale;
		this.clientWhoBuy = clientWhoBuy;
		this.items = new ArrayList<>();
		this.completed = completed;
		this.totalValueOfsale = totalValueOfsale;
	}

	public Sale() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean isCompleted() {
		return totalValueOfsale != null && totalValueOfsale > 0.0;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Instant getMoment() {
		return Instant.now();
	}

	public void setMoment(Instant moment) {
		this.moment = Instant.now();
	}

	public Seller getSellerWhoSale() {
		return sellerWhoSale;
	}

	public void setSellerWhoSale(Seller sellerWhoSale) {
		this.sellerWhoSale = sellerWhoSale;
	}

	public Client getClientWhoBuy() {
		return clientWhoBuy;
	}

	public void setClientWhoBuy(Client clientWhoBuy) {
		this.clientWhoBuy = clientWhoBuy;
	}

    public List<Product> getItems() {
        if (this.items == null) {
            this.items = new ArrayList<>(); // Inicialize a lista se for nula
        }
        return this.items;
    }
	public void setItems(List<Product> items) {
		this.items = items;
	}

	public Double getTotalValueOfsale() {
		return totalValueOfsale;
	}

	public void setTotalValueOfsale(Double totalValueOfsale) {
		this.totalValueOfsale = totalValueOfsale;
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
		Sale other = (Sale) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
	    return "Sale [id=" + id + ", moment=" + moment + ", sellerWhoSaleId=" + sellerWhoSale.getId() + ", clientWhoBuyId=" + clientWhoBuy.getId() + ", totalValueOfsale=" + totalValueOfsale + ", completed=" + completed + "]";
	}
}
