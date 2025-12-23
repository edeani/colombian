package com.mycompany.enums;

/**
 *
 * @author 10 Spring Creators
 */
public enum EnumTipoPagoTarjeta {
    VISA("V","Tarjeta"),
    MASTER("M","Tarjeta"),
    TRANSFERENCIA("T","Transferencia"),
    NEQUI("N","Nequi"),
    DAVIPLATA("P","Daviplata");
    
    private final String tipo;
    private final String name;

    private EnumTipoPagoTarjeta(String tipo, String name) {
        this.tipo = tipo;
        this.name = name;
    }

    public String getTipo() {
        return tipo;
    }

    public String getName() {
        return name;
    }
 
}
