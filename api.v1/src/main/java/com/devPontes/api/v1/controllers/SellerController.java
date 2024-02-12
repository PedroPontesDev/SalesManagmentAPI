package com.devPontes.api.v1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devPontes.api.v1.model.dtos.SellerDTO;
import com.devPontes.api.v1.services.impl.SellerServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Seller", description = "Endpoint to manage sellers")
@RestController
@RequestMapping(path = "/seller/v1")
public class SellerController {

	@Autowired
	private SellerServices sellerServices;

	@Operation(tags = {"Seller"}, description = "Retrieve all sellers")
	@GetMapping(path = "/find-all")
	public ResponseEntity<List<SellerDTO>> findAll() {
		List<SellerDTO> all = sellerServices.findAll();
		return new ResponseEntity<>(all, HttpStatus.OK);
	}

	@Operation(tags = {"Seller"}, description = "Register one seller")
	@PostMapping(path = "/register-seller", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellerDTO> registerNewSeller(@RequestBody SellerDTO newSeller) throws Exception {
		var seller = sellerServices.registerSeller(newSeller);
		return new ResponseEntity<>(seller, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/update-seller")
	public ResponseEntity<SellerDTO> updateExistentSeller(SellerDTO updatedSeller) throws Exception {
		SellerDTO updated = sellerServices.updateSeller(updatedSeller);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
	
	

}
