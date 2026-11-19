package com.shop.tienda_ropa.infrastructure.adapter.in.web;

import com.shop.tienda_ropa.domain.exception.ReglaNegocioException;
import com.shop.tienda_ropa.domain.port.in.CategoriaUseCase;
import com.shop.tienda_ropa.domain.port.in.ProductoUseCase;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoUseCase productoUseCase;
    private final CategoriaUseCase categoriaUseCase;

    public ProductoController(ProductoUseCase productoUseCase,
                              CategoriaUseCase categoriaUseCase) {
        this.productoUseCase = productoUseCase;
        this.categoriaUseCase = categoriaUseCase;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoUseCase.listar());
        return "productos/listar";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        model.addAttribute("producto", productoUseCase.buscarPorId(id));
        return "productos/detalle";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new ProductoForm());
        model.addAttribute("categorias", categoriaUseCase.listar());
        return "productos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("producto", ProductoForm.desde(productoUseCase.buscarPorId(id)));
        model.addAttribute("categorias", categoriaUseCase.listar());
        return "productos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("producto") ProductoForm form,
                          BindingResult result,
                          Model model,
                          RedirectAttributes redirect) {

        if (!result.hasErrors()) {
            try {
                boolean esNuevo = form.getId() == null;
                productoUseCase.guardar(form.aDominio());
                redirect.addFlashAttribute("mensaje",
                        esNuevo ? "Producto creado correctamente" : "Producto actualizado correctamente");
                return "redirect:/productos";
            } catch (ReglaNegocioException e) {
                String campo = "categoria".equals(e.getCampo()) ? "categoriaId" : e.getCampo();
                if (campo != null) {
                    result.rejectValue(campo, "regla.negocio", e.getMessage());
                } else {
                    result.reject("regla.negocio", e.getMessage());
                }
            }
        }

        model.addAttribute("categorias", categoriaUseCase.listar());
        return "productos/formulario";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        productoUseCase.eliminar(id);
        redirect.addFlashAttribute("mensaje", "Producto eliminado correctamente");
        return "redirect:/productos";
    }
}