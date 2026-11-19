package com.shop.tienda_ropa.domain.exception;

public class ReglaNegocioException extends RuntimeException{
    
    private final String campo;

    public ReglaNegocioException(String mensaje) {
        this(null, mensaje);
    }

    public ReglaNegocioException(String campo, String mensaje) {
        super(mensaje);
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }

}
