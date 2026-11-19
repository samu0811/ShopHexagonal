package com.shop.tienda_ropa.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.shop.tienda_ropa.domain.model.Categoria;
import com.shop.tienda_ropa.domain.port.out.CategoriaRepositoryPort;

@Repository 
public class CategoriaPersistenceAdapter implements CategoriaRepositoryPort{

    private final CategoriaJpaRepository jpaRepository;
    private final CategoriaMapper mapper;

    public CategoriaPersistenceAdapter(CategoriaJpaRepository jpaRepository,CategoriaMapper mapper){
        this.jpaRepository=jpaRepository;
        this.mapper=mapper;
    }

    @Override 
    public List<Categoria> buscarTodas(){
        return  jpaRepository.findAll(Sort.by("nombre")).stream().map(mapper::toDomain).toList();
    }

    @Override 
    public Optional<Categoria> buscarPorId(Long id){
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override 
    public Categoria guardar(Categoria categoria){
        CategoriaJpaEntity guardar = jpaRepository.save(mapper.toEntity(categoria));
        return mapper.toDomain(guardar);
    }

    @Override 
    public void eliminarPorId(Long id){
        jpaRepository.deleteById(id);
    }

    @Override 
     public boolean existePorNombre(String nombre) {
        return jpaRepository.existsByNombre(nombre);
    }

    @Override 
    public boolean existePorNombreExcluyendoId(String nombre, Long id){
        return jpaRepository.existsByNombreAndIdNot(nombre, id);
    }


}
