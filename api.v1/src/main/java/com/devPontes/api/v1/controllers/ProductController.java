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
	public ResponseEntity<List<ProductDTO>> findAll() {
		List<ProductDTO> all = productServices.findAll();
		return new ResponseEntity<>(all, HttpStatus.OK);
	}

	@Operation(tags = {"Product"}, summary = "Register one Product")
	@PostMapping(path = "/register-product", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> registerNewProduct(@RequestBody ProductDTO newProduct) throws Exception {
		var Product = ProductServices.registerProduct(newProduct);
		return new ResponseEntity<>(Product, HttpStatus.CREATED);
	}
	
	@Operation(tags = {"Product"}, summary = "Update a existent Product")
	@PutMapping(path = "/update-product")
	public ResponseEntity<ProductDTO> updateExistentProduct(@RequestBody ProductDTO updatedProduct) throws Exception {
		ProductDTO updated = ProductServices.updateProduct(updatedProduct);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
	
	@Operation(tags = {"Product"}, summary = "Delete one Product")
	@DeleteMapping(path = "{productId}/delete-product/ ")
	public ResponseEntity<?> deleteProduct(@PathVariable Long ProductId) throws Exception {
		productServices.deleteProduct(ProductId);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(tags = {"Product"}, summary = "Update salary of one Product")
	@PutMapping(path = "/update-Product-salary")
	public ResponseEntity<ProductDTO> updateSalary(@RequestParam Long id, @RequestParam Double newSalary) throws Exception {
	    ProductDTO updatedProduct = ProductServices.updateProductSalary(id, newSalary);
	    return ResponseEntity.ok().body(updatedProduct);
	}
	
	//Metodoa implementar 
	
	//1 -> Verificar vendedores que mais venderam no mês 
	
	//2 -> Calcular comissão baseada em vendas
	

}
