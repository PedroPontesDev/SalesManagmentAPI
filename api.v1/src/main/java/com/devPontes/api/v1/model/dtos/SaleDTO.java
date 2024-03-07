package com.devPontes.api.v1.model.dtos;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "moment", "sellerWhoSale", "ClientWhoBuy", "items", "priceTotal", "completed" })
public class SaleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Instant moment;
	private Long sellerWhoSaleId;
	private Long ClientWhoBuyId;
	private List<ProductDTO> items;
	private Optional<Double> priceTotal;
	private Boolean completed;

	public SaleDTO() {

	}

	// Construtor que aceita uma string no formato da data/hora
	public SaleDTO(Long id, String moment, SellerDTO sellerWhoSale, ClientDTO ClientWhoBuy, List<ProductDTO> items,
			Double totalValueOfSale, Boolean completed) {
		this.id = id;
		this.moment = Instant.parse(moment);
		this.sellerWhoSaleId = sellerWhoSale.getId();
		this.ClientWhoBuyId = ClientWhoBuy.getId();
		this.items = items;
		this.priceTotal = Optional.of(totalValueOfSale);
		this.completed = completed;
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

	public Long getSellerWhoSale() {
		return this.sellerWhoSaleId;
	}

	public void setSellerWhoSale(Long sellerWhoSaleId) {
		this.sellerWhoSaleId = sellerWhoSaleId;
	}

	public Long getClientWhoBuy() {
		return ClientWhoBuyId;
	}

	public void setClientWhoBuy(Long clientWhoBuyId) {
		this.ClientWhoBuyId = clientWhoBuyId;
	}

	public List<ProductDTO> getItems() {
		return items;
	}

	public void setItems(List<ProductDTO> items) {
		this.items = items;
	}

	public Optional<Double> getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(Optional<Double> priceTotal) {
		this.priceTotal = priceTotal;
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
		return "SaleDTO [id=" + id + ", moment=" + moment + ", sellerWhoSaleId=" + sellerWhoSaleId + ", ClientWhoBuyId="
				+ ClientWhoBuyId + ", items=" + items + ", priceTotal=" + priceTotal + ", completed=" + completed + "]";
	}

	
}
