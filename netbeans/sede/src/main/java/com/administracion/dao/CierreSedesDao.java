/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;


import com.administracion.dto.CierreSedesDto;
import com.administracion.dto.ComprobanteConsolidadoSedeDto;
import com.administracion.dto.ReporteComprobanteCierreDto;
import com.administracion.dto.ReporteTotalCuentasXNivelDto;
import com.administracion.entidad.CierreSedes;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author user
 */
public interface CierreSedesDao extends GenericDao<CierreSedes>{
    public void guardarDetalleComprobanteCierre(DataSource nameDataSource,ComprobanteConsolidadoSedeDto comprobanteConsolidadoSedeDto);
    public void guardarComprobanteCierre(DataSource nameDataSource,CierreSedes cierreSedes);
    public CierreSedesDto buscarComprobanteCierreDto(DataSource nameDataSource,Long idComprobanteCierre);
    public List<CierreSedesDto> buscarComprobanteCierreDtoXFecha(DataSource nameDataSource,String fechaInicial,String fechaFinal);
    public List<ReporteComprobanteCierreDto> buscarDetalleComprobanteCierreSedesView(DataSource nameDataSource,Long idComprobanteCierre);
    public List<ComprobanteConsolidadoSedeDto> buscarConsignacionesXFecha(DataSource nameDataSource,String fechaInicial,String fechaFinal);
    public ReporteTotalCuentasXNivelDto totalCierreCuentaXNivel(DataSource nameDataSource,int tipoCuenta,String fechaInicial,String fechaFinal);
    public ReporteTotalCuentasXNivelDto totalCierreCuentaXNivelSede(DataSource nameDataSource,Long idsede,int tipoCuenta,String fechaInicial,String fechaFinal);
    public void borrarComprobanteCierre(DataSource nameDataSource,Long idComprobanteCierre);
    public void borrarComprobanteCierreXDia(DataSource nameDataSource,String fecha,Integer idSedePoint);
    public void borrarDetalleComprobanteCierre(DataSource nameDataSource,Long idComprobanteCierre);
    public void borrarDetalleComprobanteCierreXDia(DataSource nameDataSource,String fecha,Integer idSedePoint);
    public CierreSedes buscarCabeceraComprobanteCierreXFechaXSede(DataSource nameDataSource, String fechaInicial, String fechaFinal,Long idSede);
    public List<CierreSedesDto> reporteComprobanteCierreSedesView(DataSource nameDataSource, String fechaInicial, String fechaFinal,Long idsede);
}
