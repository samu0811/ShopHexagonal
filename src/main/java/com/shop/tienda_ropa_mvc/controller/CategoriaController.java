package com.shop.tienda_ropa_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.tienda_ropa_mvc.model.Categoria;
import com.shop.tienda_ropa_mvc.service.CategoriaServicio;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;



@Controller 
@RequestMapping ("/categorias")
public class CategoriaController {
    private final CategoriaServicio categoriaServicio;

    public CategoriaController(CategoriaServicio categoriaServicio){
        this.categoriaServicio = categoriaServicio;
    }

    //listar registros
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaServicio.listar());
        return "categorias/lista";
    }

    //consulta un registro especifico
    @GetMapping("{id}")
    public String detalle(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", categoriaServicio.obtenerPorId(id));
        return "categorias/detalle";
    }

    //formulario para crear un registro
    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("categoria", new Categoria());
        return "categorias/Formulario";
    }

    //formulario para editar un registro
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model){
        model.addAttribute("categoria", categoriaServicio.obtenerPorId(id));
        return "categorias/Formulario";
    }

    //crear o actualizar un registro
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("categoria") Categoria categoria, BindingResult result, RedirectAttributes redirect){
        if(categoria.getNombre()!=null && !categoriaServicio.nombreDisponible(categoria.getNombre(),categoria.getId())){
            result.rejectValue("nombre","nombre.duplicado","Ya existe una categoria con ese nombre");
        }

        if(result.hasErrors()){
            return "categorias/Formulario";
        }

        boolean esNueva = categoria.getId() ==null;
        categoriaServicio.guardar(categoria);
        redirect.addFlashAttribute("mensaje", esNueva ?"categoria creada correctamente":"Categoria actualizada correctamente");
        return "redirect:/categorias";


    }
    
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect){
        try{
            categoriaServicio.eliminar(id);
            redirect.addFlashAttribute("mensaje", "Categoria eliminada correctamente");
        }catch(IllegalStateException e){
            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/categorias";

    }
    

    


    
}
