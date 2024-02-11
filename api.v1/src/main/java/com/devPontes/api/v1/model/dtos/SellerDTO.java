package com.devPontes.api.v1.model.dtos;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class SellerDTO extends RepresentationModel<SellerDTO> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String username;
	private String password;
	private String email;
	private String fullName;
	private double salary;

}
