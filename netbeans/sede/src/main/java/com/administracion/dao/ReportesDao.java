/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.dto.BalanceDto;
import com.administracion.dto.ComprasProveedorFechaDto;
import com.administracion.dto.ComprobanteConsolidadoSedeDto;
import com.administracion.dto.CuentasPagarProveedoresDto;
import com.administracion.dto.EstadoPerdidaGananciaProvisionalDto;
import com.administracion.dto.ReporteConsolidadoDto;
import com.administracion.dto.ReporteInventarioDTO;
import com.administracion.dto.SubSedesDto;
import com.administracion.entidad.DetallePorcentajeVentas;
import com.administracion.entidad.PorcentajeVentas;
import com.administracion.entidad.Sedes;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author EderArmando
 */
public interface ReportesDao {
    public List<ReporteConsolidadoDto> reporteConsolidado(List<SubSedesDto> subSedes,String fechaInicial,String fechaFinal); 
    List<ReporteInventarioDTO> findInventarioXFechaFinal(DataSource nameSede,String fecha);
    public Long gastosConsolidadoSede(Sedes sede,String fecha);
    public Long consignacionesConsolidadoSede(Sedes sede,String fecha);
    public Long comprasConsolidadoSede(Sedes sede,String fecha);
    public Long totalConsolidadoSede(Sedes sede,String fecha);
    public Long pagosConsolidado(DataSource nameDataSource,String fecha);
    public List<ComprobanteConsolidadoSedeDto> buscarGastosXFecha(DataSource nameDataSource,String fecha);
    public List<ComprobanteConsolidadoSedeDto> bucarMovimientoCajaMayor(DataSource nameDataSource,String fechaInicio,String fechaFin);
    public List<ComprobanteConsolidadoSedeDto> bucarMovimientoCajaMenor(DataSource nameDataSource, String fechaInicio, String fechaFin);
    public List<DetallePorcentajeVentas> buscarDetallePagoConsolidadoMes(DataSource nameDataSource,int mes);
    public PorcentajeVentas buscarPagoConsolidadoMes(DataSource nameDataSource,int mes);
    public List<EstadoPerdidaGananciaProvisionalDto> reporteEstadoPerdidaGananciaProvisional(DataSource nameDataSource,String fechInicial, String fechaFinal);
    public List<EstadoPerdidaGananciaProvisionalDto> reporteEstadoPerdidaGananciaProvisionalXSede(DataSource nameDataSource,String fechInicial, String fechaFinal,Long idSede);
    public List<ComprasProveedorFechaDto> reporteComprasProveedorFechaDto(DataSource nameDataSource,String fechInicial, String fechaFinal);
    public List<CuentasPagarProveedoresDto> reporteCuentasPagarProveedoresDto(DataSource nameDataSource,String fechInicial, String fechaFinal,Long idProveedor);
    public List<BalanceDto> reporteBalance(DataSource nameDataSource,String fechInicial, String fechaFinal,Long idsede);
    public Long pagosContarjetaTotal(DataSource nameDataSource,String fecha);
    public Long pagosDescuentoTotal(DataSource nameDataSource,String fecha);

    public List<ComprobanteConsolidadoSedeDto> bucarMovimientoCajaMayor(String nameDataSource, String sfechaInicial, String sfechaFinal);
}
