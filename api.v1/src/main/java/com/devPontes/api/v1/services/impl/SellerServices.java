package com.devPontes.api.v1.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devPontes.api.v1.model.dtos.SellerDTO;
import com.devPontes.api.v1.model.entities.Seller;
import com.devPontes.api.v1.model.mapper.MyMapper;
import com.devPontes.api.v1.repositories.SellerRepositories;
import com.devPontes.api.v1.services.SellerManagment;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@Service
public class SellerServices implements SellerManagment {

	@Autowired
	private SellerRepositories sellerRepositories;

	@Override
	public List<SellerDTO> findAll() {
		var sellers = sellerRepositories.findAll();
		List<SellerDTO> sellerDTOs = MyMapper.parseListObjects(sellers, SellerDTO.class);
		return sellerDTOs;
	}

	@Override
	public SellerDTO findById(Long id) throws Exception {
		var entity = sellerRepositories.findById(id);
		if(entity.isPresent()) {
			return MyMapper.parseObject(entity, SellerDTO.class);
		} else {
			throw new Exception("Vendedor não encontrado, verifique os dados e tente noavamente!");
		}
		
	}

	@Override
	public SellerDTO registerSeller(SellerDTO sellerDto) throws Exception {
		if (sellerDto == null)
			throw new Exception("O vendedor não pode ser criado, verifique os dados e tente novamente! ");
		var entity = MyMapper.parseObject(sellerDto, Seller.class);
		sellerRepositories.save(entity);
		return MyMapper.parseObject(entity, SellerDTO.class);

	}

	@Override
	public SellerDTO updateSeller(SellerDTO seller) throws Exception {
		var entity = sellerRepositories.findById(seller.getId());
		if(entity.isPresent()) {
			Seller updatedSeller = entity.get();
			updatedSeller.setEmail(seller.getEmail());
			updatedSeller.setFullName(seller.getFullName());
			updatedSeller.setPassword(seller.getPassword());
			updatedSeller.setQuantitySales(seller.getQuantitySalesInMonth());
			updatedSeller.setSalary(seller.getSalary());
			sellerRepositories.save(updatedSeller);
			return MyMapper.parseObject(updatedSeller, SellerDTO.class);
		} else {
			throw new Exception("");
		}
	} 

	@Override
	public void deleteSeller(Long id) throws Exception {
		var entity = sellerRepositories.findById(id);
		if(entity.isPresent()) {
			sellerRepositories.delete(entity.get());
		} else {
			throw new Exception("Não foi possivel deletar usuario, tente novamente!");
		}
		
	}

	@Override
	public double calculateComission(Long id, Double comissionValue) throws Exception {
		var entity = sellerRepositories.findById(id);
		Seller seller = entity.get();
		if(seller.getQuantitySales() >= 10) {
			Double updatedSalary = seller.getSalary() * comissionValue;
			seller.setSalary(updatedSalary);
			return updatedSalary;
		} else {
			throw new Exception("O Vendedor Não Pode Receber Comissão Pois As Vendas Foram Poucas!");
		}
	}
	
	public void insertQuantitySalesInSeller(SellerDTO sellerDTO, Integer quantitySale) throws Exception {
		Seller seller = MyMapper.parseObject(sellerDTO, Seller.class);
		if(seller != null && seller.getQuantitySales() <= 50) {
			seller.setQuantitySales(quantitySale);
			sellerRepositories.save(seller);
		} else {
			throw new Exception("Não foi possivel alterar numero de vendas no mês, verifique se o vendedor já excedeu a cota de vendas");
		}
	}

	@Override
	public SellerDTO updateSellerSalary(Long id, Double newSalary) throws Exception {
		var entity = sellerRepositories.findById(id);
		if(entity.isPresent()) {
			Seller seller = entity.get();
			seller.setSalary(newSalary);
			sellerRepositories.save(seller);
			return MyMapper.parseObject(seller, SellerDTO.class);
		} else {
			throw new Exception("");
		}
	}


}
