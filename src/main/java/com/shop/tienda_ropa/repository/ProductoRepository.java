package com.shop.tienda_ropa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.tienda_ropa.model.Producto;

import java.util.List;
import java.util.Optional;


public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p JOIN FETCH p.categoria ORDER BY p.nombre")
    List<Producto> listarConCategoria();

    @Query("SELECT p FROM Producto p JOIN FETCH p.categoria WHERE p.id = :id")
    Optional<Producto> buscarConCategoria(@Param("id") Long id);



    
    List<Producto> findByNombre(String nombre);

    Boolean existsByCodigo(String codigo);

    Boolean existsByCodigoAndIdNot(String codigo, Long id);

    Boolean existsByCategoriaId(Long categoriaId);

}
