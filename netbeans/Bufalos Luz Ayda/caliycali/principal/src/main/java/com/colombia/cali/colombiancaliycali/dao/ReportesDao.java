/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao;

import com.colombian.cali.colombiancaliycali.dto.BalanceDto;
import com.colombian.cali.colombiancaliycali.dto.ComprasProveedorFechaDto;
import com.colombian.cali.colombiancaliycali.dto.ComprobanteConsolidadoSedeDto;
import com.colombian.cali.colombiancaliycali.dto.CuentasPagarProveedoresDto;
import com.colombian.cali.colombiancaliycali.dto.EstadoPerdidaGananciaProvisionalDto;
import com.colombian.cali.colombiancaliycali.dto.ReporteConsolidadoDto;
import com.colombian.cali.colombiancaliycali.entidades.DetallePorcentajeVentas;
import com.colombian.cali.colombiancaliycali.entidades.PorcentajeVentas;
import com.colombian.cali.colombiancaliycali.entidades.Sedes;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public interface ReportesDao {
    
    public List<ReporteConsolidadoDto> reporteConsolidado(List<Sedes> sedes,String fechaInicial,String fechaFinal); 
    public Long gastosConsolidadoSede(Sedes sede,String fecha);
    public Long consignacionesConsolidadoSede(Sedes sede,String fecha);
    public Long comprasConsolidadoSede(Sedes sede,String fecha);
    public Long ordenesConsolidadoSede(Sedes sede,String fecha);
    public Long mesasConsolidadoSede(Sedes sede,String fecha);
    public Long llevarConsolidadoSede(Sedes sede,String fecha);
    public Long pagosConsolidado(String nameDataSource,String fecha);
    public List<ComprobanteConsolidadoSedeDto> buscarGastosXFecha(String nameDataSource,String fecha);
    public List<ComprobanteConsolidadoSedeDto> bucarMovimientoCajaMayor(String nameDataSource,String fechaInicio,String fechaFin);
    public List<ComprobanteConsolidadoSedeDto> bucarMovimientoCajaMenor(String nameDataSource, String fechaInicio, String fechaFin);
    public List<DetallePorcentajeVentas> buscarDetallePagoConsolidadoMes(String nameDataSource,int mes);
    public PorcentajeVentas buscarPagoConsolidadoMes(String nameDataSource,int mes);
    public List<EstadoPerdidaGananciaProvisionalDto> reporteEstadoPerdidaGananciaProvisional(String nameDataSource,String fechInicial, String fechaFinal);
    public List<EstadoPerdidaGananciaProvisionalDto> reporteEstadoPerdidaGananciaProvisionalXSede(String nameDataSource,String fechInicial, String fechaFinal,Long idSede);
    public List<ComprasProveedorFechaDto> reporteComprasProveedorFechaDto(String nameDataSource,String fechInicial, String fechaFinal);
    public List<CuentasPagarProveedoresDto> reporteCuentasPagarProveedoresDto(String nameDataSource,String fechInicial, String fechaFinal,Long idProveedor);
    public List<BalanceDto> reporteBalance(String nameDataSource,String fechInicial, String fechaFinal,Long idsede);
}
