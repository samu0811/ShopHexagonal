package com.shop.tienda_ropa.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.shop.tienda_ropa.domain.model.Usuarios;
import com.shop.tienda_ropa.domain.port.out.UsuariosRepositoryPort;

@Repository 
public class UsuariosPersistenceAdapter implements UsuariosRepositoryPort{

    private final UsuariosJpaRepository jpaRepository;
    private final UsuariosMapper mapper;

    public UsuariosPersistenceAdapter(UsuariosJpaRepository jpaRepository, UsuariosMapper mapper){
        this.jpaRepository=jpaRepository;
        this.mapper=mapper;
    }

    @Override 
    public List<Usuarios> buscarTodos(){
        return jpaRepository.findAll(Sort.by("nombre")).stream().map(mapper::toDomain).toList();
    }

    @Override 
    public Optional<Usuarios> buscarPorId(Long id){
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override 
    public Usuarios guardar(Usuarios usuarios){
        UsuariosJpaEntity guardar = jpaRepository.save(mapper.toEntity(usuarios));
        return mapper.toDomain(guardar);
    }

    @Override 
    public void eliminarPorId(Long id){
        jpaRepository.deleteById(id);
    }

    @Override 
    public boolean existePorDocumento(String documento){
        return jpaRepository.existsByDocumento(documento);
    }

    @Override 
    public boolean existePorDocumentoExcluyendoId(String documento,Long id){
        return jpaRepository.existsByDocumentoAndIdNot(documento, id);
    }
}
