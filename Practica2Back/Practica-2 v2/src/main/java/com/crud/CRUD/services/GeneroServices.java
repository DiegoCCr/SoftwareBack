package com.crud.CRUD.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.CRUD.models.GeneroModel;
import com.crud.CRUD.repositories.IGeneroRepository;

@Service
public class GeneroServices {
    
    @Autowired
    IGeneroRepository generoRepository;

    public ArrayList<GeneroModel> getGeneros(){
        return (ArrayList<GeneroModel>) generoRepository.findAll();
    }

    public GeneroModel saveGenero(GeneroModel genero){
        return generoRepository.save(genero);
    }

    public Optional<GeneroModel> getById(Long id){
        return generoRepository.findById(id);
    }

    public GeneroModel updateById(GeneroModel request, Long id){
        GeneroModel genero = generoRepository.findById(id).get();
        genero.setNombre(request.getNombre());
        generoRepository.save(genero);

        return genero;
    }

    public Boolean deleteGeneroById(Long id){
        try {
            generoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
