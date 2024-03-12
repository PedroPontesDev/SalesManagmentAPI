package com.devPontes.api.v1.model.dtos;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.devPontes.api.v1.model.entities.Seller;
import com.devPontes.api.v1.model.mapper.MyMapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "moment", "sellerInSale", "items", "priceTotal", "completed"})
public class SaleDTO extends RepresentationModel<SellerInSaleDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Instant moment;
	private SellerInSaleDTO sellerInSale;
	private List<ProductDTO> items;
	private Double priceTotal;
	private Boolean completed;
	
	@JsonProperty(required = false)
	private Seller sellerNsale;

	public SaleDTO(Long id, Instant moment, SellerInSaleDTO sellerInSale, List<ProductDTO> items, Double priceTotal,
			Boolean completed, Seller sellerNsale) {
		this.id = id;
		this.moment = moment;
		this.sellerInSale = MyMapper.parseObject(sellerNsale, SellerInSaleDTO.class);
		this.items = items;
		this.priceTotal = priceTotal;
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

	
	public Long getSellerInSale() {
		Long mappedDTOid = this.sellerInSale.getId();
		this.sellerInSale.setId(mappedDTOid);
		Long dbSeller = this.sellerInSale.getId();
		return dbSeller;
	}

	public void setSellerInSale(SellerInSaleDTO sellerInSale) {
		this.sellerInSale = sellerInSale;
	}

	public List<ProductDTO> getItems() {
		return items;
	}

	public void setItems(List<ProductDTO> items) {
		this.items = items;
	}

	public Double getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(Double priceTotal) {
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
		return "SaleDTO [id=" + id + ", moment=" + moment + ", items=" + items + ", priceTotal=" + priceTotal
				+ ", completed=" + completed + "]";
	}


	

}
