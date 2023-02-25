/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
/**
 *
 * @author edeani
 */
@Entity
@Table(name = "factura")
public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="numero_factura")
    private Long numeroFactura;
    
    @Column(name="fecha_factura")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFactura;
    
    @Column(name="estado_factura")
    private String estadoFactura;
    
    @Column(name="valor_total")
    private Double valorTotal;
    /**
     * @return the numeroFactura
     */
    public Long getNumeroFactura() {
        return numeroFactura;
    }

    /**
     * @param numeroFactura the numeroFactura to set
     */
    public void setNumeroFactura(Long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    /**
     * @return the fechaFactura
     */
    public Date getFechaFactura() {
        return fechaFactura;
    }

    /**
     * @param fechaFactura the fechaFactura to set
     */
    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    /**
     * @return the estadoFactura
     */
    public String getEstadoFactura() {
        return estadoFactura;
    }

    /**
     * @param estadoFactura the estadoFactura to set
     */
    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    /**
     * @return the valorTotal
     */
    public Double getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
  
}
