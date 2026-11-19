package com.shop.tienda_ropa.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.shop.tienda_ropa.domain.model.Producto;

public interface ProductoRepositoryPort {

    List<Producto> buscarTodos();

    List<Producto> buscarPorCategoriaId(Long categoriaId);

    Optional<Producto> buscarPorId(Long id);

    Producto guardar(Producto producto);

    void eliminarPorId(Long id);

    boolean existePorCodigo(String codigo);

    boolean existePorCodigoExcluyendoId(String codigo, Long id);

    boolean existePorCategoriaId(Long categoriaId);


}


