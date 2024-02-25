package com.devPontes.api.v1.services;

import java.util.Set;

import com.devPontes.api.v1.model.dtos.SaleDTO;


public interface SaleManagment {

	SaleDTO findOneSaleById(Long id) throws Exception;
	
	// A implementar consultas personalizadas
	
	//como -> Procurar por vendas mais caras
	// -> Descobrir qual mês mais vendeu
	// -> Descobir o cliente mais ativo
	
	Set<SaleDTO> findAllSales() throws Exception;

	SaleDTO registerNewSale(SaleDTO Sale) throws Exception;

	SaleDTO updateExistentSale(SaleDTO Sale) throws Exception;
	
	void deleteExistentSale(Long id) throws Exception;	

	
	
}
