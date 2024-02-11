package com.devPontes.api.v1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devPontes.api.v1.model.dtos.SellerDTO;
import com.devPontes.api.v1.services.impl.UserServices;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Seller", description = "Endpoint to manage sellers")
@RestController
@RequestMapping(path = "/api/seller/v1")
public class SellerController {

	@Autowired
	private UserServices userServices;
	
	@PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE,
									produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellerDTO> registerNewSeller(@RequestBody SellerDTO newSeller) throws Exception {
		var seller = userServices.registerSeller(newSeller);
		return new ResponseEntity<>(seller, HttpStatus.CREATED);
	}

}
