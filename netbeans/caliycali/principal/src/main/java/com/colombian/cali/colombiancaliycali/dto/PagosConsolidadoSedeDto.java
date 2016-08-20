/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombian.cali.colombiancaliycali.dto;

import java.util.List;

/**
 *
 * @author Jose Efren
 */
public class PagosConsolidadoSedeDto {
    
    private Long secuencia;
    private String idBeneficiario;
    private String nombreBeneficiario;
    private String fechaPago;
    private Double total;
    private int mes;
    private List<DetallePagosCosolidadoSedeDto> detallePagosCosolidadoSedeDtos;

    public Long getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Long secuencia) {
        this.secuencia = secuencia;
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

    
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<DetallePagosCosolidadoSedeDto> getDetallePagosCosolidadoSedeDtos() {
        return detallePagosCosolidadoSedeDtos;
    }

    public void setDetallePagosCosolidadoSedeDtos(List<DetallePagosCosolidadoSedeDto> detallePagosCosolidadoSedeDtos) {
        this.detallePagosCosolidadoSedeDtos = detallePagosCosolidadoSedeDtos;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
    
    
}
