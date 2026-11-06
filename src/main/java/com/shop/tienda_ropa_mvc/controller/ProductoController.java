package com.shop.tienda_ropa_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.tienda_ropa_mvc.model.Producto;
import com.shop.tienda_ropa_mvc.service.CategoriaServicio;
import com.shop.tienda_ropa_mvc.service.ProductoServicio;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.shop.tienda_ropa_mvc.model.Categoria;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoServicio productoServicio;
    private final CategoriaServicio categoriaServicio;

    public ProductoController(ProductoServicio productoServicio, CategoriaServicio categoriaServicio) {
        this.productoServicio = productoServicio;
        this.categoriaServicio = categoriaServicio;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoServicio.listar());
        return "productos/lista";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        model.addAttribute("producto", productoServicio.obtenerPorId(id));
        return "productos/detalle";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Producto producto = new Producto();
        producto.setCategoria(new Categoria());
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaServicio.listar());
        return "productos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("producto", productoServicio.obtenerPorId(id));
        model.addAttribute("categorias", categoriaServicio.listar());
        return "productos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("producto") Producto producto, BindingResult result, Model model,
            RedirectAttributes redirect) {
        if (producto.getCategoria() == null || producto.getCategoria().getId() == null) {
            result.addError(new FieldError("producto", "categoria", "La categoría es obligatoria"));
        }

        if (producto.getCodigo() != null
                && !productoServicio.codigoDisponible(producto.getCodigo(), producto.getId())) {
            result.rejectValue("codigo", "Codigo duplicado", "Ya existe un producto con ese código");
        }

        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaServicio.listar());
            return "productos/formulario";
        }

        boolean esNuevo = producto.getId() == null;
        productoServicio.guardar(producto);
        redirect.addFlashAttribute("mensaje",
                esNuevo ? "Producto creado correctamente" : "Producto actualizado correctamente");
        return "redirect:/productos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        productoServicio.eliminar(id);
        redirect.addFlashAttribute("mensaje", "Producto eliminado correctamente");
        return "redirect:/productos";
    }

}
