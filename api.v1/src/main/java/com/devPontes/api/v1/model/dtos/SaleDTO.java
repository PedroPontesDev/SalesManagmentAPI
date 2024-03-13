package com.devPontes.api.v1.model.dtos;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.devPontes.api.v1.model.entities.Seller;
import com.devPontes.api.v1.model.mapper.MyMapper;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "moment", "sellerInSale", "items", "priceTotal", "completed" })
public class SaleDTO extends RepresentationModel<SellerInSaleDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Instant moment;
	private List<ProductDTO> items;
	private Double priceTotal;
	private Boolean completed;

	private SellerDTO sellerInSale;
	

	public SaleDTO(Long id, Instant moment, List<ProductDTO> items, Double priceTotal, Boolean completed,
			SellerDTO sellerInSale) {
		this.id = id;
		this.moment = moment;
		this.items = items;
		this.priceTotal = priceTotal;
		this.completed = completed;
		this.sellerInSale = sellerInSale;
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

	public List<ProductDTO> getItems() {
		return items;
	}

	public void setItems(List<ProductDTO> items) {
		this.items = items;
	}

	public SellerDTO getSellerInSale() throws Exception{
	      SellerDTO seller = this.sellerInSale;
	      Seller mapped = MyMapper.parseObject(seller, Seller.class);
	      mapped.setId(seller.getId());
	      mapped.setFullName(seller.getFullName());
	      mapped.setEmail(seller.getEmail());
	      mapped.setUsername(seller.getUsername());
	     
	      var dto = MyMapper.parseObject(mapped, SellerDTO.class);
	      
	      if(dto != null) return dto;
	      throw new Exception("Algodeu errraodo");
	    	
	}
	
	public void setSellerInSale(SellerDTO sellerInSale) {
		this.sellerInSale = sellerInSale;
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
