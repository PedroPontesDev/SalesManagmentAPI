package com.devPontes.api.v1.services.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devPontes.api.v1.model.dtos.SaleDTO;
import com.devPontes.api.v1.model.dtos.SellerDTO;
import com.devPontes.api.v1.model.dtos.SellerInSaleDTO;
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
    public SaleDTO registerNewSale(SaleDTO newSale, Long clientId, Long sellerId, Long stockId) throws Exception {
        Optional<Client> clientExistOptional = clientRepo.findById(clientId);
        Optional<Stock> stockExistOptional = stockRepo.findById(stockId);
        Optional<Seller> sellerExistOptional = sellerRepo.findById(sellerId); // Corrigido para usar sellerId
        if (clientExistOptional.isPresent() && stockExistOptional.isPresent() && sellerExistOptional.isPresent()) {
            Sale sale = MyMapper.parseObject(newSale, Sale.class);
            sale.setClientWhoBuy(clientExistOptional.get());
            sale.setMoment(Instant.now());

            // Convertendo para DTO interno para uso interno no sistema
            SellerInSaleDTO sellerInternalDTO = new SellerInSaleDTO(
                sellerExistOptional.get().getId(),
                sellerExistOptional.get().getUsername(),
                sellerExistOptional.get().getEmail(),
                sellerExistOptional.get().getFullName()
            );
            sale.setSellerWhoSale(MyMapper.parseObject(sellerInternalDTO, Seller.class));

            SaleDTO processed = processSale(newSale, sellerId, stockId);
            return processed;
        } else {
            throw new Exception("Não foi possível registrar a venda devido a dados ausentes.");
        }
    }

    @Override
    @Transactional
    public SaleDTO processSale(SaleDTO newSale, Long sellerId, Long stockId) throws Exception {
        Optional<Seller> sellerExistOptional = sellerRepo.findById(sellerId);
        if (sellerExistOptional.isPresent()) {
            Double total = newSale.getItems().stream().mapToDouble(p -> p.getQuantity() * p.getPrice()).sum(); // Utiliza o total fornecido pelo cliente
            Sale sale = MyMapper.parseObject(newSale, Sale.class);
            sale.setTotalValueOfsale(total);

            Seller seller = sellerExistOptional.get();
            
            // Convertendo para DTO interno para uso interno no sistema
            SellerInSaleDTO sellerInternalDTO = new SellerInSaleDTO(
                seller.getId(),
                seller.getUsername(),
                seller.getEmail(),
                seller.getFullName()
            );
            sale.setSellerWhoSale(MyMapper.parseObject(sellerInternalDTO, Seller.class));

            salesRepo.save(sale);
            
            // Convertendo para DTO do usuário para fornecer informações detalhadas ao cliente
            SellerDTO sellerUserDTO = new SellerDTO();
            sellerUserDTO.setId(seller.getId());
            sellerUserDTO.setUsername(seller.getUsername());
            sellerUserDTO.setEmail(seller.getEmail());
            sellerUserDTO.setFullName(seller.getFullName());
            sellerUserDTO.setSalary(seller.getSalary());
            sellerUserDTO.setQuantitySales(seller.getQuantitySales());

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
        Optional<Sale> saleOptional = salesRepo.findById(id);
        if (saleOptional.isPresent()) {
            Sale sale = saleOptional.get();
            return MyMapper.parseObject(sale, SaleDTO.class);
        } else {
            throw new Exception("Venda não encontrada.");
        }
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
	public void deleteExistentSale(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFrequencyOfSellerOfSales(Long saleId, Long sellerId) {
		// TODO Auto-generated method stub
		
	}

    // Métodos não implementados omitidos para brevidade
}
