package com.crud.CRUD.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "COMPRAS_VENTAS")
public class CVModel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_compra;

    @Column
    private Long id_cliente;

    @Column
    private Long id_pelicula;

    @Column
    private String fecha;

    public Long getIdCompra(){
        return id_compra;
    }

    public void setIdCompra(Long id){
        this.id_compra = id;
    }
    
    public Long getIdCliente(){
        return id_cliente;
    }

    public void setIdCliente(Long id){
        this.id_cliente = id;
    }

    public Long getIdPelicula(){
        return id_pelicula;
    }

    public void setIdPelicula(Long id){
        this.id_pelicula = id;
    }
    
    public String getFecha(){
        return fecha;
    }

    public void setFecha(String fecha){
        this.fecha = fecha;
    }

}
