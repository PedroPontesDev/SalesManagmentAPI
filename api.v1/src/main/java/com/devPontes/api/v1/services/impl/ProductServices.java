package com.devPontes.api.v1.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import com.devPontes.api.v1.model.dtos.ProductDTO;
import com.devPontes.api.v1.model.entities.Product;
import com.devPontes.api.v1.model.entities.Sale;
import com.devPontes.api.v1.model.entities.Stock;
import com.devPontes.api.v1.model.mapper.MyMapper;
import com.devPontes.api.v1.repositories.ProductRepositories;
import com.devPontes.api.v1.repositories.SaleRepositories;
import com.devPontes.api.v1.repositories.StockRepositories;
import com.devPontes.api.v1.services.ProductManagment;

public class ProductServices implements ProductManagment {

	@Autowired
	private ProductRepositories productRepositories;

	@Autowired
	private StockRepositories stockRepositories;

	@Autowired
	private SaleRepositories saleRepositories;

	@Override
	public ProductDTO findProductById(Long id) throws Exception {
		var entity = productRepositories.findById(id);
		if (entity.isPresent()) {
			return MyMapper.parseObject(entity.get(), ProductDTO.class);
		} else {
			throw new Exception("Não foi possivel encontrar o produto de ID" + id);
		}
	}

	@Override
	public List<ProductDTO> findMostExpansivesInStock(Long stockId) throws Exception {
		var stock = stockRepositories.findById(stockId);
		if (stock.isPresent() && stock.get().isStockFull()) {
			Stock entity = stock.get();
			List<Product> allProducts = entity.getProductsInStock().stream()
					.sorted(Comparator.comparingDouble(Product::getPrice)).collect(Collectors.toList());

			var prod = MyMapper.parseListObjects(allProducts, ProductDTO.class);
			return prod;

		} else {
			throw new Exception("O estoque não foi encontrado, verifique os dados e tente novamente!");
		}
	}

	@Override
	public List<ProductDTO> findLessExpansivesInStock(Long stockId) throws Exception {
		var stock = stockRepositories.findById(stockId);
		if (stock.isPresent() && stock.get().isStockFull()) {
			Stock stok = stock.get();
			List<Product> products = stok.getProductsInStock().stream()
					.sorted(Comparator.comparingDouble(Product::getPrice).reversed()).toList();
			var dto = MyMapper.parseListObjects(products, ProductDTO.class);
			return dto;
		} else {
			throw new Exception("Estoque não pode ser encontrado ou está vazio!");
		}
	}

	@Override
	public List<ProductDTO> findMostSalleds(Long stockId) {
		List<Sale> sales = saleRepositories.findAll();

		Map<Product, Integer> productFrequency = new HashMap<>();
		for (Sale sale : sales) {
			List<Product> products = sale.getItems();
			for (Product items : products) {
				productFrequency.put(items, productFrequency.getOrDefault(items, 0) + 1);
			}
		}

		List<Product> mostSoldProducts = productFrequency.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).map(Map.Entry::getKey)
				.collect(Collectors.toList());

		return MyMapper.parseListObjects(mostSoldProducts, ProductDTO.class);

	}

	@Override
	public ProductDTO registerProductInStock(ProductDTO newProduct, Long stockId) throws Exception {
		var stock = stockRepositories.findById(stockId);
		if (newProduct != null && stock.isPresent() && !stock.get().isStockFull()) {
			Stock entity = stock.get();
			Product prod = MyMapper.parseObject(newProduct, Product.class);
			entity.getProductsInStock().add(prod);
			stockRepositories.save(entity);
			productRepositories.save(prod);
			return MyMapper.parseObject(prod, ProductDTO.class);
		} else {
			throw new Exception("Verifique os dados inseridos e tente novamente!");
		}
	}

	@Override
	public ProductDTO updateProductInStock(ProductDTO product, Long stockId) throws Exception {
		var stock = stockRepositories.findById(stockId);
		var prod = productRepositories.findById(product.getId());
		if (stock.isPresent() && prod.isPresent()) {
			Product prd = prod.get();
			if (prd.getStock().getId().equals(stockId)) { // Verifica se o produto pertence ao estoque especificado
				prd.setName(product.getName());
				prd.setHasInStock(product.isHasInStock());
				prd.setPrice(product.getPrice());
				prd.setQuantity(product.getQuantity());
				productRepositories.save(prd); // Atualiza o produto no banco de dados
				return MyMapper.parseObject(prd, ProductDTO.class); // Retorna o produto atualizado
			} else {
				throw new Exception("O produto não pertence ao estoque especificado");
			}
		} else {
			throw new NoSuchElementException("Produto ou estoque não encontrado");
		}
	}

	@Override
	public void deleteProductByIdInStock(Long id, Long stockId) {
	   var optionalStock = stockRepositories.findById(stockId);
	   var optionalProduct = productRepositories.findById(id);
	    if (optionalStock.isPresent() && optionalProduct.isPresent()) {
	        Stock stock = optionalStock.get();
	        Product product = optionalProduct.get();
	        if (product.getStock().getId().equals(stockId)) {
	            stock.getProductsInStock().remove(product);
	            stockRepositories.save(stock);
	        } else {
	            throw new IllegalArgumentException("O produto não pertence ao estoque especificado");
	        }
	    } else {
	        throw new NoSuchElementException("Produto ou estoque não encontrado");
	    }
	}


	@Override
	public boolean verifyInStock(Long stockId, Long productId) {
		// TODO Auto-generated method stub
		return false;
	}

}
