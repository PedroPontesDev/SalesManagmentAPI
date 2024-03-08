package com.devPontes.api.v1.services;

import java.util.List;

import com.devPontes.api.v1.model.dtos.ProductDTO;

public interface ProductManagment {

	ProductDTO findProductById(Long id) throws Exception;

	List<ProductDTO> findAll() throws Exception;
	
	List<ProductDTO> findMostExpansivesInStock(Long stockId)throws Exception;

	List<ProductDTO> findLessExpansivesInStock(Long stockId) throws Exception;

	List<ProductDTO> findTop5MostFrequentProducts() throws Exception;;

	ProductDTO registerProductInStock(ProductDTO newProduct, Long stockId) throws Exception;

	ProductDTO updateProductInStock(ProductDTO product, Long stockId) throws Exception;;

	void deleteProductByIdInStock(Long prodcutId, Long stockId) throws Exception;

	boolean verifyInStock(Long stockId, Long productId) throws Exception;;

}
