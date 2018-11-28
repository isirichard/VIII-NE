package com.example.jonathan.negocioselectronicos.utilidades;

public class UtilidadesRecibo {
    //CONSTANTES CAMPOS TABLA RECIBO

    public static String TABLA_RECIBO="recibo";

    public static String CAMPO_CODIGO_RECIBO="codigoRecibo";
    public static String CAMPO_MONTO="monto";
    public static String CAMPO_ESTADO_REGISTRO3="estadoRegistro";
    public static String CAMPO_CODIGO_CAJERO="codigoCajero";
    public static String CAMPO_CODIGO_CLIENTE="codigoCliente";

    public static final String CREAR_TABLA_RECIBO="CREATE TABLE "+TABLA_RECIBO+"("+CAMPO_CODIGO_RECIBO+" INTEGER, "+CAMPO_CODIGO_CAJERO+" INTEGER, "+CAMPO_CODIGO_CLIENTE+" INTEGER, "+CAMPO_MONTO+" TEXT,"+CAMPO_ESTADO_REGISTRO3+" TEXT)";


}
