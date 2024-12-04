package com.crud.CRUD.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.CRUD.models.ClientModel;

public interface IClientRepository extends JpaRepository<ClientModel, Long> {
    Optional<ClientModel> findByCorreo(String correo);
}