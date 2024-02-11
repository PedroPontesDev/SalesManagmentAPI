package com.devPontes.api.v1.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devPontes.api.v1.model.dtos.SellerDTO;
import com.devPontes.api.v1.model.entities.Seller;
import com.devPontes.api.v1.model.mapper.MyMapper;
import com.devPontes.api.v1.repositories.UsersRepositories;
import com.devPontes.api.v1.services.SellerManagment;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserServices implements SellerManagment{

	@Autowired 
	private UsersRepositories userRepositories;

	@Override
	public SellerDTO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SellerDTO registerSeller(SellerDTO sellerDto) throws Exception{
		if(sellerDto == null) throw new Exception("O vendedor não pode ser criado, verifique os dados e tente novamente! ");
		var entity = MyMapper.parseObject(sellerDto, Seller.class);
		userRepositories.save(entity);
		return MyMapper.parseObject(entity, SellerDTO.class);
		
	}

	@Override
	public SellerDTO updateSeller(SellerDTO seller) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSeller(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double calculateComission(SellerDTO seller, double comissionValue) {
		// TODO Auto-generated method stub
		return 0;
	}

}
