package com.example.jonathan.negocioselectronicos.entidades;

import java.io.Serializable;

public class Recibo implements Serializable {
    private Integer codigoCajero;
    private Integer codigoCliente;
    private Integer codigoRecibo;
    private String monto;
    private String estadoRegistro;

    public Recibo(Integer codigoCajero, Integer codigoCliente, Integer codigoRecibo, String monto, String estadoRegistro) {
        this.codigoCajero = codigoCajero;
        this.codigoCliente = codigoCliente;
        this.codigoRecibo = codigoRecibo;
        this.monto = monto;
        this.estadoRegistro = estadoRegistro;
    }
    public Recibo(){

    }

    public Integer getCodigoCajero() {
        return codigoCajero;
    }

    public void setCodigoCajero(Integer codigoCajero) {
        this.codigoCajero = codigoCajero;
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Integer getCodigoRecibo() {
        return codigoRecibo;
    }

    public void setCodigoRecibo(Integer codigoRecibo) {
        this.codigoRecibo = codigoRecibo;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
