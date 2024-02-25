package com.devPontes.api.v1.services.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.devPontes.api.v1.model.dtos.SaleDTO;
import com.devPontes.api.v1.model.entities.Product;
import com.devPontes.api.v1.model.entities.Sale;
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
	    if(sale == null || sale.getItems().isEmpty()) throw new Exception("Não foi possível registrar uma nova venda, verifique os dados e tente novamente!");
	    else {
	        var entity = MyMapper.parseObject(sale, Sale.class);
	        // Obter o cliente e vendedor da base de dados
	        var client = clientesRepositories.findById(sale.getClientWhoBuyId());
	        var seller = sellersRepository.findById(sale.getSellerId()).orElseThrow(() -> new Exception("Vendedor não encontrado"));
	        entity.setClientWhoBuy(client);
	        entity.setSellerWhoSale(seller);

	        // Calcular o valor total da venda com base nos produtos e quantidades
	        double totalValue = entity.getItems().stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();
	        entity.setValue(totalValue);

	        // Salvar a venda
	        var savedEntity = salesRepository.save(entity);

	        // Retornar a venda salva
	        return MyMapper.parseObject(savedEntity, SaleDTO.class);
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