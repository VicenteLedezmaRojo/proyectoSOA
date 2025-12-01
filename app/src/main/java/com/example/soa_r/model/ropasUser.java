package com.example.soa_r.model;

public class ropasUser {
    String Estado, Genero, Talla, Tipo;

    public ropasUser(){}

    public ropasUser(String estado, String genero, String talla, String tipo) {
        this.Estado = estado;
        this.Genero = genero;
        this.Talla = talla;
        this.Tipo = tipo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public String getTalla() {
        return Talla;
    }

    public void setTalla(String talla) {
        Talla = talla;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }
}
