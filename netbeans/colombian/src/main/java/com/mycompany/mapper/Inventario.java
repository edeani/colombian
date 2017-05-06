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
    
    private int codigo;
    private String nombreProducto;
    private float inicial;
    private float compras;
    private float traslados;
    private float ventas;
    private float consumo;
    private float real;
    private float diferencia;
    private float inventarioFinal;
    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
     * @return the consumo
     */
    public float getConsumo() {
        return consumo;
    }

    /**
     * @param consumo the consumo to set
     */
    public void setConsumo(float consumo) {
        this.consumo = consumo;
    }

    /**
     * @return the real
     */
    public float getReal() {
        return real;
    }

    /**
     * @param real the real to set
     */
    public void setReal(float real) {
        this.real = real;
    }

    /**
     * @return the nombreProducto
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * @param nombreProducto the nombreProducto to set
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
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
}
