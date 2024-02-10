package com.devPontes.api.v1.services;

import com.devPontes.api.v1.model.dtos.SellerDTO;


public interface SellerManagment {

	SellerDTO findById(Long id);

	SellerDTO registerSeller(SellerDTO seller);

	SellerDTO updateSeller(SellerDTO seller);

	void deleteSeller(Long id);

	double calculateComission(SellerDTO seller, double comissionValue);
}
