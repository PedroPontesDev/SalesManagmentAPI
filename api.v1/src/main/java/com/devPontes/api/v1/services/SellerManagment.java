package com.devPontes.api.v1.services;

import java.util.List;

import com.devPontes.api.v1.model.dtos.SellerInSaleDTO;


public interface SellerManagment {

	SellerInSaleDTO findById(Long id) throws Exception;
	
	List<SellerInSaleDTO> findAll() throws Exception;

	SellerInSaleDTO registerSeller(SellerInSaleDTO seller) throws Exception;

	SellerInSaleDTO updateSeller(SellerInSaleDTO seller) throws Exception;

	SellerInSaleDTO updateSellerSalary(Long id, Double newSalary) throws Exception;
	
	void deleteSeller(Long id) throws Exception;	

	double calculateComission(Long id, Double comissionValue) throws Exception;
	
	
}
