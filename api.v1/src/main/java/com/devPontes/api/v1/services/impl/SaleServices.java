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
        Sale sale = MyMapper.parseObject(newSale, Sale.class);
        if (sale == null)
            throw new Exception("Erro ao mapear SaleDTO para entidade Sale");

        Optional<Client> existentCli = clientRepositories.findById(clientId);
        Optional<Seller> existentSeller = sellerRepositories.findById(sellerId);
        Optional<Stock> existentStock = stockRepositories.findById(stockId);

        if (!existentCli.isPresent()) {
            logger.warn("Cliente com ID {} não encontrado. Atribuindo `null` ao campo `clientWhoBuy` da venda.",
                    clientId);
            throw new Exception("Cliente com ID " + clientId + " não encontrado.");
        }

        if (!existentSeller.isPresent()) {
            logger.warn("Vendedor com ID {} não encontrado. Atribuindo `null` ao campo `sellerWhoSale` da venda.",
                    sellerId);
            throw new Exception("Vendedor com ID " + sellerId + " não encontrado.");
        }

        if (!existentStock.isPresent()) {
            throw new Exception("Estoque com ID " + stockId + " não encontrado.");
        }

        Sale processed = processSale(sale, existentStock.get());

        if (processed.isCompleted()) {
            return MyMapper.parseObject(processed, SaleDTO.class);
        } else {
            throw new Exception("Venda não pode ser processada ou registrada!");
        }
    }

    public Sale processSale(Sale sale, Stock stock) throws Exception {
        boolean isComplete = false;
        sale.setMoment(Instant.now());
        Set<Long> productIdsInSale = sale.getItems().stream().map(Product::getId).collect(Collectors.toSet());
        Set<Long> productIdsInStock = stock.getProductsInStock().stream().map(Product::getId)
                .collect(Collectors.toSet());

        if (productIdsInStock.containsAll(productIdsInSale)) {
            for (Product saleProduct : sale.getItems()) {
                Product stockProduct = stock.getProductsInStock().stream()
                        .filter(product -> product.getId() == (saleProduct.getId())).findFirst().orElseThrow(
                                () -> new Exception("Produto não encontrado no estoque: " + saleProduct.getName()));

                int remainingQuantity = stockProduct.getQuantity() - saleProduct.getQuantity();
                if (remainingQuantity < 0) {
                    throw new Exception(
                            "Quantidade insuficiente do produto no estoque: " + stockProduct.getName());
                }

                stockProduct.setQuantity(remainingQuantity);
                sale.setTotalValueOfsale(
                        sale.getItems().stream().mapToDouble(Product::getPrice).sum());
                isComplete = true;
                sale.setCompleted(isComplete);
                saleRepositories.save(sale);
                stockRepositories.save(stock);
            }
        }
        return sale;
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
	public boolean updateStockLevels(Sale sale, Long stockId) {
		// TODO Auto-generated method stub
		return false;
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

    // Métodos restantes omitidos por brevidade
}
