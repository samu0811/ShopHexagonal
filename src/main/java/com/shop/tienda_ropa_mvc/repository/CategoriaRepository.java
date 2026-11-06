package com.shop.tienda_ropa_mvc.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.tienda_ropa_mvc.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Boolean existsByNombre(String nombre);

    Boolean existsByNombreAndIdNot(String nombre, Long id);
}
