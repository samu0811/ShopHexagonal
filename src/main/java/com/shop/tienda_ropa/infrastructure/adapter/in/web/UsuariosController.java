package com.shop.tienda_ropa.infrastructure.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.tienda_ropa.domain.exception.ReglaNegocioException;
import com.shop.tienda_ropa.domain.port.in.UsuariosUseCase;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




@Controller 
@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuariosUseCase usuariosUseCase;

    public UsuariosController(UsuariosUseCase usuariosUseCase){
        this.usuariosUseCase=usuariosUseCase;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuariosUseCase.listar());
        return "usuarios/listar";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        model.addAttribute("usuarios",usuariosUseCase.buscarPorId(id));
        return "usuarios/detalle";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("usuarios", new UsuariosForm());
        return "usuarios/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("usuarios", UsuariosForm.desde(usuariosUseCase.buscarPorId(id)));
        return "usuarios/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute ("usuarios") UsuariosForm form, BindingResult result, RedirectAttributes redirect) {
        if(result.hasErrors()){
            return "usuarios/formulario";
        }
        boolean esNuevo = form.getId() == null;
        try{
            usuariosUseCase.guardar(form.aDominio());
        }catch(ReglaNegocioException e){
            if(e.getCampo()!=null){
                result.rejectValue(e.getCampo(), "regle.negocio",e.getMessage());
            }else{
                result.reject("regla.negocio",e.getMessage());
            }
            return "usuarios/formulario";
        }
        redirect.addFlashAttribute("mensaje",esNuevo? "Usuario creado correctamente":"Usuario actualizado correctamente");
        return "redirect:/usuarios";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect){
        try{
            usuariosUseCase.eliminar(id);
            redirect.addFlashAttribute("mensaje","Usuario eliminado correctamente");
        }catch(ReglaNegocioException e){
            redirect.addFlashAttribute("error",e.getMessage());
        }
        return "redirect:/usuarios";
    }
    
    
    
    
    
    
   

}
