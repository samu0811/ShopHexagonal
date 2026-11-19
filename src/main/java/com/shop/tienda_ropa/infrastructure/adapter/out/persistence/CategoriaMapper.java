package com.shop.tienda_ropa.infrastructure.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.shop.tienda_ropa.domain.model.Categoria;

@Component 
public class CategoriaMapper {

    public Categoria toDomain(CategoriaJpaEntity entity){
        if(entity==null){
            return  null;
        }
        return new Categoria(entity.getId(), entity.getNombre(), entity.getDescripcion());
    }

    public  CategoriaJpaEntity toEntity(Categoria categoria){
        if(categoria==null){
            return  null;
        }
        CategoriaJpaEntity entity= new CategoriaJpaEntity();
        entity.setId(categoria.getId());
        entity.setNombre(categoria.getNombre());
        entity.setDescripcion(categoria.getDescripcion());
        return entity;
    }
}
