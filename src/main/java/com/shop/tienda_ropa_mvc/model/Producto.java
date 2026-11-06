package com.shop.tienda_ropa_mvc.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El Codigo es obligatorio")
    @Size(max = 30, message = "El codigo no puede superar 30 caracteres")
    @Column(nullable = false, length = 30, unique = true)
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @Size(max = 250, message = "La descripcion no puede superar 250 caracteres")
    @Column(length = 250)
    private String descripcion;

    @NotBlank(message = "La marca es obligatoria")
    @Size(max = 60, message = "La marca no puede superar 60 caracteres")
    @Column(nullable = false, length = 60)
    private String marca;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal precio;

    @NotBlank(message = "La talla es obligatoria")
    @Size(max = 10, message = "La talla no puede superar 10 caracteres")
    private String talla;

    @NotBlank(message = "El color es obligatorio")
    @Size(max = 20, message = "El color no puede superar 20 caracteres")
    private String color;

    @NotNull(message = "La cantidad Disponible es obligatorio")
    @Min(value = 0, message = "La cantidad Disponible no puede ser negativo")
    @Column(nullable = false)
    private Integer cantidadDisponible;

    @Size(max = 250, message = "La URL de la imagen no puede superar 250 caracteres")
    private String imagenUrl;

    @NotNull(message = "Debe elegir una categoria ")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Producto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getPrecio(){
        return precio;
    }

    public void setPrecio(BigDecimal precio){
        this.precio=precio;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor(){
        return color;
    }

    public void setColor(String color){
        this.color = color;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
