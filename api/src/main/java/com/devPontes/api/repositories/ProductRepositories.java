package com.devPontes.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devPontes.api.model.entities.Product;

@Repository
public interface ProductRepositories extends JpaRepository<Product, Long> {

}
