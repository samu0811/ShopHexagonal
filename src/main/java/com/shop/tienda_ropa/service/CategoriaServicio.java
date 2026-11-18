package com.shop.tienda_ropa.service;


import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.shop.tienda_ropa.model.Categoria;
import com.shop.tienda_ropa.repository.CategoriaRepository;
import com.shop.tienda_ropa.repository.ProductoRepository;

@Service 
public class CategoriaServicio {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;
    
    public CategoriaServicio(CategoriaRepository categoriaRepository, ProductoRepository productoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
    }

    public List<Categoria> listar() {
        return categoriaRepository.findAll(Sort.by("nombre"));
    }

    public Categoria obtenerPorId(Long id){
        return categoriaRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Categoria no encontrada con id "+id));
    }

    public Categoria guardar(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public boolean nombreDisponible(String nombre, Long id) {
        return id==null
            ? !categoriaRepository.existsByNombre(nombre)
            : !categoriaRepository.existsByNombreAndIdNot(nombre, id);
    }

    public void eliminar(Long id){
        if(productoRepository.existsByCategoriaId(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No se puede eliminar la categoria porque tiene productos asociados");
        }
        categoriaRepository.deleteById(id);
    }

}
