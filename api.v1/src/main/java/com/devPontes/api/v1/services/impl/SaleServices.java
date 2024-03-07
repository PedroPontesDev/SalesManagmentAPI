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
	public SaleDTO registerNewSale(SaleDTO sale, Long stockId, Long sellerId, Long clientId) throws Exception {
		 Sale newSale = MyMapper.parseObject(sale, Sale.class);
		if (!verifyIfStockHasProducts(stockId, newSale)) {
	        throw new Exception("Estoque sem produtos suficientes para venda!");
	    }
	    newSale.setSellerWhoSale(sellerRepositories.findById(sellerId).get());
	    
	    //Tratamento de cliente
	    var client = clientsRepo.findById(clientId).get();
	    newSale.setClientWhoBuy(client);
	    newSale.setMoment(Instant.now());
	    var counting = sale.getItems().stream()
	    							  .mapToDouble(p -> p.getQuantity() * p.getPrice()).sum();
	    newSale.setTotalValueOfsale(counting);

	    // Chamar `processSale` para processar a venda e atualizar os níveis de estoque
	    processSale(newSale);
	    updateStockLevels(newSale, stockId);

	    return MyMapper.parseObject(newSale, SaleDTO.class);
	}


	@Override
	@Transactional
	public void processSale(Sale sale) {
	    // Persistir a venda
		if(sale != null) 
		sale.setCompleted(true);
		salesRepo.save(sale);
	}
	
	public boolean verifyIfStockHasProducts(Long stockId, Sale transSale) throws Exception{
		var entity = stockRepositories.findById(stockId);
		if (entity.isPresent()) {
			Stock stock = entity.get();
			if (!stock.getProductsInStock().isEmpty() && !transSale.getItems().isEmpty()) return true;
		}
		throw new Exception("Estoque não contem os itens da venda!");
	}
	
	@Override
	@Transactional
	public void updateStockLevels(Sale sale, Long stockId) {
		   // Obter o estoque
	    var stock = stockRepositories.findById(stockId);
	    // Iterar pelos itens da venda e adicionar uma frequencia
	    for (Product item : sale.getItems()) {
	        // Obter o produto do estoque
	        Product product = stock.get().getProductsInStock().stream()
	                .filter(p -> p.getId().equals(item.getId()))
	                .findFirst().orElse(null);

	        if (product != null) {
	            // Atualizar a quantidade do produto no estoque
	            product.setQuantity(product.getQuantity() - item.getQuantity());
	            
	        }
	    }
	    // Salvar o estoque atualizado
	    stockRepositories.save(stock.get());
	}                                          

	@Override
	public SaleDTO findOneSaleById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;                     
	}

	@Override
	public void deleteExistentSale(Long id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public SaleDTO updateExistentSale(SaleDTO Sale) throws Exception {
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
	public Set<SaleDTO> findAllSales() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFrequencyOfSellerOfSales(Long saleId) {
		// TODO Auto-generated method stub
		
	}
}
