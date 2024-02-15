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
import org.springframework.web.bind.annotation.RestController;

import com.devPontes.api.v1.model.dtos.ProductDTO;
import com.devPontes.api.v1.model.dtos.StockDTO;
import com.devPontes.api.v1.services.impl.StockServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Stock", description = "Endpoint to manage Stocks")
@RestController
@RequestMapping(path = "/stock/v1")
public class StockController {

	@Autowired
	private StockServices stockServices;

	@Operation(tags = {"Stock"}, description = "Retrieve One Stock")
	@GetMapping(path = "/show-stock/{stockId}")
	public ResponseEntity<StockDTO> showStock(@PathVariable Long stockId) throws Exception {
	    StockDTO stock = stockServices.findStockById(stockId);
	    return new ResponseEntity<>(stock, HttpStatus.OK);
	}

	@Operation(tags = {"Stock"}, summary = "Register one Stock")
	@PostMapping(path = "/create-stock", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDTO> registerNewStock(@RequestBody StockDTO newStock) throws Exception {
		var stock = stockServices.createStock(newStock);
		return new ResponseEntity<>(stock, HttpStatus.CREATED);
	}
	
	@Operation(tags = {"Stock"}, summary = "Update a existent Stock")
	@PutMapping(path = "/update-stock/{stockId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDTO> updateExistentStock(@PathVariable Long stockId, @RequestBody StockDTO updatedStock) throws Exception {
		StockDTO updated = stockServices.updateStock(stockId, updatedStock);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
	
	@Operation(tags = {"Stock"}, summary = "Delete one Stock")
	@DeleteMapping(path = "/delete-stock/{StockId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteStock(@PathVariable Long StockId) throws Exception {
		stockServices.deleteStock(StockId);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(tags = {"Stock"}, summary = "Upgrade the capacity of stock if stocks get full")
	@PutMapping(path = "/upgrade-capacity/{stockId}")
	public ResponseEntity<String> upgradeCapacityOfStock(@PathVariable Long stockId, @PathVariable Integer newCapacity) throws Exception {
		stockServices.upgradeCapacityOfStock(stockId, newCapacity);
		return ResponseEntity.ok("Capaciada maxima do estoque atualizada!");
	}
	
	@Operation(tags = {"Stock"}, summary = "Verify if stock is full, if the current capacity is equal max capicity so return true")
	@GetMapping(path = "/verify-stock/{stockId}")
	public ResponseEntity<Boolean> verifyIfStockIsFull(@PathVariable Long stockId) throws Exception {
		Boolean response = stockServices.verifyIfStockIsFull(stockId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@Operation(tags = {"Stock"}, summary = "Add a new product to referent StockID")
	@PostMapping(path = "/add-product/{stockId}")
	public ResponseEntity<ProductDTO> addProductInStock(@RequestBody ProductDTO product, @PathVariable Long stockId) throws Exception {
		ProductDTO newProduct = stockServices.addProductsInStock(stockId, product);
		return new ResponseEntity<>(newProduct, HttpStatus.OK);
	}
	
	@Operation(tags = {"Stock"}, summary = "Delete one product to referent StockID")
	@DeleteMapping(path = "/delete-product/{stockId}/{productId}")
	public ResponseEntity<ProductDTO> deleteProductInStock(@PathVariable Long stockId, @PathVariable Long productId) throws Exception {
		stockServices.deleteProductsInStock(stockId, productId);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(tags = {"Stock"}, summary = "Calculate total prince in referent StockID")
	@PutMapping(path = "/calculate-stock/{stockId}")
	public ResponseEntity<?> calculateAllPriceOfStock(@PathVariable Long stockId) {
		var calculated = calculateAllPriceOfStock(stockId);
		return new ResponseEntity<>(calculated, HttpStatus.OK);
	}
	
}
	
	