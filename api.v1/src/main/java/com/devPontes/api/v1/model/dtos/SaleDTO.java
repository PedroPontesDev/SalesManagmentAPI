package com.devPontes.api.v1.model.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.devPontes.api.v1.model.entities.Client;
import com.devPontes.api.v1.model.entities.Seller;

public class SaleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDateTime moment;
	private Long sellerWhoSaleId;
	private Long clientWhoBuyId;
	private Double value;

	private List<ProductDTO> items = new ArrayList<>();
	
	public SaleDTO(Long id, LocalDateTime moment, Seller sellerWhoSaleId, Client clientWhoBuyId, List<ProductDTO> items,
			Double value) {
		this.id = id;
		this.moment = moment;
		this.sellerWhoSaleId = sellerWhoSaleId.getId();
		this.clientWhoBuyId = clientWhoBuyId.getId();
		this.items = items;
		this.value = value;
	}

	public SaleDTO() {

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

	public Long getSellerWhoSaleId() {
		return sellerWhoSaleId;
	}

	public void setSellerWhoSaleId(Long sellerWhoSaleId) {
		this.sellerWhoSaleId = sellerWhoSaleId;
	}

	public Long getClientWhoBuyId() {
		return clientWhoBuyId;
	}

	public void setClientWhoBuyId(Long clientWhoBuyId) {
		this.clientWhoBuyId = clientWhoBuyId;
	}

	public List<ProductDTO> getItems() {
		return items;
	}

	public void setItems(List<ProductDTO> items) {
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
		SaleDTO other = (SaleDTO) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "SaleDTO [id=" + id + ", moment=" + moment + ", sellerWhoSaleId=" + sellerWhoSaleId + ", clientWhoBuyId="
				+ clientWhoBuyId + ", items=" + items + ", value=" + value + "]";
	}

}
