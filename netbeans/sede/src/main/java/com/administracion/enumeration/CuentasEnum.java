/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.administracion.enumeration;

/**
 *
 * @author Anlod
 */
public enum CuentasEnum {
    
    CUENTA_VENTAS("414015"),
    CUENTA_CONSIGNACIONES("11050501"),
    CUENTA_PAGOS_CON_TARJETA("11201010"),
    CUENTA_PROPINA("281505"),
    CUENTA_PAGOS_NEQUI("11201011"),
    CUENTA_PAGOS_DAVIPLATA("11201012"),
    CUENTA_PAGOS_TRANSFERENCIAS("11201013"),
    CUENTA_DESCUENTOS("421040");
    
    private final String cuenta;
    
    private CuentasEnum(String cuenta){
        this.cuenta = cuenta;
    }

    public String getCuenta() {
        return cuenta;
    }
    
    
    
}
