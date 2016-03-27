/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.facturador.dto;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author EderArmando
 */
public class FacturaNormalDto {
    private SimpleStringProperty codigoProducto;
    private SimpleStringProperty descripcion;
    private SimpleStringProperty unidades;
    private SimpleStringProperty valorUnitario;
    private SimpleStringProperty valorTotal;

    public FacturaNormalDto(String codigoProducto,String descripcion,String unidades,String valorUnitario,String valorTotal)
    {
        this.codigoProducto = new SimpleStringProperty(codigoProducto);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.unidades = new SimpleStringProperty(unidades);
        this.valorUnitario = new SimpleStringProperty(valorUnitario);
        this.valorTotal = new SimpleStringProperty(valorTotal);
    }
            
    /**
     * @return the codigoProducto
     */
    public String getCodigoProducto() {
        return codigoProducto.getValue();
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion.getValue();
    }

    /**
     * @return the unidades
     */
    public String getUnidades() {
        return unidades.getValue();
    }

    /**
     * @return the valorUnitario
     */
    public String getValorUnitario() {
        return valorUnitario.getValue();
    }

    /**
     * @return the valorTotal
     */
    public String getValorTotal() {
        return valorTotal.getValue();
    }

    /**
     * @param codigoProducto the codigoProducto to set
     */
    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = new SimpleStringProperty(codigoProducto);
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = new SimpleStringProperty(descripcion);
    }

    /**
     * @param unidades the unidades to set
     */
    public void setUnidades(String unidades) {
        this.unidades = new SimpleStringProperty(unidades);
    }

    /**
     * @param valorUnitario the valorUnitario to set
     */
    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = new SimpleStringProperty(valorUnitario);
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(String valorTotal) {
        this.valorTotal = new SimpleStringProperty(valorTotal);
    }

    
    
}
