package com.shop.tienda_ropa.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.shop.tienda_ropa.domain.model.Producto;
import com.shop.tienda_ropa.domain.port.out.ProductoRepositoryPort;

@Repository 
public class ProductoPersistenceAdapter implements ProductoRepositoryPort{

    private final ProductoMapper mapper;
    private final ProductoJpaRepository jpaRepository;

    public  ProductoPersistenceAdapter(ProductoMapper mapper, ProductoJpaRepository jpaRepository){
        this.mapper=mapper;
        this.jpaRepository=jpaRepository;
    }

    @Override 
    public List<Producto> buscarTodos(){
        return jpaRepository.findAll(Sort.by("nombre")).stream().map(mapper::toDomain).toList();
    }

    @Override 
    public List<Producto> buscarPorCategoriaId(Long categoriaId){
        return jpaRepository.findByCategoriaId(categoriaId).stream().map(mapper::toDomain).toList();
    }

    @Override 
    public Optional<Producto> buscarPorId(Long id){
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override 
    public Producto guardar(Producto producto){
        ProductoJpaEntity guardado = jpaRepository.save(mapper.toEntity(producto));
        return mapper.toDomain(guardado);
    }

    @Override 
    public void eliminarPorId(Long id){
        jpaRepository.deleteById(id);
    }

    @Override 
    public boolean existePorCodigo(String codigo){
        return jpaRepository.existsByCodigo(codigo);
    }

    @Override 
    public boolean existePorCodigoExcluyendoId(String codigo,Long id){
        return jpaRepository.existsByCodigoAndIdNot(codigo, id);
    }

    @Override 
    public boolean existePorCategoriaId(Long categoriaId){
        return jpaRepository.existsByCategoriaId(categoriaId);
    }

    
}
