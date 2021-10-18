/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dto;

/**
 *
 * @author joseefren
 */
public class ReporteComprasSedeDto {
    
    
    private Long codigo_producto_inventario;
    private String descripcion_producto;
    private Double detalle_factura_valor_producto;
    private Long numero_unidades;
    private Double  promedio;
    /**
     * @return the codigo_producto_inventario
     */
    public Long getCodigo_producto_inventario() {
        return codigo_producto_inventario;
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
     * @return the detalle_factura_valor_producto
     */
    public Double getDetalle_factura_valor_producto() {
        return detalle_factura_valor_producto;
    }

    /**
     * @param detalle_factura_valor_producto the detalle_factura_valor_producto to set
     */
    public void setDetalle_factura_valor_producto(Double detalle_factura_valor_producto) {
        this.detalle_factura_valor_producto = detalle_factura_valor_producto;
    }

    /**
     * @return the numero_unidades
     */
    public Long getNumero_unidades() {
        return numero_unidades;
    }

    /**
     * @param numero_unidades the numero_unidades to set
     */
    public void setNumero_unidades(Long numero_unidades) {
        this.numero_unidades = numero_unidades;
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
     * @param codigo_producto_inventario the codigo_producto_inventario to set
     */
    public void setCodigo_producto_inventario(Long codigo_producto_inventario) {
        this.codigo_producto_inventario = codigo_producto_inventario;
    }

    
}
