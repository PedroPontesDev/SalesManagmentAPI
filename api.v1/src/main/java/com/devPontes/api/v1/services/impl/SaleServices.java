package com.devPontes.api.v1.services.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devPontes.api.v1.model.dtos.SaleDTO;
import com.devPontes.api.v1.model.entities.Client;
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
	public SaleDTO registerNewSale(SaleDTO newsale, Long stockId, Long clientId, Long sellerId) throws Exception {
	    Sale newSale = MyMapper.parseObject(newsale, Sale.class);
	    if (newSale == null) {
	        throw new Exception("Não foi possível registrar essa venda!");
	    }
	    
	    Stock newStock = stockRepositories.findById(stockId).orElseThrow(() -> new Exception("Estoque não encontrado!"));
	    Client client = clientsRepo.findById(clientId).orElseThrow(() -> new Exception("Cliente não encontrado!"));
	    Seller seller = sellerRepositories.findById(sellerId).orElseThrow(() -> new Exception("Vendedor não encontrado!"));
	    
	    if(newSale.getMoment().isBefore(Instant.now())) throw new Exception("Momento de venda inválido!");
	    
	    if (Sale.initTransaction(newSale.getMoment(), newSale, seller, client, newStock)) {
	        newSale.setClientWhoBuy(client);
	        newSale.setSellerWhoSale(seller);
	        newSale.setCompleted(true);
	        newSale.setTotalValueOfsale(newSale.getTotalValueOfsale());
	        newSale.setItems(newStock.getProductsInStock());
	        
	        Sale savedSale = salesRepo.save(newSale);
	        return MyMapper.parseObject(savedSale, SaleDTO.class);
	    } else {
	        throw new Exception("Não foi possível processar a venda!");
	    }
	}

	@Override
	public SaleDTO findOneSaleById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SaleDTO> findAllSales() throws Exception {
		Set<Sale> allSalesInSystem = salesRepo.saleSet();
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

}