package com.devPontes.api.v1.services.impl;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devPontes.api.v1.model.dtos.SaleDTO;
import com.devPontes.api.v1.model.entities.Sale;
import com.devPontes.api.v1.model.entities.Seller;
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
	public SaleDTO registerNewSale(SaleDTO newsale, Long stockId, Long clientId, Long sellerId) throws Exception {
			Sale newSale = MyMapper.parseObject(newsale, Sale.class);
			Stock newStock = stockRepositories.findById(stockId).orElseThrow(Exception::new);
			if(newSale == null && newStock == null)throw new Exception("Não foi possivel registrar essa venda!");
			Optional<Seller> ifSellerExsits = sellerRepositories.findById(sellerId);
			
			Sale.initTransaction(newSale.getMoment(), newSale, null, null, null);
			
			
			return null;
			
	}
	@Override
	public SaleDTO findOneSaleById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SaleDTO> findAllSales() throws Exception {
		Set<Sale> allSalesInSystem = salesRepositories.saleSet();
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