/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombian.cali.colombiancaliycali.dto;

/**
 *
 * @author Jose Efren
 */
public class DetallePagosTercerosDto {
    private Long consecutivo;
    private String idCuenta;
    private String conceptoCuenta;
    private Double total;
    private String detalle;
    private int numero;
    private String fecha;
    private Long idpagotercero;
    private String nombreSede;
    private Long idSede;

    public Long getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Long getIdpagotercero() {
        return idpagotercero;
    }

    public void setIdpagotercero(Long idpagotercero) {
        this.idpagotercero = idpagotercero;
    }
    
    public String getConceptoCuenta() {
        return conceptoCuenta;
    }

    public void setConceptoCuenta(String conceptoCuenta) {
        this.conceptoCuenta = conceptoCuenta;
    }    

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public Long getIdSede() {
        return idSede;
    }

    public void setIdSede(Long idSede) {
        this.idSede = idSede;
    }
    
}
