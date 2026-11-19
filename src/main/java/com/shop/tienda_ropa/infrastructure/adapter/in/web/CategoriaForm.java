package com.shop.tienda_ropa.infrastructure.adapter.in.web;

import com.shop.tienda_ropa.domain.model.Categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoriaForm {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 80, message = "El nombre no puede superar los 80 caracteres")
    private String nombre;

    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    private String descripcion;

    public static CategoriaForm desde(Categoria categoria) {
        CategoriaForm form = new CategoriaForm();
        form.setId(categoria.getId());
        form.setNombre(categoria.getNombre());
        form.setDescripcion(categoria.getDescripcion());
        return form;
    }

    public Categoria aDominio(){
        return new Categoria(id,nombre,descripcion);
    }

    public Long getId(){
        return id;
    }
    public void  setId(Long id){
        this.id=id;
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
}
