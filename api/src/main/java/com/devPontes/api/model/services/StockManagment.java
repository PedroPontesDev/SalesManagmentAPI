package com.devPontes.api.model.services;

import com.devPontes.api.model.entities.Stock;
import com.devPontes.api.model.entities.dtos.SellerDTO;
import com.devPontes.api.model.entities.dtos.StockDTO;

public interface StockManagment {

	StockDTO findStockById(Long id);

	StockDTO createStock(SellerDTO seller);

	StockDTO updateStock(SellerDTO seller);

	void deleteStock(Long id);

	double calculateStockPrice(Long id);

}
