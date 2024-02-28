package com.devPontes.api.v1.services;

import java.time.LocalDate;
import java.util.Set;

import com.devPontes.api.v1.model.dtos.SaleDTO;
import com.devPontes.api.v1.model.entities.Sale;

public interface SaleManagment {

	SaleDTO findOneSaleById(Long id) throws Exception;

	Set<SaleDTO> findAllSales() throws Exception;

	void deleteExistentSale(Long id) throws Exception;

	SaleDTO updateExistentSale(SaleDTO Sale) throws Exception;

	SaleDTO registerNewSale(SaleDTO newsale, Long stockId, Long sellerId) throws Exception;
	
	Set<SaleDTO> findWhoMounthHaveMoreSale(SaleDTO sale, LocalDate now, LocalDate datesOfSale);

	Set<SaleDTO> findSalesByDate(SaleDTO sale, LocalDate be, LocalDate tween);

	

}
