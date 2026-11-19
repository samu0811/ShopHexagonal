package com.shop.tienda_ropa.infrastructure.adapter.out.persistence;

import java.math.BigDecimal;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "productos")
public class ProductoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 30)
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @Column(length = 150)
    private String marca;

    @Column(length = 20)
    private String color;

    @Column(length=10)
    private String talla;

    @Column(nullable = false)
    private Integer cantidadDisponible;

    @Column(nullable = false,precision = 12,scale = 2)
    private BigDecimal precio;

    @Column(length = 255)
    private String imagenURL;


    @ManyToOne(optional=false)
    @JoinColumn(name = "categoriaId",nullable = false)
    private CategoriaJpaEntity categoria;

    public ProductoJpaEntity(){
        
    }

    public Long gettId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

     public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    public CategoriaJpaEntity getCategoria(){
        return categoria;
    }

    public void setCategoria(CategoriaJpaEntity categoria){
        this.categoria=categoria;
    }

}
