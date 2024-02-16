package com.devPontes.api.v1.services.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.devPontes.api.v1.model.dtos.ProductDTO;
import com.devPontes.api.v1.model.dtos.SellerDTO;
import com.devPontes.api.v1.model.entities.Product;
import com.devPontes.api.v1.model.entities.Stock;
import com.devPontes.api.v1.model.mapper.MyMapper;
import com.devPontes.api.v1.repositories.ProductRepositories;
import com.devPontes.api.v1.repositories.StockRepositories;
import com.devPontes.api.v1.services.ProductManagment;


public class ProductServices implements ProductManagment{

	@Autowired
	private ProductRepositories productRepositories;

	@Autowired
	private StockRepositories stockRepositories;

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
	public List<ProductDTO> findMostExpansivesInStock(Long stockId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDTO> findLessExpansivesInStock(Long stockId) throws Exception {
		var stock = stockRepositories.findById(stockId);
		if(stock.isPresent()) {
			Stock stok = stock.get(); 
			List<Product> products = stok.getProductsInStock()
											.stream()
											.sorted(Comparator.comparingDouble(Product::getPrice))
											.collect(Collectors.toList());
			var dto = MyMapper.parseListObjects(products, ProductDTO.class);
			return dto;					
		} else {
			throw new Exception("");
		}
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
