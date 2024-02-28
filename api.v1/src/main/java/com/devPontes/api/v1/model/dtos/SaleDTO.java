package com.devPontes.api.v1.model.dtos;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.devPontes.api.v1.model.entities.Seller;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class SaleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Instant moment;
	private SellerDTO sellerWhoSale;
	private ClientDTO ClientWhoBuy;
	private List<ProductDTO> items;
	private Double totalValueOfSale;
	private Boolean completed;

	public SaleDTO(Long id, Instant moment, SellerDTO sellerWhoSale, ClientDTO ClientWhoBuy, List<ProductDTO> items,
			Double totalValueOfSale, Boolean completed) {
		this.id = id;
		this.moment = moment;
		this.sellerWhoSale = sellerWhoSale;
		this.ClientWhoBuy = ClientWhoBuy;
		this.items = items;
		this.totalValueOfSale = totalValueOfSale;
		this.completed = completed;
	}

	public SaleDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public SellerDTO getSellerWhoSale() {
		return sellerWhoSale;
	}

	public void setSellerWhoSale(SellerDTO sellerWhoSale) {
		this.sellerWhoSale = sellerWhoSale;
	}

	public ClientDTO getClientWhoBuy() {
		return ClientWhoBuy;
	}

	public void setClientWhoBuy(ClientDTO clientWhoBuy) {
		ClientWhoBuy = clientWhoBuy;
	}

	public List<ProductDTO> getItems() {
		return items;
	}

	public void setItems(List<ProductDTO> items) {
		this.items = items;
	}

	public Double getTotalValueOfSale() {
		return totalValueOfSale;
	}

	public void setTotalValueOfSale(Double totalValueOfSale) {
		this.totalValueOfSale = totalValueOfSale;
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
		return "SaleDTO [id=" + id + ", moment=" + moment + ", sellerWhoSale=" + sellerWhoSale + ", ClientWhoBuy="
				+ ClientWhoBuy + ", items=" + items + ", totalValueOfSale=" + totalValueOfSale + ", completed="
				+ completed + "]";
	}

	
}
