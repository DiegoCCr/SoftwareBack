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

import com.crud.CRUD.models.CVModel;
import com.crud.CRUD.services.CVServices;

@RestController
@RequestMapping("/CV")
public class CVController {
    
    @Autowired
    private CVServices cvService;

    @GetMapping
    public ArrayList<CVModel> getCVs(){
        return this.cvService.getCV();
    }

    @PostMapping
    public CVModel saveCV(@RequestBody CVModel cv){
        return this.cvService.saveCV(cv);
    }

    @GetMapping(path = "/{id}")
    public Optional<CVModel> getCVById(@PathVariable Long id){
        return this.cvService.getById(id); 
    }

    @PutMapping(path = "/{id}")
    public CVModel updateCVById(@RequestBody CVModel request, @PathVariable Long id){
        return this.cvService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteCVById(@PathVariable("id") Long id){
        boolean ok = this.cvService.deleteCVById(id);
        if(ok){
            return "Compra/Venta eliminada correctamente";
        }else{
            return "La Compra/Venta no pudo ser eliminada";
        }
    }
}
