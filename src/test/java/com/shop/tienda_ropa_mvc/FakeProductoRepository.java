package com.shop.tienda_ropa_mvc;

import com.shop.tienda_ropa.domain.model.Producto;
import com.shop.tienda_ropa.domain.port.out.ProductoRepositoryPort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeProductoRepository implements ProductoRepositoryPort {

    private final Map<Long, Producto> datos = new HashMap<>();
    private long siguienteId = 1;

    @Override
    public List<Producto> buscarTodos() {
        return new ArrayList<>(datos.values());
    }

    @Override
    public List<Producto> buscarPorCategoriaId(Long categoriaId) {
        return datos.values().stream()
                .filter(p -> p.getCategoria().getId().equals(categoriaId))
                .toList();
    }

    @Override
    public Optional<Producto> buscarPorId(Long id) {
        return Optional.ofNullable(datos.get(id));
    }

    @Override
    public Producto guardar(Producto producto) {
        if (producto.getId() == null) {
            producto.setId(siguienteId++);
        }
        datos.put(producto.getId(), producto);
        return producto;
    }

    @Override
    public void eliminarPorId(Long id) {
        datos.remove(id);
    }

    @Override
    public boolean existePorCodigo(String codigo) {
        return datos.values().stream().anyMatch(p -> p.getCodigo().equals(codigo));
    }

    @Override
    public boolean existePorCodigoExcluyendoId(String codigo, Long id) {
        return datos.values().stream()
                .anyMatch(p -> p.getCodigo().equals(codigo) && !p.getId().equals(id));
    }

    @Override
    public boolean existePorCategoriaId(Long categoriaId) {
        return datos.values().stream()
                .anyMatch(p -> p.getCategoria().getId().equals(categoriaId));
    }
}