package com.devPontes.api.v1.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.devPontes.api.v1.model.dtos.SellerDTO;
import com.devPontes.api.v1.model.dtos.StockDTO;
import com.devPontes.api.v1.repositories.StockRepositories;
import com.devPontes.api.v1.services.StockManagment;


public class StockServices implements StockManagment {

	@Autowired
	private StockRepositories stockRepositories;

	@Override
	public StockDTO findStockById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockDTO createStock(SellerDTO seller) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockDTO updateStock(SellerDTO seller) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStock(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public double calculateStockPrice(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
