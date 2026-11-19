package com.shop.tienda_ropa.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.shop.tienda_ropa.domain.model.Categoria;

public interface CategoriaRepositoryPort {

    List<Categoria> buscarTodas();

    Optional<Categoria> buscarPorId(Long id);

    Categoria guardar(Categoria categoria);

    void eliminarPorId(Long id);

    boolean existePorNombre(String nombre);
    
    boolean existePorNombreExcluyendoId(String nombre, Long id);

}
