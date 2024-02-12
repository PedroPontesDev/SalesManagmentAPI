package com.devPontes.api.v1.model.dtos;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

public class SellerDTO extends RepresentationModel<SellerDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private String password;
	private String email;
	private String fullName;
	private double salary;

	private Integer quantitySalesInMonth;

	public SellerDTO(Long id, String username, String password, String email, String fullName, double salary,
			Integer quantitySalesInMonth) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
		this.salary = salary;
		this.setQuantitySalesInMonth(quantitySalesInMonth);
	}

	public SellerDTO() {

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Integer getQuantitySalesInMonth() {
		return quantitySalesInMonth;
	}

	public void setQuantitySalesInMonth(Integer quantitySalesInMonth) {
		this.quantitySalesInMonth = quantitySalesInMonth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id);
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
		SellerDTO other = (SellerDTO) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "SellerDTO [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", fullName=" + fullName + ", salary=" + salary + "]";
	}

}
