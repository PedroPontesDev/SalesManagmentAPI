package com.devPontes.api.v1.services.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devPontes.api.v1.model.dtos.ProductDTO;
import com.devPontes.api.v1.model.dtos.SaleDTO;
import com.devPontes.api.v1.model.entities.Client;
import com.devPontes.api.v1.model.entities.Product;
import com.devPontes.api.v1.model.entities.Sale;
import com.devPontes.api.v1.model.entities.Seller;
import com.devPontes.api.v1.model.entities.Stock;
import com.devPontes.api.v1.model.mapper.MyMapper;
import com.devPontes.api.v1.repositories.ClientRepositories;
import com.devPontes.api.v1.repositories.ProductRepositories;
import com.devPontes.api.v1.repositories.SaleRepositories;
import com.devPontes.api.v1.repositories.SellerRepositories;
import com.devPontes.api.v1.repositories.StockRepositories;
import com.devPontes.api.v1.services.SaleManagment;

import jakarta.transaction.Transactional;

@Service
public class SaleServices implements SaleManagment {

	@Autowired
	private SellerRepositories sellerRepositories;

	@Autowired
	private StockRepositories stockRepositories;

	@Autowired
	private ProductRepositories productsRepositories;

	@Autowired
	private SaleRepositories salesRepo;

	@Autowired
	private ClientRepositories clientsRepo;

	private final Logger logger = LoggerFactory.getLogger(SaleServices.class);

	@Override
	@Transactional
	public SaleDTO registerNewSale(SaleDTO saleDTO, Long stockId, Long sellerId, Long clientId) throws Exception {
		Sale sale = MyMapper.parseObject(saleDTO, Sale.class);
		if (!verifyIfStockHasProducts(stockId, sale)) {
			throw new Exception("Estoque sem produtos suficientes para venda!");
		}
		sale.setSellerWhoSale(sellerRepositories.findById(sellerId)
				.orElseThrow(() -> new IllegalArgumentException("Vendedor não encontrado")));

		// Tratamento de cliente
		Client client = clientsRepo.findById(clientId)
				.orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
		sale.setClientWhoBuy(client);  // Chamar `processSale` para processar a venda e atualizar os níveis de estoque
		sale.setMoment(Instant.now());
		double totalValue = sale.getItems().stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();
		sale.setTotalValueOfsale(totalValue);
	
		verifyIfStockHasProducts(stockId, sale);
		updateStockLevels(sale, stockId);
		processSale(sale);

		return MyMapper.parseObject(sale, SaleDTO.class);
	}

	@Override
	@Transactional
	public void processSale(Sale sale) {
		// Persistir a venda
		if (sale != null) {
			sale.setCompleted(true);
			salesRepo.save(sale);
		}
	}
	public boolean verifyIfStockHasProducts(Long stockId, Sale sale) throws Exception {
	    Stock stock = stockRepositories.findById(stockId).get();
	    if (stock.getProductsInStock().isEmpty()) {
	        throw new Exception("Estoque vazio");
	    }
	    for (Product item : stock.getProductsInStock()) {
	        Optional<Product> productInStock = sale.getItems().stream()
	                .filter(product -> product.getId().equals(item.getId()))
	                .findFirst();
	        if (productInStock.isEmpty() || productInStock.get().getQuantity() < item.getQuantity()) {
	            throw new Exception("Não há quantidade disponível para o produto com ID " + item.getId());
	        }
	    }
	    // Remove the line that adds item to sale.getItems()
	    // This method is for verification only, not for adding items to sale
	    return true;
	}
	@Transactional
	public void updateStockLevels(Sale sale, Long stockId) {
	    Optional<Stock> optionalStock = stockRepositories.findById(stockId);
	    optionalStock.ifPresent(stock -> {
	        // Filtrar produtos do estoque que estão presentes na venda
	        List<Product> productsToUpdate = stock.getProductsInStock().stream()
	                .filter(product -> sale.getItems().stream().anyMatch(item -> item.getId().equals(product.getId())))
	                .collect(Collectors.toList());

	        // Atualizar quantidades de produtos no estoque
	        for (Product product : productsToUpdate) {
	            Optional<Product> productItem = sale.getItems().stream()
	                    .filter(item -> item.getId().equals(product.getId()))
	                    .findFirst();
	            if (productItem.isPresent()) {
	                product.setQuantity(product.getQuantity() - productItem.get().getQuantity());
	                // Validar se a quantidade atual é suficiente (opcional)
	            }
	        }

	        // Salvar o estoque atualizado
	        stockRepositories.save(stock);
	    });
	}

	@Override
	public SaleDTO findOneSaleById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SaleDTO updateExistentSale(SaleDTO Sale) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SaleDTO> findAllSales() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SaleDTO> findWhoMounthHaveMoreSale(SaleDTO sale, LocalDate now, LocalDate datesOfSale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SaleDTO> findSalesByDate(SaleDTO sale, LocalDate be, LocalDate tween) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteExistentSale(Long id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void addFrequencyOfSellerOfSales(Long saleId) {
		// TODO Auto-generated method stub

	}

	// Métodos não implementados omitidos para brevidade
}
