package com.example.jonathan.negocioselectronicos.utilidades;

public class Utilidades {
    //CONSTANTES CAMPOS TABLA CAJERO
    public static String TABLA_CAJERO="cajero";
    public static String CAMPO_CODIGO="codigo";
    public static String CAMPO_NOMBRE="nombre";
    public static String CAMPO_ESTADO_REGISTRO="estadoRegistro";

    //CREAR TABLA CAJERO
    public static final String CREAR_TABLA_CAJEROS="CREATE TABLE "+TABLA_CAJERO+"("+CAMPO_CODIGO+" INTEGER, "+CAMPO_NOMBRE+" TEXT,"+CAMPO_ESTADO_REGISTRO+" TEXT)";

}
