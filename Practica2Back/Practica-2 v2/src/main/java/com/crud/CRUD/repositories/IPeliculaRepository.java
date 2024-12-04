package com.crud.CRUD.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.CRUD.models.PeliculaModel;

@Repository
public interface  IPeliculaRepository extends JpaRepository<PeliculaModel, Long>{
    
    
}
