package com.devPontes.api.v1.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
		for(Sale sale : sales) {
			List<Product> products = sale.getItems();
			for(Product items : products) {
				productFrequency.put(items, productFrequency.getOrDefault(items, 0) +1);
			}
		}
		
	    List<Product> mostSoldProducts = productFrequency.entrySet().stream()
	            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
	            .map(Map.Entry::getKey)
	            .collect(Collectors.toList());

	        return MyMapper.parseListObjects(mostSoldProducts, ProductDTO.class);
		
	}

	@Override
	public ProductDTO registerProductInStock(ProductDTO newProduct, Long stockId) throws Exception {
		var stock = stockRepositories.findById(stockId);
		if(newProduct != null && stock.isPresent() && !stock.get().isStockFull()) {
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
	public ProductDTO updateProductInStock(ProductDTO product, Long stockId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProductByIdInStock(Long id, Long stockId) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean verifyInStock(Long stockId, Long productId) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
