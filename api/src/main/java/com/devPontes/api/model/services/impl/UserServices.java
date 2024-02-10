package com.devPontes.api.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devPontes.api.model.entities.dtos.SellerDTO;
import com.devPontes.api.model.services.SellerManagment;
import com.devPontes.api.repositories.UsersRepositories;

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
	public SellerDTO registerSeller(SellerDTO seller) {
		// TODO Auto-generated method stub
		return null;
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
