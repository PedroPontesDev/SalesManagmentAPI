package com.devPontes.api.v1.services;

import java.util.List;
import java.util.Set;

import com.devPontes.api.v1.model.dtos.ProductDTO;
import com.devPontes.api.v1.model.dtos.SellerDTO;

public interface ProductManagment {

	ProductDTO findProductById(Long id) throws Exception;

	List<ProductDTO> findAll() throws Exception;
	
	List<ProductDTO> findMostExpansivesInStock(Long stockId)throws Exception;

	List<ProductDTO> findLessExpansivesInStock(Long stockId) throws Exception;

	List<ProductDTO> findMostSalleds(Long stockId) throws Exception;;

	ProductDTO registerProductInStock(ProductDTO newProduct, Long stockId) throws Exception;

	ProductDTO updateProductInStock(ProductDTO product, Long stockId) throws Exception;;

	void deleteProductByIdInStock(Long prodcutId, Long stockId) throws Exception;

	boolean verifyInStock(Long stockId, Long productId) throws Exception;;

}
