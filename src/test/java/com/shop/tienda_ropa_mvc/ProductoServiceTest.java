package com.shop.tienda_ropa_mvc;

import com.shop.tienda_ropa.application.service.ProductoService;
import com.shop.tienda_ropa.domain.exception.ReglaNegocioException;
import com.shop.tienda_ropa.domain.model.Categoria;
import com.shop.tienda_ropa.domain.model.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductoServiceTest {

    private FakeCategoriaRepository categorias;
    private FakeProductoRepository productos;
    private ProductoService servicio;
    private Categoria camisas;

    @BeforeEach
    void preparar() {
        categorias = new FakeCategoriaRepository();
        productos = new FakeProductoRepository();
        servicio = new ProductoService(categorias, productos);
        camisas = categorias.guardar(new Categoria(null, "Camisas", null));
    }

    private Producto nuevoProducto(String codigo, Long categoriaId) {
        Producto producto = new Producto();
        producto.setCodigo(codigo);
        producto.setNombre("Camisa azul");
        producto.setPrecio(new BigDecimal("59900.00"));
        producto.setCantidadDisponible(10);
        if (categoriaId != null) {
            Categoria referencia = new Categoria();
            referencia.setId(categoriaId);
            producto.setCategoria(referencia);
        }
        return producto;
    }

    @Test
    void guardaUnProductoConCategoriaValida() {
        Producto guardado = servicio.guardar(nuevoProducto("CAM-001", camisas.getId()));

        assertNotNull(guardado.getId());
        assertEquals("Camisas", guardado.getCategoria().getNombre());
    }

    @Test
    void rechazaUnCodigoDuplicado() {
        servicio.guardar(nuevoProducto("CAM-001", camisas.getId()));

        ReglaNegocioException error = assertThrows(ReglaNegocioException.class,
                () -> servicio.guardar(nuevoProducto("CAM-001", camisas.getId())));

        assertEquals("codigo", error.getCampo());
    }

    @Test
    void rechazaUnProductoSinCategoria() {
        ReglaNegocioException error = assertThrows(ReglaNegocioException.class,
                () -> servicio.guardar(nuevoProducto("CAM-002", null)));

        assertEquals("categoria", error.getCampo());
    }

    @Test
    void rechazaUnaCategoriaQueNoExiste() {
        assertThrows(ReglaNegocioException.class,
                () -> servicio.guardar(nuevoProducto("CAM-003", 99L)));
    }

    @Test
    void listaLosProductosDeUnaCategoria() {
        servicio.guardar(nuevoProducto("CAM-001", camisas.getId()));
        servicio.guardar(nuevoProducto("CAM-002", camisas.getId()));

        assertEquals(2, servicio.listarPorCategoria(camisas.getId()).size());
    }
}