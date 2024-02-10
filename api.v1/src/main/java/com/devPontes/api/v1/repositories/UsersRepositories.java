package com.devPontes.api.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devPontes.api.v1.model.entities.User;

@Repository
public interface UsersRepositories extends JpaRepository<User, Long> {

}
