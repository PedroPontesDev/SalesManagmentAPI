package com.devPontes.api.v1.services;

import java.util.List;

import com.devPontes.api.v1.model.dtos.SellerDTO;


public interface SellerManagment {

	SellerDTO findById(Long id) throws Exception;
	
	List<SellerDTO> findAll() throws Exception;

	SellerDTO registerSeller(SellerDTO seller) throws Exception;

	SellerDTO updateSeller(SellerDTO seller) throws Exception;

	void deleteSeller(Long id) throws Exception;	

	double calculateComission(SellerDTO seller, double comissionValue) throws Exception;
}
