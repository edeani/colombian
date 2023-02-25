/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao;

import com.colombian.cali.colombiancaliycali.dto.ComprobanteConsolidadoSedeDto;
import com.colombian.cali.colombiancaliycali.dto.DetallePagosProveedorDto;
import com.colombian.cali.colombiancaliycali.dto.DetallePagosTercerosDto;
import com.colombian.cali.colombiancaliycali.dto.PagosCabeceraDto;
import com.colombian.cali.colombiancaliycali.dto.ReportePagosDto;
import com.colombian.cali.colombiancaliycali.dto.ReporteTotalCuentasXNivelDto;
import com.colombian.cali.colombiancaliycali.entidades.DetallePagos;
import com.colombian.cali.colombiancaliycali.entidades.Pagos;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public interface PagosDao {
    public Long secuenciaPagos(String nameDataSource);
    public void guardarPagos(String nameDataSource, Pagos pagosTerceros);
    public void guardarDetallePagosTerceros(String nameDataSource,DetallePagos detallePagosTerceros);
    public void guardarDetallePagosProveedor(String nameDataSource,DetallePagos detallePagosProveedor);
    public Pagos buscarPagoXIdPago(String nameDataSource,Long idpagotercero);
    public List<DetallePagosTercerosDto> buscarDetallePagosTercerosDtos(String nameDataSource,Long idpagotercero);
    public List<DetallePagosProveedorDto> buscarDetallePagosDtos(String nameDataSource,Long idpagoproveedor);
    public List<ComprobanteConsolidadoSedeDto> buscarPagosXFecha(String nameDataSource,String fechaInicial,String fechaFinal);
    public ReporteTotalCuentasXNivelDto totalPagoCuentaXNivel(String nameDataSource,int tipoCuenta,String fechaInicial,String fechaFinal);
    public ReporteTotalCuentasXNivelDto totalPagoCuentaXNivelSede(String nameDataSource,Long idsede,int tipoCuenta,String fechaInicial,String fechaFinal);
    public List<PagosCabeceraDto> buscarPagosXFecha(String nameDataSource,String fecha);
    public List<ReportePagosDto> reportePagos(String nameDataSource,String fechaInicial, String fechaFinal,Long idsede);
}