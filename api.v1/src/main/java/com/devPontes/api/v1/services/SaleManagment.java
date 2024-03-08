package com.devPontes.api.v1.services;

import java.time.LocalDate;
import java.util.Set;

import com.devPontes.api.v1.model.dtos.SaleDTO;
import com.devPontes.api.v1.model.entities.Sale;
import com.devPontes.api.v1.model.entities.Stock;

public interface SaleManagment {

	public SaleDTO registerNewSale(SaleDTO newSale, Long clientId, Long sellerId, Long stockId) throws Exception;

	SaleDTO findSaleDetails(Long id) throws Exception;

	SaleDTO updateSale(SaleDTO Sale) throws Exception;

	void processSale(Sale sale, Stock stock) throws Exception;
	
	boolean updateStockLevels(Sale sale, Long stockId);
	
	void verifyItensAndSetValue(Sale sale) throws Exception;
	
	Set<SaleDTO> findAllSales() throws Exception;

	Set<SaleDTO> findWhoMounthHaveMoreSale(SaleDTO sale, LocalDate now, LocalDate datesOfSale);

	Set<SaleDTO> findSalesByDate(SaleDTO sale, LocalDate be, LocalDate tween);

	void deleteExistentSale(Long id) throws Exception;

	void addFrequencyOfSellerOfSales(Long saleId, Long sellerId);



}
