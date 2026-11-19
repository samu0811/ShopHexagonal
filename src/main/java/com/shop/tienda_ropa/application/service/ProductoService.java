package com.shop.tienda_ropa.application.service;

import java.util.List;

import com.shop.tienda_ropa.domain.exception.RecursoNoEncontradoException;
import com.shop.tienda_ropa.domain.exception.ReglaNegocioException;
import com.shop.tienda_ropa.domain.model.Categoria;
import com.shop.tienda_ropa.domain.model.Producto;
import com.shop.tienda_ropa.domain.port.in.ProductoUseCase;
import com.shop.tienda_ropa.domain.port.out.CategoriaRepositoryPort;
import com.shop.tienda_ropa.domain.port.out.ProductoRepositoryPort;

public class ProductoService implements ProductoUseCase {

    private final CategoriaRepositoryPort categoriaRepository;
    private final ProductoRepositoryPort  productoRepository;

    public ProductoService(CategoriaRepositoryPort categoriaRepository, ProductoRepositoryPort productoRepository){
        this.productoRepository=productoRepository;
        this.categoriaRepository=categoriaRepository;
    }

    @Override 
    public List<Producto> listar(){
        return productoRepository.buscarTodos();
    }

    @Override 
    public List<Producto> listarPorCategoria(Long categoriaId){
        return productoRepository.buscarPorCategoriaId(categoriaId);
    }

    @Override 
    public Producto buscarPorId(Long id){
        return productoRepository.buscarPorId(id).orElseThrow(()->new RecursoNoEncontradoException(
            "No se encontro el producto por el id "+id));
    }

    @Override 
    public Producto guardar(Producto producto){
        Long categoriaId = producto.getCategoria() != null
            ? producto.getCategoria().getId() :null;

        if (categoriaId == null){
            throw new ReglaNegocioException("categoria","debe seleccionar una categoria");
        }
        
        Categoria categoria= categoriaRepository.buscarPorId(categoriaId)
            .orElseThrow(()-> new ReglaNegocioException("categoria","La categoria seleccionada no existe"));

        boolean duplicado = producto.getId() == null
            ? productoRepository.existePorCodigo(producto.getCodigo())
            : productoRepository.existePorCodigoExcluyendoId(producto.getCodigo(), producto.getId());
        
        if (duplicado){
            throw new ReglaNegocioException("codigo","Ya existe un producto con el codigo" + producto.getCodigo());
        }

        producto.setCategoria(categoria);
        return productoRepository.guardar(producto);
    }

    @Override 
    public void eliminar(Long id){
        buscarPorId(id);
        productoRepository.eliminarPorId(id);
    }
}
