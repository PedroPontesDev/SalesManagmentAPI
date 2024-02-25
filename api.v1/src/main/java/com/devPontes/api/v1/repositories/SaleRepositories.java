package com.devPontes.api.v1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devPontes.api.v1.model.dtos.ProductDTO;
import com.devPontes.api.v1.model.entities.Sale;

@Repository
public interface SaleRepositories extends JpaRepository<Sale, Long> {

	@Query(nativeQuery = true, value = "SELECT p.id, p.name, p.price, COUNT(sp.product_id) as frequency " +
									   "FROM sale_product sp" +
									   "INNER JOIN tb_product p ON sp.product_id = p.id " +
									   "GROUP BY p.id " +
									   "ORDER BY frequency DESC " +
									   "LIMIT 5")
	List<Object[]> findTop5MostFrequentProduct();
        
}