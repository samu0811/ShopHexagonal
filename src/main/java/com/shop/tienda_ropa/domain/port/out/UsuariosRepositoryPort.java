package com.shop.tienda_ropa.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.shop.tienda_ropa.domain.model.Usuarios;

public interface UsuariosRepositoryPort {

    List<Usuarios> buscarTodos();
    Optional<Usuarios> buscarPorId(Long id);
    Usuarios guardar(Usuarios usuarios);
    void eliminarPorId(Long id);

    boolean existePorDocumento(String documento);
    boolean existePorDocumentoExcluyendoId(String documento, Long id);
}
