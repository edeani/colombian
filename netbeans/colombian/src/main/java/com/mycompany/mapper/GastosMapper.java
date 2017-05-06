/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mapper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joseefren
 */
public class GastosMapper {
    
    private String codigo;
    private String nombre;
    private String valor;
    private String padre;
    /**
     * @return the codigo
     */
    
    public GastosMapper(){
        
    }
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the padre
     */
    public String getPadre() {
        return padre;
    }

    /**
     * @param padre the padre to set
     */
    public void setPadre(String padre) {
        this.padre = padre;
    }

    /**
     * @return the gasto
     */
    /*public GastosMapper getGasto() {
        return gasto;
    }

    /**
     * @param gasto the gasto to set
     */
    /*public void setGasto(GastosMapper gasto) {
        this.gasto = gasto;
    }*/
    
    
    
}
