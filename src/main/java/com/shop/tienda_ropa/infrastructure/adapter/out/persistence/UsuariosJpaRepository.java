package com.shop.tienda_ropa.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosJpaRepository extends JpaRepository<UsuariosJpaEntity,Long>{
    boolean existsByDocumento(String documento);
    boolean existsByDocumentoAndIdNot(String documento,Long id);
}
