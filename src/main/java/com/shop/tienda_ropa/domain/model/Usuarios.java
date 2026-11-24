package com.shop.tienda_ropa.domain.model;

public class Usuarios {

    private Long id;
    private String nombre;
    private String documento;
    private String email;
    private String celular;

    public Usuarios(){

    }
    public Usuarios(Long id, String nombre,String documento,String email, String celular){
        this.id=id;
        this.nombre=nombre;
        this.documento=documento;
        this.email=email;
        this.celular=celular;
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
