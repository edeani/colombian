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
public class PagosProveedorDto {
    
    private Long secuencia;
    private Long idSede;
    private String sede;
    private String idProveedor;
    private String nombreProveedor;
    private String fechaPago;
    private Double totalPago;
    private List<DetallePagosProveedorDto> detallePagosProveedor;

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

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String FechaPago) {
        this.fechaPago = FechaPago;
    }

    public Double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(Double totalPago) {
        this.totalPago = totalPago;
    }

    public List<DetallePagosProveedorDto> getDetallePagosProveedor() {
        return detallePagosProveedor;
    }

    public void setDetallePagosProveedor(List<DetallePagosProveedorDto> detallePagosProveedorDtos) {
        this.detallePagosProveedor = detallePagosProveedorDtos;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }
}
