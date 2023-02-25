/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mapper;

/**
 *
 * @author joseefren
 */
public class Inventario {
    
    private Long codigo_producto_inventario;
    private String producto;
    private float inicial;
    private float compras;
    private float traslados;
    private float ventas;
    private float averias;
    private float fisico;
    private float diferencia;
    private float inventarioFinal;

    public Long getCodigo_producto_inventario() {
        return codigo_producto_inventario;
    }

    public void setCodigo_producto_inventario(Long codigo_producto_inventario) {
        this.codigo_producto_inventario = codigo_producto_inventario;
    }
    
    

    /**
     * @return the inicial
     */
    public float getInicial() {
        return inicial;
    }

    /**
     * @param inicial the inicial to set
     */
    public void setInicial(float inicial) {
        this.inicial = inicial;
    }

    /**
     * @return the compras
     */
    public float getCompras() {
        return compras;
    }

    /**
     * @param compras the compras to set
     */
    public void setCompras(float compras) {
        this.compras = compras;
    }

    /**
     * @return the traslados
     */
    public float getTraslados() {
        return traslados;
    }

    /**
     * @param traslados the traslados to set
     */
    public void setTraslados(float traslados) {
        this.traslados = traslados;
    }

    /**
     * @return the ventas
     */
    public float getVentas() {
        return ventas;
    }

    /**
     * @param ventas the ventas to set
     */
    public void setVentas(float ventas) {
        this.ventas = ventas;
    }

    /**
     * @return the averias
     */
    public float getAverias() {
        return averias;
    }

    /**
     * @param averias the averias to set
     */
    public void setAverias(float averias) {
        this.averias = averias;
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
     * @return the diferencia
     */
    public float getDiferencia() {
        return diferencia;
    }

    /**
     * @param diferencia the diferencia to set
     */
    public void setDiferencia(float diferencia) {
        this.diferencia = diferencia;
    }

    /**
     * @return the inventarioFinal
     */
    public float getInventarioFinal() {
        return inventarioFinal;
    }

    /**
     * @param inventarioFinal the inventarioFinal to set
     */
    public void setInventarioFinal(float inventarioFinal) {
        this.inventarioFinal = inventarioFinal;
    }

    public float getFisico() {
        return fisico;
    }

    public void setFisico(float fisico) {
        this.fisico = fisico;
    }
    
    
}
