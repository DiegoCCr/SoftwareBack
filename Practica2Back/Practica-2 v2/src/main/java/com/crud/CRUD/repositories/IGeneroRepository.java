package com.crud.CRUD.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.CRUD.models.GeneroModel;

@Repository
public interface  IGeneroRepository extends JpaRepository<GeneroModel, Long>{
    
    
}
