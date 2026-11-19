package com.shop.tienda_ropa.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaJpaRepository extends JpaRepository<CategoriaJpaEntity,Long>{

        boolean existsByNombre(String nombre);

        boolean existsByNombreAndIdNot(String nombre,Long id);
}
