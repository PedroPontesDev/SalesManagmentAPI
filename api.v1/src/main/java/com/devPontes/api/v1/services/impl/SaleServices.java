package com.devPontes.api.v1.services.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devPontes.api.v1.model.dtos.ProductDTO;
import com.devPontes.api.v1.model.dtos.SaleDTO;
import com.devPontes.api.v1.model.dtos.StockDTO;
import com.devPontes.api.v1.model.entities.Product;
import com.devPontes.api.v1.model.entities.Sale;
import com.devPontes.api.v1.model.entities.Stock;
import com.devPontes.api.v1.model.mapper.MyMapper;
import com.devPontes.api.v1.repositories.ProductRepositories;
import com.devPontes.api.v1.repositories.SaleRepositories;
import com.devPontes.api.v1.repositories.StockRepositories;
import com.devPontes.api.v1.services.SaleManagment;
import com.devPontes.api.v1.services.StockManagment;

@Service
public class SaleServices implements SaleManagment {

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
	public SaleDTO registerNewSale(SaleDTO sale) throws Exception {
		if(sale == null) throw new Exception("Não foi possivel registrar uma nova venda, verifique os dados e tente novamente!");
		else {
			var entity = MyMapper.parseObject(sale, Sale.class);
			salesRepositories.save(entity);
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