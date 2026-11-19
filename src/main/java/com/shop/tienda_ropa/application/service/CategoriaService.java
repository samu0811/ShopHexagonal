package com.shop.tienda_ropa.application.service;

import java.util.List;

import com.shop.tienda_ropa.domain.exception.RecursoNoEncontradoException;
import com.shop.tienda_ropa.domain.exception.ReglaNegocioException;
import com.shop.tienda_ropa.domain.model.Categoria;
import com.shop.tienda_ropa.domain.port.in.CategoriaUseCase;
import com.shop.tienda_ropa.domain.port.out.CategoriaRepositoryPort;
import com.shop.tienda_ropa.domain.port.out.ProductoRepositoryPort;


public class CategoriaService implements CategoriaUseCase {

    private final CategoriaRepositoryPort categoriaRepository;
    private final ProductoRepositoryPort productoRepository;

    public CategoriaService(CategoriaRepositoryPort categoriaRepository,ProductoRepositoryPort productoRepository){
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
    }

    @Override 
    public List<Categoria> listar(){
        return categoriaRepository.buscarTodas();
    }

    @Override 
    public Categoria buscarPorId(Long id){
        return categoriaRepository.buscarPorId(id)
        .orElseThrow(()-> new RecursoNoEncontradoException("No se encontro la categoria por Id"+id));
    }

    @Override 
    public Categoria guardar(Categoria categoria){
        boolean duplicada = categoria.getId() == null
            ? categoriaRepository.existePorNombre(categoria.getNombre())
            : categoriaRepository.existePorNombreExcluyendoId(categoria.getNombre(), categoria.getId());

        if (duplicada){
            throw new ReglaNegocioException("nombre","Ya existe una categoria con este nombre" + categoria.getNombre());
        }
        return  categoriaRepository.guardar(categoria);
    }

    @Override 
    public void eliminar(Long id){
        buscarPorId(id);

        if(productoRepository.existePorCategoriaId(id)){
            throw new ReglaNegocioException(
                "No se puede eliminar la categoria porque tiene productos asociados");
        }
        categoriaRepository.eliminarPorId(id);
    }


}
