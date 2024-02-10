package com.devPontes.api.model.services;

import com.devPontes.api.model.entities.Product;
import com.devPontes.api.model.entities.Stock;
import com.devPontes.api.model.entities.dtos.SellerDTO;

public interface ProductManagment {

	SellerDTO findProductById(Long id);

	SellerDTO registerProduct(SellerDTO seller);

	SellerDTO updateProduct(SellerDTO seller);

	void deleteProduct(Long id);

	boolean verifyInStock(Long stockId, Long productId);

}
