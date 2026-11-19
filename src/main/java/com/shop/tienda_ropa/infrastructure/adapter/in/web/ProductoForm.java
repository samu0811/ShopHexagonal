package com.shop.tienda_ropa.infrastructure.adapter.in.web;

import java.math.BigDecimal;

import com.shop.tienda_ropa.domain.model.Categoria;
import com.shop.tienda_ropa.domain.model.Producto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductoForm {

    private Long id;

    @NotBlank(message = "El codigo es obligatorio")
    @Size(max=30, message = "el codigo no puede pasar de 30 caracteres")
    private String codigo;

    @NotBlank(message = "El nombre es obligario")
    @Size(max=100,message = "el nombre no puede pasar de 100 caracteres")
    private String nombre;

    @Size(max=255, message = "La descripcion no puede sobre pasar 255 caracteres")
    private String descripcion;

    @Size(max = 60, message = "La marca no puede superar los 60 caracteres")
    private String marca;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor que cero")
    @Digits(integer = 10, fraction = 2, message = "El precio admite máximo 2 decimales")
    private BigDecimal precio;

    @Size(max = 10, message = "La talla no puede superar los 10 caracteres")
    private String talla;

    @Size(max = 20, message = "El color no puede superar los 20 caracteres")
    private String color;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private Integer cantidadDisponible;

    @Size(max = 250, message = "La URL no puede superar los 250 caracteres")
    private String imagenUrl;

    @NotNull(message = "Debe seleccionar una categoría")
    private Long categoriaId;

    public static ProductoForm desde(Producto producto){
        ProductoForm form = new ProductoForm();
        form.setId(producto.getId());
        form.setCodigo(producto.getCodigo());
        form.setNombre(producto.getNombre());
        form.setDescripcion(producto.getDescripcion());
        form.setMarca(producto.getMarca());
        form.setPrecio(producto.getPrecio());
        form.setTalla(producto.getTalla());
        form.setColor(producto.getColor());
        form.setCantidadDisponible(producto.getCantidadDisponible());
        form.setImagenUrl(producto.getImagenUrl());
        if(producto.getCategoria()!=null){
            form.setCategoriaId(producto.getCategoria().getId());
        }

        return form;
    }


    public Producto aDominio(){
        Producto producto = new Producto();
        producto.setId(id);
        producto.setCodigo(codigo);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setMarca(marca);
        producto.setPrecio(precio);
        producto.setTalla(talla);
        producto.setColor(color);
        producto.setCantidadDisponible(cantidadDisponible);
        producto.setImagenUrl(imagenUrl);
        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);
        producto.setCategoria(categoria);
        return producto;
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
        this.nombre=nombre;
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
    public BigDecimal getPrecio(){
        return precio;
    }
    public void setPrecio(BigDecimal precio){
        this.precio=precio;
    }
    public String getTalla(){
        return talla;
    }
    public void setTalla(String talla){
        this.talla=talla;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color=color;
    }
    public Integer getCantidadDisponible(){
        return cantidadDisponible;
    }
    public void setCantidadDisponible(Integer cantidadDisponible){
        this.cantidadDisponible=cantidadDisponible;
    }
    public String getImagenUrl(){
        return imagenUrl;
    }
    public void setImagenUrl(String imagenUrl){
        this.imagenUrl=imagenUrl;
    }
    public Long getCategoriaId(){
        return categoriaId;
    }
    public void setCategoriaId(Long categoriaId){
        this.categoriaId=categoriaId;
    }

}
