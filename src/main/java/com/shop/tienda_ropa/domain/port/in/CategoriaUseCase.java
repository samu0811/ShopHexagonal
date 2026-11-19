package com.shop.tienda_ropa.domain.port.in;

import java.util.List;

import com.shop.tienda_ropa.domain.model.Categoria;

public interface CategoriaUseCase {

    List<Categoria> listar();
    Categoria buscarPorId(Long id);
    Categoria guardar(Categoria categoria);
    void eliminar(Long id);
}
