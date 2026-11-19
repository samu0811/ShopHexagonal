package com.shop.tienda_ropa.infrastructure.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.tienda_ropa.domain.exception.ReglaNegocioException;
import com.shop.tienda_ropa.domain.port.in.CategoriaUseCase;
import com.shop.tienda_ropa.domain.port.in.ProductoUseCase;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




@Controller 
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaUseCase categoriaUseCase;
    private final ProductoUseCase productoUseCase;

    public  CategoriaController(CategoriaUseCase categoriaUseCase, ProductoUseCase productoUseCase){
        this.categoriaUseCase=categoriaUseCase;
        this.productoUseCase=productoUseCase;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias",categoriaUseCase.listar());
        return "categorias/listar";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", categoriaUseCase.buscarPorId(id));
        model.addAttribute("productos", productoUseCase.listarPorCategoria(id));
        return "categorias/detalle";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("categoria", new CategoriaForm());
        return "categorias/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", CategoriaForm.desde(categoriaUseCase.buscarPorId(id)));
        return "categorias/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("categoria") CategoriaForm form, BindingResult result, RedirectAttributes redirect) {
        
       if(result.hasErrors()){
        return "categorias/formulario";
       }
       boolean esNueva = form.getId() == null;
       try{
        categoriaUseCase.guardar(form.aDominio());
       }catch(ReglaNegocioException e){
            if (e.getCampo()!=null) {
               result.rejectValue(e.getCampo(), "regle.negocio",e.getMessage()); 
            }else{
                result.reject("regla.negocio",e.getMessage());
            }
            return "categorias/formulario";
       }
       redirect.addFlashAttribute("mesaje",
            esNueva ? "categoria creada correctamente" : "Categoria actualizada correctamente");
       return "redirect:/categorias";

       
    }
    

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            categoriaUseCase.eliminar(id);
            redirect.addFlashAttribute("mensaje", "Categoría eliminada correctamente");
        } catch (ReglaNegocioException e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/categorias";
    }
    
    

}
