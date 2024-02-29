package com.devPontes.api.v1.services.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devPontes.api.v1.model.dtos.ClientDTO;
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
	public SaleDTO registerNewSale(SaleDTO newsale, Long stockId, Long sellerId) throws Exception {
		Sale newSale = MyMapper.parseObject(newsale, Sale.class);
		if (processSale(newsale, sellerId, stockId) && newSale != null) {
			return MyMapper.parseObject(newSale, SaleDTO.class);
		} else {
			throw new Exception("A venda não foi processada, tente novamente!");
		}
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
	@Transactional
    public boolean processSale(SaleDTO newSale, Long sellerId, Long stockId) throws Exception {
            Sale sale = MyMapper.parseObject(newSale, Sale.class);
            if(sale != null) {
            	var existStock = stockRepositories.findById(stockId);
            	if(existStock.isPresent()) {
            		Stock stock = existStock.get();
            		for(Product item : stock.getProductsInStock()) {    //Verificar produtos de um determinado estoque, verificar se na venda os items estejam disponiveis no estoque
            			List<Long> itemsId = sale.getItems().stream().map(Product::getId).toList();
            		}
            	}
            	var itemsInSale = sale.getItems();
            }
            
            return true;
	}
}
