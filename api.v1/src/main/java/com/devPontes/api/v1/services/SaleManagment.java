package com.devPontes.api.v1.services;

import java.time.LocalDate;
import java.util.Set;

import com.devPontes.api.v1.model.dtos.SaleDTO;
import com.devPontes.api.v1.model.dtos.SellerDTO;


public interface SaleManagment {

	SaleDTO findOneSaleById(Long id) throws Exception;
	
	Set<SaleDTO> findAllSales() throws Exception;
	
	Set<SaleDTO> findMostExpansivesSale() throws Exception;
	
	Set<SaleDTO> findBestSeller(SellerDTO seller) throws Exception;
	
	Set<SaleDTO> findLessExpansivesSale() throws Exception;
	
	Set<SaleDTO> findSalesByDate(LocalDate be, LocalDate tween);
	
	Set<SaleDTO> findWhoMounthHaveMoreSale(LocalDate be, LocalDate tween);

	SaleDTO updateExistentSale(SaleDTO Sale) throws Exception;
	
	void deleteExistentSale(Long id) throws Exception;

	SaleDTO registerNewSale(SaleDTO newsale, Long stockId) throws Exception;
	
}
