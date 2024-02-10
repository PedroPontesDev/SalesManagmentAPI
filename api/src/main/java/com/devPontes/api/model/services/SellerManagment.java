package com.devPontes.api.model.services;

import com.devPontes.api.model.entities.Product;
import com.devPontes.api.model.entities.Seller;
import com.devPontes.api.model.entities.Stock;
import com.devPontes.api.model.entities.dtos.SellerDTO;

public interface SellerManagment {

	SellerDTO findById(Long id);
	SellerDTO registerSeller(SellerDTO seller);
	SellerDTO updateSeller(SellerDTO seller);
	void deleteSeller(Long id);
	double calculateComission(SellerDTO seller, double comissionValue);
}
