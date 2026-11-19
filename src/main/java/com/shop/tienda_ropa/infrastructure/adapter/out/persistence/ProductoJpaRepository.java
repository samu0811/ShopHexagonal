package com.shop.tienda_ropa.infrastructure.adapter.out.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoJpaRepository extends JpaRepository<ProductoJpaEntity,Long>{

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo,Long id);

    List<ProductoJpaEntity> findByCategoriaId(Long id);

    boolean existsByCategoriaId(Long categoriaId);
    


}
