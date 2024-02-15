package com.devPontes.api.v1.services;

import java.util.Set;

import com.devPontes.api.v1.model.dtos.ProductDTO;
import com.devPontes.api.v1.model.dtos.SellerDTO;

public interface ProductManagment {

	ProductDTO findProductById(Long id);

	Set<ProductDTO> findMostExpansives(Long stockId);

	Set<ProductDTO> findLessExpansives(Long stockId);

	Set<ProductDTO> findMostSalleds(Long stockId);

	ProductDTO registerProduct(ProductDTO newProduct);

	ProductDTO updateProduct(ProductDTO product);

	void deleteProductById(Long id);

	boolean verifyInStock(Long stockId, Long productId);

}
