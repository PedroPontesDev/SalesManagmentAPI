package com.devPontes.api.v1.model.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_users")
public class Seller implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "salary")
	private double salary;

	@Column(name = "sale_quantity_in_month")
	private Integer quantitySales;

	public Seller(Long id, String username, String password, String email, String fullName, double salary,
			Integer quantitySales) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
		this.salary = salary;
		this.id = id;
		this.setQuantitySales(quantitySales);
	}

	public Seller() {
		super();
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

	public Integer getQuantitySales() {
		return quantitySales;
	}

	public void setQuantitySales(Integer quantitySales) {
		this.quantitySales = quantitySales;
	}

	public void setSalary(double salary) {
		this.salary = salary;
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
		Seller other = (Seller) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Seller [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", fullName=" + fullName + ", salary=" + salary + "]";
	}

}
