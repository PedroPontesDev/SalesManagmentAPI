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
import org.springframework.web.bind.annotation.RestController;

import com.devPontes.api.v1.model.dtos.ProductDTO;
import com.devPontes.api.v1.services.impl.ProductServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product", description = "Endpoint to manage Products")
@RestController
@RequestMapping(path = "/product/v1")
public class ProductController {

	@Autowired
	private ProductServices productServices;

	@Operation(tags = {"Product"}, description = "Retrieve all Products")
	@GetMapping(path = "/find-all")
	public ResponseEntity<List<ProductDTO>> findAll() throws Exception {
		List<ProductDTO> all = productServices.findAll();
		return new ResponseEntity<>(all, HttpStatus.OK);
	}
	
	@Operation(tags = {"Product"}, description = "Retrieve most expansives products in stock")
	@GetMapping(path = "/find-expansives/{stockId}")
	public ResponseEntity<List<ProductDTO>> findMostExpansives(@PathVariable Long stockId) throws Exception {
		var prods = productServices.findMostExpansivesInStock(stockId);
		return new ResponseEntity<>(prods, HttpStatus.OK);
	}
	
	@Operation(tags = {"Product"}, description = "Retrieve less expansives products in stock")
	@GetMapping(path = "/find-less/{stockId}")
	public ResponseEntity<List<ProductDTO>> findLessExpansivesInStock(@PathVariable Long stockId) throws Exception {
		var prods = productServices.findLessExpansivesInStock(stockId);
		return new ResponseEntity<>(prods, HttpStatus.OK);
	}

	@Operation(tags = {"Product"}, summary = "Register one Product")
	@PostMapping(path = "/register-product/{stockId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> registerNewProduct(@RequestBody ProductDTO newProduct, @PathVariable Long stockId) throws Exception {
		var Product = productServices.registerProductInStock(newProduct, stockId);
		return new ResponseEntity<>(Product, HttpStatus.CREATED);
	}
	
	@Operation(tags = {"Product"}, summary = "Update a existent Product")
	@PutMapping(path = "/update-product/{stockId}")
	public ResponseEntity<ProductDTO> updateExistentProduct(@RequestBody ProductDTO updatedProduct, @PathVariable Long stockId) throws Exception {
		ProductDTO updated = productServices.updateProductInStock(updatedProduct, stockId);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
	
	@Operation(tags = {"Product"}, summary = "Delete one Product")
	@DeleteMapping(path = "/delete-product/{stockId}/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long productId, @PathVariable Long stockId) throws Exception {
		productServices.deleteProductByIdInStock(productId, stockId);
		return ResponseEntity.noContent().build();
	}

	// A implementar metodos de produtos mais vendidos
}
