package com.devPontes.api.v1.services;

import com.devPontes.api.v1.model.dtos.SellerDTO;

public interface ProductManagment {

	SellerDTO findProductById(Long id);

	SellerDTO registerProduct(SellerDTO seller);

	SellerDTO updateProduct(SellerDTO seller);

	void deleteProduct(Long id);

	boolean verifyInStock(Long stockId, Long productId);

}
