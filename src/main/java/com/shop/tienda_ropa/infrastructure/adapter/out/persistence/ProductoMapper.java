package com.shop.tienda_ropa.infrastructure.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.shop.tienda_ropa.domain.model.Producto;

@Component 
public class ProductoMapper {

    private final CategoriaMapper categoriaMapper;

    public ProductoMapper(CategoriaMapper categoriaMapper){
        this.categoriaMapper=categoriaMapper;
    }

    public Producto toDomain(ProductoJpaEntity entity){
        if(entity==null){
            return null;
        }
        Producto producto =new Producto();
        producto.setId(entity.gettId());
        producto.setCodigo(entity.getCodigo());
        producto.setNombre(entity.getNombre());
        producto.setDescripcion(entity.getDescripcion());
        producto.setMarca(entity.getMarca());
        producto.setPrecio(entity.getPrecio());
        producto.setColor(entity.getColor());
        producto.setTalla(entity.getTalla());
        producto.setCantidadDisponible(entity.getCantidadDisponible());
        producto.setImagenUrl(entity.getImagenURL());
        producto.setCategoria(categoriaMapper.toDomain(entity.getCategoria()));
        return producto;
    }

    public ProductoJpaEntity toEntity(Producto producto){
        if(producto==null){
            return null;
        }
        ProductoJpaEntity entity = new ProductoJpaEntity();
        entity.setId(producto.getId());
        entity.setCodigo(producto.getCodigo());
        entity.setNombre(producto.getNombre());
        entity.setDescripcion(producto.getDescripcion());
        entity.setMarca(producto.getMarca());
        entity.setPrecio(producto.getPrecio());
        entity.setColor(producto.getColor());
        entity.setTalla(producto.getTalla());
        entity.setCantidadDisponible(producto.getCantidadDisponible());
        entity.setImagenURL(producto.getImagenUrl());
        entity.setCategoria(categoriaMapper.toEntity(producto.getCategoria()));
        return entity;
    }

}
