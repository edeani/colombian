/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao;

import com.colombian.cali.colombiancaliycali.dto.CierreSedesDto;
import com.colombian.cali.colombiancaliycali.dto.ComprobanteConsolidadoSedeDto;
import com.colombian.cali.colombiancaliycali.dto.ReporteComprobanteCierreDto;
import com.colombian.cali.colombiancaliycali.dto.ReporteTotalCuentasXNivelDto;
import com.colombian.cali.colombiancaliycali.entidades.CierreSedes;
import java.util.List;

/**
 *
 * @author user
 */
public interface CierreSedesDao {
    public void guardarDetalleComprobanteCierre(String nameDataSource,ComprobanteConsolidadoSedeDto comprobanteConsolidadoSedeDto);
    public void guardarComprobanteCierre(String nameDataSource,CierreSedes cierreSedes);
    public CierreSedesDto buscarComprobanteCierreDto(String nameDataSource,Long idComprobanteCierre);
    public List<CierreSedesDto> buscarComprobanteCierreDtoXFecha(String nameDataSource,String fechaInicial,String fechaFinal);
    public List<ReporteComprobanteCierreDto> buscarDetalleComprobanteCierreSedesView(String nameDataSource,Long idComprobanteCierre);
    public List<ComprobanteConsolidadoSedeDto> buscarConsignacionesXFecha(String nameDataSource,String fechaInicial,String fechaFinal);
    public ReporteTotalCuentasXNivelDto totalCierreCuentaXNivel(String nameDataSource,int tipoCuenta,String fechaInicial,String fechaFinal);
    public ReporteTotalCuentasXNivelDto totalCierreCuentaXNivelSede(String nameDataSource,Long idsede,int tipoCuenta,String fechaInicial,String fechaFinal);
    public void borrarComprobanteCierre(String nameDataSource,Long idComprobanteCierre);
    public void borrarDetalleComprobanteCierre(String nameDataSource,Long idComprobanteCierre);
    public CierreSedes buscarCabeceraComprobanteCierreXFechaXSede(String nameDataSource, String fechaInicial, String fechaFinal,Long idSede);
    public List<CierreSedesDto> reporteComprobanteCierreSedesView(String nameDataSource, String fechaInicial, String fechaFinal,Long idsede);
}
