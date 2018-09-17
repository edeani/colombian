/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dao;


import com.administracion.dto.ComprobanteConsolidadoSedeDto;
import com.administracion.dto.DetallePagosProveedorDto;
import com.administracion.dto.DetallePagosTercerosDto;
import com.administracion.dto.PagosCabeceraDto;
import com.administracion.dto.ReportePagosDto;
import com.administracion.dto.ReporteTotalCuentasXNivelDto;
import com.administracion.entidad.DetallePagos;
import com.administracion.entidad.Pagos;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Jose Efren
 */
public interface PagosDao extends GenericDao<Pagos>{
    public Long secuenciaPagos(DataSource nameDataSource);
    public void guardarPagos(DataSource nameDataSource, Pagos pagosTerceros);
    public void borrarPagos(DataSource nameDataSource,Long idpago);
    public void borrarDetallePagos(DataSource nameDataSource,Long idpago);
    public void guardarDetallePagosTerceros(DataSource nameDataSource,DetallePagos detallePagosTerceros);
    public void guardarDetallePagosProveedor(DataSource nameDataSource,DetallePagos detallePagosProveedor);
    public Pagos buscarPagoXIdPago(DataSource nameDataSource,Long idpagotercero);
    public List<DetallePagosTercerosDto> buscarDetallePagosTercerosDtos(DataSource nameDataSource,Long idpagotercero);
    public List<DetallePagosProveedorDto> buscarDetallePagosDtos(DataSource nameDataSource,Long idpagoproveedor);
    public List<ComprobanteConsolidadoSedeDto> buscarPagosXFecha(DataSource nameDataSource,String fechaInicial,String fechaFinal);
    public ReporteTotalCuentasXNivelDto totalPagoCuentaXNivel(DataSource nameDataSource,int tipoCuenta,String fechaInicial,String fechaFinal);
    public ReporteTotalCuentasXNivelDto totalPagoCuentaXNivelSede(DataSource nameDataSource,Long idsede,int tipoCuenta,String fechaInicial,String fechaFinal);
    public List<PagosCabeceraDto> buscarPagosXFecha(DataSource nameDataSource,String fecha);
    public PagosCabeceraDto buscarPagosXId(DataSource nameDataSource,Long idpago,Integer tipo);
    public List<ReportePagosDto> reportePagos(DataSource nameDataSource,String fechaInicial, String fechaFinal,Long idsede);
}