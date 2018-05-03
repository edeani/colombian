/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EderArmando
 */
public class GastosNivel2Dto {
    private String cod;
    private String nombre;
    private String nivel;
    private String padre;
    private Double valor_gastos;
    
    private List<GastosNivel3Dto> nivel3;

    public GastosNivel2Dto(){
        nivel3 = new ArrayList<>();
        valor_gastos = 0D;
    }
            
    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public Double getValor_gastos() {
        return valor_gastos;
    }

    public void setValor_gastos(Double valor_gastos) {
        this.valor_gastos = valor_gastos;
    }
    
    public List<GastosNivel3Dto> getNivel3() {
        return nivel3;
    }

    public void setNivel3(List<GastosNivel3Dto> nivel3) {
        this.nivel3 = nivel3;
    }
    
    
}
