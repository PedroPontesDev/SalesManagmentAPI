package com.devPontes.api.v1.services;

import java.util.Set;

import com.devPontes.api.v1.model.dtos.SaleDTO;


public interface SaleManagment {

	SaleDTO findOneSaleById(Long id) throws Exception;
	
	Set<SaleDTO> findAllSales() throws Exception;

	SaleDTO registerNewSale(SaleDTO Sale) throws Exception;

	SaleDTO updateExistentSale(SaleDTO Sale) throws Exception;
	
	void deleteExistentSale(Long id) throws Exception;	

	
	
}
