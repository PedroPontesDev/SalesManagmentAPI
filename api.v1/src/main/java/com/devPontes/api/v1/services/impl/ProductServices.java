package com.devPontes.api.v1.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.devPontes.api.v1.model.dtos.ProductDTO;
import com.devPontes.api.v1.model.dtos.SellerDTO;
import com.devPontes.api.v1.model.mapper.MyMapper;
import com.devPontes.api.v1.repositories.ProductRepositories;
import com.devPontes.api.v1.repositories.StockRepositories;
import com.devPontes.api.v1.services.ProductManagment;


public class ProductServices implements ProductManagment{

	@Autowired
	private ProductRepositories productRepositories;

	@Autowired
	private StockRepositories stockRepositorie;

	@Override
	public ProductDTO findProductById(Long id) throws Exception {
		var entity = productRepositories.findById(id);
		if(entity.isPresent()) {
			return MyMapper.parseObject(entity.get(), ProductDTO.class);
		} else {
			throw new Exception("Não foi possivel encontrar o produto de ID" + id);
		}
	}

	@Override
	public List<ProductDTO> findMostExpansives(Long stockId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDTO> findLessExpansives(Long stockId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDTO> findMostSalleds(Long stockId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDTO registerProduct(ProductDTO newProduct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDTO updateProduct(ProductDTO product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProductById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verifyInStock(Long stockId, Long productId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
