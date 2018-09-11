/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dto;

/**
 *
 * @author Jose Efren
 */
public class VentasTotalesDTO {
    
    private Long  codigo;
    private String producto;
    private Long unidades;
    private Double promedio;
    private Double valor;

    /**
     * @return the codigo
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the unidades
     */
    public Long getUnidades() {
        return unidades;
    }

    /**
     * @param unidades the unidades to set
     */
    public void setUnidades(Long unidades) {
        this.unidades = unidades;
    }

    /**
     * @return the promedio
     */
    public Double getPromedio() {
        return promedio;
    }

    /**
     * @param promedio the promedio to set
     */
    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    /**
     * @return the producto
     */
    public String getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(String producto) {
        this.producto = producto;
    }

    /**
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

}
