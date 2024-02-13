package com.devPontes.api.v1.services;

import com.devPontes.api.v1.model.dtos.ProductDTO;
import com.devPontes.api.v1.model.dtos.StockDTO;

public interface StockManagment {

	StockDTO findStockById(Long id) throws Exception;

	StockDTO createStock(StockDTO stock) throws Exception;

	StockDTO updateStock(Long id, StockDTO stock) throws Exception;

	void deleteStock(Long id) throws Exception;

	void upgradeCapacityOfStock(Long id, Integer newCapacity) throws Exception;

	ProductDTO addProductsInStock(Long stockId, ProductDTO newProduct) throws Exception;

	void deleteProductsInStock(Long productId) throws Exception;
	
	void setQuantityItemUp(Long itemId, Long stockId, Integer quantity) throws Exception;
	
	void setQuantityItemDown(Long itemId, Long stockId, Integer quantity) throws Exception;

	StockDTO calculateStockPrice(Long id) throws Exception;

	Boolean verifyIfStockIsFull(Long id) throws Exception;

}
