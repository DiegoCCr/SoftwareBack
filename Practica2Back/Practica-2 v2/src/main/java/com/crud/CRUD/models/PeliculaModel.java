package com.crud.CRUD.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PELICULAS")
public class PeliculaModel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_peliculas;

    @Column
    private String titulo;

    @Column
    private String descripcion;

    @Column
    private Float costo;

    @Column
    private Long id_genero;

    public Long getId(){
        return id_peliculas;
    }

    public void setId(Long id){
        this.id_peliculas = id;
    }
    
    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public Float getCosto(){
        return costo;
    }

    public void setCosto(Float costo){
        this.costo = costo;
    }

    public Long getIdGenero(){
        return id_genero;
    }

    public void setIdGenero(Long id){
        this.id_genero = id;
    }

}
