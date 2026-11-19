package com.shop.tienda_ropa_mvc;

import com.shop.tienda_ropa.domain.model.Categoria;
import com.shop.tienda_ropa.domain.port.out.CategoriaRepositoryPort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeCategoriaRepository implements CategoriaRepositoryPort {

    private final Map<Long, Categoria> datos = new HashMap<>();
    private long siguienteId = 1;

    @Override
    public List<Categoria> buscarTodas() {
        return new ArrayList<>(datos.values());
    }

    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return Optional.ofNullable(datos.get(id));
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        if (categoria.getId() == null) {
            categoria.setId(siguienteId++);
        }
        datos.put(categoria.getId(), categoria);
        return categoria;
    }

    @Override
    public void eliminarPorId(Long id) {
        datos.remove(id);
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return datos.values().stream().anyMatch(c -> c.getNombre().equals(nombre));
    }

    @Override
    public boolean existePorNombreExcluyendoId(String nombre, Long id) {
        return datos.values().stream()
                .anyMatch(c -> c.getNombre().equals(nombre) && !c.getId().equals(id));
    }
}