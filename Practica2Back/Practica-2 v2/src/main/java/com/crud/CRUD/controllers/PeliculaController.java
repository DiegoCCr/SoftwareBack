package com.crud.CRUD.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.CRUD.models.PeliculaModel;
import com.crud.CRUD.services.PeliculaServices;

@RestController
@RequestMapping("/pelicula")
public class PeliculaController {
    
    @Autowired
    private PeliculaServices peliculaService;

    @GetMapping
    public ArrayList<PeliculaModel> getPeliculas(){
        return this.peliculaService.getPeliculas();
    }

    @PostMapping
    public PeliculaModel savePelicula(@RequestBody PeliculaModel pelicula){
        return this.peliculaService.savePelicula(pelicula);
    }

    @GetMapping(path = "/{id}")
    public Optional<PeliculaModel> getPeliculaById(@PathVariable Long id){
        return this.peliculaService.getById(id); 
    }

    @PutMapping(path = "/{id}")
    public PeliculaModel updatePeliculaById(@RequestBody PeliculaModel request, @PathVariable Long id){
        return this.peliculaService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteUPeliculaById(@PathVariable("id") Long id){
        boolean ok = this.peliculaService.deletePeliculaById(id);
        if(ok){
            return "Pelicula eliminada correctamente";
        }else{
            return "La pelocula no pudo ser eliminada";
        }
    }
}
