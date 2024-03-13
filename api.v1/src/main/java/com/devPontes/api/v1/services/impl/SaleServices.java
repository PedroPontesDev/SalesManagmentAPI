package com.devPontes.api.v1.services.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    private ProductRepositories prodRepo;

    @Autowired
    private StockRepositories stockRepo;

    @Autowired
    private SaleRepositories salesRepo;

    @Autowired
    private ClientRepositories clientRepo;

    @Autowired
    private SellerRepositories sellerRepo;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public SaleDTO registerNewSale(ProductDTO productRequest, Long clientId, Long sellerId, Long stockId) throws Exception {
        Optional<Client> clientExistOptional = clientRepo.findById(clientId);
        Optional<Stock> stockExistOptional = stockRepo.findById(stockId);
        Optional<Seller> sellerExistOptional = sellerRepo.findById(sellerId);

        if (clientExistOptional.isPresent() && stockExistOptional.isPresent() && sellerExistOptional.isPresent()) {
        	
        	Client client = clientExistOptional.get();
        	
            // Criar lista de produtos a partir do ProductDTO
            List<Product> products = new ArrayList<>();
            Product product = new Product();
            Double price = productRequest.getPrice();
            if (price == null) {
                throw new IllegalArgumentException("O preço do produto não pode ser nulo.");
            }
            product.setPrice(price);
            product.setId(productRequest.getId());
            product.setQuantity(productRequest.getQuantity());
            products.add(product);

            Stock stock = stockExistOptional.get();
            boolean hasAllProductsInStock = products.stream()
                    .anyMatch(p -> stock.getProductsInStock().contains(p));
            if (hasAllProductsInStock && client != null) {
                SaleDTO processedSale = processSale(productRequest, sellerId, stockId, client);
                return processedSale;
            } else {
                throw new Exception("Um ou mais produtos não estão disponíveis no estoque.");
            }
        } else {
            throw new Exception("Não foi possível registrar a venda devido a dados ausentes.");
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public SaleDTO processSale(ProductDTO productRequest, Long sellerId, Long stockId, Client client) throws Exception {
        boolean isComplete = false;
        var stock = stockRepo.findById(stockId);
        var sellerExist = sellerRepo.findById(sellerId);
        Product product = MyMapper.parseObject(productRequest, Product.class);
        if (sellerExist.isPresent() && stock.isPresent() && product != null)  {
            Seller seller = sellerExist.get();
            Sale sale = new Sale();
            sale.setMoment(Instant.now());
            sale.setCompleted(isComplete);
            sale.setSellerWhoSale(seller);
            sale.setMoment(Instant.now());
            sale.setClientWhoBuy(client);
            
            // Buscar o produto no banco de dados com base no ID fornecido
            Optional<Product> productOptional = prodRepo.findById(productRequest.getId());
            if (productOptional.isPresent()) {
                Product storedProduct = productOptional.get();
                
                // Calcular o preço total com base na quantidade fornecida e no preço do produto no banco de dados
                double totalPrice = storedProduct.getPrice() * productRequest.getQuantity();
                
                // Configurar o preço total na venda
                sale.setTotalValueOfsale(totalPrice);
            } else {
                throw new Exception("Produto não encontrado.");
            }
         
            salesRepo.save(sale);
            return MyMapper.parseObject(sale, SaleDTO.class);
        } else {
            throw new Exception("Vendedor não encontrado.");
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
	public boolean verifyIfHasProductsToSale(List<Long> saleItems, List<Long> inDbItems) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateStockLevels(Sale sale, Long stockId) {
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

    // Outros métodos da classe...
}
