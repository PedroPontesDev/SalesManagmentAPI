package com.devPontes.api.v1.model.dtos;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

public class SellerInSaleDTO extends RepresentationModel<SellerInSaleDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private String email;
	private String fullName;

	public SellerInSaleDTO(Long id, String username, String email, String fullName) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.fullName = fullName;
	}

	public SellerInSaleDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(email, fullName, id, username);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SellerInSaleDTO other = (SellerInSaleDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(id, other.id) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "SellerInSaleDTO [id=" + id + ", username=" + username + ", email=" + email + ", fullName=" + fullName
				+ "]";
	}

}
