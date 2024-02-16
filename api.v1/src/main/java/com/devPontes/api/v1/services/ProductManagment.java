package com.devPontes.api.v1.services;

import java.util.List;
import java.util.Set;

import com.devPontes.api.v1.model.dtos.ProductDTO;
import com.devPontes.api.v1.model.dtos.SellerDTO;

public interface ProductManagment {

	ProductDTO findProductById(Long id) throws Exception;

	List<ProductDTO> findMostExpansivesInStock(Long stockId)throws Exception;;

	List<ProductDTO> findLessExpansivesInStock(Long stockId) throws Exception;

	List<ProductDTO> findMostSalleds(Long stockId) throws Exception;;

	ProductDTO registerProduct(ProductDTO newProduct) throws Exception;

	ProductDTO updateProduct(ProductDTO product) throws Exception;;

	void deleteProductById(Long id) throws Exception;

	boolean verifyInStock(Long stockId, Long productId) throws Exception;;

}
