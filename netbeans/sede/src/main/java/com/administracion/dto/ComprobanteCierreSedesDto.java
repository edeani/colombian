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
public class ComprobanteCierreSedesDto {
    private Long consecutivo;
    private String fecha;
    private String nombreSede;
    private Long idSede;
    private Double totalHaber;
    private Double totalDeber;
    private Integer idsedepoint;
    
    private List<ComprobanteConsolidadoSedeDto> comprobanteConsolidadoSedeDto;

    public Long getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public List<ComprobanteConsolidadoSedeDto> getComprobanteConsolidadoSedeDto() {
        return comprobanteConsolidadoSedeDto;
    }

    public void setComprobanteConsolidadoSedeDto(List<ComprobanteConsolidadoSedeDto> comprobanteConsolidadoSedeDto) {
        this.comprobanteConsolidadoSedeDto = comprobanteConsolidadoSedeDto;
    }

    public Double getTotalHaber() {
        return totalHaber;
    }

    public void setTotalHaber(Double totalHaber) {
        this.totalHaber = totalHaber;
    }

    public Double getTotalDeber() {
        return totalDeber;
    }

    public void setTotalDeber(Double totalDeber) {
        this.totalDeber = totalDeber;
    }

    public Integer getIdsedepoint() {
        return idsedepoint;
    }

    public void setIdsedepoint(Integer idsedepoint) {
        this.idsedepoint = idsedepoint;
    }
    
}
