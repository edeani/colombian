/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mapper;

/**
 *
 * @author joseefren
 */
public class VentasMapper {
    
    private String tipo;
    private Long codigo_proucto;
    private String descripcion_producto;
    private String valor_producto;
    private String numero_unidades;
    private String total_producto;
    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the codigo_proucto
     */
    public Long getCodigo_proucto() {
        return codigo_proucto;
    }

    /**
     * @param codigo_proucto the codigo_proucto to set
     */
    public void setCodigo_proucto(Long codigo_proucto) {
        this.codigo_proucto = codigo_proucto;
    }

    /**
     * @return the descripcion_producto
     */
    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    /**
     * @param descripcion_producto the descripcion_producto to set
     */
    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    /**
     * @return the valor_producto
     */
    public String getValor_producto() {
        return valor_producto;
    }

    /**
     * @param valor_producto the valor_producto to set
     */
    public void setValor_producto(String valor_producto) {
        this.valor_producto = valor_producto;
    }

    /**
     * @return the numero_unidades
     */
    public String getNumero_unidades() {
        return numero_unidades;
    }

    /**
     * @param numero_unidades the numero_unidades to set
     */
    public void setNumero_unidades(String numero_unidades) {
        this.numero_unidades = numero_unidades;
    }

    /**
     * @return the total_producto
     */
    public String getTotal_producto() {
        return total_producto;
    }

    /**
     * @param total_producto the total_producto to set
     */
    public void setTotal_producto(String total_producto) {
        this.total_producto = total_producto;
    }
    
}
