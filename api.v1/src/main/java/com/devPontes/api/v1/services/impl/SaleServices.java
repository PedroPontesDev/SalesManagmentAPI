package com.devPontes.api.v1.services.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devPontes.api.v1.model.dtos.SaleDTO;
import com.devPontes.api.v1.model.dtos.SellerInSaleDTO;
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
public class SaleServices implements SaleManagment{

    @Autowired
    private ProductRepositories prodRepo;

    @Autowired
    private StockRepositories stockRepo;

    @Autowired
    private SaleRepositories salesRepo;

    @Autowired
    private ClientRepositories clientRepo;

    @Autowired
    private SellerRepositories sellerRepo;

    @Transactional
    public SaleDTO registerNewSale(SaleDTO newSale, Long clientId, Long sellerId, Long stockId) throws Exception {
        Optional<Client> clientExistOptional = clientRepo.findById(clientId);
        Optional<Stock> stockExistOptional = stockRepo.findById(stockId);
        Optional<Seller> sellerExistOptional = sellerRepo.findById(sellerId);

        if (clientExistOptional.isPresent() && stockExistOptional.isPresent() && sellerExistOptional.isPresent()) {
            Sale sale = MyMapper.parseObject(newSale, Sale.class);
            sale.setClientWhoBuy(clientExistOptional.get());
            sale.setMoment(Instant.now());
            
            // Verificando se os produtos estão disponíveis no estoque
            List<Long> productIds = newSale.getItems().stream().map(item -> item.getId()).collect(Collectors.toList());
            List<Product> products = prodRepo.findAllById(productIds);
            Stock stock = stockExistOptional.get();
            boolean hasAllProductsInStock = products.stream().allMatch(product -> stock.getProductsInStock().contains(product));

            if (hasAllProductsInStock) {
                sale.setItems(new ArrayList<>(products));
                // Chamando o método processSale para finalizar as operações lógicas
                SaleDTO processedSale = processSale(sale, sellerId, stockId);
                return processedSale;
            } else {
                throw new Exception("Um ou mais produtos não estão disponíveis no estoque.");
            }
        } else {
            throw new Exception("Não foi possível registrar a venda devido a dados ausentes.");
        }
    }

    @Transactional
    public SaleDTO processSale(Sale sale, Long sellerId, Long stockId) throws Exception {
        Optional<Seller> sellerExistOptional = sellerRepo.findById(sellerId);
        if (sellerExistOptional.isPresent()) {
            Double total = sale.getItems().stream().mapToDouble(p -> p.getQuantity() * p.getPrice()).sum();
            sale.setTotalValueOfsale(total);

            Seller seller = sellerExistOptional.get();

            SellerInSaleDTO sellerInternalDTO = new SellerInSaleDTO(
                seller.getId(),
                seller.getUsername(),
                seller.getEmail(),
                seller.getFullName()
            );
            sale.setSellerWhoSale(MyMapper.parseObject(sellerInternalDTO, Seller.class));

            salesRepo.save(sale);

            return MyMapper.parseObject(sale, SaleDTO.class);
        } else {
            throw new Exception("Vendedor não encontrado.");
        }
    }

	@Override
	public boolean verifyIfHasProductsToSale(List<Long> saleItems, List<Long> inDbItems) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateStockLevels(Sale sale, Long stockId) {
		// TODO Auto-generated method stub
		
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
	public SaleDTO processSale(SaleDTO newSale, Long sellerId, Long stockId) throws Exception {
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
}
