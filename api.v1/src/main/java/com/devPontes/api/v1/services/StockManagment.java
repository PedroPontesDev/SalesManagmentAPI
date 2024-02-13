package com.devPontes.api.v1.services;

import com.devPontes.api.v1.model.dtos.ProductDTO;
import com.devPontes.api.v1.model.dtos.StockDTO;

public interface StockManagment {

	StockDTO findStockById(Long id) throws Exception;

	StockDTO createStock(StockDTO stock) throws Exception;

	StockDTO updateStock(Long id, StockDTO stock) throws Exception;

	StockDTO verifyCapacityInStock(StockDTO stock) throws Exception;
	
	void deleteStock(Long id) throws Exception;

	void upgradeCapacityOfStock(Long id, Integer newCapacity);
	
	void addProductsInStock(ProductDTO newProduct) throws Exception;

	double calculateStockPrice(Long id) throws Exception;
}
