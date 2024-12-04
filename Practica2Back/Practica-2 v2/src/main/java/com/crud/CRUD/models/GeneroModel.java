package com.crud.CRUD.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "GENEROS")
public class GeneroModel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_genero;

    @Column
    private String nombre;

    public Long getId(){
        return id_genero;
    }

    public void setId(Long id){
        this.id_genero = id;
    }
    
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

}
