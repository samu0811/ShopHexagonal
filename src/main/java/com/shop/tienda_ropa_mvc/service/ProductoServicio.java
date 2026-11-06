package com.shop.tienda_ropa_mvc.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.shop.tienda_ropa_mvc.model.Categoria;
import com.shop.tienda_ropa_mvc.model.Producto;
import com.shop.tienda_ropa_mvc.repository.ProductoRepository;


@Service 
public class ProductoServicio {

    private final ProductoRepository productoRepository;
    private final CategoriaServicio categoriaServicio;

    public ProductoServicio(ProductoRepository productoRepository, CategoriaServicio categoriaServicio) {
        this.productoRepository = productoRepository;
        this.categoriaServicio = categoriaServicio;
    }

    public List<Producto> listar(){
        return productoRepository.listarConCategoria();
    }

    public Producto obtenerPorId(Long id) {
        return productoRepository.buscarConCategoria(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Producto no encontrado con id " + id));
    }
    
    public Producto guardar(Producto producto){
        Categoria categoria = categoriaServicio.obtenerPorId(producto.getCategoria().getId());
        producto.setCategoria(categoria);  
        return productoRepository.save(producto);
    }

    public boolean codigoDisponible(String codigo,Long id){
        return id==null
            ? !productoRepository.existsByCodigo(codigo)
            : !productoRepository.existsByCodigoAndIdNot(codigo, id);
    }

    public void eliminar(Long id){
        productoRepository.deleteById(id);
    }

}
