package com.devPontes.api.v1.services.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private SaleRepositories saleRepositories;

    @Autowired
    private StockRepositories stockRepositories;

    @Autowired
    private SellerRepositories sellerRepositories;

    @Autowired
    private ClientRepositories clientRepositories;

    @Autowired
    private ProductRepositories productRepositories;

    private final Logger logger = LoggerFactory.getLogger(SaleServices.class);

    @Override
    @Transactional
    public SaleDTO registerNewSale(SaleDTO newSale, Long clientId, Long sellerId, Long stockId) throws Exception {
        Sale sale = MyMapper.parseObject(newSale, Sale.class); // Mapeando as entidades
        var stock0 = stockRepositories.findById(stockId);
        var client0 = clientRepositories.findById(clientId);
        var seller0 = sellerRepositories.findById(sellerId);
        if (sale != null && stock0.isPresent() && client0.isPresent() && seller0.isPresent()) {
            verifyItensInSaleAndSetValue(sale);
            Stock stock = stock0.get();
            Client client = client0.get();
            Seller seller = seller0.get();
            
            // Carregar os produtos para o objeto Sale
            for (Product item : sale.getItems()) {
                Product product = productRepositories.findById(item.getId())
                                                      .orElseThrow(() -> new Exception("Produto não encontrado"));
                // Verificar se a instância carregada é a mesma passada no DTO
                if (product != null && item != null) {
                    item.getStock().getProductsInStock().add(item);
                }
            }

            var processed = processSale(sale, stock, client, seller);
            if (sale.isCompleted()) return MyMapper.parseObject(sale, SaleDTO.class);
        }
        throw new Exception("Não foi possível registrar a venda, tente novamente!");
    }

    @Override
    @Transactional
    public Sale processSale(Sale sale, Stock stock, Client buyer, Seller seller) throws Exception {
        Sale processed = sale;
        if (processed.getTotalValueOfsale() != null) {
            sale.setMoment(Instant.now());
            sale.setClientWhoBuy(buyer);
            sale.setSellerWhoSale(seller);
            sale.setItems(sale.getItems());
            sale.setCompleted(true);
            saleRepositories.save(sale);
            return sale;
        }
        throw new Exception("A venda não foi processada porque o estoque não foi verificado antes!");
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
    public void updateStockLevels(Sale sale, Long stockId) {
        // TODO Auto-generated method stub
    }

    @Override
    public void verifyItensInSaleAndSetValue(Sale newSale) throws Exception {
        Sale sale = newSale;
        List<Long> jsonItemIds = newSale.getItems().stream().mapToLong(p -> p.getId()).boxed().collect(Collectors.toList());

        if (!sale.getItems().stream().map(Product::getId).collect(Collectors.toList()).containsAll(jsonItemIds)) {
            throw new Exception("Itens da venda não estão disponíveis no banco de dados!");
        }
        double totalOfSale = sale.getItems().stream()
                              .mapToDouble(item -> item.getPrice() * item.getQuantity())
                              .sum();
        sale.setTotalValueOfsale(totalOfSale);
        saleRepositories.save(sale);
    }


  /*  @Override
    public Set<SaleDTO> findAllSales() throws Exception {
        Set<Sale> allSales = saleRepositories.findAll();
        return allSales.stream()
                       .map(sale -> MyMapper.parseObject(sale, SaleDTO.class))
                       .collect(Collectors.toSet());
    }*/

    @Override
    public Set<SaleDTO> findWhoMounthHaveMoreSale(SaleDTO sale, LocalDate now, LocalDate datesOfSale) {
        // Lógica para encontrar as vendas do mês com mais vendas
        // Você precisará consultar as vendas dentro do intervalo de datas e calcular as vendas por mês
        // Depois, você pode retornar as vendas com o mês com mais vendas
        // Aqui está um esboço básico:
        
        // Consulta as vendas dentro do intervalo de datas
        // List<Sale> salesInRange = saleRepositories.findByDateBetween(datesOfSale, now);
        
        // Lógica para calcular as vendas por mês
        // Map<Month, Integer> salesByMonth = salesInRange.stream()
        //                                                .collect(Collectors.groupingBy(sale -> sale.getDate().getMonth(), Collectors.summingInt(Sale::getTotalSales)));

        // Lógica para encontrar o mês com mais vendas
        // Month mostSalesMonth = salesByMonth.entrySet().stream()
        //                                      .max(Map.Entry.comparingByValue())
        //                                      .orElseThrow(() -> new IllegalStateException("Não há vendas no período especificado"))
        //                                      .getKey();

        // Lógica para filtrar as vendas do mês com mais vendas
        // Set<SaleDTO> mostSalesMonthSales = salesInRange.stream()
        //                                                .filter(sale -> sale.getDate().getMonth() == mostSalesMonth)
        //                                                .map(sale -> MyMapper.parseObject(sale, SaleDTO.class))
        //                                                .collect(Collectors.toSet());

        // return mostSalesMonthSales;
        
        // Lembre-se de ajustar de acordo com a estrutura real do seu banco de dados e de suas entidades
        return null;
    }

    @Override
    public Set<SaleDTO> findSalesByDate(SaleDTO sale, LocalDate beginDate, LocalDate endDate) {
        // Lógica para encontrar as vendas dentro do intervalo de datas especificado
        // Você precisará consultar as vendas dentro do intervalo de datas e retornar os resultados
        // Aqui está um esboço básico:

        // Consulta as vendas dentro do intervalo de datas
        // List<Sale> salesInRange = saleRepositories.findByDateBetween(beginDate, endDate);

        // Mapeia as vendas para SaleDTO
        // Set<SaleDTO> salesDTO = salesInRange.stream()
        //                                     .map(sale -> MyMapper.parseObject(sale, SaleDTO.class))
        //                                     .collect(Collectors.toSet());

        // return salesDTO;

        // Lembre-se de ajustar de acordo com a estrutura real do seu banco de dados e de suas entidades
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
	public Set<SaleDTO> findAllSales() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
