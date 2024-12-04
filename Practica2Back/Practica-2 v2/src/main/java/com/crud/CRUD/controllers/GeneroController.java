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

import com.crud.CRUD.models.GeneroModel;
import com.crud.CRUD.services.GeneroServices;

@RestController
@RequestMapping("/genero")
public class GeneroController {
    
    @Autowired
    private GeneroServices generoService;

    @GetMapping
    public ArrayList<GeneroModel> getGeneros(){
        return this.generoService.getGeneros();
    }

    @PostMapping
    public GeneroModel saveGenero(@RequestBody GeneroModel genero){
        return this.generoService.saveGenero(genero);
    }

    @GetMapping(path = "/{id}")
    public Optional<GeneroModel> getGeneroById(@PathVariable Long id){
        return this.generoService.getById(id); 
    }

    @PutMapping(path = "/{id}")
    public GeneroModel updateGeneroById(@RequestBody GeneroModel request, @PathVariable Long id){
        return this.generoService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteGeneroById(@PathVariable("id") Long id){
        boolean ok = this.generoService.deleteGeneroById(id);
        if(ok){
            return "Genero eliminado correctamente";
        }else{
            return "El genero no pudo ser eliminado";
        }
    }
}
