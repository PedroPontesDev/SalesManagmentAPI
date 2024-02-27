package com.devPontes.api.v1.model.entities;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "moment_of_sale")
	private static LocalDate moment;

	@ManyToOne
	private Seller sellerWhoSale;

	@OneToOne
	private Client clientWhoBuy;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "sale_product", joinColumns = @JoinColumn(name = "sale_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> items = new ArrayList<>();

	@Column(name = "has_completed")
	private Boolean completed;

	@Column(name = "total_value_of_sale")
	private Double totalValueOfsale;

	public Sale(Long id, LocalDate moment, Seller sellerWhoSale, Client clientWhoBuy, List<Product> items,
			Boolean completed, Double totalValueOfsale) {
		this.id = id;
		this.moment = moment;
		this.sellerWhoSale = sellerWhoSale;
		this.clientWhoBuy = clientWhoBuy;
		this.items = items;
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

	public Boolean hasCompleted() {
		return completed;
	}

	public Boolean isCompleted(Double value) {
		if (value != null) {
			return true;
		}
		return false;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	@SuppressWarnings("static-access")
	public static LocalDate getMoment() {
		return moment.now();
	}

	public void setMoment(LocalDate moment) {
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

	public Double getTotalValueOfsale() {
		return totalValueOfsale;
	}

	public void setTotalValueOfsale(Double totalValueOfsale) {
		this.totalValueOfsale = totalValueOfsale;
	}

	@SuppressWarnings("unused")
	public static void initTransaction(LocalDate moment, Sale sale, Seller seller, Client client, Stock stock) throws Exception {
		if (sale.getMoment().isEqual(getMoment())) {
			Sale newSale = new Sale();
			if (newSale.getTotalValueOfsale() == 0.0 && newSale == null) {
				newSale.setClientWhoBuy(client);
				newSale.setSellerWhoSale(seller);
				Stock newStock = stock;
				if (!newStock.isStockFull()) {
					for (Product product : newStock.getProductsInStock()) {
						Double counter = newSale.items.stream().mapToDouble(Product::getPrice).sum();
						newSale.setTotalValueOfsale(counter);
						boolean completeTransaction = sale.isCompleted(counter);
						return;
					}
				} else {
					throw new Exception("");
				}
			}
		}
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
				+ clientWhoBuy + ", items=" + items + ", totalValueOfsale=" + totalValueOfsale + ", completed="
				+ completed + "]";
	}

}
