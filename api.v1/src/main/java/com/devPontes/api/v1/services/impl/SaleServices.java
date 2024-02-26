package com.devPontes.api.v1.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devPontes.api.v1.model.dtos.ProductDTO;
import com.devPontes.api.v1.model.dtos.SaleDTO;
import com.devPontes.api.v1.model.dtos.SellerDTO;
import com.devPontes.api.v1.model.entities.Product;
import com.devPontes.api.v1.model.entities.Sale;
import com.devPontes.api.v1.model.entities.Stock;
import com.devPontes.api.v1.model.mapper.MyMapper;
import com.devPontes.api.v1.repositories.ProductRepositories;
import com.devPontes.api.v1.repositories.SaleRepositories;
import com.devPontes.api.v1.repositories.SellerRepositories;
import com.devPontes.api.v1.repositories.StockRepositories;
import com.devPontes.api.v1.services.SaleManagment;

@Service
public class SaleServices implements SaleManagment {

	@Autowired
	private SellerRepositories sellerRepositories;

	@Autowired
	private StockRepositories stockRepositories;

	@Autowired
	private ProductRepositories productsRepositories;

	@Autowired
	private SaleRepositories salesRepositories;

	@Override
	public SaleDTO findOneSaleById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SaleDTO> findAllSales() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SaleDTO> findMostExpansivesSale() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SaleDTO> findBestSeller(SellerDTO seller) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SaleDTO> findLessExpansivesSale() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SaleDTO> findSalesByDate(LocalDate be, LocalDate tween) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SaleDTO> findWhoMounthHaveMoreSale(LocalDate be, LocalDate tween) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SaleDTO updateExistentSale(SaleDTO Sale) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteExistentSale(Long id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public SaleDTO registerNewSale(SaleDTO newsale, Long stockId) throws Exception {
	    if (newsale == null)
	        throw new Exception("Não foi possível registrar uma nova venda, verifique os dados e tente novamente!");
	    // Parse da SaleDTO para Sale
	    Sale sale = MyMapper.parseObject(newsale, Sale.class);
	    List<Long> productIds = sale.getItems().stream().mapToLong(Product::getId).boxed().toList();
	    List<Product> products = productsRepositories.findAllById(productIds);

	    if (products.size() != productIds.size()) {
	        throw new Exception("Alguns produtos não foram encontrados no estoque!"); // Verificando se todos os produtos foram encontrados
	    }

	    var stockOptional = stockRepositories.findById(stockId);
	    if (stockOptional.isEmpty()) throw new Exception("Estoque não encontrado!"); // Buscando o estoque pelo ID

	    Stock stock = stockOptional.get();

	    // Verifica se todos os produtos da venda estão disponíveis no estoque
	    for (Product product : products) {
	        if (!stock.getProductsInStock().contains(product)) {
	            throw new Exception("Produto não disponível no estoque: " + product.getName());
	        }
	    }

	    // Associa os produtos ao estoque e calcula o valor total da venda
	    for (Product product : products) {
	        product.setStock(stock);
	    }
	    sale.setItems(products);
	    sale.setValue(products.stream().mapToDouble(p -> p.getPrice() * p.getQuantity()).sum());

	    // Atualiza a quantidade de produtos no estoque após a venda
	    for (Product product : products) {
	        product.setQuantity(product.getQuantity() - product.getStock().getProductsInStock().stream().mapToInt(Product::getQuantity).sum());
	    }

	    // Salva a venda e retorna uma SaleDTO
	    Sale savedSale = salesRepositories.save(sale);
	    return MyMapper.parseObject(savedSale, SaleDTO.class);
	}

}