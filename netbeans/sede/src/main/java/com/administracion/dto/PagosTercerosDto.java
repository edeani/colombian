/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dto;

import java.util.List;

/**
 *
 * @author Jose Efren
 */
public class PagosTercerosDto {
    
    private Long secuencia;
    private Long idSede;
    private String sede;
    private String idBeneficiario;
    private String nombreBeneficiario;
    private String fechaPago;
    private Double totalPago;
    private List<DetallePagosTercerosDto> detallePagosTerceros;
    private Integer tipo;

    public Long getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Long secuencia) {
        this.secuencia = secuencia;
    }

    public Long getIdSede() {
        return idSede;
    }

    public void setIdSede(Long idSede) {
        this.idSede = idSede;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(String idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    public void setNombreBeneficiario(String nombreBeneficiario) {
        this.nombreBeneficiario = nombreBeneficiario;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String FechaPago) {
        this.fechaPago = FechaPago;
    }

    public List<DetallePagosTercerosDto> getDetallePagosTerceros() {
        return detallePagosTerceros;
    }

    public void setDetallePagosTerceros(List<DetallePagosTercerosDto> detallePagosTerceros) {
        this.detallePagosTerceros = detallePagosTerceros;
    }

    public Double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(Double totalPago) {
        this.totalPago = totalPago;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    
    
}
