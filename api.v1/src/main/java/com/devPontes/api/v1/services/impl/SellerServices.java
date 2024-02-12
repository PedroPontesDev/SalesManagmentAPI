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
	public double calculateComission(SellerDTO seller, double comissionValue) throws Exception {
		if(seller.getQuantitySalesInMonth() >= 50) {
			Double updatedSalary = seller.getSalary() * comissionValue;
			seller.setSalary(updatedSalary);
			return updatedSalary;
		} else {
			throw new Exception("O Vendedor Não Pode Receber Comissão Pois As Vendas Foram Poucas!");
		}
	}
	
	public void insertQuantitySalesInSeller(SellerDTO seller, Integer quantitySale) throws Exception {
		if(seller != null) {
			seller.setQuantitySalesInMonth(quantitySale);
		} else {
			throw new Exception("Não foi possivel alterar numero de vendas no mês");
		}
	}


}
