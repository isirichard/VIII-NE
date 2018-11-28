package com.example.jonathan.negocioselectronicos.entidades;

import java.io.Serializable;

public class Cajero implements Serializable {
    private Integer codigo;
    private String nombre;
    private String estadoRegistro;

    public Cajero(Integer codigo, String nombre, String estadoRegistro) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.estadoRegistro = estadoRegistro;
    }
    public Cajero(){

    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
