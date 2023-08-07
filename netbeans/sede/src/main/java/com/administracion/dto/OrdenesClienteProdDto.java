/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administracion.dto;

/**
 *
 * @author Anlod
 */
public class OrdenesClienteProdDto {

    private String telefono;
    private String nombreCliente;
    private Integer codigoBarrio;
    private Integer unidades;
    private Float promedioValorProducto;
    private String nombreProducto;
    private String codigoProducto;

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Integer getCodigoBarrio() {
        return codigoBarrio;
    }

    public void setCodigoBarrio(Integer codigoBarrio) {
        this.codigoBarrio = codigoBarrio;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    public Float getPromedioValorProducto() {
        return promedioValorProducto;
    }

    public void setPromedioValorProducto(Float promedioValorProducto) {
        this.promedioValorProducto = promedioValorProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

}
