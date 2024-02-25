package com.devPontes.api.v1.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.devPontes.api.v1.model.dtos.ProductDTO;
import com.devPontes.api.v1.model.dtos.SaleDTO;
import com.devPontes.api.v1.model.entities.Product;
import com.devPontes.api.v1.model.entities.Sale;
import com.devPontes.api.v1.model.entities.Stock;
import com.devPontes.api.v1.model.mapper.MyMapper;
import com.devPontes.api.v1.repositories.ClientRepositories;
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

	@Autowired
	private ClientRepositories clientesRepositories;

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
	public SaleDTO registerNewSale(SaleDTO sale) throws Exception {
	    if (sale == null || sale.getItems() == null || sale.getItems().isEmpty()) {
	        throw new Exception("Não foi possível registrar uma nova venda, verifique os dados e tente novamente!");
	    } else {
	        // Obtendo os IDs dos produtos a serem vendidos
	        List<Long> productIds = sale.getItems().stream()
	                                .map(ProductDTO::getId)
	                                .collect(Collectors.toList());

	        // Verificando se os produtos existem no estoque
	        List<Product> products = productsRepositories.findAllById(productIds);
	        if (products.size() != sale.getItems().size()) {
	            throw new Exception("Alguns produtos não foram encontrados no estoque!");
	        }

	        // Calculando o valor total da venda
	        Double totalValue = sale.getItems().stream()
	                                .mapToDouble(item -> item.getPrice() * item.getQuantity())
	                                .sum();

	        // Criando a venda
	        Sale entity = new Sale();
	        entity.setMoment(LocalDateTime.now());
	        entity.setItems(products);
	        entity.setValue(totalValue);

	        // Salvando a venda no banco de dados
	        salesRepositories.save(entity);

	        // Atualizando o estoque
	        int totalQuantitySold = 0;
	        for (Product product : products) {
	        	totalQuantitySold += product.getQuantity();
	            Stock stock = product.getStock();
	            if (totalQuantitySold > stock.getCurrentCapacity()) {
	                throw new Exception("A quantidade de produtos vendidos é maior que a quantidade disponível no estoque!");
	            }
	            stock.setCurrentCapacity(stock.getCurrentCapacity() - product.getQuantity());
	            stockRepositories.save(stock);
	        }

	        return MyMapper.parseObject(entity, SaleDTO.class);
	    }
	}


	@Override
	public SaleDTO updateExistentSale(SaleDTO sale) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteExistentSale(Long id) throws Exception {
		// TODO Auto-generated method stub

	}

}