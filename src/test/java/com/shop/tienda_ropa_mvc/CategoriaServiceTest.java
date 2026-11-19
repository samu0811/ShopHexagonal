package com.shop.tienda_ropa_mvc;

import com.shop.tienda_ropa.application.service.CategoriaService;
import com.shop.tienda_ropa.domain.exception.RecursoNoEncontradoException;
import com.shop.tienda_ropa.domain.exception.ReglaNegocioException;
import com.shop.tienda_ropa.domain.model.Categoria;
import com.shop.tienda_ropa.domain.model.Producto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CategoriaServiceTest {

    private FakeCategoriaRepository categorias;
    private FakeProductoRepository productos;
    private CategoriaService servicio;

    @BeforeEach
    void preparar() {
        categorias = new FakeCategoriaRepository();
        productos = new FakeProductoRepository();
        servicio = new CategoriaService(categorias, productos);
    }

    @Test
    void guardaUnaCategoriaNuevaYLeAsignaId() {
        Categoria guardada = servicio.guardar(new Categoria(null, "Camisas", "Camisas de vestir"));

        assertNotNull(guardada.getId());
        assertEquals("Camisas", servicio.buscarPorId(guardada.getId()).getNombre());
    }

    @Test
    void rechazaUnNombreDeCategoriaDuplicado() {
        servicio.guardar(new Categoria(null, "Camisas", null));

        ReglaNegocioException error = assertThrows(ReglaNegocioException.class,
                () -> servicio.guardar(new Categoria(null, "Camisas", null)));

        assertEquals("nombre", error.getCampo());
    }

    @Test
    void permiteEditarUnaCategoriaConservandoSuNombre() {
        Categoria guardada = servicio.guardar(new Categoria(null, "Camisas", null));
        guardada.setDescripcion("Nueva descripción");

        Categoria editada = servicio.guardar(guardada);

        assertEquals("Nueva descripción", editada.getDescripcion());
    }

    @Test
    void noEliminaUnaCategoriaConProductos() {
        Categoria categoria = servicio.guardar(new Categoria(null, "Camisas", null));
        Producto producto = new Producto();
        producto.setCodigo("CAM-001");
        producto.setCategoria(categoria);
        productos.guardar(producto);

        assertThrows(ReglaNegocioException.class, () -> servicio.eliminar(categoria.getId()));
        assertEquals(1, servicio.listar().size());
    }

    @Test
    void eliminaUnaCategoriaSinProductos() {
        Categoria categoria = servicio.guardar(new Categoria(null, "Camisas", null));

        servicio.eliminar(categoria.getId());

        assertTrue(servicio.listar().isEmpty());
    }

    @Test
    void lanzaErrorSiLaCategoriaNoExiste() {
        assertThrows(RecursoNoEncontradoException.class, () -> servicio.buscarPorId(99L));
    }
}