/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombian.cali.colombiancaliycali.services;

import com.colombian.cali.colombiancaliycali.dto.DetallePagosProveedorDto;
import com.colombian.cali.colombiancaliycali.dto.DetallePagosTercerosDto;
import com.colombian.cali.colombiancaliycali.dto.PagosCabeceraDto;
import com.colombian.cali.colombiancaliycali.dto.ReportePagosDto;
import com.colombian.cali.colombiancaliycali.entidades.DetallePagos;
import com.colombian.cali.colombiancaliycali.entidades.Pagos;
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
    public List<ReportePagosDto> reportePagos(String nameDataSource,String fechaInicial, String fechaFinal,Long idsede);
}
