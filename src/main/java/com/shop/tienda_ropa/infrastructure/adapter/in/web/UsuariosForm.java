package com.shop.tienda_ropa.infrastructure.adapter.in.web;

import com.shop.tienda_ropa.domain.model.Usuarios;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuariosForm {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max=80,message="el nombre no puede exceder 80 caracteres")
    private String nombre;

    @Size(max = 10, message = "El documento es obligatorio")
    @Column(nullable = false,unique = true)
    private String documento;

    @NotBlank( message = "El correo electronico es obligatorio")
    @Size(max = 250, message = "El correo electronico no puede superar los 250 caracteres")
    private String email;

    @NotBlank(message = "el celular es obligatorio")
    @Size(max = 10, message = "el celular no puede superar 11 digitos")
    private String celular;

    public static UsuariosForm desde(Usuarios usuarios){
        UsuariosForm form = new UsuariosForm();
        form.setId(usuarios.getId());
        form.setNombre(usuarios.getNombre());
        form.setDocumento(usuarios.getDocumento());
        form.setEmail(usuarios.getEmail());
        form.setCelular(usuarios.getCelular());
        return form;
    }

    public Usuarios aDominio(){
        return new Usuarios(id,nombre,documento,email,celular);
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public String getDocumento(){
        return documento;
    }
    public void setDocumento(String documento){
        this.documento=documento;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }

    public String getCelular(){
        return celular;
    }
    public void setCelular(String celular){
        this.celular=celular;
    }

}
