package com.devPontes.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devPontes.api.model.entities.User;

@Repository
public interface UsersRepositories extends JpaRepository<User, Long> {

}
