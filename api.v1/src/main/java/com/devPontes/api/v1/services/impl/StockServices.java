package com.devPontes.api.v1.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.devPontes.api.v1.model.dtos.ProductDTO;
import com.devPontes.api.v1.model.dtos.StockDTO;
import com.devPontes.api.v1.model.entities.Stock;
import com.devPontes.api.v1.model.mapper.MyMapper;
import com.devPontes.api.v1.repositories.StockRepositories;
import com.devPontes.api.v1.services.StockManagment;

public class StockServices implements StockManagment {

	@Autowired
	private StockRepositories stockRepositories;

	@Override
	public StockDTO findStockById(Long id) throws Exception {
		var entity = stockRepositories.findById(id);
		if (entity.isPresent()) {
			Stock finded = entity.get();
			return MyMapper.parseObject(finded, StockDTO.class);
		} else {
			throw new Exception("Não foi possivel achar o estoque pelo id fornecido, tente novamente mais tarde!");
		}
	}

	@Override
	public StockDTO createStock(StockDTO stock) throws Exception {
		var entity = MyMapper.parseObject(stock, Stock.class);
		if (entity == null) {
			throw new Exception("O estoque criado parece estar nulo, tente novamente!");
		} else if (entity.getCapacity() > 200) {
			throw new Exception("O estoque só armazena 200 itens, tente novamente!");
		} else {
			stockRepositories.save(entity);
			return MyMapper.parseObject(entity, StockDTO.class);
		}

	}

	@Override
	public StockDTO updateStock(Long id, StockDTO updated) throws Exception {
		var entity = stockRepositories.findById(id);
		if(entity.isPresent()) {
			Stock stock = entity.get();
			stock.setCapacityMax(updated.getCapacityMax());
			stock.setStockName(updated.getStockName());
			stock.setTotalPriceInStock(updated.getTotalPriceInStock());
			stockRepositories.save(stock);
			return MyMapper.parseObject(entity, StockDTO.class);
		} else {
			throw new Exception("Estoque não pode ser atualizado, verifique os dados e tente novamente!");
		}
	}

	@Override
	public void deleteStock(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void upgradeCapacityOfStock(Long id, Integer newCapacity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double calculateStockPrice(Long id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public StockDTO verifyCapacityInStock(StockDTO stock) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addProductsInStock(ProductDTO newProduct) throws Exception {
		// TODO Auto-generated method stub
		
	}

	

}