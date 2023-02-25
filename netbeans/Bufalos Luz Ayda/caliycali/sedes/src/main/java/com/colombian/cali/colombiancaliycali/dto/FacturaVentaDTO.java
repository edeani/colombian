/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.dto;

/**
 *
 * @author Jose Efren
 */
public class FacturaVentaDTO {
    
    private Long codigo;
    private String producto;
    private Long unidades;
    private Double valorUnitario;
    private Long totalProducto;
    private Long idsede; 
    private String estado;
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
     * @return the valorUnitario
     */
    public Double getValorUnitario() {
        return valorUnitario;
    }

    /**
     * @param valorUnitario the valorUnitario to set
     */
    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    /**
     * @return the totalProducto
     */
    public Long getTotalProducto() {
        return totalProducto;
    }

    /**
     * @param totalProducto the totalProducto to set
     */
    public void setTotalProducto(Long totalProducto) {
        this.totalProducto = totalProducto;
    }

    /**
     * @return the idsede
     */
    public Long getIdsede() {
        return idsede;
    }

    /**
     * @param idsede the idsede to set
     */
    public void setIdsede(Long idsede) {
        this.idsede = idsede;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
