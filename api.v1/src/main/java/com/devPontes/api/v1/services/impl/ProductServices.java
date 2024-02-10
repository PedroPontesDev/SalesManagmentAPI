package com.devPontes.api.v1.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.devPontes.api.v1.model.dtos.SellerDTO;
import com.devPontes.api.v1.repositories.ProductRepositories;
import com.devPontes.api.v1.services.ProductManagment;


public class ProductServices implements ProductManagment{

	@Autowired
	private ProductRepositories productRepositories;

	@Override
	public SellerDTO findProductById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SellerDTO registerProduct(SellerDTO seller) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SellerDTO updateProduct(SellerDTO seller) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verifyInStock(Long stockId, Long productId) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
