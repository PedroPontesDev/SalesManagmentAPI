package com.devPontes.api.v1.controllers;

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

import com.devPontes.api.v1.model.dtos.StockDTO;
import com.devPontes.api.v1.services.impl.StockServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Stock", description = "Endpoint to manage Stocks")
@RestController
@RequestMapping(path = "/stock/v1")
public class StockController {

	@Autowired
	private StockServices StockServices;

	@Operation(tags = {"Stock"}, description = "Retrieve One Stock")
	@GetMapping(path = "{/show-stock/{stockId}")
	public ResponseEntity<StockDTO> showStock(@RequestParam Long stockId) throws Exception {
		StockDTO stock = StockServices.findStockById(stockId);
		return new ResponseEntity<>(stock, HttpStatus.OK);
	}

	@Operation(tags = {"Stock"}, summary = "Register one Stock")
	@PostMapping(path = "/create-stock", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDTO> registerNewStock(@RequestBody StockDTO newStock) throws Exception {
		var Stock = StockServices.createStock(newStock);
		return new ResponseEntity<>(Stock, HttpStatus.CREATED);
	}
	
	@Operation(tags = {"Stock"}, summary = "Update a existent Stock")
	@PutMapping(path = "/update-stock", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDTO> updateExistentStock(@RequestParam Long stockId, @RequestBody StockDTO updatedStock) throws Exception {
		StockDTO updated = StockServices.updateStock(stockId, updatedStock);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
	
	@Operation(tags = {"Stock"}, summary = "Delete one Stock")
	@DeleteMapping(path = "/delete-stock/{StockId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteStock(@PathVariable Long StockId) throws Exception {
		StockServices.deleteStock(StockId);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(tags = {"Stock"}, summary = "Upgrade the capacity of stock if stocks get full")
	@PutMapping(name = "")
	public ResponseEntity<String> upgradeCapacityOfStock(@PathVariable Long stockId, @RequestParam Integer newCapacity) {
		
	}
}
