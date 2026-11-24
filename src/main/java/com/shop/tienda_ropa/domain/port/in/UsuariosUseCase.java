package com.shop.tienda_ropa.domain.port.in;

import java.util.List;

import com.shop.tienda_ropa.domain.model.Usuarios;

public interface UsuariosUseCase {

    List<Usuarios> listar();
    Usuarios buscarPorId(Long id);
    Usuarios guardar(Usuarios usuarios);
    void eliminar(Long id);
    
}
