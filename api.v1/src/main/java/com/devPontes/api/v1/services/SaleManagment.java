package com.devPontes.api.v1.services;

import java.time.LocalDate;
import java.util.Set;

import com.devPontes.api.v1.model.dtos.SaleDTO;
import com.devPontes.api.v1.model.entities.Client;
import com.devPontes.api.v1.model.entities.Sale;
import com.devPontes.api.v1.model.entities.Seller;
import com.devPontes.api.v1.model.entities.Stock;

public interface SaleManagment {

	SaleDTO registerNewSale(SaleDTO newSale, Long clientId, Long sellerId, Long stockId) throws Exception;

	Sale processSale(Sale sale, Stock stock, Client buyer, Seller seller) throws Exception;
	
	SaleDTO findSaleDetails(Long id) throws Exception;

	SaleDTO updateSale(SaleDTO Sale) throws Exception;
	
	Set<SaleDTO> findAllSales() throws Exception;

	Set<SaleDTO> findWhoMounthHaveMoreSale(SaleDTO sale, LocalDate now, LocalDate datesOfSale);

	Set<SaleDTO> findSalesByDate(SaleDTO sale, LocalDate be, LocalDate tween);

	void updateStockLevels(Sale sale, Long stockId);

	void deleteExistentSale(Long id) throws Exception;

	void addFrequencyOfSellerOfSales(Long saleId, Long sellerId);
	
	void verifyItensInSaleAndSetValue(Sale sale) throws Exception;




}
