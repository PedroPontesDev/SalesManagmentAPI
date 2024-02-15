package com.devPontes.api.v1.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devPontes.api.v1.model.dtos.ProductDTO;
import com.devPontes.api.v1.model.dtos.StockDTO;
import com.devPontes.api.v1.model.entities.Product;
import com.devPontes.api.v1.model.entities.Stock;
import com.devPontes.api.v1.model.mapper.MyMapper;
import com.devPontes.api.v1.repositories.ProductRepositories;
import com.devPontes.api.v1.repositories.StockRepositories;
import com.devPontes.api.v1.services.StockManagment;

@Service
public class StockServices implements StockManagment {

	@Autowired
	private StockRepositories stockRepositories;

	@Autowired
	private ProductRepositories productsRepositories;

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
			stock.setCurrentCapacity(updated.getCurrentCapacity());
			stock.setCapacityMax(updated.getCapacityMax());
			stock.setStockName(updated.getStockName());
			stock.setTotalPriceInStock(updated.getTotalPriceInStock());
			stockRepositories.save(stock);
			var dto = MyMapper.parseObject(stock, StockDTO.class);
			return dto;
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
		if (entity.isPresent()) {
			Stock stock = entity.get();
			Double productsPrice = stock.getProductsInStock().stream().mapToDouble(p -> p.getPrice() * p.getQuantity())
					.sum();
			stock.setTotalPriceInStock(productsPrice);
			stockRepositories.save(stock);
			return MyMapper.parseObject(stock, StockDTO.class);
		} else {
			throw new Exception("Não foi possivel identificar o ID" + id);
		}

	}

	@Override
	public ProductDTO addProductsInStock(Long stockId, ProductDTO newProduct) throws Exception {
		var entity = stockRepositories.findById(stockId);
		if (entity.isPresent()) {
			Stock stock = entity.get();
			if (stock.isStockFull()) {
				throw new Exception("Não é possivel adicionar produtos em um estoque cheio!");
			} else {
				Product newProd = MyMapper.parseObject(newProduct, Product.class);
				newProd.setStock(stock);
				newProd.getStock().getProductsInStock().add(newProd);
				Integer in = stock.getProductsInStock()
						.stream()
						.mapToInt(Product::getQuantity)
						.sum() + newProd.getQuantity();
				stock.setCurrentCapacity(in);
				productsRepositories.save(newProd);
				stockRepositories.save(stock);
				return MyMapper.parseObject(newProd, ProductDTO.class);
			}

		}
		return newProduct;
	}

	@Override
	public void deleteProductsInStock(Long productId, Long stockId) throws Exception {
		var entity = productsRepositories.findById(productId);
		var stock = stockRepositories.findById(stockId);
		if (entity.isPresent()) {
			Integer out = stock.get().getCurrentCapacity() - 1;
			productsRepositories.delete(entity.get());
		} else {
			throw new Exception("Não foi possivel encontrar o produto pelo ID" + productId);
		}

	}

	@Override
	public void setQuantityItemUp(Long itemId, Long stockId, Integer quantity) throws Exception {
		var entity = productsRepositories.findById(itemId);
		var stk = stockRepositories.findById(stockId);
		if (entity.isPresent() && stk.isPresent()) {
			Stock stock = stk.get();
			Product item = entity.get();
			Integer apply = stock.getProductsInStock().stream().filter(p -> p.getId().equals(itemId))
					.mapToInt(Product::getQuantity).sum() + quantity;
			stock.getProductsInStock().stream().filter(p -> p.getId().equals(itemId)).findFirst()
					.ifPresent(p -> p.setQuantity(apply));
			stockRepositories.save(stock);

		} else {
			throw new Exception("Não foi possivel atualizar");
		}
	}

	@Override
	public void setQuantityItemDown(Long itemId, Long stockId, Integer quantity) throws Exception {
		var entity = productsRepositories.findById(itemId);
		var stk = stockRepositories.findById(stockId);
		if (entity.isPresent() && stk.isPresent()) {
			Stock stock = stk.get();
			Product item = entity.get();
			Integer apply = stock.getProductsInStock().stream().filter(p -> p.getId().equals(itemId))
					.mapToInt(Product::getQuantity).sum() - quantity;
			stock.getProductsInStock().stream().filter(p -> p.getId().equals(itemId)).findFirst()
					.ifPresent(p -> p.setQuantity(apply));
			stockRepositories.save(stock);
		} else {
			throw new Exception("Não foi possivel atualizar");
		}
	}

}