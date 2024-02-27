package com.devPontes.api.v1.model.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.devPontes.api.v1.model.entities.Client;
import com.devPontes.api.v1.model.entities.Seller;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class SaleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDate moment;
	
	private Seller sellerWhoSale;
	private Client clientWhoBuy;
	private Double totalValueOfsale;

	private List<ProductDTO> items = new ArrayList<>();

	private Boolean completed;
	

	public SaleDTO(Long id, LocalDate moment, Seller sellerWhoSaleId, Client clientWhoBuyId, List<ProductDTO> items,
			Double value) {
		this.id = id;
		this.moment = moment;
		this.sellerWhoSale = sellerWhoSaleId;
		this.clientWhoBuy = clientWhoBuyId;
		this.items = items;
		this.totalValueOfsale = value;
	}

	public SaleDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getMoment() {
		return moment;
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

	public List<ProductDTO> getItems() {
		return items;
	}

	public void setItems(List<ProductDTO> items) {
		this.items = items;
	}


	public Double getTotalValueOfsale() {
		return totalValueOfsale;
	}

	public void setTotalValueOfsale(Double totalValueOfsale) {
		this.totalValueOfsale = totalValueOfsale;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
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
		SaleDTO other = (SaleDTO) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "SaleDTO [id=" + id + ", moment=" + moment + ", sellerWhoSale=" + sellerWhoSale + ", clientWhoBuy="
				+ clientWhoBuy + ", totalValueOfsale=" + totalValueOfsale + ", items=" + items + ", completed="
				+ completed + "]";
	}
}
