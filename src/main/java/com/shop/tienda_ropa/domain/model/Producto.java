package com.shop.tienda_ropa.domain.model;

import java.math.BigDecimal;

public class Producto {

    private long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String marca;
    private String color;
    private String talla;
    private BigDecimal precio;
    private Integer cantidadDisponible;
    private String imagenUrl;
    
    private Categoria categoria;

    public Producto(){

    }

    public Boolean tieneStock(){
        return cantidadDisponible != null && cantidadDisponible>0;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }

    public String getCodigo(){
        return codigo;
    }
    public void setCodigo(String codigo){
        this.codigo=codigo;
    }

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre= nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }

    public String getMarca(){
        return marca;
    }
    public void setMarca(String marca){
        this.marca=marca;
    }

    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color=color;
    }

    public String getTalla(){
        return talla;
    }
    public void setTalla(String talla){
        this.talla=talla;
    }

    public BigDecimal getPrecio(){
        return precio;
    }
    public void setPrecio(BigDecimal precio){
        this.precio=precio;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }
    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Integer getCantidadDisponible(){
        return cantidadDisponible;
    }
    public void setCantidadDisponible(Integer cantidadDisponible){
        this.cantidadDisponible=cantidadDisponible;
    }

    public Categoria getCategoria(){
        return categoria;
    }
    public void  setCategoria(Categoria categoria){
        this.categoria=categoria;
    }


    



    


}
