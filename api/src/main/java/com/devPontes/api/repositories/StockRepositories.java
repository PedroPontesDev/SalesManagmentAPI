package com.devPontes.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devPontes.api.model.entities.Stock;

@Repository
public interface StockRepositories extends JpaRepository<Stock, Long> {

}
