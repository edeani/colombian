/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.service;


import com.administracion.dto.DetallePagosProveedorDto;
import com.administracion.dto.DetallePagosTercerosDto;
import com.administracion.dto.PagosCabeceraDto;
import com.administracion.dto.ReportePagosDto;
import com.administracion.entidad.DetallePagos;
import com.administracion.entidad.Pagos;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public interface PagosService {
    /**
     * Obtiene la secuencia de la tabla pagos_terceros
     * @param nameDataSource
     * @return 
     */
    public Long secuenciaPagos(String nameDataSource);
    public void guardarPagosTerceros(String nameDataSource,Pagos pagosTerceros, List<DetallePagos> detallePagosTerceros);
    public void guardarPagosProveedor(String nameDataSource,Pagos pagosProveedor, List<DetallePagos> detallePagosProveedor);
    public List<DetallePagosTercerosDto> buscarDetallePagosTercerosDtos(String nameDataSource,Long idpagotercero);
    public List<DetallePagosProveedorDto> buscarDetallePagosDtos(String nameDataSource,Long idpagoproveedor);
    public Pagos buscarPagoXIdPago(String nameDataSource,Long idpagotercero);
    public List<PagosCabeceraDto> buscarPagosProveedorXFecha(String nameDataSource,String fecha);
    public PagosCabeceraDto buscarPagosProveedorXId(String nameDataSource, Long idpago);
    public List<ReportePagosDto> reportePagos(String nameDataSource,String fechaInicial, String fechaFinal,Long idsede);
}
