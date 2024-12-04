package com.crud.CRUD.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.CRUD.models.CVModel;

@Repository
public interface  ICVRepository extends JpaRepository<CVModel, Long>{
    
    
}
