package com.devPontes.api.v1.services.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devPontes.api.v1.model.dtos.ClientDTO;
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

	@Override
	public SaleDTO registerNewSale(SaleDTO newsale, Long stockId, Long sellerId) throws Exception {
		Sale newSale = MyMapper.parseObject(newsale, Sale.class);
		if (newSale == null) throw new Exception("Não foi possível registrar essa venda!");
		if(processSale(Instant.now(), newsale, sellerId, stockId)) return MyMapper.parseObject(newSale, SaleDTO.class);
		throw new Exception("A venda não foi processada, tente novamente!");
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

	@Transactional
	public boolean processSale(Instant moment, SaleDTO newSale, Long sellerId, Long stockId) throws Exception {
	    // Mapear as entidades vindas do Json
        Stock newStock = stockRepositories.findById(stockId).orElseThrow(() -> new Exception("Estoque não encontrado!"));
        Seller seller = sellerRepositories.findById(sellerId).orElseThrow(() -> new Exception("Vendedor não encontrado!"));
        Sale sale = MyMapper.parseObject(newSale, Sale.class);
        
        if (sale == null || !newStock.isStockFull()) throw new Exception("Não foi possível registrar essa venda!");
        
        // Verificar se o cliente já existe no sistema
        ClientDTO clientDTO = newSale.getClientWhoBuy();
        Client client = null;
        if (clientDTO != null && clientDTO.getId() != null) client = clientsRepo.findById(clientDTO.getId()).orElse(null);
        if (client == null) {
            client = MyMapper.parseObject(clientDTO, Client.class);
            client = clientsRepo.save(client);
        }
        sale.setClientWhoBuy(client);
	   
        boolean allItemsAvailable = sale.getItems().stream().allMatch(item -> productsRepositories.findById(item.getId()).isPresent());
	  
	    if (allItemsAvailable && sale.getTotalValueOfsale() != 0.0) {
	        sale.setItems(sale.getItems());
	        sale.setCompleted(true);
	        Integer capacity = newStock.getCurrentCapacity();
	        Integer newCapacityAfterSale = capacity - sale.getItems().stream().mapToInt(Product::getQuantity).sum();
	        newStock.setCurrentCapacity(newCapacityAfterSale);
	        salesRepo.save(sale);
	        return true;
	    } else {
	        throw new Exception("Alguns itens da venda não estão disponíveis no estoque!");
	    }
	}



}