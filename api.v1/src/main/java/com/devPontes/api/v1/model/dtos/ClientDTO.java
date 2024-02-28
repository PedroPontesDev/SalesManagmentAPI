package com.devPontes.api.v1.model.dtos;

import java.io.Serializable;

public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String fullName;
    private String cpf;
    private SaleDTO sale;

    public ClientDTO() {
    }

    public ClientDTO(Long id, String fullName, String cpf, SaleDTO sale) {
        this.id = id;
        this.fullName = fullName;
        this.cpf = cpf;
        this.sale = sale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public SaleDTO getSale() {
        return sale;
    }

    public void setSale(SaleDTO sale) {
        this.sale = sale;
    }
}
