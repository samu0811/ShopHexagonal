package com.shop.tienda_ropa.domain.port.in;

import java.util.List;

import com.shop.tienda_ropa.domain.model.Producto;

public interface ProductoUseCase {

    List<Producto> listar();
    List<Producto> listarPorCategoria(Long categoriaId);
    Producto buscarPorId(Long id);
    Producto guardar(Producto producto);
    void eliminar(Long id);

}
