package com.shop.tienda_ropa.application.service;

import java.util.List;


import com.shop.tienda_ropa.domain.exception.RecursoNoEncontradoException;
import com.shop.tienda_ropa.domain.exception.ReglaNegocioException;
import com.shop.tienda_ropa.domain.model.Usuarios;
import com.shop.tienda_ropa.domain.port.in.UsuariosUseCase;
import com.shop.tienda_ropa.domain.port.out.UsuariosRepositoryPort;

public class UsuariosService implements UsuariosUseCase{

    private UsuariosRepositoryPort repositoryPort;

    public UsuariosService(UsuariosRepositoryPort repositoryPort){
        this.repositoryPort=repositoryPort;
    }

    @Override 
    public List<Usuarios> listar(){
        return repositoryPort.buscarTodos();
    }

    @Override 
    public Usuarios buscarPorId(Long id){
        return repositoryPort.buscarPorId(id).
        orElseThrow(() -> new RecursoNoEncontradoException("no se encontro ningun usuario"));
    }

    @Override 
    public  Usuarios guardar(Usuarios usuarios){
        boolean duplicada = usuarios.getId() == null
        ?repositoryPort.existePorDocumento(usuarios.getDocumento())
        :repositoryPort.existePorDocumentoExcluyendoId(usuarios.getDocumento(), usuarios.getId());

        if(duplicada){
            throw new ReglaNegocioException("documento","Ya existe un usuario con este documento");
        }
        return repositoryPort.guardar(usuarios);
    }

    @Override 
    public void eliminar(Long id){
        buscarPorId(id);
        repositoryPort.eliminarPorId(id);
    }

}
