package com.shop.tienda_ropa.infrastructure.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.shop.tienda_ropa.domain.model.Usuarios;

@Component
public class UsuariosMapper {

    public Usuarios toDomain(UsuariosJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Usuarios(entity.getId(), entity.getNombre(), entity.getDocumento(),entity.getEmail(), entity.getCelular());
    }

    public UsuariosJpaEntity toEntity(Usuarios usuarios){
        if (usuarios==null) {
            return null;
        }
        UsuariosJpaEntity entity=new UsuariosJpaEntity();
        entity.setId(usuarios.getId());
        entity.setNombre(usuarios.getNombre());
        entity.setDocumento(usuarios.getDocumento());
        entity.setEmail(usuarios.getEmail());
        entity.setCelular(usuarios.getCelular());
        return entity;
    }
}
