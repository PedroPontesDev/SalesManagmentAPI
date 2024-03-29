package com.devPontes.api.v1.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.devPontes.api.v1.model.dtos.ProductDTO;
import com.devPontes.api.v1.model.dtos.SaleDTO;
import com.devPontes.api.v1.model.entities.Client;
import com.devPontes.api.v1.model.entities.Sale;
import com.devPontes.api.v1.model.entities.Seller;

public interface SaleManagment {

	SaleDTO registerNewSale(ProductDTO productRequest, Long clientId, Long sellerId, Long stockId) throws Exception;

	SaleDTO processSale(ProductDTO productRequest, Long sellerId, Long stockId, Client client) throws Exception;

	SaleDTO findSaleDetails(Long id) throws Exception;

	SaleDTO updateSale(SaleDTO Sale) throws Exception;

	boolean verifyIfHasProductsToSale(List<Long> saleItems, List<Long> inDbItems);

	void updateStockLevels(Sale sale, Long stockId);

	Set<SaleDTO> findAllSales() throws Exception;

	Set<SaleDTO> findWhoMounthHaveMoreSale(SaleDTO sale, LocalDate now, LocalDate datesOfSale);

	Set<SaleDTO> findSalesByDate(SaleDTO sale, LocalDate be, LocalDate tween);

	void deleteExistentSale(Long id) throws Exception;

	void addFrequencyOfSellerOfSales(Long saleId, Long sellerId);

}
