package com.devPontes.api.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.devPontes.api.model.entities.dtos.SellerDTO;
import com.devPontes.api.model.entities.dtos.StockDTO;
import com.devPontes.api.model.services.StockManagment;
import com.devPontes.api.repositories.StockRepositories;

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
