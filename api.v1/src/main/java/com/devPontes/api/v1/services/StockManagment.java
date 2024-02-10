package com.devPontes.api.v1.services;

import com.devPontes.api.v1.model.dtos.SellerDTO;
import com.devPontes.api.v1.model.dtos.StockDTO;

public interface StockManagment {

	StockDTO findStockById(Long id);

	StockDTO createStock(SellerDTO seller);

	StockDTO updateStock(SellerDTO seller);

	void deleteStock(Long id);

	double calculateStockPrice(Long id);

}
