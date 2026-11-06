package com.shop.tienda_ropa_mvc.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity 
@Table(name="Categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "el nombre de la categoria es obligatorio")
    @Size(max=80, message ="el nombre no puede superar 80 caracteres")
    @Column (nullable=false, length=80, unique=true)
    private String nombre;

    @Size(max=250,message="La descripcion no puede superar 250 caracteres")
    private String descripcion;

    @OneToMany (mappedBy = "categoria")
    private List<Producto> productos = new ArrayList<>();
    
    public Categoria() {

    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }

    public List<Producto> getProductos(){
        return productos;
    }
    public void setProductos (List<Producto> productos){
        this.productos = productos;
    }

}
