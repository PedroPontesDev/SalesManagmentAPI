package com.devPontes.api.v1.model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "moment_of_sale")
	private LocalDateTime moment;

	@ManyToOne
	private Seller sellerWhoSale;

	@OneToOne
	private Client clientWhoBuy;

	@ManyToMany(cascade = CascadeType.ALL, fetch = )
	@JoinTable(name = "sale_product", 
			   joinColumns = @JoinColumn(name = "sale_id"),
			   inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> items = new ArrayList<>();

	private Double value;

	public Sale(Long id, LocalDateTime moment, Client client, List<Product> items, Seller seller, Double value) {
		this.id = id;
		this.moment = moment.now();
		this.clientWhoBuy = client;
		this.items = items;
		this.sellerWhoSale = seller;
		this.value = value;
	}
	
	public Sale() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getMoment() {
		return moment;
	}

	public void setMoment(LocalDateTime moment) {
		this.moment = moment;
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
		return items;
	}

	public void setItems(List<Product> items) {
		this.items = items;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
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
		return "Sale [id=" + id + ", moment=" + moment + ", sellerWhoSale=" + sellerWhoSale + ", clientWhoBuy="
				+ clientWhoBuy + ", items=" + items + ", value=" + value + "]";
	}

}
