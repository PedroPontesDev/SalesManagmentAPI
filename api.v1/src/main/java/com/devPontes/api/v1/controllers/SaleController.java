package com.devPontes.api.v1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devPontes.api.v1.model.dtos.SaleDTO;
import com.devPontes.api.v1.model.mapper.MyMapper;
import com.devPontes.api.v1.services.impl.SaleServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Sale", description = "Endpoint to manage sales")
@RestController
@RequestMapping(path = "/sale/v1")
public class SaleController {

	@Autowired
	private SaleServices saleServices;

	@Operation(tags = {"Sale"}, description = "Retrieve One Sale")
	@GetMapping(path = "/sale-details/{saleId}")
	public ResponseEntity<SaleDTO> showSaleDetails(@PathVariable Long saleId) throws Exception {
	    SaleDTO sale = saleServices.findSaleDetails(saleId);
	    if(sale != null) return new ResponseEntity<>(sale, HttpStatus.OK);
	    else {return ResponseEntity.badRequest().build();}
	}

	  @PostMapping("/register-sale/{clientId}/{sellerId}/s/{stockId}")
	    public ResponseEntity<SaleDTO> registerNewSale(
	            @RequestBody SaleDTO saleRequest,
	            @PathVariable Long clientId,
	            @PathVariable Long sellerId,
	            @PathVariable Long stockId) {
	        try {
	            SaleDTO registeredSale = saleServices.registerNewSale(saleRequest, clientId, sellerId, stockId);
	            return ResponseEntity.status(HttpStatus.CREATED).body(registeredSale);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
	}