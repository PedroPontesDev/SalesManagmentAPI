package com.devPontes.api.v1.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devPontes.api.v1.model.dtos.ProductDTO;
import com.devPontes.api.v1.model.entities.Product;
import com.devPontes.api.v1.model.entities.Stock;
import com.devPontes.api.v1.model.mapper.MyMapper;
import com.devPontes.api.v1.repositories.ProductRepositories;
import com.devPontes.api.v1.repositories.SaleRepositories;
import com.devPontes.api.v1.repositories.StockRepositories;
import com.devPontes.api.v1.services.ProductManagment;

@Service
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
	public List<ProductDTO> findLessExpansivesInStock(Long stockId) throws Exception {
		var stock = stockRepositories.findById(stockId);
		if (stock.isPresent()) {
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
	public List<ProductDTO> findMostExpansivesInStock(Long stockId) throws Exception {
		var stock = stockRepositories.findById(stockId);
		if (stock.isPresent()) {
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
	public ProductDTO registerProductInStock(ProductDTO newProduct, Long stockId) throws Exception {
		var stock = stockRepositories.findById(stockId);
		if (newProduct != null && stock.isPresent() && !stock.get().isStockFull()) {
			Stock entity = stock.get();
			Product prod = MyMapper.parseObject(newProduct, Product.class);
			prod.setStock(entity);
			prod.setHasInStock(true);
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
		var optionalStock = stockRepositories.findById(stockId);
		var optionalProduct = productRepositories.findById(productId);
		if (optionalStock.isPresent() && optionalProduct.isPresent()) {
			Stock stock = optionalStock.get();
			Product product = optionalProduct.get();
			if (product.getStock().getId().equals(stockId)) { // Verifica se o produto pertence ao estoque especificado
				return product.isHasInStock(); // Retorna true se o produto estiver em estoque ou false se não estiver
			} else {
				throw new IllegalArgumentException("O produto não pertence ao estoque especificado");
			}
		} else {
			throw new NoSuchElementException("Produto ou estoque não encontrado");
		}
	}

	@Override
	public List<ProductDTO> findAll() throws Exception {
		var prods = productRepositories.findAll();
		if (prods != null) {
			return MyMapper.parseListObjects(prods, ProductDTO.class);
		} else {
			throw new Exception("Não foi possivel recuperar todos produtos");
		}
	}

	@Override
	public List<ProductDTO> findTop5MostFrequentProducts() throws Exception {
		List<Object[]> results = saleRepositories.findTop5MostFrequentProduct();
		List<ProductDTO> dtos = new ArrayList<>();
		for (Object[] result : results) {
			Long productId = (Long) result[0];
			String productName = (String) result[1];
			Double productPrice = (Double) result[2];
			Long frequency = (Long) result[3];

			ProductDTO dto = new ProductDTO(productId, productName, productPrice, null, false, null, null, frequency);
			dtos.add(dto);
		}

		return dtos;
	}
}
