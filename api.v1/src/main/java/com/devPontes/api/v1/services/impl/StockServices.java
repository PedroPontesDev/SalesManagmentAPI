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
		} else {
			stockRepositories.save(entity);
			return MyMapper.parseObject(entity, StockDTO.class);
		}

	}

	@Override
	public StockDTO updateStock(Long id, StockDTO updated) throws Exception {
		var entity = stockRepositories.findById(id);
		if (entity.isPresent()) {
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
		var entity = stockRepositories.findById(id);
		if (entity.isPresent()) {
			stockRepositories.delete(entity.get());
		} else {
			throw new Exception("Não foi possivel deletar o estoque, verifique o id e tente novamente!");
		}
	}

	@Override
	public void upgradeCapacityOfStock(Long id, Integer newCapacity) throws Exception {
		var entity = stockRepositories.findById(id);
		if (entity.isPresent()) {
			Stock stock = entity.get();
			if (stock.isStockFull()) {
				stock.setCapacityMax(newCapacity);
				stockRepositories.save(stock);
			} else {
				throw new Exception("O upgrade da capacidade só é permitido quando o estoque está cheio");
			}
		} else {
			throw new NullPointerException("Estoque não encontrado para o ID fornecido: " + id);
		}
	}

	@Override
	public Boolean verifyIfStockIsFull(Long id) throws Exception {
		var entity = stockRepositories.findById(id);
		if (entity.isPresent()) {
			Stock stock = entity.get();
			if (stock.isStockFull()) {
				throw new Exception("O estoque está cheio, exclua alguns itens");
			} else {
				return false;
			}
		} else {
			throw new Exception("Estoque não encontrado para o ID fornecido: " + id);
		}
	}

	@Override
	public StockDTO calculateStockPrice(Long id) throws Exception {
		var entity = stockRepositories.findById(id);
		if(entity.isPresent()) {
			
		}
	}

	@Override
	public ProductDTO addProductsInStock(ProductDTO newProduct) throws Exception {
		return newProduct;
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProductsInStock(Long productId, Integer removeQuantity) throws Exception {
		// TODO Auto-generated method stub

	}

}