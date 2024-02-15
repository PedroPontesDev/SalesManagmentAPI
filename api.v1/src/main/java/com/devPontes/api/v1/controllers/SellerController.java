package com.devPontes.api.v1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@Operation(tags = {"Seller"}, summary = "Register one seller")
	@PostMapping(path = "/register-seller", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellerDTO> registerNewSeller(@RequestBody SellerDTO newSeller) throws Exception {
		var seller = sellerServices.registerSeller(newSeller);
		return new ResponseEntity<>(seller, HttpStatus.CREATED);
	}
	
	@Operation(tags = {"Seller"}, summary = "Update a existent seller")
	@PutMapping(path = "/update-seller")
	public ResponseEntity<SellerDTO> updateExistentSeller(@RequestBody SellerDTO updatedSeller) throws Exception {
		SellerDTO updated = sellerServices.updateSeller(updatedSeller);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
	
	@Operation(tags = {"Seller"}, summary = "Delete one seller")
	@DeleteMapping(path = "{sellerId}/delete-seller/ ")
	public ResponseEntity<?> deleteSeller(@PathVariable Long sellerId) throws Exception {
		sellerServices.deleteSeller(sellerId);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(tags = {"Seller"}, summary = "Update salary of one seller")
	@PutMapping(path = "/update-seller-salary")
	public ResponseEntity<SellerDTO> updateSalary(@RequestParam Long id, @RequestParam Double newSalary) throws Exception {
	    SellerDTO updatedSeller = sellerServices.updateSellerSalary(id, newSalary);
	    return ResponseEntity.ok().body(updatedSeller);
	}
	
	//Metodoa implementar 
	
	//1 -> Verificar vendedores que mais venderam no mês 
	
	//2 -> Calcular comissão baseada em vendas
	

}
