package com.devPontes.api.v1.services.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devPontes.api.v1.model.dtos.SaleDTO;
import com.devPontes.api.v1.model.entities.Client;
import com.devPontes.api.v1.model.entities.Product;
import com.devPontes.api.v1.model.entities.Sale;
import com.devPontes.api.v1.model.entities.Seller;
import com.devPontes.api.v1.model.entities.Stock;
import com.devPontes.api.v1.model.mapper.MyMapper;
import com.devPontes.api.v1.repositories.ClientRepositories;
import com.devPontes.api.v1.repositories.SaleRepositories;
import com.devPontes.api.v1.repositories.SellerRepositories;
import com.devPontes.api.v1.repositories.StockRepositories;
import com.devPontes.api.v1.services.SaleManagment;

@Service
public class SaleServices implements SaleManagment {

    @Autowired
    private SaleRepositories saleRepositories;

    @Autowired
    private StockRepositories stockRepositories;

    @Autowired
    private SellerRepositories sellerRepositories;

    @Autowired
    private ClientRepositories clientRepositories;

    private final Logger logger = LoggerFactory.getLogger(SaleServices.class);

    @Override
    public SaleDTO registerNewSale(SaleDTO newSale, Long clientId, Long sellerId, Long stockId) throws Exception {
      Sale sale = mapperEntities(newSale, clientId, sellerId, stockId);  // Processe a venda somente se ela não estiver marcada como concluída
      if (!sale.isCompleted()) {
        processSale(sale, stockRepositories.findById(stockId).get());
      }
      return MyMapper.parseObject(sale, SaleDTO.class);
    }

    private Sale mapperEntities(SaleDTO newSale, Long clientId, Long sellerId, Long stockId) throws Exception {
        Optional<Client> existentCli = clientRepositories.findById(clientId);
        Optional<Seller> existentSeller = sellerRepositories.findById(sellerId);
        Optional<Stock> existentStock = stockRepositories.findById(stockId);
        if (existentCli.isPresent() && existentSeller.isPresent() && existentStock.isPresent()) {
            Sale sale = MyMapper.parseObject(newSale, Sale.class);
            sale.setClientWhoBuy(existentCli.get());
            sale.setSellerWhoSale(existentSeller.get());
            processSale(sale, existentStock.get());
            return sale;
        }
        throw new Exception("Cliente, vendedor ou estoque não encontrado!");
    }

    @Override
    @Transactional
    public void processSale(Sale sale, Stock stock) throws Exception {
        sale.setMoment(Instant.now());
        Set<Long> productIdsInSale = sale.getItems().stream().map(Product::getId).collect(Collectors.toSet());
        Set<Long> productIdsInStock = stock.getProductsInStock().stream().map(Product::getId).collect(Collectors.toSet());

        if (productIdsInStock.containsAll(productIdsInSale)) {
            for (Product saleProduct : sale.getItems()) {
                Product stockProduct = stock.getProductsInStock().stream()
                        .filter(product -> product.getId().equals(saleProduct.getId()))
                        .findFirst()
                        .orElseThrow(() -> new Exception("Produto não encontrado no estoque: " + saleProduct.getName()));
                int remainingQuantity = stockProduct.getQuantity() - saleProduct.getQuantity();
                if (remainingQuantity < 0) {
                    throw new Exception("Quantidade insuficiente do produto no estoque: " + stockProduct.getName());
                }
                stockProduct.setQuantity(remainingQuantity);              
            }
            sale.setTotalValueOfsale(sale.getItems().stream().mapToDouble(Product::getPrice).sum());
            stockRepositories.save(stock);       
            saleRepositories.save(sale);
            logger.info("Venda processada com sucesso!");
        } else {
            throw new Exception("Itens da venda não estão disponíveis no estoque!");
        }     
    }

    @Override
    public SaleDTO findSaleDetails(Long id) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SaleDTO updateSale(SaleDTO Sale) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void verifyItensAndSetValue(Sale sale) throws Exception {
        // TODO Auto-generated method stub

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
    public void addFrequencyOfSellerOfSales(Long saleId, Long sellerId) {
        // TODO Auto-generated method stub

    }
    
	@Override
	public boolean updateStockLevels(Sale sale, Long stockId) {
		// TODO Auto-generated method stub
		return false;
	}
}
