package com.crud.CRUD.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.CRUD.models.PeliculaModel;
import com.crud.CRUD.repositories.IPeliculaRepository;

@Service
public class PeliculaServices {
    
    @Autowired
    IPeliculaRepository peliculaRepository;

    public ArrayList<PeliculaModel> getPeliculas(){
        return (ArrayList<PeliculaModel>) peliculaRepository.findAll();
    }

    public PeliculaModel savePelicula(PeliculaModel pelicula){
        return peliculaRepository.save(pelicula);
    }

    public Optional<PeliculaModel> getById(Long id){
        return peliculaRepository.findById(id);
    }

    public PeliculaModel updateById(PeliculaModel request, Long id){
        PeliculaModel pelicula = peliculaRepository.findById(id).get();
        pelicula.setTitulo(request.getTitulo());
        pelicula.setDescripcion(request.getDescripcion());
        pelicula.setCosto(request.getCosto());
        pelicula.setIdGenero(request.getIdGenero());
        peliculaRepository.save(pelicula);

        return pelicula;
    }

    public Boolean deletePeliculaById(Long id){
        try {
            peliculaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
